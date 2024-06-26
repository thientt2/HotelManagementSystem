package UI.Room;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import BLL.CHITIETHOADON_BLL;
import BLL.CHITIETPNP_BLL;
import BLL.KHACHHANG_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.PHIEUDATPHONG_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import DTO.KHACHHANG;
import DTO.LOAIPHONG;
import DTO.PHIEUNHANPHONG;
import UI.Resource.itemDetailsRoomCustomer_Controller;
import UI.Resource.itemDetailsRoomService_Controller;
import UI.Resource.itemRoom_Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class detailsRoom_Controller implements Initializable{
    @FXML
    private Button cancel_btn;

    @FXML
    private TextField checkIn_txt;

    @FXML
    private TextField checkOut_txt;

    @FXML
    private TextField customerName_txt;

    @FXML
    private VBox detailCustomer_vbox;

    @FXML
    private VBox detailService_vbox;

    @FXML
    private TextField roomNumber_txt;

    @FXML
    private TextField roomType_txt;

    @FXML
    private TextField timeLeft_txt;
    
    public void setData(String roomNumber) throws SQLException, IOException {    	
    	PHIEUNHANPHONG receiveRoom = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(roomNumber);
    	KHACHHANG customer = KHACHHANG_BLL.getCustomerByReceiveRoomId(receiveRoom.getMAPNP());
    	LOAIPHONG roomType = LOAIPHONG_BLL.getRoomTypeByRoomNumber(roomNumber);
		
    	customerName_txt.setText(customer.getTENKH());
    	roomNumber_txt.setText(roomNumber);
    	roomType_txt.setText(roomType.getTENLOAI());
    	
    	
		String checkout = receiveRoom.getTGTRA();
        String formattedDate2 = formatDate(checkout);
        checkOut_txt.setText(formattedDate2);
        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime checkindate = LocalDateTime.parse(receiveRoom.getTGNHAN(), inputFormatter);
		String checkin = checkindate.format(outputFormatter);	
		checkIn_txt.setText(checkin);
		
		calculateTimeLeft(checkout);
		
		List<Object[]> list = CHITIETPNP_BLL.getCustomerId(receiveRoom.getMAPNP());
		list.forEach(System.out::println);
		for (Object[] customerOrther : list) {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/UI/Resource/itemDetailsRoomCustomer.fxml"));
	        Parent customerData = loader1.load();
	        itemDetailsRoomCustomer_Controller controller = loader1.getController();
	        controller.setData(customerOrther);
	        detailCustomer_vbox.getChildren().add(customerData);
		}

		List<Object[]> listService = CHITIETHOADON_BLL.getServiceDetailsByMapnp(receiveRoom.getMAPNP());
		listService.forEach(System.out::println);
		System.out.println(listService.toString());
		for (Object[] service : listService) {
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/UI/Resource/itemDetailsRoomService.fxml"));
	        Parent serviceData = loader2.load();
	        itemDetailsRoomService_Controller controller = loader2.getController();
	        controller.setData(service);
	        detailService_vbox.getChildren().add(serviceData);
		}
	}
    
    private String formatDate(String dateStr) {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = fromUser.parse(dateStr);
            return myFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;  
        }
    }
    


    private ScheduledExecutorService scheduler;
   
    private void calculateTimeLeft(String checkoutStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkoutDateTime;

        try {
            checkoutDateTime = LocalDateTime.parse(checkoutStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            checkoutDateTime = LocalDate.parse(checkoutStr, dateFormatter).atStartOfDay();
        }

        final LocalDateTime checkoutDateTimeFinal = checkoutDateTime; 

        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }

        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable updateTimeLeftTask = new Runnable() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                if (checkoutDateTimeFinal.isAfter(now)) { 
                    Duration duration = Duration.between(now, checkoutDateTimeFinal);
                    long secondsLeft = duration.getSeconds();

                    long daysLeft = secondsLeft / (24 * 3600);
                    secondsLeft = secondsLeft % (24 * 3600);

                    long hoursLeft = secondsLeft / 3600;
                    secondsLeft = secondsLeft % 3600;

                    long minutesLeft = secondsLeft / 60;
                    secondsLeft = secondsLeft % 60;
                    String timeLeft = String.format("%d ngày, %02d:%02d:%02d", daysLeft, hoursLeft, minutesLeft, secondsLeft);
                    Platform.runLater(() -> timeLeft_txt.setText(timeLeft));
                } else {
                    Platform.runLater(() -> timeLeft_txt.setText("Tới hạn check-out"));
                    scheduler.shutdown();
                }
            }
        };

        scheduler.scheduleAtFixedRate(updateTimeLeftTask, 0, 1, TimeUnit.SECONDS);
    }



    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub		
	}
}
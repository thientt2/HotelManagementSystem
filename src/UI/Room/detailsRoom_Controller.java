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
import BLL.PHIEUDATPHONG_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import DTO.KHACHHANG;
import UI.Resource.itemDetailsRoomCustomer_Controller;
import UI.Resource.itemDetailsRoomService_Controller;
import UI.Resource.itemRoom_Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    	Object[] room = PHIEUDATPHONG_BLL.getRoomDetails(roomNumber);
    	customerName_txt.setText(room[2].toString());
    	roomNumber_txt.setText(room[0].toString());
    	roomType_txt.setText(room[1].toString());
    	
//		String checkin = item[3].toString();
//        String formattedDate1 = formatDate(checkin);
//        checkIn_txt.setText(formattedDate1);
    	
		String checkout = room[4].toString();
        String formattedDate2 = formatDate(checkout);
        checkOut_txt.setText(formattedDate2);
        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime checkindate = LocalDateTime.parse(room[3].toString(), inputFormatter);
		String checkin = checkindate.format(outputFormatter);	
		checkIn_txt.setText(checkin);
		
		calculateTimeLeft(checkout);
		
        //String mapnp = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(room[0].toString());
		List<Object[]> list = CHITIETPNP_BLL.getCustomerId(room[5].toString());
		for (Object[] customer : list) {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/UI/Resource/itemDetailsRoomCustomer.fxml"));
	        Parent customerData = loader1.load();
	        itemDetailsRoomCustomer_Controller controller = loader1.getController();
	        controller.setData(customer);
	        detailCustomer_vbox.getChildren().add(customerData);
		}

		List<Object[]> listService = CHITIETHOADON_BLL.getServiceDetailsByMapnp(room[5].toString());
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
    

//    private void calculateTimeLeft(String checkoutStr) {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate today = LocalDate.now();
//            LocalDate checkoutDate = LocalDate.parse(checkoutStr, formatter);
//
//            if (checkoutDate.isAfter(today)) {
//                Duration duration = Duration.between(today.atStartOfDay(), checkoutDate.atStartOfDay());
//                long daysLeft = duration.toDays();
//                long hoursLeft = duration.toHoursPart();
//                long minutesLeft = duration.toMinutesPart();
//                long secondsLeft = duration.toSecondsPart();
//
//                String timeLeft = String.format("%d ngày, %d giờ, %d phút, %d giây", daysLeft, hoursLeft, minutesLeft, secondsLeft);
//                timeLeft_txt.setText(timeLeft);
//            } else {
//                timeLeft_txt.setText("Tới hạn check-out");
//                timeline.stop(); // Dừng timeline nếu đã tới hạn
//            }
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
    
//    private ScheduledExecutorService scheduler;
    
//    private void calculateTimeLeft(String checkoutStr) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime checkoutDateTime = LocalDateTime.parse(checkoutStr, formatter);
//
//        Runnable updateTimeLeftTask = new Runnable() {
//            @Override
//            public void run() {
//                if (checkoutDateTime.isAfter(now)) {
//                    Duration duration = Duration.between(now, checkoutDateTime);
//                    long secondsLeft = duration.getSeconds();
//
//                    long daysLeft = secondsLeft / (24 * 3600); // Số giây trong một ngày = 24 * 3600
//                    secondsLeft = secondsLeft % (24 * 3600); // Cập nhật lại số giây còn lại
//
//                    long hoursLeft = secondsLeft / 3600; // Số giây trong một giờ = 3600
//                    secondsLeft = secondsLeft % 3600; // Cập nhật lại số giây còn lại
//
//                    long minutesLeft = secondsLeft / 60; // Số giây trong một phút = 60
//                    secondsLeft = secondsLeft % 60; // Số giây còn lại sau khi tính toán phút
//
//                    String timeLeft = String.format("%d ngày, %02d:%02d:%02d", daysLeft, hoursLeft, minutesLeft, secondsLeft);
//                    timeLeft_txt.setText(timeLeft);
//                } else {
//                    timeLeft_txt.setText("Tới hạn check-out");
//                    scheduler.shutdown(); // Dừng scheduler nếu đã tới hạn
//                }
//            }
//        };
//
//        // Cập nhật thời gian còn lại mỗi giây
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(updateTimeLeftTask, 0, 1, TimeUnit.SECONDS);
//    }
    
    private void calculateTimeLeft(String checkoutStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        LocalDate checkoutDate = LocalDate.parse(checkoutStr, formatter);

        if (checkoutDate.isAfter(now)) {
            long daysLeft = ChronoUnit.DAYS.between(now, checkoutDate);
            String timeLeft = String.format("%d ngày", daysLeft);
            timeLeft_txt.setText(timeLeft);
        } else {
            timeLeft_txt.setText("Tới hạn check-out");
        }
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

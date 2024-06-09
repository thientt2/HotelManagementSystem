package UI.Resource;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import BLL.HOADONDICHVU_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import BLL.PHONG_BLL;
import DTO.HOADONDICHVU;
import DTO.LOAIPHONG;
import DTO.PHIEUNHANPHONG;
import DTO.PHONG;
import UI.Bill.billService_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class itemBill_Controller implements Initializable{
	
    @FXML
    private Label billId_txt;

    @FXML
    private Label checkinDate_txt;
    
    @FXML
	private Label checkoutDate_txt;

    @FXML
    private Button printAgain_btn;

    @FXML
    private Button pay_btn;

    @FXML
    private Label priceService_txt;

    @FXML
    private Label room_txt;

    @FXML
    private Label surcharge_txt;

    @FXML
    private Label status_txt;
    
    @FXML
    private AnchorPane bill_anchorpane;
    
    public Button getPay_btn() {
		return pay_btn;
	}
    
    private HOADONDICHVU billService;
    
    public void setData(Object[] item) {
    	billId_txt.setText(item[0].toString());		
		room_txt.setText(item[1].toString());		
		billService = HOADONDICHVU_BLL.getBillServiceByBillId(item[0].toString());
//		PHIEUNHANPHONG receiveRoom = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(item[1].toString());
		//priceService_txt.setText(item[2].toString());
		Double gia1 = Double.parseDouble(item[2].toString());
    	String formattedPrice1 = String.format("%.0f", gia1);
    	StringBuilder sb1 = new StringBuilder(formattedPrice1);
    	int length1 = sb1.length();
    	for (int i = length1 - 3; i > 0; i -= 3) {
    	    sb1.insert(i, ".");
    	}
    	//sb.append(" VND");
    	String finalPrice1 = sb1.toString();
    	priceService_txt.setText(finalPrice1);
		
		//surcharge_txt.setText(item[3].toString());
		Double gia2 = Double.parseDouble(item[3].toString());
    	String formattedPrice2 = String.format("%.0f", gia2);
    	StringBuilder sb2 = new StringBuilder(formattedPrice2);
    	int length2 = sb2.length();
    	for (int i = length2 - 3; i > 0; i -= 3) {
    	    sb2.insert(i, ".");
    	}
    	//sb.append(" VND");
    	String finalPrice2 = sb2.toString();
    	surcharge_txt.setText(finalPrice2);
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime checkindate = LocalDateTime.parse(item[4].toString(), inputFormatter);
		LocalDateTime checkoutdate = LocalDateTime.parse(item[5].toString(), inputFormatter);
		String checkin = checkindate.format(outputFormatter);
		String checkout = checkoutdate.format(outputFormatter);		
		
		checkinDate_txt.setText(checkin.toString());		
		checkoutDate_txt.setText(checkout.toString());
		//Đoạn này để set bấm được nút thanh toán, thanh toán chỉ hiện khi phòng đang dọn dẹp
		PHONG currentRoom = PHONG_BLL.getRoom(room_txt.getText());
		
		status_txt.setText(getStatus(billService.getTRANGTHAI()));   
    	status_txt.setStyle(getStatusStyle(status_txt.getText()));
    	
//    	 DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//         // Chuyển đổi chuỗi ngày tháng sang LocalDate
////         LocalDate checkOutReceive = LocalDate.parse(receiveRoom.getTGTRA(), inputFormatter2);
//
//         // Lấy ngày hiện tại
//         LocalDate now = LocalDate.now();
		if((status_txt.getText() == "Chưa thanh toán") && (currentRoom.getMATRANGTHAI() == 2) ) {
			pay_btn.setDisable(false);
		}else {
			pay_btn.setDisable(true);
		}
			
    }
    
    public void printBill() {
    	try {
			// TODO Auto-generated method stub
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Bill/billService.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(pay_btn.getScene().getWindow());
            Scene scene = new Scene(root);

			billService_Controller billServiceController = loader.getController();
		    Circle image = billServiceController.getCircle();
		    Image defaultImage = new Image("/Images/LAOPERA.jpg");
		    image.setFill(new ImagePattern(defaultImage));
            Object[] item = new Object[6];
			item[0] = billId_txt.getText();
			item[1] = room_txt.getText();
			item[2] = priceService_txt.getText();
			item[3] = surcharge_txt.getText();
			item[4] = checkinDate_txt.getText();
			item[5] = checkoutDate_txt.getText();
			billServiceController.setData(item);
			
			stage.setScene(scene);
            stage.showAndWait();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }   

    public static String getStatus(int trangThaiHoaDon) {
		if(trangThaiHoaDon == 0){
			return "Chưa thanh toán";
		}else {
			return "Đã thanh toán";
		}            
	}
	
    private String getStatusStyle(String status) {
	    String textColor = "";
	    String bgColor = "";
	    switch (status) {
	        case "Đã thanh toán":
	            textColor = "#41C588";
	            bgColor = "#E7F8F0";
	            break;
	        case "Chưa thanh toán":
	            textColor = "#F36960";
	            bgColor = "#FEECEB";
	            break;
	    }
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-background-radius: 20; ", bgColor, textColor);
	}

    public void printBillAgain() {
    	
    	try {
			// TODO Auto-generated method stub
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Bill/billService.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(pay_btn.getScene().getWindow());
            Scene scene = new Scene(root);
            

			billService_Controller billServiceController = loader.getController();
			Circle image = billServiceController.getCircle();
		    Image defaultImage = new Image("/Images/LAOPERA.jpg");
		    image.setFill(new ImagePattern(defaultImage));
            Object[] item = new Object[6];
			item[0] = billId_txt.getText();
			item[1] = room_txt.getText();
			item[2] = priceService_txt.getText();
			item[3] = surcharge_txt.getText();
			item[4] = checkinDate_txt.getText();
			item[5] = checkoutDate_txt.getText();
			billServiceController.setData(item);
			
			stage.setScene(scene);
            stage.showAndWait();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

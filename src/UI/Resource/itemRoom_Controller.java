package UI.Resource;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import BLL.PHIEUNHANPHONG_BLL;
import BLL.PHONG_BLL;
import DTO.PHIEUNHANPHONG;
import UI.Room.servicesRoom_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class itemRoom_Controller implements Initializable {
	
	 @FXML
	    private Button bookService_btn;

	    @FXML
	    private Button control_btn;

	    @FXML
	    private Button detail_btn;

	    @FXML
	    private Label roomId_txt;

	    @FXML
	    private Label roomtype_txt;
	    
	    @FXML
	    private Button checkOut_btn;

	    @FXML
	    private Label status_txt;

	    public void setData(Object roomId, Object roomtype, Object status) {
		    roomId_txt.setText(String.valueOf(roomId));
		    roomtype_txt.setText(String.valueOf(roomtype));
		    status_txt.setText(String.valueOf(status));    
		    
		    if(status.equals("Trống")) {
		    	status_txt.setStyle("-fx-background-color: #E8F1FD; -fx-text-fill: #448DF2; -fx-background-radius: 20; -fx-padding: 5 10");
		    	bookService_btn.setVisible(false);
		    	detail_btn.setVisible(false);
		    	control_btn.setVisible(false);
		    	checkOut_btn.setVisible(false);
		    }else if (status.equals("Chưa dọn dẹp")) {
		    	status_txt.setStyle("-fx-background-color: #FEECEB; -fx-text-fill: #F36960; -fx-background-radius: 20; -fx-padding: 5 10");
		    	bookService_btn.setDisable(true);
		    	detail_btn.setDisable(true);
		    	control_btn.setDisable(false);
		    	checkOut_btn.setVisible(false);
		    }else {
		    	status_txt.setStyle("-fx-background-color: #E7F8F0; -fx-text-fill: #41C588; -fx-background-radius: 20; -fx-padding: 5 10");
		    	bookService_btn.setDisable(false);
		    	detail_btn.setDisable(false);
		    	control_btn.setVisible(false);
		    	checkOut_btn.setVisible(true);
		    	PHIEUNHANPHONG receiveRoom = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(String.valueOf(roomId));
		    	System.out.println("Lay phieu nhan phong ra de set trang thai check out: "+receiveRoom +"cho phong: "+roomId);
		    	if(receiveRoom == null) {
			    	return;
		    	}else {	    		
		    	
			    	String checkOut = receiveRoom.getTGTRA();
			    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");		    	
	
			    	// Lấy thời gian hiện tại
			    	LocalDate now = LocalDate.now();
	
			    	// Parse thời gian trả phòng từ chuỗi
			    	LocalDateTime checkOutTime = LocalDateTime.parse(checkOut, inputFormatter);
	
			    	// Chuyển đổi LocalDateTime thành LocalDate để so sánh chỉ ngày
			    	LocalDate formattedCheckOutDate = checkOutTime.toLocalDate();
			    	// So sánh ngày hiện tại với ngày trả phòng
			    	if (now.isEqual(formattedCheckOutDate)) {
			    	    checkOut_btn.setDisable(false);
			    	} else {
			    	    checkOut_btn.setDisable(true);
			    	}	   
		    	}
		    }
		    	
	    }
	    
	    public void checkOut() {	    
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Xác nhận trả phòng");
	        alert.setHeaderText(null);
	        alert.setContentText("Xác nhận check-out phòng " + roomId_txt.getText() + " ?" );
	        Optional<ButtonType> option = alert.showAndWait();

	        if (option.get().equals(ButtonType.OK)) {
		    	LocalDateTime now = LocalDateTime.now();
		    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		    	String checkOut = now.format(inputFormatter);
		    	System.out.println(checkOut);
		    	String roomNumber = roomId_txt.getText();
		    	PHIEUNHANPHONG receiveRoom = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(roomNumber);
		    	PHIEUNHANPHONG_BLL.updateCheckOut(receiveRoom.getMAPNP(), checkOut);
		    	PHONG_BLL.changeNotCleanRoomStatus(roomNumber);
	        	alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Thông báo");
	            alert.setHeaderText(null);
	            alert.setContentText("Trả phòng " + roomId_txt.getText() + " thành công!");
	            alert.showAndWait();
		    	
	        } else {
	            return;
	        }

	    }
	    
	    public void bookService() {
	    	try {
                FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
                Parent root = loaderServices.load();
                servicesRoom_Controller servicesController = loaderServices.getController();
                String roomNumber = roomId_txt.getText();
                servicesController.setData(roomNumber);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(bookService_btn.getScene().getWindow());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	    
	    public void cleanRoom() {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Xác nhận dọn dẹp");
	        alert.setHeaderText(null);
	        alert.setContentText("Xác nhận đã dọn dẹp phòng " + roomId_txt.getText() + " ?" );
	        Optional<ButtonType> option = alert.showAndWait();

	        if (option.get().equals(ButtonType.OK)) {
		    	PHONG_BLL.changeEmptyRoomStatus(roomId_txt.getText());
	        	alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Thông báo");
	            alert.setHeaderText(null);
	            alert.setContentText("Dọn dẹp phòng " + roomId_txt.getText() + " thành công!");
	            alert.showAndWait();
		    	
	        } else {
	            return;
	        }

	    }
	    
	    public Button getDetailBtn() {
	    	return detail_btn;
	    }
	    
	    public Button getCleanBtn() {
	    	return control_btn;
	    }
	    
	    public Button getCheckOutBtn() {
	    	return checkOut_btn;
	    }
	    
	    
	    	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}

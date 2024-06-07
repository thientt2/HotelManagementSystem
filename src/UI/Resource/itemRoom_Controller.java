package UI.Resource;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.PHIEUNHANPHONG_BLL;
import BLL.PHONG_BLL;
import UI.Room.servicesRoom_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		    }
		    	
	    }
	    
	    public void checkOut() {	    
	    	LocalDateTime now = LocalDateTime.now();
	    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	    	String checkOut = now.format(inputFormatter);
	    	System.out.println(checkOut);
	    	String roomNumber = roomId_txt.getText();
	    	String mapnp = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(roomNumber);
	    	PHIEUNHANPHONG_BLL.updateCheckOut(mapnp, checkOut);
	    	PHONG_BLL.changeNotCleanRoomStatus(roomNumber);
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
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	    
	    public void cleanRoom() {
	    	PHONG_BLL.changeEmptyRoomStatus(roomId_txt.getText());
	    }
	    
	    public Button getDetailBtn() {
	    	return detail_btn;
	    }
	    
	    	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}

package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
	    
	    public Button getDetailBtn() {
	    	return detail_btn;
	    }
	    
	    public Button getBookServiceBtn() {
	    	return bookService_btn;
	    }
	    
	    public Button getControlBtn() {
	    	return control_btn;
	    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}

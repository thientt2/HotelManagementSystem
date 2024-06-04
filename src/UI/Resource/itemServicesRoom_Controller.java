package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class itemServicesRoom_Controller implements Initializable {

	 	@FXML
	    private Button addDetail_btn;

	    @FXML
	    private Button close_btn;

	    @FXML
	    private Button confirmBookRoom_btn;

	    @FXML
	    private VBox detailServiceRoom_vbox;

	    @FXML
	    private TextField price_txt;

	    @FXML
	    private ComboBox<String> quantity_cb;

	    @FXML
	    private TextField roomNumber_txt;

	    @FXML
	    private ComboBox<String> serviceName_cb;

	    @FXML
	    private ComboBox<String> serviceType_cb;

	    @FXML
	    private Label totalPrice_txt;
	    
	    public void setData(String roomNumber) {
	    	roomNumber_txt.setText(roomNumber);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
}

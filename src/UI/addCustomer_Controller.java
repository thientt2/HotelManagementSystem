package UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addCustomer_Controller implements Initializable {

	 @FXML
	    private Button addCustomer_btn;

	    @FXML
	    private AnchorPane addCustomer_form;

	    @FXML
	    private TextField address_txt;

	    @FXML
	    private Button cancel_btn;

	    @FXML
	    private TextField cccdTxt;

	    @FXML
	    private TextField country_txt;

	    @FXML
	    private TextField customerName_txt;

	    @FXML
	    private DatePicker date_picker;

	    @FXML
	    private TextField email_txt;

	    @FXML
	    private ComboBox<String> gender_combobox;

	    @FXML
	    private TextField phoneNumber_txt;
	    
	    public void addCustomer() {
	    	
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

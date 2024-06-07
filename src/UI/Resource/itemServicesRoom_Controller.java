package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemServicesRoom_Controller implements Initializable {

	    @FXML
	    private Button deleteItem_btn;
	
	    @FXML
	    private Label number_txt;
	
	    @FXML
	    private Label price_txt;
	
	    @FXML
	    private Label serviceName_txt;
	
	    @FXML
	    private Label serviceType_txt;
	
	    @FXML
	    private Label totalPrice_txt;
	    
	    public void setData(Object[] item) {
	    	serviceType_txt.setText(item[0].toString());
	    	serviceName_txt.setText(item[1].toString());	    	    	
	    	price_txt.setText(item[2].toString());
	    	number_txt.setText(item[3].toString());	 	    	
	    	totalPrice_txt.setText(item[4].toString());
	    }
	    
	    public Button getDeleteItem_btn() {
			return deleteItem_btn;
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
}

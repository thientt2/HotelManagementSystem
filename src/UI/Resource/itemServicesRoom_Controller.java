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
	    	int price = Integer.parseInt(item[2].toString());
	    	System.out.println(price);
	    	String formattedPrice = String.valueOf(price);
	    	StringBuilder sb = new StringBuilder(formattedPrice);
	    	int length = sb.length();
	    	for (int i = length - 3; i > 0; i -= 3) {
	    		sb.insert(i, ".");
	    	}
	    	String finalPrice = sb.toString();
	    	price_txt.setText(finalPrice);
	    	number_txt.setText(item[3].toString());	 	
	    	int totalPrice = Integer.parseInt(item[4].toString());
	    	System.out.println(totalPrice);
	    	String formattedTotalPrice = String.valueOf(totalPrice);
	    	StringBuilder sb2 = new StringBuilder(formattedTotalPrice);
	    	int length2 = sb2.length();
	    	for (int i = length2 - 3; i > 0; i -= 3) {
	    		sb2.insert(i, ".");
	    	}
	    	String finalTotalPrice = sb2.toString();
	    	totalPrice_txt.setText(finalTotalPrice);	    	
	    }
	    
	    public Button getDeleteItem_btn() {
			return deleteItem_btn;
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
}

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
	    	Double gia1 = Double.parseDouble(item[2].toString());
	    	String formattedPrice1 = String.format("%.0f", gia1);
	    	StringBuilder sb1 = new StringBuilder(formattedPrice1);
	    	int length1 = sb1.length();
	    	for (int i = length1 - 3; i > 0; i -= 3) {
	    	    sb1.insert(i, ".");
	    	}
	    	//sb2.append(" VND");
	    	String finalPrice1 = sb1.toString();
	    	price_txt.setText(finalPrice1);
	    	
	    	number_txt.setText(item[3].toString());
	    	
	    	//totalPrice_txt.setText(item[4].toString());
	    	Double gia2 = Double.parseDouble(item[4].toString());
	    	String formattedPrice2 = String.format("%.0f", gia2);
	    	StringBuilder sb2 = new StringBuilder(formattedPrice2);
	    	int length2 = sb2.length();
	    	for (int i = length2 - 3; i > 0; i -= 3) {
	    	    sb2.insert(i, ".");
	    	}
	    	//sb2.append(" VND");
	    	String finalPrice2 = sb2.toString();
	    	totalPrice_txt.setText(finalPrice2);
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
}

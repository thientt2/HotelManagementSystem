package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class itemDetailsRoomService_Controller implements Initializable{	
    @FXML
    private Label quanlity_txt;

    @FXML
    private Label serviceName_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(Object[] item) {
    	serviceName_txt.setText(item[0].toString());
    	
		quanlity_txt.setText(item[1].toString());
		
		//totalPrice_txt.setText(item[3].toString());
		Double gia = Double.parseDouble(item[2].toString());
    	String formattedPrice = String.format("%.0f", gia);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	//sb2.append(" VND");
    	String finalPrice = sb.toString();
    	totalPrice_txt.setText(finalPrice);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}	
}
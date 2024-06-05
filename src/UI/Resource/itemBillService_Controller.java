package UI.Resource;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class itemBillService_Controller {	

    @FXML
    private Label price_txt;

    @FXML
    private Label quanlity_txt;

    @FXML
    private Label serviceName_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(Object[] item) {
    	serviceName_txt.setText(item[0].toString());
    	
		//price_txt.setText(item[1].toString());
		Double gia1 = Double.parseDouble(item[1].toString());
    	String formattedPrice1 = String.format("%.0f", gia1);
    	StringBuilder sb1 = new StringBuilder(formattedPrice1);
    	int length1 = sb1.length();
    	for (int i = length1 - 3; i > 0; i -= 3) {
    	    sb1.insert(i, ".");
    	}
    	//sb2.append(" VND");
    	String finalPrice1 = sb1.toString();
    	price_txt.setText(finalPrice1);
		
		quanlity_txt.setText(item[2].toString());
		
		//totalPrice_txt.setText(item[3].toString());
		Double gia2 = Double.parseDouble(item[3].toString());
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

}

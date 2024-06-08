package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class itemBillBookRoom_Controller implements Initializable{	
	

    @FXML
    private Label numOfDays_txt;

    @FXML
    private Label price_txt;

    @FXML
    private Label quanlity_txt;

    @FXML
    private Label roomType_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(Object[] item) {
		numOfDays_txt.setText(item[3].toString());
		
		//price_txt.setText(item[1].toString());
		int gia1 = Integer.parseInt(item[1].toString().replace(".", ""));
    	String formattedPrice1 = String.valueOf(gia1);
    	StringBuilder sb1 = new StringBuilder(formattedPrice1);
    	int length1 = sb1.length();
    	for (int i = length1 - 3; i > 0; i -= 3) {
    	    sb1.insert(i, ".");
    	}
    	//sb2.append(" VND");
    	String finalPrice1 = sb1.toString();
    	price_txt.setText(finalPrice1);
		
		quanlity_txt.setText(item[2].toString());
		roomType_txt.setText(item[0].toString());
		
		//totalPrice_txt.setText(item[3].toString());
		int gia2 = Integer.parseInt(item[4].toString().replace(".", ""));
    	String formattedPrice2 = String.valueOf(gia2);
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

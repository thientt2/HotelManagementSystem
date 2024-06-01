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
		numOfDays_txt.setText(item[4].toString());
		price_txt.setText(item[1].toString());
		quanlity_txt.setText(item[2].toString());
		roomType_txt.setText(item[0].toString());
		totalPrice_txt.setText(item[3].toString());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

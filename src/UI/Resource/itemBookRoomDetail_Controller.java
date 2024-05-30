package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemBookRoomDetail_Controller implements Initializable {
	
	@FXML
    private Button deleteItem_btn;

    @FXML
    private Label numOfDay_txt;

    @FXML
    private Label price_txt;

    @FXML
    private Label quanlity_txt;

    @FXML
    private Label roomType_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(Object[] item) {
		roomType_txt.setText(String.valueOf(item[0]));
		price_txt.setText(String.valueOf(item[1]));
		quanlity_txt.setText(String.valueOf(item[2]));		
		totalPrice_txt.setText(String.valueOf(item[3]));
		numOfDay_txt.setText(String.valueOf(item[4]));
	}
    
    public Button getDeleteItem_btn() {
		return deleteItem_btn;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}	

}

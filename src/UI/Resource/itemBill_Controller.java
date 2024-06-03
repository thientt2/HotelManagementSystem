package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.HOADONDICHVU;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemBill_Controller implements Initializable{
	
    @FXML
    private Label billId_txt;

    @FXML
    private Label checkinDate_txt;
    
    @FXML
	private Label checkoutDate_txt;

    @FXML
    private Button control_btn;

    @FXML
    private Button pay_btn;

    @FXML
    private Label priceService_txt;

    @FXML
    private Label room_txt;

    @FXML
    private Label surcharge_txt;
    
    public void setData(Object[] item) {
		billId_txt.setText(item[0].toString());		
		room_txt.setText(item[1].toString());
		priceService_txt.setText(item[2].toString());
		surcharge_txt.setText(item[3].toString());
		checkinDate_txt.setText(item[4].toString());
		checkoutDate_txt.setText(item[5].toString());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

package UI.Resource;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime checkindate = LocalDateTime.parse(item[4].toString(), inputFormatter);
		LocalDateTime checkoutdate = LocalDateTime.parse(item[5].toString(), inputFormatter);
		String checkin = checkindate.format(outputFormatter);
		String checkout = checkoutdate.format(outputFormatter);		
		
		checkinDate_txt.setText(checkin.toString());		
		checkoutDate_txt.setText(checkout.toString());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

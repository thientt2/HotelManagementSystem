package UI;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.KHACHHANG;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class editCustomer_Controller implements Initializable {

	
	@FXML
    private TextField addressCustomer_txt;

    @FXML
    private DatePicker birthdayCustomer_txt;

    @FXML
    private TextField cccdCustomer_txt;

    @FXML
    private TextField countryCustomer_txt;

    @FXML
    private TextField customerName_txt;

    @FXML
    private TextField emailCustomer_txt;

    @FXML
    private ComboBox<String> genderCustomer_cb;

    @FXML
    private TextField phoneCustomer_txt;    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void setCustomer(KHACHHANG item) {
		customerName_txt.setText(item.getTENKH());
		cccdCustomer_txt.setText(item.getCCCD());
		phoneCustomer_txt.setText(item.getSDT());
		emailCustomer_txt.setText(item.getEMAIL());
		genderCustomer_cb.setValue(item.getGIOITINH());
//		birthdayCustomer_txt.setValue(item.getNGAYSINH());
		addressCustomer_txt.setText(item.getDIACHI());
		countryCustomer_txt.setText(item.getQUOCTICH());
		
	}

}

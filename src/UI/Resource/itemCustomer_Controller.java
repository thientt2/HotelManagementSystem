package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.KHACHHANG;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemCustomer_Controller implements Initializable{
	
	@FXML
    private Label birthDay_txt;

    @FXML
    private Label cccd_txt;

    @FXML
    private Button contextMenu_btn;

    @FXML
    private Label gender_txt;

    @FXML
    private Label phoneNumber_txt;

    @FXML
    private Label customerId_txt;

    @FXML
    private Label customerName_txt;

    @FXML
    private Label country_txt;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public Button getContextMenu_btn() {
		return contextMenu_btn;
	}	

    public void setCustomer(KHACHHANG item) {
        customerId_txt.setText(item.getMAKH());
        customerName_txt.setText(item.getTENKH());
        birthDay_txt.setText(item.getNGAYSINH());
        gender_txt.setText(item.getGIOITINH());
        cccd_txt.setText(item.getCCCD());
        phoneNumber_txt.setText(item.getSDT());
        country_txt.setText(item.getQUOCTICH());	    	
	}

//	public void refreshCustomerList() {
//		//staffController.refreshStaffList();
//	}
}
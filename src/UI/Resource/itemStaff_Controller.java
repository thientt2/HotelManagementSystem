package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.NHANVIEN;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemStaff_Controller implements Initializable{
	
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
    private Label staffId_txt;

    @FXML
    private Label staffName_txt;

    @FXML
    private Label startDay_txt;
	
	public Button getContextMenu_btn() {
		return contextMenu_btn;
	}	
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

    public void setStaff(NHANVIEN item) {
        staffId_txt.setText(item.getMANV());
        staffName_txt.setText(item.getTENNV());
        birthDay_txt.setText(item.getNGAYSINH());
        gender_txt.setText(item.getGIOITINH());
        cccd_txt.setText(item.getCCCD());
        phoneNumber_txt.setText(item.getSDT());
        startDay_txt.setText(item.getNGAYVAOLAM());			    	
	}
}
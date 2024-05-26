package UI;

import java.net.URL;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import DTO.NHANVIEN;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class staffDetails_Controller implements Initializable{
	@FXML
    private TextField address_txt;

    @FXML
    private TextField birthday_picker;

    @FXML
    private Button cancel_btn;

    @FXML
    private TextField cccd_txt;

    @FXML
    private TextField daywork_txt;

    @FXML
    private TextField customerName_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private TextField gender_combobox;

    @FXML
    private TextField phone_txt;
    
    @FXML
    private TextField type_txt;
    
    public void setStaff(NHANVIEN item) {
    	customerName_txt.setText(item.getTENNV());
		phone_txt.setText(item.getSDT());
		address_txt.setText(item.getDIACHI());
		email_txt.setText(item.getEMAIL());
		birthday_picker.setText(item.getNGAYSINH());// DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		cccd_txt.setText(item.getCCCD());
		gender_combobox.setText(item.getGIOITINH());
		daywork_txt.setText(item.getNGAYVAOLAM());
		type_txt.setText(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));	
	}  
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
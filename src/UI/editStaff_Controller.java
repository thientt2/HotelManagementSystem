package UI;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import BLL.NHANVIEN_BLL;
import DTO.NHANVIEN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class editStaff_Controller implements Initializable{
	
	 @FXML
	    private TextField address_txt;

	    @FXML
	    private DatePicker birthday_txt;

	    @FXML
	    private Button cancel_btn;

	    @FXML
	    private TextField cccd_txt;

	    @FXML
	    private Button edit_btn;

	    @FXML
	    private TextField email_txt;

	    @FXML
	    private ComboBox<String> gender_cb;	

	    @FXML
	    private ComboBox<String> staffType_cb;

	    @FXML
	    private TextField name_txt;

	    @FXML
	    private TextField phoneNumber_txt;

	    @FXML
	    private DatePicker startDay_txt;
	    
	    private List<String> staffTypes = LOAINHANVIEN_BLL.listStaffType();
	    public void showStaffType() {
	        ObservableList<String> listStaffTypes = FXCollections.observableArrayList(staffTypes);
	        staffType_cb.setItems(listStaffTypes);
	    }
	    
	    
	    public void setStaff(NHANVIEN item) {
			name_txt.setText(item.getTENNV());
			phoneNumber_txt.setText(item.getSDT());
			address_txt.setText(item.getDIACHI());
			email_txt.setText(item.getEMAIL());
			startDay_txt.setValue(LocalDate.parse(item.getNGAYVAOLAM(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			birthday_txt.setValue(LocalDate.parse(item.getNGAYSINH(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			cccd_txt.setText(item.getCCCD());
			gender_cb.setValue(item.getGIOITINH());
			staffType_cb.setValue(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));		
		}   

	    
	    public void editStaff() {
	    	
	    }

	   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	

}

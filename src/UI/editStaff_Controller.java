package UI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
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
	    private TextField gender_txt;	

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
	    
	    private NHANVIEN nhanVien;
	    public void setStaff(NHANVIEN item) {
			try {
				nhanVien = item.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
//	    public void setUpStaff() {
//	    	name_txt.setText(nhanVien.getTENNV());
//	    	phoneNumber_txt.setText(nhanVien.getSDT());
//	    	address_txt.setText(nhanVien.getDIACHI());
//	    	email_txt.setText(nhanVien.getEMAIL());
//	    	startDay_txt.setValue(nhanVien.getNGAYVAOLAM());
//	    	birthday_txt.setValue(nhanVien.getNGAYSINH());
//	    	cccd_txt.setText(nhanVien.getCCCD());
//	    	gender_txt.setText(nhanVien.getGIOITINH());
//	    	staffType_cb.setValue(nhanVien.getLOAINHANVIEN());	
//	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showStaffType();
		if (nhanVien != null) {
	        System.out.println("Name: " + nhanVien.getTENNV());
	        System.out.println("Age: " + nhanVien.getNGAYSINH());
	        System.out.println("Address: " + nhanVien.getDIACHI());
	    }
	}


	

}

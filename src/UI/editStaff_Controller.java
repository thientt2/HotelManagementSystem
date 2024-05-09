package UI;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import BLL.NHANVIEN_BLL;
import DTO.NHANVIEN;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.SystemMessage;

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
	    
	    public void showGender() {
		    gender_cb.getItems().addAll("Nam", "Nữ");
	    }
	    
	    private String manv;
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
			manv = item.getMANV();
			
		}   

	    
	    public void editStaff() {
	    	Map<String, String> data  = new HashMap<String, String>();
	    	data.put("name", name_txt.getText());
	    	data.put("phone", phoneNumber_txt.getText());
	    	data.put("address", address_txt.getText());
	    	data.put("email", email_txt.getText());
	    	data.put("startDay", startDay_txt.getValue().toString());
	    	data.put("birthday", birthday_txt.getValue().toString());
	    	data.put("cccd", cccd_txt.getText());
	    	data.put("gender", gender_cb.getSelectionModel().getSelectedItem());
	    	data.put("job", String.valueOf(LOAINHANVIEN_BLL.getStaffTypeId(staffType_cb.getValue())));
	    	data.put("staffid",manv);	    	
			
	    	NHANVIEN_BLL.editStaff(data);
	    	
	    	String check = SystemMessage.ERROR_MESSAGE;
	    	AlertMessage alert = new AlertMessage();
	    	if(check.equals("ERROR_EMPTY")) {
		    	alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
		    	SystemMessage.ERROR_MESSAGE = "";
	    	} else if(check.equals("ERROR_EMAIL")) {
	    		alert.errorMessage("Sai định dạng email xin vui lòng nhập lại!");
	    		SystemMessage.ERROR_MESSAGE = "";
	    	} else if(check.equals("ERROR_PHONE")) {
		    	alert.errorMessage("Số điện thoại không được chứa kí tự và bắt đầu bằng số 0!");
		    	SystemMessage.ERROR_MESSAGE = "";
	    	}else {	      	
	    		alert.successMessage("Thay đổi thông tin nhân viên thành công!");
	    	}
	    }

	    
	    public void cancel() {
	    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
	    	stage.close();
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showStaffType();
		showGender();

	}


	

}

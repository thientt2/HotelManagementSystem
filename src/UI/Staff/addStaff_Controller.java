package UI.Staff;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import BLL.NHANVIEN_BLL;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.SystemMessage;

public class addStaff_Controller implements Initializable {
	
	@FXML
    private Button addStaff_btn;

    @FXML
    private TextField address_txt;

    @FXML
    private DatePicker birthday_datepicker;

    @FXML
    private Button cancel_btn;

    @FXML
    private TextField cccd_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private ComboBox<String> gender_cb;

    @FXML
    private ComboBox<String> job_cb;

    @FXML
    private TextField phoneNumber_txt;

    @FXML
    private TextField staffName_txt;

    @FXML
    private DatePicker startDay_datepicker;
    
    public void showGender() {
		gender_cb.getItems().addAll("Nam", "Nữ");
	}
    
    
    
    private List<String> staffTypes = LOAINHANVIEN_BLL.listStaffType();
    public void showStaffType() {
        ObservableList<String> listStaffTypes = FXCollections.observableArrayList(staffTypes);
        job_cb.setItems(listStaffTypes);
    }
    
    public void addStaff() {
    	Map<String,String> data = new HashMap<String,String>();
    	
		data.put("name", staffName_txt.getText());
		data.put("birthday", birthday_datepicker.getValue().toString());
		data.put("gender", gender_cb.getValue());
		data.put("cccd", cccd_txt.getText());
		data.put("address", address_txt.getText());
		data.put("phone", phoneNumber_txt.getText());
		data.put("email", email_txt.getText());
		data.put("job", String.valueOf(LOAINHANVIEN_BLL.getStaffTypeId(job_cb.getValue())));
		data.put("startDay", startDay_datepicker.getValue().toString());
		
		String check = SystemMessage.ERROR_MESSAGE;
		AlertMessage alert = new AlertMessage();
		NHANVIEN_BLL.insertStaff(data);
		if(check.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
			SystemMessage.ERROR_MESSAGE = "";
		}else if(check.equals("ERROR_EMAIL")) {
			alert.errorMessage("Vui lòng nhập địa chỉ email hợp lệ!");
			SystemMessage.ERROR_MESSAGE = "";
		}else if(check.equals("ERROR_PHONE")) {
			alert.errorMessage("Vui lòng nhập số điện thoại hợp lệ!");
			SystemMessage.ERROR_MESSAGE = "";
		}else {			
			alert.successMessage("Thêm nhân viên thành công!");
			clearStaff();
		}
    	
    }
    
    public void clearStaff() {
    	staffName_txt.clear();	
		birthday_datepicker.setValue(null);
		gender_cb.setValue(null);
		cccd_txt.clear();
		address_txt.clear();
		phoneNumber_txt.clear();
		email_txt.clear();
		job_cb.setValue(null);
		startDay_datepicker.setValue(null);
    }
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showGender();
		showStaffType();
	}
	

}

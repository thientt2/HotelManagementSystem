package UI.Staff;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import BLL.NHANVIEN_BLL;
import DAO.NHANVIEN_DAO;
import DTO.LOAIPHONG;
import DTO.NHANVIEN;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import system.SystemMessage;

public class createUser_Controller implements Initializable {
	
	@FXML
    private Button cancel_btn;

    @FXML
    private PasswordField confirmPassword_txt;

    @FXML
    private Button confirm_btn;

    @FXML
    private Button openFolder_btn;

    @FXML
    private PasswordField password_txt;

    @FXML
    private TextField photoUrl_txt;

    @FXML
    private TextField username_txt;
    
    @FXML
    private ComboBox<String> job_cb;

    @FXML
    private ComboBox<String> staff_cb;
    
    public void showStaffType() {
		job_cb.getItems().addAll("Quản trị viên", "Nhân viên quản lý", "Nhân viên lễ tân");
		job_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String selectedValue = newVal.toString();
                showStaffNames(selectedValue);
            }
        });
	}
    
    public void showStaffNames(String selectedValue) {
        int jobType = LOAINHANVIEN_BLL.getStaffTypeId(selectedValue);
        List<String> staffNames = NHANVIEN_BLL.getStaffNamesByJob(jobType);
        ObservableList<String> staffNamesList = FXCollections.observableArrayList(staffNames);
        staff_cb.setItems(staffNamesList);
    }
    
    //sử dụng đổi mật khẩu
    public void setUser(NHANVIEN item) {
    	//staffName_txt.setText(item.getTENNV());
    	//job_txt.setText(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));
    	if(item.getTENDANGNHAP() != null && item.getMATKHAU() != null) {
    		username_txt.setText(item.getTENDANGNHAP());
    		password_txt.setText(item.getMATKHAU());
    		confirmPassword_txt.setText(item.getMATKHAU());
    		//photoUrl_txt.setText(item.getPHOTOURL());
		}
    }
    
    public void choesePhoto() {
    	Stage stage = (Stage) openFolder_btn.getScene().getWindow();
    	FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		photoUrl_txt.setText(selectedFile.getAbsolutePath());
    }
    
    public void createUser() {
    	Map<String, String> data  = new HashMap<String, String>();
		data.put("username", username_txt.getText());
		data.put("password", password_txt.getText());
		data.put("confirmPassword", confirmPassword_txt.getText());
		data.put("photourl", photoUrl_txt.getText());
		data.put("staffName", staff_cb.getValue());
		data.put("job", job_cb.getValue());
		
		NHANVIEN_BLL.createUser(data);
		AlertMessage alert = new AlertMessage();
		String check = SystemMessage.ERROR_MESSAGE;
		if(check.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
			check = "";
		}else if(check.equals("ERROR_PASSWORD")) {
			alert.errorMessage("Mật khẩu không trùng khớp với nhau!\n Vui lòng nhập lại!");
			check = "";
		}else if(check.equals("ERROR_USERNAME_EXIST")) {
			alert.errorMessage("Tài khoản đã tồn tại!\nVui lòng nhập lại!");
			check = "";
		}
		else {
			alert.successMessage("Tạo tài khoản thành công!");
			cancel();
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
		
	}

}

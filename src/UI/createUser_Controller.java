package UI;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import BLL.NHANVIEN_BLL;
import DTO.NHANVIEN;
import application.AlertMessage;
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
    private TextField confirmPassword_txt;

    @FXML
    private Button confirm_btn;

    @FXML
    private TextField job_txt;

    @FXML
    private Button openFolder_btn;

    @FXML
    private TextField password_txt;

    @FXML
    private TextField photoUrl_txt;

    @FXML
    private TextField staffName_txt;

    @FXML
    private TextField username_txt;
    
    public void setUser(NHANVIEN item) {
    	staffName_txt.setText(item.getTENNV());
    	job_txt.setText(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));
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
		data.put("staffName", staffName_txt.getText());
		data.put("job", job_txt.getText());
		data.put("photoUrl", photoUrl_txt.getText());
		
		NHANVIEN_BLL.createUser(data);
		AlertMessage alert = new AlertMessage();
		String check = SystemMessage.ERROR_MESSAGE;
		if(check.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
			check = "";
		}else if(check.equals("ERROR_PASSWORD")) {
			alert.errorMessage("Mật khẩu không trùng khớp với nhau!\n Vui lòng nhập lại!");
			check = "";
		}else {
			alert.successMessage("Tạo tài khoản thành công!");
		}
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

package UI;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.NHANVIEN_BLL;
import DTO.NHANVIEN;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import system.Encode;
import system.SystemMessage;

public class ChangePassword_Controller implements Initializable {
	
	 @FXML
	    private Button cancel_btn;

	    @FXML
	    private PasswordField confirmPassword_txt;

	    @FXML
	    private Button confirm_btn;

	    @FXML
	    private PasswordField oldPassword_txt;

	    @FXML
	    private PasswordField password_txt;

	    @FXML
	    private Label statusComfirmPass_lb;

	    @FXML
	    private Label statusOldPass_lb;

	    @FXML
	    private TextField username_txt;
    
    
    public void cancel() {
		cancel_btn.getScene().getWindow().hide();
	}
	
	public void confirm() {
		String staffId = SystemMessage.getMANV();
		String newPassword = confirmPassword_txt.getText();
		NHANVIEN_BLL.changePassword(staffId, newPassword);
		System.out.println("Đổi mật khẩu thành công");
		cancel_btn.getScene().getWindow().hide();
	}   

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		NHANVIEN staff = NHANVIEN_BLL.getStaffById(SystemMessage.getMANV());
		
		Image checkImage = new Image(getClass().getResourceAsStream("/Images/check.png"));
        Image crossImage = new Image(getClass().getResourceAsStream("/Images/cross.png"));
        
		username_txt.setText(staff.getTENDANGNHAP());
		oldPassword_txt.textProperty().addListener((observable, oldValue, newValue) -> {
			String currentPassword = Encode.base64EncodeAndMd5Hash(newValue);
			if (!currentPassword.equals(staff.getMATKHAU())) {
				password_txt.setDisable(true);				
				ImageView imageView = new ImageView(crossImage);
                imageView.setFitWidth(12);
                imageView.setFitHeight(12);
                statusOldPass_lb.setGraphic(imageView);
			} else {
				password_txt.setDisable(false);						
				ImageView imageView = new ImageView(checkImage);
                imageView.setFitWidth(12);
                imageView.setFitHeight(12);
                statusOldPass_lb.setGraphic(imageView);			
			}
		});
		
		password_txt.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!newValue.equals("") && !newValue.equals(oldPassword_txt.getText())) {
				confirmPassword_txt.setDisable(false);				
			}
			else {
				confirmPassword_txt.setDisable(true);								
			}
		});	
		
		confirmPassword_txt.textProperty().addListener((observable, oldValue, newValue) -> {
			String password = password_txt.getText();
			if(!newValue.equals(password)) {				
				confirm_btn.setDisable(true);
				ImageView imageView = new ImageView(crossImage);
                imageView.setFitWidth(12);
                imageView.setFitHeight(12);
                statusOldPass_lb.setGraphic(imageView);
			} else {				
				confirm_btn.setDisable(false);
				ImageView imageView = new ImageView(checkImage);
                imageView.setFitWidth(12);
                imageView.setFitHeight(12);
                statusOldPass_lb.setGraphic(imageView);	
			}
		});
	}
}

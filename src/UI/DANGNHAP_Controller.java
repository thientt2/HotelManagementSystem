package UI;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.DANGNHAP_BLL;
import DAO.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.SystemMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import application.*;


public class DANGNHAP_Controller implements Initializable {
	private double x = 0;
	private double y = 0;
    
     @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimizeBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField showPasswordTxt;
    
    @FXML
    private CheckBox login_checkBox;

    @FXML
    private StackPane stack_form;

    @FXML
    private TextField usernameTxt;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AlertMessage alert = new AlertMessage();
    private DANGNHAP_BLL dangnhapBLL = null;
    
    public void login() throws SQLException{
    	
    	Map<String, String> data  = new HashMap<String, String>();
    	data.put("user", usernameTxt.getText());
    	data.put("pass", passwordTxt.getText());
    	dangnhapBLL = new DANGNHAP_BLL();
    	boolean check = dangnhapBLL.checkLogin(data);
    	if(check == false) {
    		String error = SystemMessage.ERROR_MESSAGE;
    		if(error.equals("ERROR_1"))
    			alert.errorMessage("Vui lòng nhập đầy đủ thông tin!");
    	}else {
    		String user = data.get("user");
            String pass = data.get("pass");
            String sql = "SELECT * FROM NHANVIEN WHERE TENDANGNHAP = ? AND MATKHAU = ?";

            
            connect = DatabaseConnection.connectDb();
            
            try{
                
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, user);
                prepare.setString(2, pass);
                
                result = prepare.executeQuery();                
             
                if(result.next()){
                    //Nếu đăng nhập thành công sẽ xuất thông báo qua Alert
                	alert.successMessage("Đăng nhập thành công!");

                    loginBtn.getScene().getWindow().hide();

                    
                    Parent root = FXMLLoader.load(getClass().getResource("MainWindow_UI.fxml"));

                    root.setOnMousePressed((MouseEvent event)->{
                        x = event.getSceneX();
                        y = event.getSceneY();        
                    });
                    
                    
                    
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    Scene scene = new Scene(root);
                    
                    
                    root.setOnMouseDragged((MouseEvent event)->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    
                    
                    stage.setScene(scene);
                    stage.show();
                  
                    
                }else{
                    //Nếu đăng nhập không thành công thì xuất ra màn hình 
                    alert.errorMessage("Sai tài khoản hoặc mật khẩu!");
                }
                
            } catch(Exception e){
                e.printStackTrace();
            }
    		
    	}
    	
        
        
    }
    
    public void showPassword() {
    	
    	if(login_checkBox.isSelected()) {
    		showPasswordTxt.setText(passwordTxt.getText());
    		showPasswordTxt.setVisible(true);
    		passwordTxt.setVisible(false);
    	} else  {
    		passwordTxt.setText(showPasswordTxt.getText());
    		passwordTxt.setVisible(true);
    		showPasswordTxt.setVisible(false);
    	}
    }
    
    public void exit(){
        System.exit(0);
    }
    
    public void minimize(){
        ((Stage) stack_form.getScene().getWindow()).setIconified(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

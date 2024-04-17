package application.BLL;

import java.net.URL;
import java.util.ResourceBundle;

import application.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class DANGNHAP_BLL implements Initializable {
    
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
    private StackPane stack_form;

    @FXML
    private TextField usernameTxt;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    public void login() throws SQLException{
        String user = usernameTxt.getText();
        String pass = passwordTxt.getText();
        String sql = "SELECT * FROM NHANVIEN WHERE TENDANGNHAP = '"+user+"' AND MATKHAU = '"+pass+"'";
        
        connect = DatabaseConnection.connectDb();
        
        try{
            
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(result.next()){
                //Nếu đăng nhập thành công sẽ xuất thông báo qua Alert
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đăng nhập thành công!");
                alert.showAndWait();
                
                Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
                
                
            }else{
                //Nếu đăng nhập không thành công thì xuất ra màn hình 
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Sai tài khoản hoặc mật khẩu!");
                alert.showAndWait();
            }
            
        } catch(Exception e){
            e.printStackTrace();
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

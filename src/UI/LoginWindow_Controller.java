package UI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.DANGNHAP_BLL;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import system.SystemMessage;


import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import application.*;


public class LoginWindow_Controller implements Initializable {
	private double x = 0;
	private double y = 0;
    
    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;
    
    @FXML
    private ImageView image;

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
    
    private AlertMessage alert = new AlertMessage();    
    
    @FXML
    private Button toggleButton;
    
    private boolean isPasswordVisible = false;

    @FXML
    private void initialize() {

    }
    
    public void login() throws SQLException, IOException{
    	
    	Map<String, String> data  = new HashMap<String, String>();
    	data.put("user", usernameTxt.getText());
    	data.put("pass", passwordTxt.getText());
    	
    	boolean check = DANGNHAP_BLL.checkLogin(data);
    	if(check == false) {
    		String error = SystemMessage.ERROR_MESSAGE;
    		if(error.equals("ERROR_1")) {
    			alert.errorMessage("Vui lòng nhập đầy đủ thông tin!");    	
    		}else if(error.equals("ERROR_2")) {
    			alert.errorMessage("Sai tài khoản hoặc mật khẩu!");    			
    		}
    	}
    	else{    		
    		loginBtn.getScene().getWindow().hide();   
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow_UI.fxml"));
    		Parent root = loader.load();
    		
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
    		
    		MainWindow_Controller main = loader.getController();
    		main.initData(usernameTxt.getText());    		
    		
    		stage.setScene(scene);     
    		stage.show();   		

    	}
    }

    private int currentImageIndex = 1;
    private final String[] imageUrls = {"Images/Login/1.jpg", "Images/Login/2.jpg", "Images/Login/3.jpg", "Images/Login/4.jpg"};

    private void setImage(String imageUrl) {
        // Load image from URL and set it to the ImageView
        Image newImage = new Image(imageUrl);
        image.setImage(newImage);
    }
    
    public void showPassword() {
        if (isPasswordVisible) {
            // Hiển thị PasswordField và ẩn TextField
            passwordTxt.setText(showPasswordTxt.getText());
            passwordTxt.setVisible(true);
            showPasswordTxt.setVisible(false);
        } else {
            // Hiển thị TextField và ẩn PasswordField
            showPasswordTxt.setText(passwordTxt.getText());
            showPasswordTxt.setVisible(true);
            passwordTxt.setVisible(false);
        }
        isPasswordVisible = !isPasswordVisible; 
    }
    
    public void exit(){
        System.exit(0);
    }
    
    public void minimize(){
        ((Stage) stack_form.getScene().getWindow()).setIconified(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// Initialize the first image
        setImage(imageUrls[0]);

        // Set up the timeline to change image every 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Change to the next image
                currentImageIndex = (currentImageIndex + 1) % imageUrls.length;
                setImage(imageUrls[currentImageIndex]);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }    
    
}

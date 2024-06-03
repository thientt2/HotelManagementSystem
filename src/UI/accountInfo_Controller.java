package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import DTO.NHANVIEN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class accountInfo_Controller implements Initializable{
	
	@FXML
    private Label email_txt;

    @FXML
    private Button exit_btn;

    @FXML
    private Label name_txt;

    @FXML
    private Label sdt_txt;

    @FXML
    private Button logOut_btn;

    @FXML
    private Circle top_circle;

    @FXML
    private Label type_txt;
    
    private Stage mainStage;  // Tham chiếu đến cửa sổ chính

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void accountInfo(NHANVIEN item) {
    	name_txt.setText(item.getTENNV());
		sdt_txt.setText(item.getSDT());
		email_txt.setText(item.getEMAIL());
		type_txt.setText(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));
	}  
    
    public void logOut() throws IOException {
//    	try {
//			Parent root = FXMLLoader.load(getClass().getResource("/UI/LoginWindow.fxml"));
//			Scene scene = new Scene(root);
//			Stage stage = new Stage();
//			stage.setScene(scene);
//			stage.show();
//			
////			Stage currentStage = (Stage) logOut_btn.getScene().getWindow();
////	        currentStage.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
    	Parent root = FXMLLoader.load(getClass().getResource("/UI/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        Stage loginStage = new Stage();
        loginStage.setScene(scene);
        loginStage.show();

        // Get the current stage (the one containing the logOut_btn) and close it
        Stage currentStage = (Stage) logOut_btn.getScene().getWindow();
        currentStage.close();

        //Close the main stage
        if (mainStage != null) {
            mainStage.close();
        }
    }
    
    public void exit() {
    	System.exit(0);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

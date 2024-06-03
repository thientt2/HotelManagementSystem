package UI;

import java.net.URL;
import java.util.ResourceBundle;

import BLL.LOAINHANVIEN_BLL;
import DTO.NHANVIEN;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button signOut_btn;

    @FXML
    private Circle top_circle;

    @FXML
    private Label type_txt;

    public void accountInfo(NHANVIEN item) {
    	name_txt.setText(item.getTENNV());
		sdt_txt.setText(item.getSDT());
		email_txt.setText(item.getEMAIL());
		type_txt.setText(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));
	}  
    
    public void signOut() {
    	
    }
    
    public void exit() {
    	Stage stage = (Stage) exit_btn.getScene().getWindow();
    	stage.close();
    }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

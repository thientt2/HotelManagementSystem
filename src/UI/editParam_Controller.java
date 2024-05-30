package UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.THAMSO_BLL;
import DTO.THAMSO;
import application.AlertMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.SystemMessage;

public class editParam_Controller implements Initializable{
	
    @FXML
    private Button cancel_btn;

    @FXML
    private Button edit_btn;

    @FXML
    private TextField ten_txt;

    @FXML
    private TextField tile_txt;
    
	    
    public void setParam(THAMSO item) {
		ten_txt.setText(item.getTENTHAMSO());
		tile_txt.setText(item.getGIATRI().toString()); 
	}   
	    
    public void editParam() throws SQLException {
    	Map<String, String> data  = new HashMap<String, String>();
    	data.put("ten", ten_txt.getText());
    	data.put("tile", tile_txt.getText());  	
		
    	THAMSO_BLL.editParam(data);
    	
    	String check = SystemMessage.ERROR_MESSAGE;
    	AlertMessage alert = new AlertMessage();
    	if(check.equals("ERROR_EMPTY")) {
	    	alert.errorMessage("Vui lòng điền tỉ lệ!");
	    	SystemMessage.ERROR_MESSAGE = "";
    	} else if(check.equals("ERROR_TILE")) {
	    	alert.errorMessage("Tỉ lệ phải nằm trong khoảng từ 0 đến 1!");
	    	SystemMessage.ERROR_MESSAGE = "";
    	} else {	      	
    		alert.successMessage("Thay đổi tham số thành công!");
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

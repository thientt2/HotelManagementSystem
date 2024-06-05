package UI.Param;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAIDICHVU_BLL;
import BLL.DICHVU_BLL;
import DTO.DICHVU;
import application.AlertMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.SystemMessage;

public class editService_Controller implements Initializable{
	
    @FXML
    private Button cancel_btn;

    @FXML
    private Button edit_btn;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField price_txt;

    @FXML
    private TextField type_txt;
    
    private String madv;
	    
    public void setService(Object[] item) {
    	price_txt.setText(item[3].toString());
    	
    	
    	name_txt.setText(item[2].toString());
    	type_txt.setText(item[1].toString());   
		madv = item[0].toString();
	}   
	    
    public void editService() throws SQLException {
    	Map<String, String> data  = new HashMap<String, String>();
    	data.put("name", name_txt.getText());
    	data.put("price", price_txt.getText());
    	data.put("type", String.valueOf(LOAIDICHVU_BLL.getServiceTypeId(type_txt.getText())));
    	data.put("serviceid", madv);	    	
		
    	DICHVU_BLL.editService(data);
    	
    	String check = SystemMessage.ERROR_MESSAGE;
    	AlertMessage alert = new AlertMessage();
    	if(check.equals("ERROR_EMPTY")) {
	    	alert.errorMessage("Vui lòng điền đơn giá dịch vụ!");
	    	SystemMessage.ERROR_MESSAGE = "";
    	} else {	      	
    		alert.successMessage("Thay đổi giá dịch vụ thành công!");
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



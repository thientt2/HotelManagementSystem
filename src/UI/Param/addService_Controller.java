package UI.Param;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAIDICHVU_BLL;
import BLL.DICHVU_BLL;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.SystemMessage;

public class addService_Controller implements Initializable {
	
    @FXML
    private Button add_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField price_txt;

    @FXML
    private ComboBox<String> type_cb;
    
    
    private List<String> serviceTypes = LOAIDICHVU_BLL.listServiceType();
    public void showServiceType() {
        ObservableList<String> listServiceTypes = FXCollections.observableArrayList(serviceTypes);
        type_cb.setItems(listServiceTypes);
    }
    
    public void addService() throws SQLException {
    	Map<String,String> data = new HashMap<String,String>();
    	
    	data.put("name", name_txt.getText());
    	data.put("price", price_txt.getText());
    	data.put("type", String.valueOf(LOAIDICHVU_BLL.getServiceTypeId(type_cb.getValue())));
		
		String check = SystemMessage.ERROR_MESSAGE;
		AlertMessage alert = new AlertMessage();
		DICHVU_BLL.addService(data);
		if(check.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
			SystemMessage.ERROR_MESSAGE = "";
		}else {
			
			alert.successMessage("Thêm dịch vụ thành công!");
			clearService();
		}
    	
    }
    
    public void clearService() {
    	name_txt.clear();	
		type_cb.setValue(null);
		price_txt.clear();
    }
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showServiceType();
	}
	

}

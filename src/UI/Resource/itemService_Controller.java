package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import BLL.LOAIDICHVU_BLL;
import DTO.DICHVU;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemService_Controller implements Initializable{
	
    @FXML
    private Button deleteService_btn;

    @FXML
    private Button editService_btn;

    @FXML
    private Label price_txt;

    @FXML
    private Label serviceName_txt;

    @FXML
    private Label typeName_txt;
	
    public Button getEditService_btn() {
		return editService_btn;
	}	
    
    public Button getDeleteService_btn() {
		return deleteService_btn;
	}	
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

    public void setService(Object[] item) {
    	price_txt.setText(item[3].toString());
    	serviceName_txt.setText(item[2].toString());
    	typeName_txt.setText(item[1].toString());   
    	//typeName_txt.setText(LOAIDICHVU_BLL.getServiceTypeName(item.getLOAIDV()));   
	}
}
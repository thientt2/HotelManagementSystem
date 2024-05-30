package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import BLL.LOAIDICHVU_BLL;
import DTO.DICHVU;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemBookRoom_Controller implements Initializable{
	
    @FXML
    private Label Status_txt;

    @FXML
    private Label arrivalDay_txt;

    @FXML
    private Button checkIn_btn;

    @FXML
    private Button contextMenu_btn;

    @FXML
    private Label customerName_txt;

    @FXML
    private Label departmentDay_txt;

    @FXML
    private Label id_txt;

    @FXML
    private Label roomType_txt;
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

    public void setService(Object[] item) {
    	roomType_txt.setText(item[2].toString());
    	customerName_txt.setText(item[1].toString()); 
    	arrivalDay_txt.setText(item[3].toString());
    	departmentDay_txt.setText(item[4].toString());
    	//typeName_txt.setText(LOAIDICHVU_BLL.getServiceTypeName(item.getLOAIDV()));   
	}
}
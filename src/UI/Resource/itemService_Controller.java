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
    	//price_txt.setText(item[3].toString());
    	Double gia = Double.parseDouble(item[3].toString());
    	String formattedPrice = String.format("%.0f", gia);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	sb.append(" VND");
    	String finalPrice = sb.toString();
    	price_txt.setText(finalPrice);
    	
    	serviceName_txt.setText(item[2].toString());
    	typeName_txt.setText(item[1].toString());   
    	//typeName_txt.setText(LOAIDICHVU_BLL.getServiceTypeName(item.getLOAIDV()));   
	}
}
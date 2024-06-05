package UI.Resource;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class itemBillService_Controller {	

    @FXML
    private Label price_txt;

    @FXML
    private Label quanlity_txt;

    @FXML
    private Label serviceName_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(Object[] item) {
    	serviceName_txt.setText(item[0].toString());
		price_txt.setText(item[1].toString());
		quanlity_txt.setText(item[2].toString());
		totalPrice_txt.setText(item[3].toString());
    }

}

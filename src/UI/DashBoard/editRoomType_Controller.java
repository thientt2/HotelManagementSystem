package UI.DashBoard;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.LOAIPHONG_BLL;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import system.SystemMessage;

public class editRoomType_Controller implements Initializable {

	@FXML
    private TextField area_txt;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button edit_btn;

    @FXML
    private ComboBox<String> maxCustomer_cb;

    @FXML
    private TextField price_txt;

    @FXML
    private TextField typeBed_txt;

    @FXML
    private TextField typeRoom_txt;
    
    private String[] numbers = {"2", "3", "4", "5"};  
    
    private String type;
    
    
    public void showNumberList() {
    	ObservableList<String> listNumbers = FXCollections.observableArrayList(numbers);
    	maxCustomer_cb.setItems(listNumbers);
    }
 
    public void setRoomType(Object[] item) {
    	area_txt.setText(item[4].toString());
    	//availabelRoomLabel1.setText(item[2].toString());
    	maxCustomer_cb.setValue(item[3].toString());   
    	price_txt.setText(item[2].toString());
    	//numberOfPaymentLabel1.setText(item[2].toString());
    	typeRoom_txt.setText(item[0].toString());
    		
    	if (item[1].toString() == "Giường King & Giường Queen")
    		typeBed_txt.setText("King & Queen");
    	else
    		typeBed_txt.setText(item[1].toString());
    	
    	if (item[0].toString() == "Standard")
    		type = "1";
    	else if (item[0].toString() == "Deluxe")
    		type = "2";
    	else if (item[0].toString() == "Premium")
    		type = "3";
    	else
    		type = "4";
	}  
    
    public void editRoomType() throws SQLException {
    	Map<String, String> data  = new HashMap<String, String>();
    	data.put("name", typeRoom_txt.getText());
    	data.put("bed", typeBed_txt.getText());
    	data.put("price", price_txt.getText());
    	data.put("max", maxCustomer_cb.getValue());
    	data.put("area", area_txt.getText());
    	data.put("type", type);	    	
		
    	LOAIPHONG_BLL.editRoomType(data);
    	
    	String check = SystemMessage.ERROR_MESSAGE;
    	AlertMessage alert = new AlertMessage();
    	if(check.equals("ERROR_EMPTY")) {
	    	alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
	    	SystemMessage.ERROR_MESSAGE = "";
    	} else {	      	
    		alert.successMessage("Thay đổi thông tin loại phòng thành công!");
    	}
    }
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	showNumberList();
	}
}

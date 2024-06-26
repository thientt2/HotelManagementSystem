package UI.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import DTO.KHACHHANG;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import system.SystemMessage;

public class addCustomer_Controller implements Initializable {

	 @FXML
	    private Button addCustomer_btn;

	    @FXML
	    private AnchorPane addCustomer_form;

	    @FXML
	    private TextField address_txt;

	    @FXML
	    private Button cancel_btn;

	    @FXML
	    private TextField cccdTxt;

	    @FXML
	    private TextField country_txt;

	    @FXML
	    private TextField customerName_txt;

	    @FXML
	    private DatePicker date_picker;

	    @FXML
	    private TextField email_txt;

	    @FXML
	    private ComboBox<String> gender_combobox;

	    @FXML
	    private TextField phoneNumber_txt;
	    
	    private String[] genders = {"Nam", "Nữ"};
	        
	    public void showGenderList() {
	    	ObservableList<String> listGenders = FXCollections.observableArrayList(genders);
	    	gender_combobox.setItems(listGenders);
	    }
	    

	    public void addCustomer() throws SQLException {
	    	//SystemMessage check = new SystemMessage();
	    	Map<String, String> data = new HashMap<String, String>();
	    	data.put("name", customerName_txt.getText());
	    	data.put("cccd", cccdTxt.getText());
	    	data.put("address", address_txt.getText());
	    	data.put("phone", phoneNumber_txt.getText());
	    	data.put("email", email_txt.getText());
	    	data.put("country", country_txt.getText());
	    	data.put("gender", gender_combobox.getSelectionModel().getSelectedItem());
	    	if (date_picker.getValue() != null)
	    		data.put("birthday", date_picker.getValue().toString());
	    	else 
	    		data.put("birthday", "");
	    	KHACHHANG_BLL.addCustomer(data);	
	    	String check = SystemMessage.ERROR_MESSAGE;
	    	AlertMessage alert = new AlertMessage();
			if(check.equals("ERROR_EMPTY")) {
		    	alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
		    	SystemMessage.ERROR_MESSAGE = "";
	    	} else if(check.equals("ERROR_EMAIL")) {
	    		alert.errorMessage("Sai định dạng email xin vui lòng nhập lại!");
	    		SystemMessage.ERROR_MESSAGE = "";
	    	} else if(check.equals("ERROR_PHONE")) {
		    	alert.errorMessage("Số điện thoại không được chứa kí tự và bắt đầu bằng số 0!");
		    	SystemMessage.ERROR_MESSAGE = "";
	    	}else if(check.equals("ERROR_CCCD")){
	    		alert.errorMessage("Thẻ căn cước công dân nhập không đúng định dạng!");
	    		SystemMessage.ERROR_MESSAGE = "";
	    	}else {
	    		alert.successMessage("Thêm khách hàng thành công!");
	    		clearCustomer();	    		
	    	}
	    	
	    }
	    
	    public void clearCustomer() {
	    	customerName_txt.clear();
	    	cccdTxt.clear();
	    	address_txt.clear();
	    	phoneNumber_txt.clear();
	    	email_txt.clear();
	    	country_txt.clear();
	    	gender_combobox.getSelectionModel().clearSelection();
	    	date_picker.setValue(null);
	    }
	    
	    
	    public void cancel() {
	    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
	    	stage.close();
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			showGenderList();
			
		}
}

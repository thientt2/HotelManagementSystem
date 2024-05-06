package UI;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


public class editCustomer_Controller implements Initializable {
    @FXML
    private TextField address_txt;

    @FXML
    private DatePicker birthday_picker;

    @FXML
    private Button cancel_btn;

    @FXML
    private TextField cccd_txt;

    @FXML
    private TextField country_txt;

    @FXML
    private TextField customerName_txt;

    @FXML
    private Button edit_btn;

    @FXML
    private TextField email_txt;

    @FXML
    private ComboBox<String> gender_combobox;

    @FXML
    private TextField phone_txt;
    
    private String maKH;
    
    private String[] genders = {"Nam", "Nữ"};
    
    public void showGenderList() {
    	ObservableList<String> listGenders = FXCollections.observableArrayList(genders);
    	gender_combobox.setItems(listGenders);
    }
    

    public void editCustomer() throws SQLException {
    	//SystemMessage check = new SystemMessage();
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("id", maKH);
    	data.put("name", customerName_txt.getText());
    	data.put("cccd", cccd_txt.getText());
    	data.put("address", address_txt.getText());
    	data.put("phone", phone_txt.getText());
    	data.put("email", email_txt.getText());
    	data.put("country", country_txt.getText());
    	data.put("gender", gender_combobox.getSelectionModel().getSelectedItem());
    	if (birthday_picker.getValue() != null)
    		data.put("birthday", birthday_picker.getValue().toString());
    	else 
    		data.put("birthday", "");
    	KHACHHANG_BLL.editCustomer(data);	
    	String check = SystemMessage.ERROR_MESSAGE;
    	AlertMessage alert = new AlertMessage();
		if(check.equals("ERROR_EMPTY")) {
	    	alert.errorMessage("Không được để trống thông tin, vui lòng kiểm tra lại!");
	    	SystemMessage.ERROR_MESSAGE = "";
    	} else if(check.equals("ERROR_EMAIL")) {
    		alert.errorMessage("Sai định dạng email xin vui lòng nhập lại!");
    		SystemMessage.ERROR_MESSAGE = "";
    	} else if(check.equals("ERROR_PHONE")) {
	    	alert.errorMessage("Số điện thoại không được chứa kí tự và bắt đầu bằng số 0!");
	    	SystemMessage.ERROR_MESSAGE = "";
    	}else {	      		
    		alert.successMessage("Sửa thông tin khách hàng thành công!");
    		cancel();  		
    	}
    	
    }    
    
    public void setCustomer(KHACHHANG item) {
    	maKH = item.getMAKH();
    	customerName_txt.setText(item.getTENKH());
		phone_txt.setText(item.getSDT());
		address_txt.setText(item.getDIACHI());
		email_txt.setText(item.getEMAIL());
		birthday_picker.setValue(LocalDate.parse(item.getNGAYSINH(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cccd_txt.setText(item.getCCCD());
		gender_combobox.setValue(item.getGIOITINH());
		country_txt.setText(item.getQUOCTICH());
	}  
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showGenderList();
		//editCustomer_Controller editCustomer = loader.getController();
		//editCustomer.setCustomer(item); 	
		//setCustomer(khachHang);
		//setUpCustomer();
	}
}

package UI.Customer;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import DTO.KHACHHANG;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class customerDetails_Controller implements Initializable{
	@FXML
    private TextField address_txt;

    @FXML
    private TextField birthday_picker;

    @FXML
    private Button cancel_btn;

    @FXML
    private TextField cccd_txt;

    @FXML
    private TextField country_txt;

    @FXML
    private TextField customerName_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private TextField gender_combobox;

    @FXML
    private TextField phone_txt;
    
    public void setCustomer(KHACHHANG item) {
    	customerName_txt.setText(item.getTENKH());
		phone_txt.setText(item.getSDT());
		address_txt.setText(item.getDIACHI());
		email_txt.setText(item.getEMAIL());
		
		//birthday_picker.setText(item.getNGAYSINH());
		String ngaysinh = item.getNGAYSINH();
        String formattedDate = formatDate(ngaysinh);
        birthday_picker.setText(formattedDate);
        
		cccd_txt.setText(item.getCCCD());
		gender_combobox.setText(item.getGIOITINH());
		country_txt.setText(item.getQUOCTICH());
	}  
    
    private String formatDate(String dateStr) {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = fromUser.parse(dateStr);
            return myFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;  
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

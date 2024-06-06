package UI.Resource;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import DTO.KHACHHANG;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class itemDetailsRoomCustomer_Controller implements Initializable {

    @FXML
    private Label birthday_txt;

    @FXML
    private Label customerName_txt;

    
    public void setData(Object[] item) {
		customerName_txt.setText(String.valueOf(item[0].toString()));
		String ngaysinh = String.valueOf(item[1].toString());
        String formattedDate = formatDate(ngaysinh);
        birthday_txt.setText(formattedDate);
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}	

}
package UI.Resource;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class itemRoomTypeDetails_Controller implements Initializable{
    @FXML
    private Label arrivalDay_txt;

    @FXML
    private Label customerName_txt;

    @FXML
    private Label departmentDay_txt;

    @FXML
    private Label id_txt;

    @FXML
    private Label number_txt;
    
    public void setBookRoom(Object[] item) {
    	id_txt.setText(item[0].toString());
    	customerName_txt.setText(KHACHHANG_BLL.getCustomerName(item[1].toString())); 
    	
    	String checkin = item[3].toString();
        String formattedDate1 = formatDate(checkin);
        arrivalDay_txt.setText(formattedDate1);
    	//arrivalDay_txt.setText(item[3].toString());
    	
    	String checkout = item[4].toString();
        String formattedDate2 = formatDate(checkout);
        departmentDay_txt.setText(formattedDate2);
    	//departmentDay_txt.setText(item[4].toString());
        
        number_txt.setText(item[8].toString()); 
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
		
		
	}
}

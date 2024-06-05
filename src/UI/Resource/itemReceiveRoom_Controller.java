package UI.Resource;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemReceiveRoom_Controller implements Initializable {

    @FXML
    private Label birthday_txt;

    @FXML
    private Label customerName_txt;

    @FXML
    private Button deleteItem_btn;
    
    public void setData(Object[] item) {
		customerName_txt.setText(String.valueOf(item[0]));
		//birthday_txt.setText(String.valueOf(item[1]));
		String ngaysinh = String.valueOf(item[1]);
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
    
    public Button getDeleteItem_btn() {
		return deleteItem_btn;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}	

}
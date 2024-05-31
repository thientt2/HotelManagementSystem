package UI.Resource;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemBookRoom_Controller implements Initializable{
	
    @FXML
    private Label status_txt;

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
    
    public Button getCheckIn_btn() {
		return checkIn_btn;
	}	
    
    public Button getContextMenu_btn() {
		return contextMenu_btn;
	}
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

    public void setBookRoom(Object[] item) {
    	id_txt.setText(item[0].toString());
    	roomType_txt.setText(item[2].toString());
    	customerName_txt.setText(item[1].toString()); 
    	arrivalDay_txt.setText(item[3].toString());
    	departmentDay_txt.setText(item[4].toString());
    	status_txt.setText(getStatus(item[3].toString(), item[5].toString()));   
    	status_txt.setStyle(getStatusStyle(status_txt.getText()));
    	//status_txt.setPadding(new Insets(0,5,0,5));
	}
    
    public static String getStatus(String ngayNhan, String hinhThuc) {
    	
		LocalDate now = LocalDate.now();
        int ngaynow = now.getDayOfMonth();
        int thangnow = now.getMonthValue();
        int namnow = now.getYear();
        int day = 0, month = 0, year = 0;
        String status = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(ngayNhan);        
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH) + 1;
            year = calendar.get(Calendar.YEAR);    
            
           
            if (hinhThuc.equals("Offline")) {
            	status = "Trực tiếp";
            } 
            else {            	
            	if (year < namnow) {
            		status =  "Chưa";
            		
            	}
            	else if (year == namnow) {
            		if (month < thangnow) {
            			status = "Chưa";
            		}
            		else if (month == thangnow) {
            			if (day < ngaynow) {
            				status = "Chưa";
            			}
            			else if (day == ngaynow) {
            				status = "Hôm nay";
            			}
            			else {
            				status = "Quá hạn";
            			}
            		}
            		else {
            			status = "Quá hạn";
            		}
            	}
            	else {
            		status = "Quá hạn";
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return status ;      
    }
    
    private String getStatusStyle(String status) {
	    String textColor = "";
	    String bgColor = "";
	    switch (status) {
	        case "Chưa":
	            textColor = "#448DF2";
	            bgColor = "#E8F1FD";
	            break;
	        case "Hôm nay":
	            textColor = "#41C588";
	            bgColor = "#E7F8F0";
	            break;
	        case "Quá hạn":
	            textColor = "#F36960";
	            bgColor = "#FEECEB";
	            break;
	        case "Trực tiếp":
	            textColor = "#F9A63A";
	            bgColor = "#FEF4E6";
	            break;
	    }
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-background-radius: 20; -fx-padding: 5 10;", bgColor, textColor);
	}

	private String getButtonStyle(String status) {
	    String textColor = "#1570EF";
	    String bgColor = "";
	    switch (status) {
	        case "Chưa":
	            bgColor = "#E8F1FD";
	            break;
	        case "Hôm nay":
	            bgColor = "#FFFFFF";
	            break;
	        default:
	            bgColor = "#E8F1FD"; 
	    }
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-background-radius: 20; -fx-padding: 5 10;", bgColor, textColor);
	}
		
}
package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;
import DTO.THAMSO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class itemParam_Controller implements Initializable{
	
    @FXML
    private Button editParam_btn;

    @FXML
    private Label paramName_txt;

    @FXML
    private Label value_txt;
	
    public Button getEditParam_btn() {
		return editParam_btn;
	}	
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

    public void setParam(THAMSO item) {
    	value_txt.setText(item.getGIATRI().toString());
    	paramName_txt.setText(getParamName(item.getTENTHAMSO()));    
	}
    
    public static String getParamName(String name)
	{
		switch(name)
		{
		case "PhuThuVuotSoNguoi": 
			return "Phụ thu vượt số người tối đa";
		case "PhuThuKhachNuocNgoai":
			return "Phụ thu khách nước ngoài";
		case "PhuThuCheckIn59": 
			return "Phụ thu check-in sớm 5h-9h";
		case "PhuThuCheckIn914":
			return "Phụ thu check-in sớm 9h-14h";
		case "PhuThuCheckOut1215": 
			return "Phụ thu check-out muộn 12h-15h";
		case "PhuThuCheckOut1518":
			return "Phụ thu check-out muộn 15h-18h";
		case "PhuThuCheckOutSau18": 
			return "Phụ thu check-out muộn sau 18h";
		case "GiamGiaKhachTheoDoan2":
			return "Giảm giá khách thuê 2 phòng";
		case "GiamGiaKhachTheoDoan3": 
			return "Giảm giá khách thuê 3-5 phòng";
		case "GiamGiaKhachTheoDoan5":
			return "Giảm giá khách thuê hơn 5 phòng";
		default:
            return "Tham số không xác định";
		}
	}
}
package UI.Bill;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.CHITIETHOADON_BLL;
import BLL.HOADONDICHVU_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.NHANVIEN_BLL;
import DTO.HOADONDICHVU;
import DTO.LOAIPHONG;
import DTO.NHANVIEN;
import UI.Resource.itemBillService_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.SystemMessage;

public class billService_Controller implements Initializable {
	@FXML
    private Label billId_txt;

    @FXML
    private Button cancel_btn;

    @FXML
    private Label checkin_txt;

    @FXML
    private Label checkout_txt;

    @FXML
    private VBox detailSevice_vbox;

    @FXML
    private Button printBill_btn;

    @FXML
    private Label roomPrice_txt;

    @FXML
    private Label roomType_txt;

    @FXML
    private Label servicePrice_txt;

    @FXML
    private Label staffName_txt;

    @FXML
    private Label surcharge_txt;

    @FXML
    private Label totalPrice_txt;    

    public void setData(Object[] item) {
    	String billId = item[0].toString();
    	String roomNumber = item[1].toString();
    	LOAIPHONG roomType = LOAIPHONG_BLL.getRoomTypeByRoomNumber(roomNumber);
    	HOADONDICHVU billService = HOADONDICHVU_BLL.getBillServiceByBillId(billId);
    	ObservableList<Object[]> listData = CHITIETHOADON_BLL.getListDetailService(billId);
    	String checkin = item[4].toString();
    	String checkout = item[5].toString();
    	
    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDateTime checkinDate = LocalDateTime.parse(checkin,inputFormatter);
        LocalDateTime checkoutDate = LocalDateTime.parse(checkout, inputFormatter);
        
        String outputCheckIn = checkinDate.format(outputFormatter);
        String outputCheckOut = checkoutDate.format(outputFormatter);
        
        checkin_txt.setText(outputCheckIn);
        checkout_txt.setText(outputCheckOut);
        
        roomType_txt.setText(roomType.getTENLOAI());
        roomPrice_txt.setText(roomType.getGIA().toString());

    	NHANVIEN staff = NHANVIEN_BLL.getStaffById(SystemMessage.getMANV());
    	
    	billId_txt.setText(billId);
    	staffName_txt.setText(staff.getTENNV());
    	
    	servicePrice_txt.setText(billService.getGIADICHVU().toString());
    	surcharge_txt.setText(billService.getPHUTHU().toString());
    	totalPrice_txt.setText(billService.getTONGTIEN().toString());
    	
    	listData.forEach(itemService -> {
    		try {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBillService.fxml"));
                Parent itemBillService = loader.load();                
                itemBillService_Controller controller = loader.getController();
                Object[] data = new Object[4];
                data[0] = itemService[1];
                data[1] = itemService[2];
                data[2] = itemService[3];
                data[3] = itemService[4];                
                controller.setData(data);                
                detailSevice_vbox.getChildren().add(itemBillService);
        	} catch (IOException e) {
	            e.printStackTrace();
	        }
    	});
    	
    	

    }
    
    public void printBill() {
		
	}
    
    public void close() {
		Stage stage = (Stage) cancel_btn.getScene().getWindow();
		stage.close();		
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

package UI.Room;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.CHITIETHOADON_BLL;
import BLL.DICHVU_BLL;
import BLL.HOADONDICHVU_BLL;
import BLL.LOAIDICHVU_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import DTO.HOADONDICHVU;
import UI.Resource.itemServicesRoom_Controller;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.SystemMessage;

public class servicesRoom_Controller implements Initializable{
	
	@FXML
    private Button addDetail_btn;

    @FXML
    private Button close_btn;

    @FXML
    private Button confirmBookRoom_btn;

    @FXML
    private VBox detailServiceRoom_vbox;

    @FXML
    private TextField price_txt;

    @FXML
    private ComboBox<String> quantity_cb;

    @FXML
    private TextField roomNumber_txt;

    @FXML
    private ComboBox<String> serviceName_cb;

    @FXML
    private ComboBox<String> serviceType_cb;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(Object[] item) {
		roomNumber_txt.setText(item[0].toString());
		
		Map<String, Object> data = new HashMap<String, Object>();
		String maPNP = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(item[0].toString());
		String maNVNhap = SystemMessage.getMANV();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String ngayTao = formatter.format(now);
		data.put("maPNP", maPNP);
		data.put("maNVNhap", maNVNhap);
		data.put("ngayTao", ngayTao);
		HOADONDICHVU_BLL.insertBillService(data);
		showListServiceType();
		showListQuantity();
    }


	public void showListServiceType() {
		ObservableList<String> listServiceType = FXCollections.observableArrayList();
		listServiceType = LOAIDICHVU_BLL.listServiceType();
		serviceType_cb.setItems(listServiceType);
		showListServiceName();
	}
    
    public void showListServiceName() {
    	ObservableList<String> listServiceName = FXCollections.observableArrayList();
    	if(serviceType_cb.getValue() == null) return;
    	else {
        	int serviceTypeId = LOAIDICHVU_BLL.getServiceTypeId(serviceType_cb.getValue());    		
        	listServiceName = DICHVU_BLL.listServiceName(serviceTypeId);    		
        	serviceName_cb.setItems(listServiceName);
        }
    	String serviceName = serviceName_cb.getValue();
		if(serviceName != null) {
			price_txt.setText(String.valueOf(DICHVU_BLL.getPriceService(serviceName)));
		}
    }
    
	public void showListQuantity() {
		ObservableList<String> listQuantity = FXCollections.observableArrayList();
		for(int i = 1; i <= 10; i++) {
			listQuantity.add(String.valueOf(i));
		}
		quantity_cb.setItems(listQuantity);
	}
	
	private ObservableList<Object[]> listDetailService = FXCollections.observableArrayList();
	public void addDetail() {	
		
		String selectedRoomType = serviceType_cb.getValue();
		String selectedServiceName = serviceName_cb.getValue();
		double selectedPrice = Double.parseDouble(price_txt.getText());
		String selectedQuantity = quantity_cb.getValue();
		double selectedTotal = selectedPrice* Integer.parseInt(selectedQuantity);
		
		
		Object[] rowdata = new Object[5];
		rowdata[0] = selectedRoomType;
		rowdata[1] = selectedServiceName;
		rowdata[2] = selectedPrice;
		rowdata[3] = selectedQuantity;
		rowdata[4] = selectedTotal;
		
		boolean isExists = listDetailService.stream()
	    		.anyMatch(row -> selectedServiceName.equals(row[1]));
	    if(isExists) {
	    	listDetailService.stream()
	    	.filter(row -> rowdata[1].equals(row[1]))
	    	.forEach(row -> {   				    	
		    	row[3] = Integer.parseInt(row[3].toString()) + Integer.parseInt(rowdata[3].toString());
		    	row[4] = Double.parseDouble(row[4].toString()) + Double.parseDouble(rowdata[4].toString());		    	
	    	});
	    } else {
	    	listDetailService.add(rowdata);
	    }	    

		detailServiceRoom_vbox.getChildren().clear();		
		for (Object[] row : listDetailService) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemServicesRoom.fxml"));
				Parent billData = loader.load();
				itemServicesRoom_Controller controller = loader.getController();
				controller.setData(row);
				detailServiceRoom_vbox.getChildren().add(billData);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}		
	}
	
	public void addDetailBill() {
		Map<String, Object> data = new HashMap<String, Object>();
		double totalPrice = 0;
		HOADONDICHVU billService = HOADONDICHVU_BLL.getLastBill();
		for(Object[] item : listDetailService) {
			totalPrice += Double.parseDouble(item[4].toString());
			String serviceId = DICHVU_BLL.getServiceIdByName(item[1].toString());
			Map<String, Object> billDetailService = new HashMap<String, Object>();
			billDetailService.put("maHD", billService.getMAHD());
			billDetailService.put("maDV", serviceId);
			billDetailService.put("donGia", item[2]);
			billDetailService.put("soLuong", item[3]);
			billDetailService.put("tongTien", item[4]);
			CHITIETHOADON_BLL.insertBillDetailService(billDetailService);			
		}
		
		data.put("maHD", billService.getMAHD());
		data.put("giaDV", totalPrice);
		HOADONDICHVU_BLL.updateBillService(data);
		
		AlertMessage alert = new AlertMessage();
		alert.successMessage("Đặt dịch vụ thành công!");
		close();
		
	}
	
	public void close() {
		Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
	}	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

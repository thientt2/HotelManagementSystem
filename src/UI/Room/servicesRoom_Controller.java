package UI.Room;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import BLL.CHITIETHOADON_BLL;
import BLL.DICHVU_BLL;
import BLL.HOADONDICHVU_BLL;
import BLL.LOAIDICHVU_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import DTO.HOADONDICHVU;
import DTO.PHIEUNHANPHONG;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.SystemMessage;

public class servicesRoom_Controller implements Initializable{
	
	@FXML
    private Button addDetail_btn;

    @FXML
    private AnchorPane bookService_pane;

    @FXML
    private VBox bookService_vbox;

    @FXML
    private AnchorPane bookedService_pane;

    @FXML
    private VBox bookedService_vbox;	   

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
    
    private ObservableList<Object[]> listDetailService = FXCollections.observableArrayList();
    
    private boolean isNew = false;
    
    private boolean isDelete = false;
    
//    private double totalPrice = 0;
    
    private HOADONDICHVU billService = null;
    
    public void setData(String roomNumber) {
		roomNumber_txt.setText(roomNumber);	
		PHIEUNHANPHONG receiveRoom = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(roomNumber_txt.getText());
		billService = HOADONDICHVU_BLL.getBillServiceByReceiveRoomID(receiveRoom.getMAPNP());
		if(billService != null) {
			bookedService_pane.setVisible(true);
			bookService_pane.setVisible(false);
			isNew = false;
			listDetailService = CHITIETHOADON_BLL.getListDetailService(billService.getMAHD());					
			totalPrice_txt.setText(String.valueOf(billService.getGIADICHVU()));
			showListService(bookedService_vbox, listDetailService);			
		}else {
			bookService_pane.setVisible(true);
			bookedService_pane.setVisible(false);
			isNew = true;
			isDelete = true;
		}
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
	
	private ObservableList<Object[]> listData = FXCollections.observableArrayList();
	
	public void addDetail() {	
		String selectedRoomType = serviceType_cb.getValue();
		String selectedServiceName = serviceName_cb.getValue();
		int selectedPrice = Integer.parseInt(price_txt.getText());
		String selectedQuantity = quantity_cb.getValue();
		int selectedTotal = selectedPrice * Integer.parseInt(selectedQuantity);
		
		Object[] rowdata = new Object[5];
		rowdata[0] = selectedRoomType;
		rowdata[1] = selectedServiceName;
		rowdata[2] = selectedPrice;
		rowdata[3] = selectedQuantity;
		rowdata[4] = selectedTotal;
		
		boolean isExists = listData.stream()
	    		.anyMatch(row -> selectedServiceName.equals(row[1]));
	    if(isExists) {
	    	listData.stream()
	    	.filter(row -> rowdata[1].equals(row[1]))
	    	.forEach(row -> {   				    	
		    	row[3] = Integer.parseInt(row[3].toString()) + Integer.parseInt(rowdata[3].toString());
		    	row[4] = Integer.parseInt(row[4].toString()) + Integer.parseInt(rowdata[4].toString());		    	
	    	});
	    } else {
	    	listData.add(rowdata);
	    }	    
	    
	    int totalPriceItem = listData.stream()
			    .mapToInt(item -> Integer.parseInt(item[4].toString()))
			    .sum();
	    if(billService != null) {
	    	int totalPrice = billService.getGIADICHVU() + totalPriceItem;
	    	totalPrice_txt.setText(String.valueOf(totalPrice));
	    }else {
	    	totalPrice_txt.setText(String.valueOf(totalPriceItem));
	    }    

    	
		if(isNew) {
			showListService(detailServiceRoom_vbox,listData);	
		}else {
			isDelete = true;
			showListService(bookService_vbox,listData);
			
		}			
	}
	
	public void showListService(VBox vbox,ObservableList<Object[]> listData) {
		vbox.getChildren().clear();		
		for (Object[] item : listData) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemServicesRoom.fxml"));
				Parent billData = loader.load();
				itemServicesRoom_Controller controller = loader.getController();
				controller.setData(item);
				Button deleteItem_btn = controller.getDeleteItem_btn();
				if(isDelete == true) {
					deleteItem_btn.setVisible(true);
					deleteItem_btn.setOnAction(event -> {
						listData.remove(item);
//						totalPrice -= Double.parseDouble(item[4].toString());
//		                String formattedPrice = String.format("%.0f", totalPrice);
//		                StringBuilder sb = new StringBuilder(formattedPrice);
//		                int length = sb.length();
//		                for (int i = length - 3; i > 0; i -= 3) {
//		                    sb.insert(i, ".");
//		                }
//		                String finalPrice = sb.toString();
//		                totalPrice_txt.setText(finalPrice);
		                vbox.getChildren().remove(billData);
					});				
					
				}else {
					deleteItem_btn.setVisible(false);
				}
				vbox.getChildren().add(billData);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}		
	}
	
	public void addDetailBill() {	
		
		System.out.println(isNew);
		if(isNew) {
			insertBillService(listData);
		}else {
			updateBillService(listData);
		}	

		AlertMessage alert = new AlertMessage();
		alert.successMessage("Đặt dịch vụ thành công!");
		close();
	}
		
	
	
	private void insertBillService(ObservableList<Object[]> listDetailService) {
		// TODO Auto-generated method stub
		Map<String, Object> dataBillService = new HashMap<String, Object>();
		PHIEUNHANPHONG receiveRoom = PHIEUNHANPHONG_BLL.getReceiveRoomIDByRoomID(roomNumber_txt.getText());
		String maNVNhap = SystemMessage.getMANV();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String ngayTao = formatter.format(now);
		
		int totalPrice = Integer.parseInt(totalPrice_txt.getText());
		
		dataBillService.put("maPNP", receiveRoom.getMAPNP());
		dataBillService.put("maNVNhap", maNVNhap);
		dataBillService.put("ngayTao", ngayTao);
		dataBillService.put("giaDV", totalPrice);	
		HOADONDICHVU_BLL.insertBillService(dataBillService);
		HOADONDICHVU billService = HOADONDICHVU_BLL.getLastBill();
		
		for(Object[] item : listDetailService) {			
			String serviceId = DICHVU_BLL.getServiceIdByName(item[1].toString());
			Map<String, Object> billDetailService = new HashMap<String, Object>();
			billDetailService.put("maHD", billService.getMAHD());
			billDetailService.put("maDV", serviceId);			
			billDetailService.put("soLuong", Integer.parseInt(item[3].toString()));
			billDetailService.put("tongTien", Integer.parseInt(item[4].toString()));

			CHITIETHOADON_BLL.insertBillDetailService(billDetailService);			
		}
	}

	public void updateBillService(ObservableList<Object[]> listData) {
		
		int totalPriceItem = listData.stream()
			    .mapToInt(item -> Integer.parseInt(item[4].toString()))
			    .sum();
		int totalPrice = billService.getGIADICHVU() + totalPriceItem; 
		String maHD = billService.getMAHD();
		
		HOADONDICHVU_BLL.updateBillService(maHD,totalPrice);
		
		for(Object[] item : listData) {
			String serviceId = DICHVU_BLL.getServiceIdByName(item[1].toString());
			Map<String, Object> billDetailService = new HashMap<String, Object>();
			billDetailService.put("maHD", maHD);
			billDetailService.put("maDV", serviceId);			
			billDetailService.put("soLuong", Integer.parseInt(item[3].toString()));
			billDetailService.put("tongTien", Integer.parseInt(item[4].toString()));
			boolean isExist = CHITIETHOADON_BLL.isBillDetailServiceExist(maHD, serviceId);			
			if(isExist) {
				CHITIETHOADON_BLL.updateDetailBillService(billDetailService);
			}else {
				CHITIETHOADON_BLL.insertBillDetailService(billDetailService);					
			}				
		}
		
			
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

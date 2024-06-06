package UI.BookRoom;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.CHITIETPDP_BLL;
import BLL.HOADONPHONG_BLL;
import BLL.KHACHHANG_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.PHIEUDATPHONG_BLL;
import BLL.PHONG_BLL;
import DAO.PHONG_DAO;
import DTO.HOADONPHONG;
import DTO.KHACHHANG;
import DTO.LOAIPHONG;
import DTO.PHIEUDATPHONG;
import UI.MainWindow_Controller;
import UI.Resource.itemBookRoomDetail_Controller;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.SystemMessage;

public class bookRoom_Cotroller implements Initializable {

    @FXML
    private Button addDetail_btn;
    
	@FXML
    private TextField cccd_txt;

    @FXML
    private VBox detailBookRoom_vbox;

    @FXML
    private TextField price_txt;
    
    @FXML
    private DatePicker checkin_datepicker;

    @FXML
    private DatePicker checkout_datepicker;
    
    @FXML
    private TextField customerName_txt;

    @FXML
    private ComboBox<Integer> quantity_cb;
    
    @FXML
    private Label numberOfDay_txt;

    @FXML
    private Button close_btn;
    
    @FXML
    private Label roomPrice_txt;
    
    @FXML
    private Label totalPrice_txt;

    @FXML
    private ComboBox<String> roomType_cb;
    
    private String MANV = SystemMessage.getMANV();
    
    private List<LOAIPHONG> roomTypes = LOAIPHONG_BLL.getRoomTypes();
    
    public void showRoomType() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for(LOAIPHONG loai : roomTypes) {
            list.add(loai.getTENLOAI());
        }
        roomType_cb.setItems(list);
        
        roomType_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String selectedValue = newVal.toString();
                for(LOAIPHONG loai : roomTypes) {
                    if(loai.getTENLOAI().equals(selectedValue)) {
                        price_txt.setText(String.valueOf(loai.getGIA()));
//                        Double gia = Double.parseDouble(String.valueOf(loai.getGIA()));
//                    	String formattedPrice = String.format("%.0f", gia);
//                    	StringBuilder sb = new StringBuilder(formattedPrice);
//                    	int length = sb.length();
//                    	for (int i = length - 3; i > 0; i -= 3) {
//                    	    sb.insert(i, ".");
//                    	}
//                    	//sb2.append(" VND");
//                    	String finalPrice = sb.toString();
//                    	price_txt.setText(finalPrice);
                    	
                        showQuantity(loai.getMALOAI());
                        break;
                    }
                }
            }
        });
    }
    
    
    private KHACHHANG khachHang;
    public void searchCustomer() {
    	cccd_txt.textProperty().addListener((observable, oldValue, newValue) -> {
	    	
			try {
				khachHang = searchCustomer(newValue);
				
				if(khachHang != null) {
		    		customerName_txt.setText(khachHang.getTENKH());
		    		
		    	} else {
		    		customerName_txt.setText("");
		    	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
    	});
	}
    
    private KHACHHANG searchCustomer(String cccd) throws SQLException {
    	return KHACHHANG_BLL.getCustomerByCCCD(cccd);
    }
    
    
    public void showQuantity(int roomTypeId) {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            LocalDate checkinDate = checkin_datepicker.getValue();
            LocalDate checkoutDate = checkout_datepicker.getValue();
            int availableRooms = PHONG_BLL.getAvailableRooms(roomTypeId, checkinDate, checkoutDate);
            for (int i = 1; i <= availableRooms; i++) {
                list.add(i);
            }
            quantity_cb.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	private ObservableList<Object[]> listDetailBookRoom = FXCollections.observableArrayList();
    
	public void addDetailBill() {
	    String selectedRoomType = roomType_cb.getValue().toString();
	    double selectedPrice = Double.parseDouble(price_txt.getText());
	    int selectedQuantity = Integer.parseInt(quantity_cb.getValue().toString());
	    double selectedTotal = selectedPrice * selectedQuantity;
	    long numberOfDays = ChronoUnit.DAYS.between(checkin_datepicker.getValue(), checkout_datepicker.getValue()) + 1;
	    	    
	    
	    Object[] rowdata = new Object[5];
	    rowdata[0] = selectedRoomType;
	    rowdata[1] = selectedPrice;
	    rowdata[2] = selectedQuantity;	    
	    rowdata[3] = selectedTotal;
	    rowdata[4] = numberOfDays;
	    
	    
	    boolean isExists = listDetailBookRoom.stream()
	    		.anyMatch(row -> selectedRoomType.equals(row[0]));
	    if(isExists) {
	    	listDetailBookRoom.stream()
	    	.filter(row -> rowdata[0].equals(row[0]))
	    	.forEach(row -> {	    		
		    	row[2] = Integer.parseInt(row[2].toString()) + Integer.parseInt(rowdata[2].toString());
		    	row[3] = Double.parseDouble(row[3].toString()) + Double.parseDouble(rowdata[3].toString());
	    	});
	    } else {
	    	listDetailBookRoom.add(rowdata);
	    }
	    
	    detailBookRoom_vbox.getChildren().clear();

	    for (Object[] item : listDetailBookRoom) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBookRoomDetail.fxml"));
	            Parent roomData = loader.load();
	            itemBookRoomDetail_Controller controller = loader.getController();
	            controller.setData(item);
	            Button deletetItem_btn = controller.getDeleteItem_btn();
	            deletetItem_btn.setOnAction(e -> {
	                listDetailBookRoom.remove(item);
	                detailBookRoom_vbox.getChildren().remove(roomData);
	                showPrice();
	            });
	            detailBookRoom_vbox.getChildren().add(roomData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    showPrice();
	}	


    public void bookRoom() {
		Map<String, Object> data  = new HashMap<String, Object>();
		data.put("maKH", khachHang.getMAKH());
		data.put("ngayNhan", checkin_datepicker.getValue().toString());
		data.put("ngayTra", checkout_datepicker.getValue().toString());
		String totalPriceString = totalPrice_txt.getText();
		// 
		totalPriceString = totalPriceString.replace(",", ".");
		// Chuyển đổi chuỗi thành số
		double totalPrice = Double.parseDouble(totalPriceString);

		// Sử dụng giá trị totalPrice
		data.put("gia", totalPrice);
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		data.put("tgDat", formatter.format(now).toString());		
		
		PHIEUDATPHONG_BLL.insertBookRoom(data);
		String error = SystemMessage.ERROR_MESSAGE;
		AlertMessage alert = new AlertMessage();
		if(error.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
			SystemMessage.ERROR_MESSAGE = "";
		}else {
			alert.successMessage("Đặt phòng thành công");
			PHIEUDATPHONG lastBookRoom = PHIEUDATPHONG_BLL.getLastBookRoom();
			for(Object[] item : listDetailBookRoom) {
				Map<String, Object> dataDetailBookRoom = new HashMap<String, Object>();
				dataDetailBookRoom.put("maPDP",lastBookRoom.getMAPDP());
				dataDetailBookRoom.put("maLoaiPhong", LOAIPHONG_BLL.getRoomTypeId(item[0].toString()));
				dataDetailBookRoom.put("soLuong", item[2]);
				CHITIETPDP_BLL.insertDetailBookRoom(dataDetailBookRoom);
			}
			
			Map<String, Object> dataBillBookRoom = new HashMap<String, Object>();
			dataBillBookRoom.put("maPDP",lastBookRoom.getMAPDP());
			dataBillBookRoom.put("maNV", MANV);
			dataBillBookRoom.put("ngTao", formatter.format(now).toString());			
			dataBillBookRoom.put("tongTien", totalPrice);
			HOADONPHONG_BLL.insertBillBookRoom(dataBillBookRoom);			
			Stage stage = (Stage) close_btn.getScene().getWindow();
			stage.close();
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("billBookRoom.fxml"));
			Parent root;
			try {
	
				root = loader.load();
				String checkin = checkin_datepicker.getValue().toString();
				String checkout = checkout_datepicker.getValue().toString();
				HOADONPHONG lastBillBookRoom = HOADONPHONG_BLL.getLastBill();
				
				billBookRoom_Controller controller = loader.getController();
				
				controller.setData(checkin, checkout, lastBillBookRoom, listDetailBookRoom);
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
				stage.show();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
    
    private void showPrice() { 	   	
    	long numberOfDays = ChronoUnit.DAYS.between(checkin_datepicker.getValue(), checkout_datepicker.getValue()) + 1;			
	    double roomPrice = 0;
	    for(Object[] item : listDetailBookRoom) {	    	
		    roomPrice += Double.parseDouble(item[3].toString());
	    }	    	   
		double totalPrice = roomPrice * numberOfDays;
		String formattedTotalPrice = String.format("%.2f", totalPrice); 
		totalPrice_txt.setText(formattedTotalPrice);
	}
	
    public void close() {
    	Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = formatter.format(now);
		LocalDate date = LocalDate.parse(formattedDate);
		checkin_datepicker.setValue(date);
		showRoomType();				
		searchCustomer();		
			
	}
}

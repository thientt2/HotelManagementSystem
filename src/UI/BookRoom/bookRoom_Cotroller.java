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
import DTO.HOADONPHONG;
import DTO.KHACHHANG;
import DTO.LOAIPHONG;
import DTO.PHIEUDATPHONG;
import UI.Resource.itemBookRoomDetail_Controller;
import application.AlertMessage;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    private TextField checkin_datepicker;

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
    
    private boolean dateChanged = false;
    
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
                        //price_txt.setText(String.valueOf(loai.getGIA()));
                        Double gia = Double.parseDouble(String.valueOf(loai.getGIA()));
                    	String formattedPrice = String.format("%.0f", gia);
                    	StringBuilder sb = new StringBuilder(formattedPrice);
                    	int length = sb.length();
                    	for (int i = length - 3; i > 0; i -= 3) {
                    	    sb.insert(i, ".");
                    	}
                    	//sb2.append(" VND");
                    	String finalPrice = sb.toString();
                    	price_txt.setText(finalPrice);
                    	
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
        //quantity_cb.getItems().clear();
        try {
            String checkin = checkin_datepicker.getText();
            DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate checkinDateInput = LocalDate.parse(checkin, formatterInput);
			LocalDate checkinDate = LocalDate.parse(checkinDateInput.format(formatterOutput), formatterOutput);
			
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
	    int selectedPrice = Integer.parseInt(price_txt.getText().replace(".", ""));
	    int selectedQuantity = Integer.parseInt(quantity_cb.getValue().toString());	
	    
        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate checkinDateInput = LocalDate.parse(checkin_datepicker.getText(), formatterInput);
		LocalDate checkinDate = LocalDate.parse(checkinDateInput.format(formatterOutput), formatterOutput);
	    long numberOfDays = ChronoUnit.DAYS.between(checkinDate, checkout_datepicker.getValue());
	    int selectedTotal = selectedPrice * selectedQuantity * (int)numberOfDays;
	    
	    Object[] rowdata = new Object[5];
	    rowdata[0] = selectedRoomType;
	    rowdata[1] = selectedPrice;
	    rowdata[2] = selectedQuantity;
	    rowdata[3] = numberOfDays;
	    rowdata[4] = selectedTotal;	    
	    
	    boolean isExists = listDetailBookRoom.stream()
	    		.anyMatch(row -> selectedRoomType.equals(row[0]));
	    if(isExists) {
	    	listDetailBookRoom.stream()
	    	.filter(row -> rowdata[0].equals(row[0]))
	    	.forEach(row -> {	    		
		    	row[2] = Integer.parseInt(row[2].toString()) + Integer.parseInt(rowdata[2].toString());
		    	row[3] = Integer.parseInt(row[3].toString()) + Integer.parseInt(rowdata[3].toString());
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
	    
	    roomType_cb.getSelectionModel().clearSelection();
	    quantity_cb.getSelectionModel().clearSelection();
	    quantity_cb.getItems().clear();
	    
	    roomType_cb.setPromptText("Chọn	loại phòng");
	    quantity_cb.setPromptText("0");

	    // Đặt lại giá trị cho TextField price_txt
	    price_txt.setText(null);
	    price_txt.setPromptText("Giá");
	}	


    public void bookRoom() {
		Map<String, Object> data  = new HashMap<String, Object>();
		data.put("maKH", khachHang.getMAKH());
		
		data.put("ngayTra", checkout_datepicker.getValue().toString());		
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatterInputBook = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate checkinDateInput = LocalDate.parse(checkin_datepicker.getText(), formatterInputBook);
		String checkinBook = formatter.format(checkinDateInput);
		data.put("tgDat", formatter.format(now).toString());	
		
		data.put("ngayNhan", checkinBook);
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
			
			HOADONPHONG_BLL.insertBillBookRoom(dataBillBookRoom);			
			Stage stage = (Stage) close_btn.getScene().getWindow();
			stage.close();
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("billBookRoom.fxml"));
			Parent root;
			try {
			    root = loader.load();
			    String checkin = checkin_datepicker.getText();			    
		        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    
			    
			    // Get the checkout date from DatePicker and parse it using the correct format
			    String checkout = checkout_datepicker.getValue().toString();
			    LocalDate checkoutDate = LocalDate.parse(checkout, formatterInput);
			    String formattedCheckoutDate = checkoutDate.format(formatterOutput);
			    
			    

			    HOADONPHONG lastBillBookRoom = HOADONPHONG_BLL.getLastBill();
			    billBookRoom_Controller controller = loader.getController();
			    
			    // Set the default image in the circle
			    Circle image = controller.getCircle();
			    Image defaultImage = new Image("/Images/LAOPERA.jpg");
			    image.setFill(new ImagePattern(defaultImage));
			    
			    // Set the data for the controller
			    controller.setData(checkin, formattedCheckoutDate, lastBillBookRoom, listDetailBookRoom);
			    
			    Scene scene = new Scene(root);
			    stage.setScene(scene);
			    stage.show();
			    
			} catch (IOException e) {
			    e.printStackTrace();
			}

		}		
	}
    
    private void showPrice() {      	
    				
	    double totalPrice = 0;
	    for(Object[] item : listDetailBookRoom) {	    	
	    	totalPrice += Double.parseDouble(item[3].toString());
	    }   	   

//		String formattedTotalPrice = String.format("%.2f", totalPrice); 
		
		String formattedPrice = String.format("%.0f", totalPrice);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	//sb2.append(" VND");
    	String finalPrice = sb.toString();
		totalPrice_txt.setText(finalPrice);
	}
	
    public void close() {
    	Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = formatter.format(now);
		
		checkin_datepicker.setText(formattedDate);
		showRoomType();				
		searchCustomer();		
		checkout_datepicker.valueProperty().addListener((observable, oldValue, newValue) -> {
			if(dateChanged) {
				listDetailBookRoom.clear();
				detailBookRoom_vbox.getChildren().clear();

				// Xóa lựa chọn hiện tại trong ComboBox roomType_cb và quantity_cb
				roomType_cb.getSelectionModel().clearSelection();
				quantity_cb.getSelectionModel().clearSelection();

				// Đặt lại giá trị cho TextField price_txt
				price_txt.setText(null);
				price_txt.setPromptText("Giá");
				
				Platform.runLater(() -> {
				    roomType_cb.setPromptText("Chọn	loại phòng");
				    quantity_cb.setPromptText("0");
				    quantity_cb.getItems().clear();
				});
			}else {
				dateChanged = true;
			}		
			
		});
			
	}
}

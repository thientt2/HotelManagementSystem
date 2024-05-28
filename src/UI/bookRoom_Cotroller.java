package UI;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.CHITIETPDP_BLL;
import BLL.KHACHHANG_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.PHIEUDATPHONG_BLL;
import DAO.LOAIPHONG_DAO;
import DTO.KHACHHANG;
import DTO.LOAIPHONG;
import DTO.PHIEUDATPHONG;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import system.SystemMessage;

public class bookRoom_Cotroller implements Initializable {

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
    
    private List<LOAIPHONG> roomTypes = LOAIPHONG_BLL.getRoomTypes();
    
    public void showRoomType() {
    	ObservableList<String> list = FXCollections.observableArrayList();
		for(LOAIPHONG loai : roomTypes) {
			list.add(loai.getTENLOAI());
		}
		roomType_cb.setItems(list);
		
		if(roomType_cb.getValue() != null) {
			String selectedValue = roomType_cb.getValue().toString();
			for(LOAIPHONG loai : roomTypes) {
				if(loai.getTENLOAI().equals(selectedValue)) {
					price_txt.setText(String.valueOf(loai.getGIA()));
					break;
				}
			}
		}
    }
    
    
    private KHACHHANG khachHang;
    public void searchCustomer() {
    	cccd_txt.textProperty().addListener((observable, oldValue, newValue) -> {
	    	
			try {
				khachHang = searchCustomer(newValue);
				
				if(khachHang != null) {
		    		customerName_txt.setText(khachHang.getTENKH());
		    		System.out.println(khachHang.getTENKH());
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
		
    
	public void showQuantity() {
		ObservableList<Integer> list = FXCollections.observableArrayList();
		for(int i = 1; i <= 10; i++) {
			list.add(i);
		}
		quantity_cb.setItems(list);
	}
    
	public void addDetailBill() {
	    String selectedRoomType = roomType_cb.getValue().toString();
	    double selectedPrice = Double.parseDouble(price_txt.getText());
	    int selectedQuantity = Integer.parseInt(quantity_cb.getValue().toString());
	    double selectedTotal = selectedPrice * selectedQuantity;
	    
	    boolean roomTypeExists = false;
	    
	    for (Node node : detailBookRoom_vbox.getChildren()) {
	        if (node instanceof HBox) {
	            HBox hBox = (HBox) node;
	            Label roomTypeLabel = (Label) hBox.getChildren().get(0); // Lấy Label đầu tiên trong HBox
	            if (roomTypeLabel.getText().equals(selectedRoomType)) {
	                // Nếu roomType đã tồn tại trong VBox, tăng số lượng và cập nhật giá trị
	                Label quantityLabel = (Label) hBox.getChildren().get(2); // Lấy Label thứ 3 trong HBox
	                int currentQuantity = Integer.parseInt(quantityLabel.getText());
	                quantityLabel.setText(String.valueOf(currentQuantity + selectedQuantity));

	                Label totalLabel = (Label) hBox.getChildren().get(3); // Lấy Label thứ 4 trong HBox
	                double currentTotal = Double.parseDouble(totalLabel.getText());
	                totalLabel.setText(String.valueOf(currentTotal + selectedTotal));

	                // Cập nhật tổng giá trị
	                double currentTotalPrice = Double.parseDouble(roomPrice_txt.getText());
	                roomPrice_txt.setText(String.valueOf(currentTotalPrice + selectedTotal));

	                roomTypeExists = true;
	                break;
	            }
	        }
	       
	    }
	    
	    if (!roomTypeExists) {
	        // Nếu roomType chưa tồn tại trong VBox, thêm một HBox mới
	        HBox hBox = new HBox();
	        hBox.setSpacing(20);
	        hBox.setAlignment(Pos.CENTER);
	        
	        Label roomType = new Label(selectedRoomType);
	        roomType.setPrefWidth(86);
	        roomType.setFont(Font.font(13));

	        Label price = new Label(String.valueOf(selectedPrice));
	        price.setPrefWidth(72);
	        price.setFont(Font.font(13));

	        Label quantity = new Label(String.valueOf(selectedQuantity));
	        quantity.setPrefWidth(65);
	        quantity.setFont(Font.font(13));

	        Label total = new Label(String.valueOf(selectedTotal));
	        total.setPrefWidth(88);
	        total.setFont(Font.font(13));

	        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/recycle.png")));
	        imageView.setFitWidth(13);
	        imageView.setFitHeight(13);
	        
	        Button button = new Button();
	        button.setStyle("-fx-background-color: transparent;");
	        button.setGraphic(imageView);
	        
	        button.setOnAction(e -> {
	            double currentTotalPrice = Double.parseDouble(roomPrice_txt.getText());
	            currentTotalPrice -= selectedTotal;
	            totalPrice_txt.setText(String.valueOf(currentTotalPrice));
	            
	            detailBookRoom_vbox.getChildren().remove(hBox);
	        });
	        
	        hBox.getChildren().addAll(roomType, price, quantity, total, button);
	        detailBookRoom_vbox.getChildren().add(hBox);
	        
	        // Cập nhật tổng giá trị
	        double currentTotalPrice = Double.parseDouble(roomPrice_txt.getText());
	        roomPrice_txt.setText(String.valueOf(currentTotalPrice + selectedTotal));
	    }  
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
		
		System.out.println(data.get("tgDat"));
		PHIEUDATPHONG_BLL.insertBookRoom(data);
		String error = SystemMessage.ERROR_MESSAGE;
		AlertMessage alert = new AlertMessage();
		if(error.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
		}else {
			alert.successMessage("Đặt phòng thành công");
			PHIEUDATPHONG lastBookRoom = PHIEUDATPHONG_BLL.getLastBookRoom();
			for(Node node : detailBookRoom_vbox.getChildren()) {
				if(node instanceof HBox) {
					HBox hBox = (HBox) node;
					Label roomTypeLabel = (Label) hBox.getChildren().get(0);
					int roomTypeId = LOAIPHONG_DAO.getRoomTypeId(roomTypeLabel.getText());
					Label quantityLabel = (Label) hBox.getChildren().get(2);
					int quantity = Integer.parseInt(quantityLabel.getText());
					Map<String, Object> dataDetailBookRoom = new HashMap<String, Object>();
					dataDetailBookRoom.put("maPDP",lastBookRoom.getMAPDP());
					dataDetailBookRoom.put("maLoaiPhong", roomTypeId);
					dataDetailBookRoom.put("soLuong", quantity);
					CHITIETPDP_BLL.insertDetailBookRoom(dataDetailBookRoom);
				}		
			}
		}		
	}
    
    private void showPrice() {
    	checkout_datepicker.valueProperty().addListener((observable, oldValue, newValue) -> {
			numberOfDay_txt.setText(String.valueOf(checkout_datepicker.getValue().getDayOfMonth() - checkin_datepicker.getValue().getDayOfMonth()));
	    	double roomPrice = Double.parseDouble(roomPrice_txt.getText());
			int numberOfDays = Integer.parseInt(numberOfDay_txt.getText());
			double totalPrice = roomPrice * numberOfDays;
			String formattedTotalPrice = String.format("%.2f", totalPrice); 
			totalPrice_txt.setText(formattedTotalPrice);
		});

    }
	
    public void close() {
    	Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showRoomType();			
		showQuantity();		
		searchCustomer();	
		showPrice();
		
	}

	

}

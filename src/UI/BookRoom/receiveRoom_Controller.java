package UI.BookRoom;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import BLL.CHITIETPNP_BLL;
import BLL.KHACHHANG_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import BLL.PHONG_BLL;
import DTO.KHACHHANG;
import UI.MainWindow_Controller;
import UI.Resource.itemReceiveRoom_Controller;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import system.SystemMessage;

public class receiveRoom_Controller implements Initializable {
	
	@FXML
    private Button addDetail_btn;

    @FXML
    private TextField bookRoomId_txt;

    @FXML
    private TextField cccd_txt;

    @FXML
    private Button close_btn;

    @FXML
    private Button confirmBookRoom_btn;

    @FXML
    private VBox detailReceiveRoom_vbox;

    @FXML
    private ComboBox<String> roomNumber_cb;

    @FXML
    private AnchorPane mainAnchor;
    
    @FXML
    private TextField roomType_txt;
    
    
    private String checkout;
    
    public void setData(Object[] item) {
    	bookRoomId_txt.setText(item[0].toString());
    	roomType_txt.setText(item[2].toString());    	
    	checkout = item[4].toString();
    	showRoomNumber();   	
	}  
    
    
    public void showRoomNumber() {
    	roomNumber_cb.getItems().clear();         
        String selectedRoomType = roomType_txt.getText();
      
        ObservableList<String> roomNumbers = FXCollections.observableArrayList();
        try {
            roomNumbers = PHONG_BLL.getRoomNumbersByTypeAndStatus(selectedRoomType, 1);             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomNumber_cb.setItems(roomNumbers);
    }
    
    
    private KHACHHANG khachHang;
    public void searchCustomer() {
		try {
			khachHang = searchCustomer(cccd_txt.getText());
			
			if(khachHang != null) {
				addCustomer(khachHang);
				AlertMessage alert = new AlertMessage();
	            alert.successMessage("Thêm khách hàng thành công");
	            cccd_txt.setText("");
	    	} else {
	    		AlertMessage alert = new AlertMessage();
	            alert.successMessage("Không tìm thấy khách hàng! Vui lòng kiểm tra lại");
	            cccd_txt.setText("");
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private KHACHHANG searchCustomer(String cccd) throws SQLException {
    	return KHACHHANG_BLL.getCustomerByCCCD(cccd);
    }
		
    
	private ObservableList<Object[]> listDetailReceiveRoom = FXCollections.observableArrayList();
	private List<String> listOrtherCustomer = new ArrayList<String>();

    
	public void addCustomer(KHACHHANG kh) {
	    String name = kh.getTENKH();
	    String birthday = kh.getNGAYSINH();
	    
	    Object[] rowdata = new Object[2];
	    rowdata[0] = name;
	    rowdata[1] = birthday;
	    
	    listOrtherCustomer.add(kh.getMAKH());
	    System.out.println(listOrtherCustomer);
	      
	    listDetailReceiveRoom.add(rowdata);
	    
	    detailReceiveRoom_vbox.getChildren().clear();

	    for (Object[] item : listDetailReceiveRoom) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemReceiveRoom.fxml"));
	            Parent roomData = loader.load();
	            itemReceiveRoom_Controller controller = loader.getController();
	            controller.setData(item);
	            Button deletetItem_btn = controller.getDeleteItem_btn();
	            deletetItem_btn.setOnAction(e -> {
	            	listDetailReceiveRoom.remove(item);
	            	listOrtherCustomer.remove(kh.getMAKH());
	            	detailReceiveRoom_vbox.getChildren().remove(roomData);
	            });
	            detailReceiveRoom_vbox.getChildren().add(roomData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}	
	
	


    public void receiveRoom() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String checkin = formatter.format(now);		
		
		Map<String, Object> data  = new HashMap<String, Object>();
		data.put("ngayNhan", checkin);
		data.put("ngayTra", checkout);
		data.put("maPDP", bookRoomId_txt.getText());
		data.put("maPhong", roomNumber_cb.getValue());
		
		String error = SystemMessage.ERROR_MESSAGE;
		AlertMessage alert = new AlertMessage();
		PHIEUNHANPHONG_BLL.insertReceiveRoom(data);
		if(error.equals("ERROR_EMPTY")) {
			alert.errorMessage("Vui lòng nhập đầy đủ thông tin!");
			SystemMessage.ERROR_MESSAGE = "";
		}else {			
			PHONG_BLL.changeInUseRoomStatus(roomNumber_cb.getValue());
			String maPNP = PHIEUNHANPHONG_BLL.getLastReceiveRoom();
			System.out.println(maPNP);
			System.out.println(listOrtherCustomer.toString());
			CHITIETPNP_BLL.insertDetailRecieveRoom(maPNP, listOrtherCustomer);
			alert.successMessage("Nhận phòng " + roomNumber_cb.getValue() + " thành công!");
			
			Stage stage = (Stage) confirmBookRoom_btn.getScene().getWindow();
	    	stage.close();
		}
		
    	
	}
    
	
    public void close() {
    	Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.BLACK);

        // Áp dụng hiệu ứng bóng mờ cho AnchorPane
        mainAnchor.setEffect(dropShadow);

	}

	

}
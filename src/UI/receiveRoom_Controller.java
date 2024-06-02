package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BLL.CHITIETPDP_BLL;
import BLL.KHACHHANG_BLL;
import BLL.LOAINHANVIEN_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.PHONG_BLL;
import DTO.KHACHHANG;
import DTO.LOAIPHONG;
import DTO.PHIEUNHANPHONG;
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
import javafx.scene.layout.VBox;
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
    private TextField roomType_txt;
    
    private MainWindow_Controller mainWindowController;
    
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
    public MainWindow_Controller getMainWindowController() {
		return mainWindowController;
	}
    
    public void setData(Object[] item) {
    	bookRoomId_txt.setText(item[0].toString());
    	roomType_txt.setText(item[2].toString());
	}  
    
    
    public void showRoomNumber() {
    	roomNumber_cb.getItems().clear(); 
        
        String selectedRoomType = roomType_txt.getText();
        List<String> roomNumbers = new ArrayList<>();
        try {
            roomNumbers = PHONG_BLL.getRoomNumbersByTypeAndStatus(selectedRoomType, 1); 
            roomNumbers.addAll(PHONG_BLL.getRoomNumbersByTypeAndStatus(selectedRoomType, 2)); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomNumber_cb.getItems().addAll(roomNumbers);
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
    
	public void addCustomer(KHACHHANG kh) {
	    String name = kh.getTENKH();
	    String birthday = kh.getNGAYSINH();
	    
	    Object[] rowdata = new Object[2];
	    rowdata[0] = name;
	    rowdata[1] = birthday;
	      
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
	            	detailReceiveRoom_vbox.getChildren().remove(roomData);
	            });
	            detailReceiveRoom_vbox.getChildren().add(roomData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}	


    public void receiveRoom() {
	
	}
    
	
    public void close() {
    	Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showRoomNumber();					
		
	}

	

}
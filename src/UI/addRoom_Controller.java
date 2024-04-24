package UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import BLL.LOAIPHONG_BLL;
import BLL.PHONG_BLL;
import BLL.TRANGTHAIPHONG_BLL;
import DTO.LOAIPHONG;
import DTO.TRANGTHAIPHONG;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import system.SystemMessage;

public class addRoom_Controller implements Initializable {
	
    @FXML
    private ComboBox<String> showRoomTypes_cb;
    
    @FXML
    private AnchorPane addForm;

    @FXML
    private Label limitPersonTxt;

    @FXML
    private Label priceTxt;   

    @FXML
    private TextField soPhongTxt;
    
    @FXML
    private ComboBox<String> showStatusRoom_cb;
    
    @FXML
    private Button closeAddRoom_btn;
    
    @FXML
    private Button minimizeAddRoom_btn;

    private List<LOAIPHONG> loaiPhong = LOAIPHONG_BLL.getRoomTypes();
	
	public void showRoomType() {
		
		List<String> listRoomType = new ArrayList<>();		
		
		for(LOAIPHONG loai : loaiPhong ) {
			listRoomType.add(loai.getTENLOAI());			
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(listRoomType);
		if (showRoomTypes_cb != null) {
			showRoomTypes_cb.setItems(list);			
		} else {
		    return;
		}			
		if(showRoomTypes_cb.getValue() != null) {
		    String selectedValue = showRoomTypes_cb.getValue().toString();		    
		    for(LOAIPHONG loai : loaiPhong ) {
		    	if (loai.getTENLOAI().equals(selectedValue)) {
		    	    limitPersonTxt.setText(String.valueOf(loai.getGIA()));
		    	    priceTxt.setText(String.valueOf(loai.getNGUOITOIDA()));
		    	}

		    }
		    
		} else {
		    return;
		}
		
	}
	

	private List<TRANGTHAIPHONG> listStatusRoom = TRANGTHAIPHONG_BLL.getStatusRoom();
	public void showStatusRoom() {
		List<String> listStatus = new ArrayList<>();		
		
		for(TRANGTHAIPHONG status : listStatusRoom ) {
			listStatus.add(status.getTENTRANGTHAI());	
			
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(listStatus);
		if (showStatusRoom_cb != null) {
			showStatusRoom_cb.setItems(list);
		} else {
		    return;
		}			
		
	}
	
	private AlertMessage alert = new AlertMessage();   
	
	
	
	public void addRoom() throws SQLException {
		Map<String, String> data  = new HashMap<String, String>();
		data.put("maphong", soPhongTxt.getText());
		Optional<String> maloai = loaiPhong.stream()
				.filter(loai -> loai.getTENLOAI().equals(showRoomTypes_cb.getValue().toString()))
				.map(loai -> String.valueOf(loai.getMALOAI()))
		        .findFirst();
		data.put("maloai", maloai.get());

		Optional<String> matrangthai = listStatusRoom.stream()
				.filter(loai -> loai.getTENTRANGTHAI().equals(showStatusRoom_cb.getValue().toString()))
				.map(loai -> String.valueOf(loai.getMATRANGTHAI()))
		        .findFirst();
		data.put("matrangthai", matrangthai.get());
		PHONG_BLL.addRoom(data);
		String error = SystemMessage.ERROR_MESSAGE;
		if(error.equals("ERROR_1")) {
			alert.errorMessage("Vui lòng nhập đầy đủ thông tin!");			
		}else if(error.equals("ERROR_2")) {
			alert.errorMessage("Thêm phòng vào cơ sở dữ liệu không thành công!");			
		} else if (error.equals("ERROR_3")){
			alert.errorMessage("Phòng đã tồn tại trong cơ sở dữ liệu.");
		}else
			{
				alert.successMessage("Thêm phòng thành công!");						
				clearRoom();				
			}
		
	}
	
	
    public void clearRoom() {
    	soPhongTxt.setText("");
    	showRoomTypes_cb.getSelectionModel().clearSelection();
    	showStatusRoom_cb.getSelectionModel().clearSelection();
    	limitPersonTxt.setText("");
    	priceTxt.setText("");    	
    }
    
    public void close() {
    	closeAddRoom_btn.getScene().getWindow().hide();
    	
    }
	
    public void minimize() {
    	((Stage) addForm.getScene().getWindow()).setIconified(true);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showRoomType();
		showStatusRoom();
		
	}

}

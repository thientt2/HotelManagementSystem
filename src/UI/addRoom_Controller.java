package UI;

import java.net.URL;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addRoom_Controller implements Initializable {
	
    @FXML
    private ComboBox<String> showRoomTypes_cb;
    

    @FXML
    private Label limitPersonTxt;

    @FXML
    private Label priceTxt;
    

    @FXML
    private TextField soPhongTxt;
    
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
	
    @FXML
    private ComboBox<String> showStatusRoom_cb;
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
	
	public void addRoom() {
		Map<String, String> data  = new HashMap<String, String>();
		data.put("sophong", soPhongTxt.getText());
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
	    
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showRoomType();
		showStatusRoom();
	}

}

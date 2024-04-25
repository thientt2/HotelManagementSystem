package UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import BLL.LOAIPHONG_BLL;
import BLL.PHONG_BLL;
import DTO.LOAIPHONG;
import DTO.PHONG;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import system.SystemMessage;

public class editRoom_Controller implements Initializable {
	
    @FXML
    private TextField giaTxt;

    @FXML
    private TextField loaiGiuongTxt;
    
    @FXML
    private TextField soNguoiToiDaTxt;

    @FXML
    private ComboBox<String> showRoomTypes_cb;
    
    @FXML
    private Label maPhongTxt;
    
    @FXML
    private AnchorPane edit_form;

    @FXML
    private Button editRoom_btn;
    
    
    
    public void initData(String obj) {
	    PHONG phong = PHONG_BLL.layPhong(obj);
	    maPhongTxt.setText(phong.getMAPHONG());
	    
	    showRoomType();
    }  	
    	
    		
	    	
    


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
		showRoomTypes_cb.setOnAction(event -> {
            String selectedValue = showRoomTypes_cb.getValue();
            for (LOAIPHONG loai : loaiPhong) {
                if (loai.getTENLOAI().equals(selectedValue)) {
                    loaiGiuongTxt.setText(String.valueOf(loai.getLOAIGIUONG()));
                    soNguoiToiDaTxt.setText(String.valueOf(loai.getNGUOITOIDA()));
                    giaTxt.setText(String.valueOf(loai.getGIA()));
                    break; // Exit loop once found the matching room type
                }
            }
        });
		
    }
    
    
    private AlertMessage alert = new AlertMessage();
    
    public void editRoom() {
		Map<String, String> data  = new HashMap<String, String>();
		data.put("maphong", maPhongTxt.getText());
		Optional<String> maloai = loaiPhong.stream()
				.filter(loai -> loai.getTENLOAI().equals(showRoomTypes_cb.getValue().toString()))
				.map(loai -> String.valueOf(loai.getMALOAI()))
		        .findFirst();
		data.put("maloai", maloai.get());
		PHONG_BLL.editRoom(data);
		if(SystemMessage.ERROR_MESSAGE.equals("ERROR_1")) {
			alert.errorMessage("Vui lòng điền đầy đủ thông tin!");
		}else {
			alert.successMessage("Cập nhật thông tin phòng thành công!");
		}
    }
    
    public void close() {
    	editRoom_btn.getScene().getWindow().hide();
    }
    
    public void minimize() {
		((Stage) edit_form.getScene().getWindow()).setIconified(true);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showRoomType();
		System.out.println(maPhongTxt.getText());
		
	}

}

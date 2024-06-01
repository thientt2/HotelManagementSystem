package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.NHANVIEN_BLL;
import DTO.HOADONPHONG;
import UI.Resource.itemBillBookRoom_Controller;
import UI.Resource.itemBookRoomDetail_Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class billBookRoom_Controller implements Initializable {

	@FXML
    private Label billId_txt;

    @FXML
    private Button cancel_btn;

    @FXML
    private Label checkinDate_txt;

    @FXML
    private Label checkoutDate_txt;

    @FXML
    private VBox detail_vbox;

    @FXML
    private Label midPrice_txt;

    @FXML
    private Button print_btn;

    @FXML
    private Label staffName_txt;

    @FXML
    private Label surcharge_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(String checkin,String checkout,HOADONPHONG hoadonphong, ObservableList<Object[]> list) {
    	checkinDate_txt.setText(checkin);
		checkoutDate_txt.setText(checkout);
		midPrice_txt.setText(hoadonphong.getTONGTIEN().toString());
		surcharge_txt.setText(hoadonphong.getGIAMGIA().toString());
		int total = (int) (hoadonphong.getTONGTIEN() - hoadonphong.getGIAMGIA());
		totalPrice_txt.setText(String.valueOf(total));
		billId_txt.setText(hoadonphong.getMAHDP().toString());
		staffName_txt.setText(NHANVIEN_BLL.getStaffById(hoadonphong.getNVNHAP()).getTENNV());
		
		for(Object[] item : list) {
			try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBillBookRoom.fxml"));
	            Parent roomData = loader.load();
	            itemBillBookRoom_Controller controller = loader.getController();	            
	            controller.setData(item);	                     
	            detail_vbox.getChildren().add(roomData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

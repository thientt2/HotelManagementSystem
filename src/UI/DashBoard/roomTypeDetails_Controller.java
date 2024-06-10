package UI.DashBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.PHIEUDATPHONG_BLL;
import UI.Resource.itemBookRoom_Controller;
import UI.Resource.itemRoomTypeDetails_Controller;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class roomTypeDetails_Controller implements Initializable{
	@FXML
    private Button cancel_btn;

    @FXML
    private VBox detailTypeRoom_vbox;

    @FXML
    private Label typeName_txt;
    

    public void setRoomType(int id) throws IOException {
    	ObservableList<Object[]> listBookRoom = PHIEUDATPHONG_BLL.showBookRoom(id);
    	for(Object[] item : listBookRoom) {
    		typeName_txt.setText(item[7].toString());
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoomTypeDetails.fxml"));
            Parent bookRoomDataPane = loader.load();
            itemRoomTypeDetails_Controller controller = loader.getController();
            controller.setBookRoom(item);
            Platform.runLater(() -> detailTypeRoom_vbox.getChildren().add(bookRoomDataPane));
    	}
	}  
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}

package UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class bookRoomWindow_Controller implements Initializable{
	

    @FXML
    private Button bookRoom_btn;
	
	private double x = 0;
	private double y = 0;
	
	public void bookRoom() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("bookRoom.fxml"));
            Parent root = loader.load();
            
            root.setOnMousePressed((MouseEvent event) -> {            
                x = event.getSceneX();            
                y = event.getSceneY();    
            });
            
            Stage stage = new Stage();        
            stage.initStyle(StageStyle.TRANSPARENT);        
            Scene scene = new Scene(root);
            
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);            
                stage.setY(event.getScreenY() - y);       
            });
            bookRoom_btn.setDisable(true);

            stage.setOnHidden(e -> bookRoom_btn.setDisable(false));
			stage.initOwner(bookRoom_btn.getScene().getWindow());
            stage.setScene(scene);     
            stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

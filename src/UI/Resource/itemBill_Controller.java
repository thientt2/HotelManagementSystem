package UI.Resource;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import UI.Bill.billService_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class itemBill_Controller implements Initializable{
	
    @FXML
    private Label billId_txt;

    @FXML
    private Label checkinDate_txt;
    
    @FXML
	private Label checkoutDate_txt;

    @FXML
    private Button control_btn;

    @FXML
    private Button pay_btn;

    @FXML
    private Label priceService_txt;

    @FXML
    private Label room_txt;

    @FXML
    private Label surcharge_txt;
    
    public void setData(Object[] item) {
		billId_txt.setText(item[0].toString());		
		room_txt.setText(item[1].toString());
		priceService_txt.setText(item[2].toString());
		surcharge_txt.setText(item[3].toString());
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime checkindate = LocalDateTime.parse(item[4].toString(), inputFormatter);
		LocalDateTime checkoutdate = LocalDateTime.parse(item[5].toString(), inputFormatter);
		String checkin = checkindate.format(outputFormatter);
		String checkout = checkoutdate.format(outputFormatter);		
		
		checkinDate_txt.setText(checkin.toString());		
		checkoutDate_txt.setText(checkout.toString());
    }
    
    public void printBill() {
    	try {
			// TODO Auto-generated method stub
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Bill/billService.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(pay_btn.getScene().getWindow());
            Scene scene = new Scene(root);

			billService_Controller receiveRoom = loader.getController();
            Object[] item = new Object[6];
			item[0] = billId_txt.getText();
			item[1] = room_txt.getText();
			item[2] = priceService_txt.getText();
			item[3] = surcharge_txt.getText();
			item[4] = checkinDate_txt.getText();
			item[5] = checkoutDate_txt.getText();
			receiveRoom.setData(item);
			
			stage.setScene(scene);
            stage.showAndWait();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    public Button getPay_btn() {
		return pay_btn;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

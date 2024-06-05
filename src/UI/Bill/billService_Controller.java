package UI.Bill;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class billService_Controller implements Initializable {
	@FXML
    private Label billId_txt;

    @FXML
    private Button cancel_btn;

    @FXML
    private Label checkin_txt;

    @FXML
    private Label checkout_txt;

    @FXML
    private VBox detailSevice_vbox;

    @FXML
    private Button printBill_btn;

    @FXML
    private Label roomPrice_txt;

    @FXML
    private Label roomType_txt;

    @FXML
    private Label servicePrice_txt;

    @FXML
    private Label staffName_txt;

    @FXML
    private Label surcharge_txt;

    @FXML
    private Label totalPrice_txt;
    
    private ObservableList<Object[]> listData= FXCollections.observableArrayList();
    
    public void setData(Object[] item) {
    	
    }
    
    public void printBill() {
		
	}
    
    public void close() {
		Stage stage = (Stage) cancel_btn.getScene().getWindow();
		stage.close();		
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

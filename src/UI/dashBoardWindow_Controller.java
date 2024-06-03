package UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class dashBoardWindow_Controller implements Initializable{
	
	private MainWindow_Controller mainWindowController;

    // Phương thức để thiết lập tham chiếu của main window controller từ bên ngoài
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
    public MainWindow_Controller getMainWindowController() {
		return mainWindowController;
	}
    
    @FXML
    private Label areaLabel1;

    @FXML
    private Label areaLabel2;

    @FXML
    private Label areaLabel3;

    @FXML
    private Label areaLabel4;

    @FXML
    private Label availabelRoomLabel1;

    @FXML
    private Label availabelRoomLabel2;

    @FXML
    private Label availabelRoomLabel3;

    @FXML
    private Label availabelRoomLabel4;

    @FXML
    private Label contextMenu_btn1;

    @FXML
    private Label contextMenu_btn2;

    @FXML
    private Label contextMenu_btn3;

    @FXML
    private Label contextMenu_btn4;

    @FXML
    private Label maxPeopleLabel1;

    @FXML
    private Label maxPeopleLabel2;

    @FXML
    private Label maxPeopleLabel3;

    @FXML
    private Label maxPeopleLabel4;

    @FXML
    private Label moneyPerNightLabel1;

    @FXML
    private Label moneyPerNightLabel2;

    @FXML
    private Label moneyPerNightLabel3;

    @FXML
    private Label moneyPerNightLabel4;

    @FXML
    private Label numberBookedRoomLabel;

    @FXML
    private Label numberCheckInLabel;

    @FXML
    private Label numberCheckOutLabel;

    @FXML
    private Label numberCustomerLabel;

    @FXML
    private Label numberEmptyRoomLabel;

    @FXML
    private Label numberOfPaymentLabel1;

    @FXML
    private Label numberOfPaymentLabel2;

    @FXML
    private Label numberOfPaymentLabel3;

    @FXML
    private Label numberOfPaymentLabel4;

    @FXML
    private Label roomTypeLabel1;

    @FXML
    private Label roomTypeLabel2;

    @FXML
    private Label roomTypeLabel3;

    @FXML
    private Label roomTypeLabel4;

    @FXML
    private Label typeBedLabel1;

    @FXML
    private Label typeBedLabel2;

    @FXML
    private Label typeBedLabel3;

    @FXML
    private Label typeBedLabel4;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}

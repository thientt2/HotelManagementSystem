package UI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DTO.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import BLL.*;

import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainWindow_Controller implements Initializable {

	
    @FXML
    private Label amountMoneyLabel;

    @FXML
    private Label amountMoneyLabel1;

    @FXML
    private Label amountMoneyLabel11;

    @FXML
    private Label amountMoneyLabel12;

    @FXML
    private Label availabelRoomLabel;

    @FXML
    private Label availabelRoomLabel1;

    @FXML
    private Label availabelRoomLabel11;

    @FXML
    private Label availabelRoomLabel12;

    @FXML
    private Button billWindow_btn;

    @FXML
    private AnchorPane bill_btn;

    @FXML
    private Button bookRoomWindow_btn;

    @FXML
    private AnchorPane bookRoom_btn;

    @FXML
    private Button customerWindow_btn;

    @FXML
    private AnchorPane customer_btn;

    @FXML
    private AnchorPane dashBoard_form;

    @FXML
    private Button mainWindow_btn;

    @FXML
    private Label moneyPerNightLabel;

    @FXML
    private Label moneyPerNightLabel1;

    @FXML
    private Label moneyPerNightLabel11;

    @FXML
    private Label moneyPerNightLabel12;

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
    private Label numberOfPaymentLabel;

    @FXML
    private Label numberOfPaymentLabel1;

    @FXML
    private Label numberOfPaymentLabel11;

    @FXML
    private Label numberOfPaymentLabel12;

    @FXML
    private Button paramWindow_btn;

    @FXML
    private AnchorPane param_btn;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label roomTypeLabel1;

    @FXML
    private Label roomTypeLabel11;

    @FXML
    private Label roomTypeLabel12;

    @FXML
    private Button roomWindow_btn;

    @FXML
    private AnchorPane roomWindow_form;

    @FXML
    private AnchorPane room_btn;

    @FXML
    private Label squareLabel;

    @FXML
    private Label squareLabel1;

    @FXML
    private Label squareLabel11;

    @FXML
    private Label squareLabel12;

    @FXML
    private Button staffWindow_btn;

    @FXML
    private AnchorPane staff_btn;

    @FXML
    private Button statisticalWindow_btn;

    @FXML
    private AnchorPane statistical_btn;

    @FXML
    private Label toDay;

    @FXML
    private Label typeBedLabel;

    @FXML
    private Label typeBedLabel1;

    @FXML
    private Label typeBedLabel11;

    @FXML
    private Label typeBedLabel12;

    @FXML
    private Label username_label;
    
    @FXML
    private FontAwesomeIcon iconDashBoard;
    
    @FXML
    private AnchorPane main;
	
    
    private String username;
    
    // Đặt username với username_label
	public void initData(String username) {
		this.username = username;
		NHANVIEN nhanVien = DANGNHAP_BLL.layTenNhanVien(username);
        username_label.setText(nhanVien.getTENNV());
        
    }

	
	
    //Đặt thời gian hiển thị
	public void setTime() {
	    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        LocalDateTime now = LocalDateTime.now();        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	        String formattedDateTime = now.format(formatter);
	        toDay.setText(formattedDateTime);
	    }));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}
	
	public void changeSceneRoomWindow() {
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(true);
		mainWindow_btn.getStyleClass().clear();		
		mainWindow_btn.getStyleClass().add("control_btn");
		mainWindow_btn.applyCss();
		mainWindow_btn.layout();
		room_btn.getStyleClass().clear();
		room_btn.getStyleClass().add("control_btn");
		room_btn.applyCss();
		room_btn.layout();
		room_btn.getStyleClass().add("dashBoard_btn");
	}
	
	public void changeSceneDashBoardWindow() {
		dashBoard_form.setVisible(true);
		roomWindow_form.setVisible(false);
		room_btn.getStyleClass().clear();
		room_btn.getStyleClass().add("control_btn");
		mainWindow_btn.getStyleClass().clear();	
		mainWindow_btn.getStyleClass().remove("dashBoard_btn");
	}
	


	
	public void changeSceneBillWindow() throws IOException {


	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		
		setTime();		
	}

}

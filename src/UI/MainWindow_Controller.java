package UI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.DANGNHAP_BLL;
import BLL.KHACHHANG_BLL;
import BLL.NHANVIEN_BLL;
import DTO.KHACHHANG;
import DTO.NHANVIEN;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainWindow_Controller implements Initializable {


    @FXML
    private Button addCustomer_btn;
    
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
    private AnchorPane billWindow_form;

    @FXML
    private AnchorPane bill_btn;

    @FXML
    private Button bookRoomWindow_btn;

    @FXML
    private AnchorPane bookRoomWindow_form;

    @FXML
    private AnchorPane bookRoom_btn;
    
    @FXML
    private TableColumn<KHACHHANG, String> colAddressCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colBirthdayCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colCccdCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colControlCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colCountryCustomer;

    @FXML
    private TableColumn<KHACHHANG, Integer> colCustomerType;

    @FXML
    private TableColumn<KHACHHANG, String> colEmailCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colGenderCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colIdCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colNameCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colPhoneCustomer;

    @FXML
    private TableColumn<KHACHHANG, Integer> colStatusCustomer;
    
    @FXML
    private TableView<KHACHHANG> customer_table;
    
    @FXML
    private TableColumn<NHANVIEN, String> colBeginStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colBirthStaff;
    
    @FXML
    private TableColumn<NHANVIEN, String> colCccdStaff;
    
    @FXML
    private TableColumn<NHANVIEN, String> colNameStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colIdStaff;
    
    @FXML
    private TableColumn<NHANVIEN, String> colGenderStaff;
    
    @FXML
    private TableView<NHANVIEN> staff_table;

    @FXML
    private Button customerWindow_btn;

    @FXML
    private AnchorPane customerWindow_form;

    @FXML
    private AnchorPane customer_btn;

    @FXML
    private AnchorPane dashBoard_form;

    @FXML
    private FontAwesomeIcon iconDashBoard;

    @FXML
    private AnchorPane main;

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
    private AnchorPane paramWindow_form;

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
    private AnchorPane staffWindow_form;

    @FXML
    private AnchorPane staff_btn;

    @FXML
    private Button statisticalWindow_btn;

    @FXML
    private AnchorPane statisticalWindow_form;

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

	
    
    private String username;
    
    // Đặt username với username_label
	public void initData(String username) {
		this.username = username;
		NHANVIEN nhanVien = NHANVIEN_BLL.layTenNhanVien(username);
        username_label.setText(nhanVien.getTENNV());
        dashBoard_form.setVisible(true);
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
	//Chuyển động giữa các giao diện
	
	public void changeSceneRoomWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(true);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(false);
	}
	
	public void changeSceneDashBoardWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(true);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(false);
	}
		
	public void changeSceneParamWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(true);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(false);
	}
	
	public void changeSceneStaffWindow() {
		staffWindow_form.setVisible(true);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(false);
	}	
	
	public void changeSceneCustomerWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(true);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(false);		
	}
	
	public void changeSceneStatisticalWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(true);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(false);		
	}

	public void changeSceneBillWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(false);
		billWindow_form.setVisible(true);	

	}
	
	public void changeSceneBookRoomWindow() {
		staffWindow_form.setVisible(false);
		paramWindow_form.setVisible(false);
		dashBoard_form.setVisible(false);
		roomWindow_form.setVisible(false);
		customerWindow_form.setVisible(false);	
		statisticalWindow_form.setVisible(false);
		bookRoomWindow_form.setVisible(true);
		billWindow_form.setVisible(false);
	}
    //Xử lý các nút tương tác
	private double x = 0;
	private double y = 0;
	public void addCustomer() throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addCustomer.fxml"));
		Parent root = loader.load();
		
		root.setOnMousePressed((MouseEvent event)->{            
			x = event.getSceneX();            
			y = event.getSceneY();    
		});
		
		Stage stage = new Stage();        
		stage.initStyle(StageStyle.TRANSPARENT);        
		Scene scene = new Scene(root);
    
		root.setOnMouseDragged((MouseEvent event)->{
			stage.setX(event.getScreenX() - x);            
			stage.setY(event.getScreenY() - y);       
		});		
				
		String css = this.getClass().getResource("/Designs/addCustomer.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);     
		stage.show();
	}
	
	public void addStaff() throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addStaff.fxml"));
		Parent root = loader.load();
		
		root.setOnMousePressed((MouseEvent event)->{            
			x = event.getSceneX();            
			y = event.getSceneY();    
		});
		
		Stage stage = new Stage();        
		stage.initStyle(StageStyle.TRANSPARENT);        
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/Designs/addNhanVien.css").toExternalForm());
		root.setOnMouseDragged((MouseEvent event)->{
			stage.setX(event.getScreenX() - x);            
			stage.setY(event.getScreenY() - y);       
		});		
		stage.setScene(scene);     
		stage.show();
	}
	
	//Xử lý phần hiện danh sách
	public void showListCustomer() {
		ObservableList<KHACHHANG> list = KHACHHANG_BLL.listCustomer();
		
		if(colIdCustomer != null && colNameCustomer != null 
				&& colPhoneCustomer != null && colBirthdayCustomer != null
				&& colGenderCustomer != null && colCountryCustomer != null 
				&& colCccdCustomer != null) {
			
			colIdCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("MAKH"));
			colNameCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("TENKH"));
			colPhoneCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("SDT"));						
			colBirthdayCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("NGAYSINH"));
			colGenderCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("GIOITINH"));
			colCountryCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("QUOCTICH"));
			colCccdCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("CCCD"));
			
			customer_table.setItems(list);			
		}		
	}
	
	public void showListStaff() {
		ObservableList<NHANVIEN> list = NHANVIEN_BLL.listStaff();
		
		if(colIdStaff != null && colNameStaff != null 
				&& colBirthStaff != null && colGenderStaff != null 
				&& colCccdStaff != null && colBeginStaff != null) {
			
			colIdStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("MANV"));
			colNameStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("TENNV"));			
			colBirthStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("NGAYSINH"));
			colGenderStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("GIOITINH"));			
			colCccdStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("CCCD"));	
			colBeginStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("NGAYVAOLAM"));
			staff_table.setItems(list);			
		}
	}
	
	
	public void exit() {
		System.exit(0);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		
		setTime();		
		showListCustomer();
		showListStaff();
	}

}

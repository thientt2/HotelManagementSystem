package UI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import BLL.NHANVIEN_BLL;
import DTO.KHACHHANG;
import DTO.NHANVIEN;
import UI.Resource.itemStaff_Controller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import system.SystemMessage;

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
    private Button feedbackWindow_btn;

    @FXML
    private AnchorPane feedback_btn;
    
    @FXML
    private Button selectFeedbackWindow_btn;
    
    @FXML
    private TableColumn<KHACHHANG, String> colAddressCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colBirthdayCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colCccdCustomer;

    @FXML
    private TableColumn<KHACHHANG, Void> colControlCustomer;

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
    private Button selectBillWindow_btn;

    @FXML
    private Button selectBookRoomWindow_btn;

    @FXML
    private Button selectCustomerWindow_btn;

    @FXML
    private Button selectMainWindow_btn;

    @FXML
    private Button selectParamWindow_btn;

    @FXML
    private Button selectRoomWindow_btn;

    @FXML
    private Button selectStaffWindow_btn;

    @FXML
    private Button selectStatisticalWindow_btn;

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
    private Circle top_circle;

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
    private AnchorPane mainWindow;
    
    @FXML
    private TableColumn<NHANVIEN,Void> colControlStaff;

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ImageView photo;
    
    @FXML
    private customerWindow_Controller customerController;
    
    private String username;
    
    public Boolean Visible = false;
    
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    
    // Đặt username với username_label
//    public void initData(String username) {
//        this.username = username;
//        NHANVIEN nhanVien = NHANVIEN_BLL.getStaff(username);
//        username_label.setText(nhanVien.getTENNV());
//        SystemMessage.setMANV(nhanVien.getMANV());
//
//        // Ensure the PHOTOURL is correctly formatted
//        String path = "file:///" + nhanVien.getPHOTOURL();
//        System.out.println("Loading image from path: " + path); // Logging the path for debugging
//
//        // Load the image with appropriate error handling
//        Image image = new Image(path, 1012, 22, false, true);
//        if (image.isError()) {
//            System.out.println("Error loading image: " + image.getException());
//            // Handle the error accordingly, maybe set a default image or notify the user
//            // Example: set a default image or a placeholder
//            Image defaultImage = new Image("/Images/home.png", 1012, 22, false, true);
//            top_circle.setFill(new ImagePattern(defaultImage));
//        } else {
//            // If the image is loaded successfully, set it to the circle
//            top_circle.setFill(new ImagePattern(image));
//        }
//    }
    
    public void initData(String username) {
        this.username = username;
        NHANVIEN nhanVien = NHANVIEN_BLL.getStaff(username);
        username_label.setText(nhanVien.getTENNV());
        SystemMessage.setMANV(nhanVien.getMANV());

        byte[] photoData = nhanVien.getPHOTO();
        if (photoData != null && photoData.length > 0) {
            ByteArrayInputStream bis = new ByteArrayInputStream(photoData);
            Image image = new Image(bis);
            photo.setImage(image);
        } else {
            Image defaultImage = new Image("/Images/LAOPERA.jpg");
            photo.setImage(defaultImage);
        }
    }

//    public void setStaff(NHANVIEN nhanVien) {
//        if (nhanVien.getPHOTO() != null) {
//            ByteArrayInputStream bis = new ByteArrayInputStream(nhanVien.getPHOTO());
//            Image image = new Image(bis);
//            photo.setImage(image);
//        }
//        // Các phần khác của setStaff
//    }
	
	public void unvisible() {
		//Visible = true;
		anchorPane.setVisible(Visible);
	}
	

	enum VietnameseDayOfWeek {
        MONDAY("Thứ Hai"),
        TUESDAY("Thứ Ba"),
        WEDNESDAY("Thứ Tư"),
        THURSDAY("Thứ Năm"),
        FRIDAY("Thứ Sáu"),
        SATURDAY("Thứ Bảy"),
        SUNDAY("Chủ Nhật");

        private final String vietnameseName;
        
        VietnameseDayOfWeek(String vietnameseName) {
            this.vietnameseName = vietnameseName;
        }
        
        public String getVietnameseName() {
            return vietnameseName;
        }
    }

    public void setTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            VietnameseDayOfWeek dayOfWeek = VietnameseDayOfWeek.valueOf(now.getDayOfWeek().name());
            String formattedDateTime = dayOfWeek.getVietnameseName() + ", " + now.format(dateFormatter);
            toDay.setText(formattedDateTime);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    
	//Chuyển động giữa các giao diện
	
	public void changeSceneRoomWindow() {		
		Platform.runLater(() -> {
			try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("roomWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("roomWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            roomWindow_Controller controller = loader.getController();
	            controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);
				
				selectMainWindow_btn.setVisible(false);
	    		selectRoomWindow_btn.setVisible(true);
	    		selectCustomerWindow_btn.setVisible(false);
	    		selectStaffWindow_btn.setVisible(false);
	    		selectParamWindow_btn.setVisible(false);
	    		selectStatisticalWindow_btn.setVisible(false);
	    		selectBookRoomWindow_btn.setVisible(false);
	    		selectBillWindow_btn.setVisible(false);
	    		selectFeedbackWindow_btn.setVisible(false);
	    		
	    		mainWindow_btn.setVisible(true);
	    		roomWindow_btn.setVisible(false);
	    		customerWindow_btn.setVisible(true);
	    		staffWindow_btn.setVisible(true);
	    		paramWindow_btn.setVisible(true);
	    		statisticalWindow_btn.setVisible(true);
	    		bookRoomWindow_btn.setVisible(true);
	    		billWindow_btn.setVisible(true);
	    		feedbackWindow_btn.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
	    });     
	}
	
	public void changeSceneDashBoardWindow() {		
        Platform.runLater(() -> {
        	try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("dashBoardWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dashBoardWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            dashBoardWindow_Controller controller = loader.getController();
	            //controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);			
				
	    		selectMainWindow_btn.setVisible(true);
	    		selectRoomWindow_btn.setVisible(false);
	    		selectCustomerWindow_btn.setVisible(false);
	    		selectStaffWindow_btn.setVisible(false);
	    		selectParamWindow_btn.setVisible(false);
	    		selectStatisticalWindow_btn.setVisible(false);
	    		selectBookRoomWindow_btn.setVisible(false);
	    		selectBillWindow_btn.setVisible(false);
	    		selectFeedbackWindow_btn.setVisible(false);
	    		
	    		mainWindow_btn.setVisible(false);
				roomWindow_btn.setVisible(true);
	    		customerWindow_btn.setVisible(true);
	    		staffWindow_btn.setVisible(true);
	    		paramWindow_btn.setVisible(true);
	    		statisticalWindow_btn.setVisible(true);
	    		bookRoomWindow_btn.setVisible(true);
	    		billWindow_btn.setVisible(true);
	    		feedbackWindow_btn.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
        	}
        });
	}
		
	public void changeSceneParamWindow() {	
		
		Platform.runLater(() -> {
			try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("paramWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("paramWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            paramWindow_Controller controller = loader.getController();
	            controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);			
				

				selectMainWindow_btn.setVisible(false);
				selectRoomWindow_btn.setVisible(false);
				selectCustomerWindow_btn.setVisible(false);
				selectStaffWindow_btn.setVisible(false);
				selectParamWindow_btn.setVisible(true);
				selectStatisticalWindow_btn.setVisible(false);
				selectBookRoomWindow_btn.setVisible(false);
				selectBillWindow_btn.setVisible(false);
				selectFeedbackWindow_btn.setVisible(false);
				
				mainWindow_btn.setVisible(true);
				roomWindow_btn.setVisible(true);
				customerWindow_btn.setVisible(true);
				staffWindow_btn.setVisible(true);
				paramWindow_btn.setVisible(false);
				statisticalWindow_btn.setVisible(true);
				bookRoomWindow_btn.setVisible(true);
				billWindow_btn.setVisible(true);
				feedbackWindow_btn.setVisible(true);
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			 
	   });
	}
	
	public void changeSceneStaffWindow() {
		Platform.runLater(() -> {
			 	try {
					//Parent newWindow = FXMLLoader.load(getClass().getResource("staffWindow_UI.fxml"));
					FXMLLoader loader = new FXMLLoader(getClass().getResource("staffWindow_UI.fxml"));
		            Parent newWindow = loader.load();
		            staffWindow_Controller controller = loader.getController();
		            controller.setMainWindowController(this);
					mainWindow.getChildren().setAll(newWindow);			
					
					selectMainWindow_btn.setVisible(false);
					selectRoomWindow_btn.setVisible(false);
					selectCustomerWindow_btn.setVisible(false);
					selectStaffWindow_btn.setVisible(true);
					selectParamWindow_btn.setVisible(false);
					selectStatisticalWindow_btn.setVisible(false);
					selectBookRoomWindow_btn.setVisible(false);
					selectBillWindow_btn.setVisible(false);
					selectFeedbackWindow_btn.setVisible(false);
					
					mainWindow_btn.setVisible(true);
					roomWindow_btn.setVisible(true);
					customerWindow_btn.setVisible(true);
					staffWindow_btn.setVisible(false);
					paramWindow_btn.setVisible(true);
					statisticalWindow_btn.setVisible(true);
					bookRoomWindow_btn.setVisible(true);
					billWindow_btn.setVisible(true);	 
					feedbackWindow_btn.setVisible(true);
			 	} catch (IOException e) {
				 	e.printStackTrace();
			 	}
				          
	        });	

	}	
	
	public void changeSceneCustomerWindow() {
		Platform.runLater(() -> {
		 	try {
		 		//Parent newWindow = FXMLLoader.load(getClass().getResource("customerWindow_UI.fxml"));
		 		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            customerWindow_Controller controller = loader.getController();
	            controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);
				
				
				selectMainWindow_btn.setVisible(false);
				selectRoomWindow_btn.setVisible(false);
				selectCustomerWindow_btn.setVisible(true);
				selectStaffWindow_btn.setVisible(false);
				selectParamWindow_btn.setVisible(false);
				selectStatisticalWindow_btn.setVisible(false);
				selectBookRoomWindow_btn.setVisible(false);
				selectBillWindow_btn.setVisible(false);
				selectFeedbackWindow_btn.setVisible(false);
				
				mainWindow_btn.setVisible(true);
				roomWindow_btn.setVisible(true);
				customerWindow_btn.setVisible(false);
				staffWindow_btn.setVisible(true);
				paramWindow_btn.setVisible(true);
				statisticalWindow_btn.setVisible(true);
				bookRoomWindow_btn.setVisible(true);
				billWindow_btn.setVisible(true);	 
				feedbackWindow_btn.setVisible(true);
		 	} catch (IOException e) {
			 	e.printStackTrace();
		 	}	
			          
        });
	}
	
	public void changeSceneStatisticalWindow() {
		Platform.runLater(() -> {			
			try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("statisticalWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("statisticalWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            statisticalWindow_Controller controller = loader.getController();
	            //controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);		
				
				selectMainWindow_btn.setVisible(false);
				selectRoomWindow_btn.setVisible(false);
				selectCustomerWindow_btn.setVisible(false);
				selectStaffWindow_btn.setVisible(false);
				selectParamWindow_btn.setVisible(false);
				selectStatisticalWindow_btn.setVisible(true);
				selectBookRoomWindow_btn.setVisible(false);
				selectBillWindow_btn.setVisible(false);
				selectFeedbackWindow_btn.setVisible(false);
				
				mainWindow_btn.setVisible(true);
				roomWindow_btn.setVisible(true);
				customerWindow_btn.setVisible(true);
				staffWindow_btn.setVisible(true);
				paramWindow_btn.setVisible(true);
				statisticalWindow_btn.setVisible(false);
				bookRoomWindow_btn.setVisible(true);
				billWindow_btn.setVisible(true);	
				feedbackWindow_btn.setVisible(true);
			}catch (IOException e) {
				e.printStackTrace();
			}		 	            
        });
	}

	public void changeSceneBillWindow() {
		Platform.runLater(() -> {
			try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("billWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("billWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            //billWindow_Controller controller = loader.getController();
	            //controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);
				
				selectMainWindow_btn.setVisible(false);
				selectRoomWindow_btn.setVisible(false);
				selectCustomerWindow_btn.setVisible(false);
				selectStaffWindow_btn.setVisible(false);
				selectParamWindow_btn.setVisible(false);
				selectStatisticalWindow_btn.setVisible(false);
				selectBookRoomWindow_btn.setVisible(false);
				selectBillWindow_btn.setVisible(true);
				selectFeedbackWindow_btn.setVisible(false);
				
				mainWindow_btn.setVisible(true);
				roomWindow_btn.setVisible(true);
				customerWindow_btn.setVisible(true);
				staffWindow_btn.setVisible(true);
				paramWindow_btn.setVisible(true);
				statisticalWindow_btn.setVisible(true);
				bookRoomWindow_btn.setVisible(true);
				billWindow_btn.setVisible(false);	
				feedbackWindow_btn.setVisible(true);
			}catch (IOException e) {
				e.printStackTrace();
			}
        });

	}
	
	public void changeSceneBookRoomWindow() {
		Platform.runLater(() -> {
			try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("bookRoomWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("bookRoomWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            bookRoomWindow_Controller controller = loader.getController();
	            controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);
				
				selectMainWindow_btn.setVisible(false);
				selectRoomWindow_btn.setVisible(false);
				selectCustomerWindow_btn.setVisible(false);
				selectStaffWindow_btn.setVisible(false);
				selectParamWindow_btn.setVisible(false);
				selectStatisticalWindow_btn.setVisible(false);
				selectBookRoomWindow_btn.setVisible(true);
				selectBillWindow_btn.setVisible(false);
				selectFeedbackWindow_btn.setVisible(false);
				
				mainWindow_btn.setVisible(true);
				roomWindow_btn.setVisible(true);
				customerWindow_btn.setVisible(true);
				staffWindow_btn.setVisible(true);
				paramWindow_btn.setVisible(true);
				statisticalWindow_btn.setVisible(true);
				bookRoomWindow_btn.setVisible(false);
				billWindow_btn.setVisible(true);
				feedbackWindow_btn.setVisible(true);
			}catch (IOException e) {
				e.printStackTrace();
			}
        });
	}
	
	public void changeSceneFeedbackWindow() {
		Platform.runLater(() -> {
			try {
				//Parent newWindow = FXMLLoader.load(getClass().getResource("bookRoomWindow_UI.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("feedbackWindow_UI.fxml"));
	            Parent newWindow = loader.load();
//	            feedbackWindow_Controller controller = loader.getController();
//	            controller.setMainWindowController(this);
				mainWindow.getChildren().setAll(newWindow);
				
				selectMainWindow_btn.setVisible(false);
				selectRoomWindow_btn.setVisible(false);
				selectCustomerWindow_btn.setVisible(false);
				selectStaffWindow_btn.setVisible(false);
				selectParamWindow_btn.setVisible(false);
				selectStatisticalWindow_btn.setVisible(false);
				selectBookRoomWindow_btn.setVisible(false);
				selectBillWindow_btn.setVisible(false);
				selectFeedbackWindow_btn.setVisible(true);
				
				mainWindow_btn.setVisible(true);
				roomWindow_btn.setVisible(true);
				customerWindow_btn.setVisible(true);
				staffWindow_btn.setVisible(true);
				paramWindow_btn.setVisible(true);
				statisticalWindow_btn.setVisible(true);
				bookRoomWindow_btn.setVisible(true);
				billWindow_btn.setVisible(true);
				feedbackWindow_btn.setVisible(false);
			}catch (IOException e) {
				e.printStackTrace();
			}
        });
	}
		
	//Đóng phần mềm	
	public void exit() {
		System.exit(0);
	}
	
	//Xử lý đăng xuất	
	public void logout() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void refresh() {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem refreshMenuItem = new MenuItem("Refresh");
		contextMenu.getItems().add(refreshMenuItem);
		refreshMenuItem.setOnAction(event -> {			
			initData(username);
		});
		 
		main.setOnContextMenuRequested(event -> {
            contextMenu.show(main, event.getScreenX(), event.getScreenY());
        });
		
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		setTime();	
		
		
	}



	

}

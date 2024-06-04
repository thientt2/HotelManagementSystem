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
    private Button accountInfo_btn;

    @FXML
    private customerWindow_Controller customerController;
    
    private String username;
    
    private Stage accountInfoStage = null;
    
    public Boolean Visible = false;
    
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    
	private double x = 0;
	private double y = 0;
    
    public void accountInfo() throws IOException {
        if (accountInfoStage != null && accountInfoStage.isShowing()) {
            accountInfoStage.close();
            accountInfoStage = null;
        } else {
            NHANVIEN nhanVien = NHANVIEN_BLL.getStaff(username);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("accountInfo.fxml"));
            Parent root = loader.load();
            
            accountInfoStage = new Stage();
            accountInfoStage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);

            double initialX = 1010;
            double initialY = 130;
            accountInfoStage.setX(initialX);
            accountInfoStage.setY(initialY);
            
            accountInfo_Controller accountInfo = loader.getController();
            accountInfo.accountInfo(nhanVien);

            accountInfoStage.setScene(scene);
            accountInfoStage.show();

            accountInfoStage.setOnHiding(event -> accountInfoStage = null);
        }
        
    }
    
//	public void accountInfo() throws IOException {
//	    NHANVIEN nhanVien = NHANVIEN_BLL.getStaff(username);
//	    FXMLLoader loader = new FXMLLoader(getClass().getResource("accountInfo.fxml"));
//	    Parent root = loader.load();
//
//	    Stage stage = new Stage();
//	    stage.initStyle(StageStyle.TRANSPARENT);
//	    Scene scene = new Scene(root);
//
//	    accountInfo_Controller accountInfo = loader.getController();
//	    accountInfo.accountInfo(nhanVien);
//
//	    // Define initial X and Y coordinates for the window
//	    double initialX = 1010;
//	    double initialY = 130;
//	    stage.setX(initialX);
//	    stage.setY(initialY);
//
//	    // Variables to store the offset of the mouse click relative to the window's position
//	    final double[] offsetX = {0};
//	    final double[] offsetY = {0};
//
//	    root.setOnMousePressed((MouseEvent event) -> {
//	        offsetX[0] = event.getSceneX();
//	        offsetY[0] = event.getSceneY();
//	    });
//
//	    root.setOnMouseDragged((MouseEvent event) -> {
//	        stage.setX(event.getScreenX() - offsetX[0]);
//	        stage.setY(event.getScreenY() - offsetY[0]);
//	    });
//
//	    stage.setScene(scene);
//	    stage.showAndWait();
//	}
    // Đặt username với username_label
    public void initData(String username) {
        this.username = username;
        NHANVIEN nhanVien = NHANVIEN_BLL.getStaff(username);
        username_label.setText(nhanVien.getTENNV());
        SystemMessage.setMANV(nhanVien.getMANV());

	    byte[] photoData = nhanVien.getPHOTO();
	    if (photoData != null && photoData.length > 0) {
	        ByteArrayInputStream bis = new ByteArrayInputStream(photoData);
	        Image image = new Image(bis);
	        top_circle.setFill(new ImagePattern(image));
	    } else {
	        Image defaultImage = new Image("/Images/LAOPERA.jpg");
	        top_circle.setFill(new ImagePattern(defaultImage));
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
	            controller.setMainWindowController(this);
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
				FXMLLoader loader = new FXMLLoader(getClass().getResource("feedbackWindow_UI.fxml"));
	            Parent newWindow = loader.load();
	            //feedbackWindow_Controller controller = loader.getController();
	            //controller.setMainWindowController(this);
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
	
	
//	public void refresh() {
//		ContextMenu contextMenu = new ContextMenu();
//		MenuItem refreshMenuItem = new MenuItem("Refresh");
//		contextMenu.getItems().add(refreshMenuItem);
//		refreshMenuItem.setOnAction(event -> {			
//			//initData(username);
//		});
//		 
//		main.setOnContextMenuRequested(event -> {
//            contextMenu.show(main, event.getScreenX(), event.getScreenY());
//        });
//		
//		
//	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		setTime();	
		changeSceneDashBoardWindow();	
		
	}



	

}

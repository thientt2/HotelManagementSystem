package UI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import BLL.LOAINHANVIEN_BLL;
import BLL.NHANVIEN_BLL;
import DTO.KHACHHANG;
import DTO.NHANVIEN;
import UI.BookRoom.bookRoomWindow_Controller;
import UI.Customer.customerWindow_Controller;
import UI.DashBoard.dashBoardWindow_Controller;
import UI.Param.paramWindow_Controller;
import UI.Resource.itemService_Controller;
import UI.Resource.itemStaff_Controller;
import UI.Room.roomWindow_Controller;
import UI.Staff.staffWindow_Controller;
import UI.Statistical.statisticalWindow_Controller;
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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private Button bookRoomWindow_btn;
       
    @FXML
    private Button feedbackWindow_btn;
        
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
    private FontAwesomeIcon iconDashBoard;

    @FXML
    private AnchorPane main;

    @FXML
    private Button mainWindow_btn;

    @FXML
    private Button paramWindow_btn;

    @FXML
    private Button roomWindow_btn;
    
    @FXML
    private Button staffWindow_btn;

    @FXML
    private Button statisticalWindow_btn;

    @FXML
    private Label toDay;
    
    @FXML
    private Circle top_circle;

    @FXML
    private Label username_label;
    
    @FXML
    private AnchorPane mainWindow;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button accountInfo_btn;

    @FXML
    private customerWindow_Controller customerController;
    
    private String username;
    
    
    public Boolean Visible = false;
    
    private boolean isPaneVisible = false;
    
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    
    // Các thành phần màn hình accountInfo
	@FXML
    private Label birthday_txt;

    @FXML
    private Label cccd_txt;

    @FXML
    private Label email_txt;

    @FXML
    private Button exit_btn;

    @FXML
    private Label gender_txt;

    @FXML
    private Label id_txt;

    @FXML
    private Button logOut_btn;

    @FXML
    private Label name_txt;

    @FXML
    private Label sdt_txt;

    @FXML
    private Label startdate_txt;

    @FXML
    private Circle top_circle1;

    @FXML
    private Label type_txt;
    
    @FXML
    private Pane accountInfo_pane;
    
    public Pane getPane() {
        return accountInfo_pane;
    }
    
    private int staffTypeId;
    
    private List<String> listScreens;

    private double x = 0;
	private double y = 0;
	
	private boolean access = false;
	
	public Label getTypelb() {
		return type_txt;
	}
	
	public void setCreateUserAccess() {
		
	}
	
	public void setAccessIcon() {
		for (String item: listScreens)
		{
//			if(item.equalsIgnoreCase("Màn hình thông số"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình trang chủ"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình khách hàng"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình nhân viên"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình phòng"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình đặt phòng"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình báo cáo"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình hóa đơn"))
//				icon.setVisible(false);
//			if(item.equalsIgnoreCase("Màn hình đánh giá"))
//				icon.setVisible(false);
		}
			
	}
	
	
	public void accessParam()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình thông số"))
			{
				changeSceneParamWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessCustomer()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình khách hàng"))
			{
				System.out.printf("có");
				changeSceneCustomerWindow();
				access = true;
			}		
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessStaff()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình nhân viên"))
			{
				changeSceneStaffWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessRoom()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình phòng"))
			{
				changeSceneRoomWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessBookRoom()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình đặt phòng"))
			{
				changeSceneBookRoomWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessBillRoom()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình hóa đơn"))
			{
				changeSceneBillWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessStatisticalRoom()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình báo cáo"))
			{
				changeSceneStatisticalWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	public void accessFeedbackRoom()
	{
		for (String item: listScreens)
			if(item.equalsIgnoreCase("Màn hình đánh giá"))
			{
				changeSceneFeedbackWindow();
				access = true;
			}
		if (!access)
		{
			Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Không có quyền truy cập");
		    alert.setHeaderText(null);
		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
		    alert.showAndWait();
		}
		else
			access = false;
	}
	
	//Trường hợp đặc biệt
	public void accessDashBoardRoom()
	{
//		for (String item: listScreens)
//			if(item.equalsIgnoreCase("Màn hình trang chủ"))
//			{
//				changeSceneDashBoardWindow();
//				access = true;
//			}
//		if (!access)
//		{
//			Alert alert = new Alert(AlertType.WARNING);
//		    alert.setTitle("Không có quyền truy cập");
//		    alert.setHeaderText(null);
//		    alert.setContentText("Bạn không có quyền truy cập vào tab này!");
//		    alert.showAndWait();
//		}
//		else
//			access = false;
		changeSceneDashBoardWindow();
	}
	
	private String formatDate(String dateStr) {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = fromUser.parse(dateStr);
            return myFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;  
        }
    }
	
    public void accountInfo(NHANVIEN item) {
    	name_txt.setText(item.getTENNV());
		sdt_txt.setText(item.getSDT());
		email_txt.setText(item.getEMAIL());
		gender_txt.setText(item.getGIOITINH());
		
		//birthday_txt.setText(item.getNGAYSINH());
		String birthday = item.getNGAYSINH();
        String formattedDate1 = formatDate(birthday);
        birthday_txt.setText(formattedDate1);
        
		//startdate_txt.setText(item.getNGAYVAOLAM());
		String startdate = item.getNGAYVAOLAM();
        String formattedDate2 = formatDate(startdate);
        startdate_txt.setText(formattedDate2);
        
		cccd_txt.setText(item.getCCCD());
		id_txt.setText(item.getMANV());
		type_txt.setText(LOAINHANVIEN_BLL.getStaffTypeName(item.getMALOAINV()));
        SystemMessage.setType(type_txt.getText());
		
	    byte[] photoData = item.getPHOTO();
	    if (photoData != null && photoData.length > 0) {
	        ByteArrayInputStream bis = new ByteArrayInputStream(photoData);
	        Image image = new Image(bis);
	        top_circle1.setFill(new ImagePattern(image));
	    } else {
	        Image defaultImage = new Image("/Images/LAOPERA.jpg");
	        top_circle1.setFill(new ImagePattern(defaultImage));
	    }
	    
	    staffTypeId = item.getMALOAINV();
	    listScreens = LOAINHANVIEN_BLL.getStaffTypeScreens(staffTypeId);
	}  
    
    public void logOut() throws IOException {
    	logOut_btn.getScene().getWindow().hide();   
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow_UI.fxml"));
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
		
		stage.setScene(scene);     
		stage.show();  
    }
    

	public void accountInfo() {
		if (isPaneVisible) {
			accountInfo_pane.setVisible(false);
            isPaneVisible = false;
        } else { // Nếu pane chưa hiển thị, thì hiển thị lên
        	accountInfo_pane.setVisible(true);
            isPaneVisible = true;
        }
	}
    

    // Đặt username với username_label
    public void initData(String username) {
        this.username = username;
        NHANVIEN nhanVien = NHANVIEN_BLL.getStaff(username);
        username_label.setText(nhanVien.getTENNV());
        SystemMessage.setMANV(nhanVien.getMANV());
        accountInfo(nhanVien);

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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Room/roomWindow_UI.fxml"));
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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/DashBoard/dashBoardWindow_UI.fxml"));
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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Param/paramWindow_UI.fxml"));
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
					//Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Staff/staffWindow_UI.fxml"));
			 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Staff/staffWindow_UI.fxml"));
			 		Parent newWindow = loader.load();
					staffWindow_Controller controller = loader.getController();
		            Button createUser_btn = controller.getAddUser_btn();
		            if(!type_txt.getText().equalsIgnoreCase("Quản trị viên"))
		            	createUser_btn.setVisible(false);
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
		 		Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Customer/customerWindow_UI.fxml"));
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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Statistical/statisticalWindow_UI.fxml"));
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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Bill/billWindow_UI.fxml"));
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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/BookRoom/bookRoomWindow_UI.fxml"));
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
				Parent newWindow = FXMLLoader.load(getClass().getResource("/UI/Feedback/feedbackWindow_UI.fxml"));
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		setTime();	
		changeSceneDashBoardWindow();	
		//setAccessIcon();
	}



	

}

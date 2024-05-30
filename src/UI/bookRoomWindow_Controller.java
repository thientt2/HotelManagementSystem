package UI;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import BLL.PHIEUDATPHONG_BLL;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class bookRoomWindow_Controller implements Initializable{
	

	@FXML
    private VBox listBookRoom_vbox;
	
    @FXML
    private Button bookRoom_btn;
	
    private MainWindow_Controller mainWindowController;
    
    // Phương thức để thiết lập tham chiếu của main window controller từ bên ngoài
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
    private double x = 0;
	private double y = 0;
	
	public void refreshBookRoomList() {
        // Xóa tất cả các nút con trong VBox
        listBookRoom_vbox.getChildren().clear();
        ObservableList<Object[]> listBookRoom = PHIEUDATPHONG_BLL.listBookRoom();
        //showListBookRoom(listBookRoom);
    }
    
//	public void showListBookRoom(ObservableList<Object[]> list) {
//		for(Object item : list) {
//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBookRoom.fxml"));
//				Parent serviceDataPane = loader.load();
//				itemService_Controller controller = loader.getController();
////				controller.setService(item);
////	            Button edit_btn = controller.getEditService_btn();
////	            Button delete_btn = controller.getDeleteService_btn();
////	            edit_btn.setOnAction(eventEditService -> {
////	            	try {
////	                    AnchorPane anchorPane = mainWindowController.getAnchorPane();
////	                    anchorPane.setVisible(true);
////	                    
////	                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("editService.fxml"));
////	                    Parent root = loader1.load();
////	
////	                    root.setOnMousePressed((MouseEvent event1) -> {            
////	                        x = event1.getSceneX();            
////	                        y = event1.getSceneY();    
////	                    });
////	                    
////	                    Stage stage = new Stage();        
////	                    stage.initStyle(StageStyle.TRANSPARENT);        
////	                    Scene scene = new Scene(root);
////	
////	                    editService_Controller editService = loader1.getController();
////	                    editService.setService(item);
////	
////	                    root.setOnMouseDragged((MouseEvent event1) -> {
////	                        stage.setX(event1.getScreenX() - x);            
////	                        stage.setY(event1.getScreenY() - y);       
////	                    });
////	                    
////	                    stage.setScene(scene);     
////	                    stage.showAndWait();
////	                    
////	                    anchorPane.setVisible(false);
////	                    refreshServiceList();
////	                } catch (IOException e) {
////	                    e.printStackTrace();
////	                }
////                });
////
////	            delete_btn.setOnAction(deleteEvent -> {
////	            	try {
////	                    DICHVU_BLL.deleteService(item);
////	                } catch (SQLException e) {
////	                    e.printStackTrace();
////	                }
////	                refreshServiceList();
////                });
////			
////	            
////				Platform.runLater(() -> listService_vbox.getChildren().add(serviceDataPane));
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}	         	
//			
////	    	HBox hBox = new HBox();
////	    	hBox.setSpacing(15);
////	    	hBox.setPrefHeight(30);
////	    	//hBox.setAlignment(Pos.CENTER);
////	    	Label maPDP = new Label(item.getMAPDP());
////	    	maPDP.setPrefWidth(50); 
////	    	maPDP.setAlignment(Pos.CENTER);
////	    	Label tenKH = new Label(); 
////	    	tenKH.setText(KHACHHANG_BLL.getCustomerName(item.getMAKH()));
////	    	tenKH.setPrefWidth(160);
////	    	Label loaiPhong = new Label();
////	    	int mapdp = CHITIETPDP_BLL.getTypeRoomId(item.getMAPDP());
////	    	loaiPhong.setText(LOAIPHONG_BLL.getRoomTypeName(mapdp));
////	    	loaiPhong.setPrefWidth(110);
////	    	Label ngNhan = new Label(item.getNGAYNHAN());
////	    	ngNhan.setPrefWidth(80);
////	    	Label ngTra = new Label(item.getNGAYTRA());
////	    	ngTra.setPrefWidth(80);
////	    	Label trangThai = new Label(getTrangThai(item.getNGAYNHAN(), item.getHINHTHUC()));
////	    	trangThai.setStyle(getTrangThaiStyle(trangThai.getText()));
////	    	trangThai.setPadding(new Insets(0,5,0,5));
//////	    	trangThai.setPrefWidth(85);
//////	    	Label ngLam = new Label(item.getNGAYVAOLAM());
//////	    	phone.setPrefWidth(85);
//////	    	ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/ellipsis_v.png")));
//////	    	imageView.setFitWidth(12);
//////	    	imageView.setFitHeight(12);
////	    	Button button = new Button("Check-in");
////	        button.setStyle(getButtonStyle(trangThai.getText()));
//////	    	Button button = new Button();
//////	    	button.setStyle("-fx-background-color: transparent;");
//////	    	button.setGraphic(imageView);
////	    	//button.setOnMouseClicked(event -> {
////	    		
////	    	hBox.getChildren().addAll(maPDP, tenKH, loaiPhong, ngNhan, ngTra, trangThai, button);
////	    	hBox.setStyle("	-fx-background-color: #FFFFFF;\r\n"	    			
////	    			+ "	-fx-border-color:  #E8F1FD;\r\n"	    			
////	    			+ "-fx-font-size: 14px; \r\n"
////	    			+ "-fx-border-width: 0 0 2 0;");
////	    	listBookRoom_vbox.getChildren().add(hBox);
//	    	
//	    }		
//	}
	
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
	
	public static String getTrangThai(String ngayNhan, String hinhThuc) {
	
		LocalDate now = LocalDate.now();
        int ngaynow = now.getDayOfMonth();
        int thangnow = now.getMonthValue();
        int namnow = now.getYear();
        int day = 0, month = 0, year = 0;
        int flag = 0;
        String status = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(ngayNhan);        
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH) + 1;
            year = calendar.get(Calendar.YEAR);    
            
           
            if (hinhThuc.equals("Offline")) {
            	status = "Trực tiếp";
            	flag = 1;
            } 
            else {            	
            	if (year < namnow) {
            		status =  "Chưa";
            		
            	}
            	else if (year == namnow) {
            		if (month < thangnow) {
            			status = "Chưa";
            		}
            		else if (month == thangnow) {
            			if (day < ngaynow) {
            				status = "Chưa";
            			}
            			else if (day == ngaynow) {
            				status = "Hôm nay";
            			}
            			else {
            				status = "Quá hạn";
            			}
            		}
            		else {
            			status = "Quá hạn";
            		}
            	}
            	else {
            		status = "Quá hạn";
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(flag);
		return status ;        
           
	}
	
	private String getTrangThaiStyle(String trangThai) {
	    String textColor = "";
	    String bgColor = "";
	    switch (trangThai) {
	        case "Chưa":
	            textColor = "#448DF2";
	            bgColor = "#E8F1FD";
	            break;
	        case "Hôm nay":
	            textColor = "#41C588";
	            bgColor = "#E7F8F0";
	            break;
	        case "Quá hạn":
	            textColor = "#F36960";
	            bgColor = "#FEECEB";
	            break;
	        case "Trực tiếp":
	            textColor = "#F9A63A";
	            bgColor = "#FEF4E6";
	            break;
	    }
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-background-radius: 20; -fx-padding: 0 10;", bgColor, textColor);
	}

	private String getButtonStyle(String trangThai) {
	    String textColor = "#1570EF";
	    String bgColor = "";
	    switch (trangThai) {
	        case "Chưa":
	            bgColor = "#E8F1FD";
	            break;
	        case "Hôm nay":
	            bgColor = "#FFFFFF";
	            break;
	        default:
	            bgColor = "#E8F1FD"; // Default background color for other states
	    }
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-background-radius: 20; -fx-padding: 5 10;", bgColor, textColor);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		ObservableList<PHIEUDATPHONG> listStaff = PHIEUDATPHONG_BLL.listBookRoom();
//		showListBookRoom(listStaff);
	}

}

package UI.BookRoom;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import BLL.HOADONPHONG_BLL;
import BLL.KHACHHANG_BLL;
import BLL.PHIEUDATPHONG_BLL;
import DTO.HOADONPHONG;
import DTO.KHACHHANG;
import DTO.PHIEUDATPHONG;
import UI.MainWindow_Controller;
import UI.Resource.itemBookRoom_Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class bookRoomWindow_Controller implements Initializable{
	@FXML
    private VBox listBookRoom_vbox;
	
    @FXML
    private Button bookRoom_btn;
    
    @FXML
    private Button notyet_btn;

    @FXML
    private Button offline_btn;

    @FXML
    private Button outofdate_btn;

    @FXML
    private Button today_btn;
    
    @FXML
    private Pagination pagination;
    
    @FXML
    private void handleNotYetButtonAction(ActionEvent event) {
        toggleStatus("Chưa", notyet_btn);
    }

    @FXML
    private void handleOfflineButtonAction(ActionEvent event) {
        toggleStatus("Trực tiếp", offline_btn);
    }

    @FXML
    private void handleOutOfDateButtonAction(ActionEvent event) {
        toggleStatus("Quá hạn", outofdate_btn);
    }

    @FXML
    private void handleTodayButtonAction(ActionEvent event) {
        toggleStatus("Hôm nay", today_btn);
    }
    
//    @FXML
//    private Button reset_btn;
//
//    @FXML
//    private void handleResetButtonAction(ActionEvent event) {
//        selectedStatuses.clear();
//        notyet_btn.setStyle(getStatusStyle("Chưa"));
//        offline_btn.setStyle(getStatusStyle("Trực tiếp"));
//        outofdate_btn.setStyle(getStatusStyle("Quá hạn"));
//        today_btn.setStyle(getStatusStyle("Hôm nay"));
//        showListBookRoom(PHIEUDATPHONG_BLL.listBookRoom()); 
//    }
    
    private static final int ITEMS_PER_PAGE = 6;
    private ObservableList<Object[]> listBookRoom;
    
    private int calculatePageCount() {
        return (int) Math.ceil((double) listBookRoom.size() / ITEMS_PER_PAGE);
    }
    
    private ContextMenu contextMenu = new ContextMenu();
    
    private double x = 0;
	private double y = 0;
	
	public void refreshBookRoomList() {
        listBookRoom = PHIEUDATPHONG_BLL.listBookRoomWithReceiveCount();
        listBookRoom = createListBookRoom(listBookRoom);
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
    }
    
	private Set<String> selectedStatuses = new HashSet<>();
	
	private void toggleStatus(String status, Button button) {
	    if (selectedStatuses.contains(status)) {
	        selectedStatuses.remove(status);
	        button.setStyle(getStatusStyle(status)); 
	    } else {
	        selectedStatuses.add(status);
	        button.setStyle("-fx-background-color: #FF6347;"); 
	    }
	    filterBookRoomList();
	}
	
	private VBox createPage(int pageIndex) {	    
		VBox page = new VBox();
	    int startIndex = pageIndex * ITEMS_PER_PAGE;
	    int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, listBookRoom.size());

	    for (int i = startIndex; i < endIndex; i++) {
	        Object[] bookRoom = listBookRoom.get(i);
	        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBookRoom.fxml"));
                Parent bookRoomDataPane = loader.load();
                itemBookRoom_Controller controller = loader.getController();
                controller.setBookRoom(bookRoom);

                Button checkIn_btn = controller.getCheckIn_btn();
                Button contextMenu_btn = controller.getContextMenu_btn();

                checkIn_btn.setOnAction(eventCheckIn -> {
                    try {
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/UI/BookRoom/receiveRoom.fxml"));
                        Parent root = loader1.load();

                        root.setOnMousePressed((MouseEvent event1) -> {
                            x = event1.getSceneX();
                            y = event1.getSceneY();
                        });

                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(checkIn_btn.getScene().getWindow());
                        Scene scene = new Scene(root);

                        receiveRoom_Controller receiveRoom = loader1.getController();
                        receiveRoom.setData(bookRoom);
                        

                        root.setOnMouseDragged((MouseEvent event1) -> {
                            stage.setX(event1.getScreenX() - x);
                            stage.setY(event1.getScreenY() - y);
                        });

                        stage.setScene(scene);
                        stage.showAndWait();

                        refreshBookRoomList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                contextMenu_btn.setOnMouseClicked(event -> {
                    if (contextMenu.isShowing()) {
                        contextMenu.hide();
                        return;
                    }
                    contextMenu.getItems().clear();

                    MenuItem billItem = new MenuItem("Xem hóa đơn");
                    MenuItem countdownItem = new MenuItem("Thời gian còn lại");
                    MenuItem deleteItem = new MenuItem("Xóa");

                    billItem.setOnAction(eventEditStaff -> {
                        try {
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("billBookRoom.fxml"));
                            Parent root = loader2.load();

//                            root.setOnMousePressed((MouseEvent event1) -> {
//                                x = event1.getSceneX();
//                                y = event1.getSceneY();
//                            });

                            PHIEUDATPHONG bookRoomBill = PHIEUDATPHONG_BLL.getBookRoom(bookRoom[0].toString());
                            HOADONPHONG roomBill = HOADONPHONG_BLL.getRoomBill(bookRoom[0].toString());
                            ObservableList<Object[]> listDetail = HOADONPHONG_BLL.listDetailBill(bookRoom[0].toString());
                            billBookRoom_Controller controllerBill = loader2.getController();
                            controllerBill.setData(bookRoomBill.getNGAYNHAN(), bookRoomBill.getNGAYTRA(), roomBill, listDetail);
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.TRANSPARENT);
							stage.initModality(Modality.WINDOW_MODAL);
							stage.initOwner(contextMenu_btn.getScene().getWindow());
                            Scene scene = new Scene(root);

//                            root.setOnMouseDragged((MouseEvent event1) -> {
//                                stage.setX(event1.getScreenX() - x);
//                                stage.setY(event1.getScreenY() - y);
//                            });

                            stage.setScene(scene);
                            stage.showAndWait();

//                            refreshBookRoomList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    deleteItem.setOnAction(deleteEvent -> {
                        // Delete item logic here
                    });

                    contextMenu.getItems().addAll(billItem, countdownItem, deleteItem);
                    contextMenu.show(contextMenu_btn, event.getScreenX(), event.getScreenY());
                });

                page.getChildren().add(bookRoomDataPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	    
	    return page; 
	}

	public ObservableList<Object[]> createListBookRoom(ObservableList<Object[]> list) {
		ObservableList<Object[]> listBookRoomWithOutReceive = FXCollections.observableArrayList();
		listBookRoom = PHIEUDATPHONG_BLL.listBookRoomWithReceiveCount();
	    for (Object[] item : listBookRoom) {
	    	int receivedRoom =(int) item[7]; 
	        int allBookRoom = (int) item[6]; 
	    	int soLuong = allBookRoom - receivedRoom;
	        for (int i = 0; i < soLuong; i++) {
	        	listBookRoomWithOutReceive.add(item);
	        }
	    }
	    return listBookRoomWithOutReceive;
	}
	
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
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(bookRoom_btn.getScene().getWindow());
            Scene scene = new Scene(root);
            
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);            
                stage.setY(event.getScreenY() - y);       
            });


            stage.setScene(scene);     
    		stage.showAndWait();
            
    		refreshBookRoomList();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getStatus(String ngayNhan, String hinhThuc) {
	
		LocalDate now = LocalDate.now();
        int ngaynow = now.getDayOfMonth();
        int thangnow = now.getMonthValue();
        int namnow = now.getYear();
        int day = 0, month = 0, year = 0;
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
		return status ;        
           
	}
	
	private String getStatusStyle(String status) {
	    String textColor = "";
	    String bgColor = "";
	    switch (status) {
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
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s", bgColor, textColor);
	}
	
	private void filterBookRoomList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    ObservableList<Object[]> list = PHIEUDATPHONG_BLL.listBookRoomWithReceiveCount();
	    list = createListBookRoom(list);
	    for (Object[] item : list) { 
	        String status = getStatus(item[3].toString(), item[5].toString());
	        if (selectedStatuses.isEmpty() || selectedStatuses.contains(status)) {
	            filteredList.add(item);
	        }
	    }
	    listBookRoom = filteredList;
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listBookRoom = PHIEUDATPHONG_BLL.listBookRoomWithReceiveCount();
		listBookRoom = createListBookRoom(listBookRoom);
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}

}

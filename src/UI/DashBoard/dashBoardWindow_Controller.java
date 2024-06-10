package UI.DashBoard;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BLL.DICHVU_BLL;
import BLL.KHACHHANG_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.NHANVIEN_BLL;
import BLL.THAMSO_BLL;
import DTO.LOAIPHONG;
import DTO.NHANVIEN;
import DTO.THAMSO;
import UI.MainWindow_Controller;
import UI.Customer.editCustomer_Controller;
import UI.Param.editService_Controller;
import UI.Resource.itemService_Controller;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.SystemMessage;

public class dashBoardWindow_Controller implements Initializable{
    
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
    private Button contextMenu_btn1;

    @FXML
    private Button contextMenu_btn2;

    @FXML
    private Button contextMenu_btn3;

    @FXML
    private Button contextMenu_btn4;

    @FXML
    private AnchorPane dashBoard_form;

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
    
    @FXML
    private Label total1;

    @FXML
    private Label total2;

    @FXML
    private Label total3;

    @FXML
    private Label total4;
    
    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;
      
    private String type;
    
    private double x = 0;
    private double y = 0;
    
    private ContextMenu contextMenu = new ContextMenu();
    
    public void standardType(Object[] item) {
    	int id = Integer.parseInt(item[5].toString());
    	areaLabel1.setText(item[4].toString());
    	availabelRoomLabel1.setText(item[8].toString());
    	maxPeopleLabel1.setText(item[3].toString());   
    	
    	Double gia = Double.parseDouble(item[2].toString());
    	String formattedPrice = String.format("đ %.0f", gia);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	String finalPrice = sb.toString();
    	moneyPerNightLabel1.setText(finalPrice);
    	
    	numberOfPaymentLabel1.setText(item[7].toString() + " đang được sử dụng");
    	total1.setText("/" + item[6].toString());
    	roomTypeLabel1.setText(item[0].toString());
    	typeBedLabel1.setText(item[1].toString());
    	    	    
    	type = SystemMessage.getType();
    	if (type.equals("Nhân viên lễ tân")) {
    		Image newImage = new Image("/Images/details.png");
    		image1.setImage(newImage);
    		contextMenu_btn1.setOnMouseClicked(eventContextMenu -> {
    			try {                        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                    Parent root = loader.load();

                    root.setOnMousePressed((MouseEvent event) -> {            
                        x = event.getSceneX();            
                        y = event.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);

                    roomTypeDetails_Controller roomTypeDetails = loader.getController();
                    roomTypeDetails.setRoomType(id);

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);            
                        stage.setY(event.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.showAndWait();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    		});
    	}
    	else {
    		contextMenu_btn1.setOnMouseClicked(eventContextMenu -> {
        		if (contextMenu.isShowing()) {
                    contextMenu.hide();
                    return;
                }
                contextMenu.getItems().clear();
                MenuItem editItem = new MenuItem("Chỉnh sửa");
                MenuItem detailItem = new MenuItem("Chi tiết");
                
                editItem.setOnAction(editEvent -> {
                	try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("editRoomType.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        showTypeRoom();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        	    
        	    detailItem.setOnAction(detailEvent -> {
        	    	try {                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        roomTypeDetails_Controller roomTypeDetails = loader.getController();
                        roomTypeDetails.setRoomType(id);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        	    });
                	
                contextMenu.getItems().addAll(editItem, detailItem);  	
            	contextMenu.show(contextMenu_btn1, eventContextMenu.getScreenX(), eventContextMenu.getScreenY());
                
            });
    	}
    	
	}
    
    public void laoperaType(Object[] item) {
    	int id = Integer.parseInt(item[5].toString());
    	areaLabel4.setText(item[4].toString());
    	availabelRoomLabel4.setText(item[8].toString());
    	maxPeopleLabel4.setText(item[3].toString());   
    	
    	Double gia = Double.parseDouble(item[2].toString());
    	String formattedPrice = String.format("đ %.0f", gia);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	String finalPrice = sb.toString();
    	moneyPerNightLabel4.setText(finalPrice);
    	
    	numberOfPaymentLabel4.setText(item[7].toString() + " đang được sử dụng");
    	total4.setText("/" + item[6].toString());
    	roomTypeLabel4.setText(item[0].toString());
    	typeBedLabel4.setText(item[1].toString());
    	    	    
    	type = SystemMessage.getType();
    	if (type.equals("Nhân viên lễ tân")) {
    		Image newImage = new Image("/Images/details.png");
    		image4.setImage(newImage);
    		contextMenu_btn4.setOnMouseClicked(eventContextMenu -> {
    			try {                        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                    Parent root = loader.load();

                    root.setOnMousePressed((MouseEvent event) -> {            
                        x = event.getSceneX();            
                        y = event.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);

                    roomTypeDetails_Controller roomTypeDetails = loader.getController();
                    roomTypeDetails.setRoomType(id);

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);            
                        stage.setY(event.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.showAndWait();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    		});
    	}
    	else {
    		contextMenu_btn4.setOnMouseClicked(eventContextMenu -> {
        		if (contextMenu.isShowing()) {
                    contextMenu.hide();
                    return;
                }
                contextMenu.getItems().clear();
                MenuItem editItem = new MenuItem("Chỉnh sửa");
                MenuItem detailItem = new MenuItem("Chi tiết");
                
                editItem.setOnAction(editEvent -> {
                	try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("editRoomType.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        showTypeRoom();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        	    
        	    detailItem.setOnAction(detailEvent -> {
        	    	try {                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        roomTypeDetails_Controller roomTypeDetails = loader.getController();
                        roomTypeDetails.setRoomType(id);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        	    });
                	
                contextMenu.getItems().addAll(editItem, detailItem);  	
            	contextMenu.show(contextMenu_btn1, eventContextMenu.getScreenX(), eventContextMenu.getScreenY());
                
            });
    	}
	}
    
    public void deluxeType(Object[] item) {
    	int id = Integer.parseInt(item[5].toString());
    	areaLabel2.setText(item[4].toString());
    	availabelRoomLabel2.setText(item[8].toString());
    	maxPeopleLabel2.setText(item[3].toString());   
    	
    	Double gia = Double.parseDouble(item[2].toString());
    	String formattedPrice = String.format("đ %.0f", gia);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	String finalPrice = sb.toString();
    	moneyPerNightLabel2.setText(finalPrice);
    	
    	numberOfPaymentLabel2.setText(item[7].toString() + " đang được sử dụng");
    	total2.setText("/" + item[6].toString());
    	roomTypeLabel2.setText(item[0].toString());
    	typeBedLabel2.setText(item[1].toString());
    	    	    
    	type = SystemMessage.getType();
    	if (type.equals("Nhân viên lễ tân")) {
    		Image newImage = new Image("/Images/details.png");
    		image2.setImage(newImage);
    		contextMenu_btn2.setOnMouseClicked(eventContextMenu -> {
    			try {                        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                    Parent root = loader.load();

                    root.setOnMousePressed((MouseEvent event) -> {            
                        x = event.getSceneX();            
                        y = event.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);

                    roomTypeDetails_Controller roomTypeDetails = loader.getController();
                    roomTypeDetails.setRoomType(id);

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);            
                        stage.setY(event.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.showAndWait();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    		});
    	}
    	else {
    		contextMenu_btn2.setOnMouseClicked(eventContextMenu -> {
        		if (contextMenu.isShowing()) {
                    contextMenu.hide();
                    return;
                }
                contextMenu.getItems().clear();
                MenuItem editItem = new MenuItem("Chỉnh sửa");
                MenuItem detailItem = new MenuItem("Chi tiết");
                
                editItem.setOnAction(editEvent -> {
                	try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("editRoomType.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        showTypeRoom();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        	    
        	    detailItem.setOnAction(detailEvent -> {
        	    	try {                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        roomTypeDetails_Controller roomTypeDetails = loader.getController();
                        roomTypeDetails.setRoomType(id);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        	    });
                	
                contextMenu.getItems().addAll(editItem, detailItem);  	
            	contextMenu.show(contextMenu_btn1, eventContextMenu.getScreenX(), eventContextMenu.getScreenY());
                
            });
    	}
	}
    
    public void premiumType(Object[] item) {
    	int id = Integer.parseInt(item[5].toString());
    	areaLabel3.setText(item[4].toString());
    	availabelRoomLabel3.setText(item[8].toString());
    	maxPeopleLabel3.setText(item[3].toString());   
    	
    	Double gia = Double.parseDouble(item[2].toString());
    	String formattedPrice = String.format("đ %.0f", gia);
    	StringBuilder sb = new StringBuilder(formattedPrice);
    	int length = sb.length();
    	for (int i = length - 3; i > 0; i -= 3) {
    	    sb.insert(i, ".");
    	}
    	String finalPrice = sb.toString();
    	moneyPerNightLabel3.setText(finalPrice);
    	
    	numberOfPaymentLabel3.setText(item[7].toString() + " đang được sử dụng");
    	total3.setText("/" + item[6].toString());
    	roomTypeLabel3.setText(item[0].toString());
    	typeBedLabel3.setText(item[1].toString());
    	    	    
    	type = SystemMessage.getType();
    	if (type.equals("Nhân viên lễ tân")) {
    		Image newImage = new Image("/Images/details.png");
    		image3.setImage(newImage);
    		contextMenu_btn3.setOnMouseClicked(eventContextMenu -> {
    			try {                        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                    Parent root = loader.load();

                    root.setOnMousePressed((MouseEvent event) -> {            
                        x = event.getSceneX();            
                        y = event.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);

                    roomTypeDetails_Controller roomTypeDetails = loader.getController();
                    roomTypeDetails.setRoomType(id);

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);            
                        stage.setY(event.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.showAndWait();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    		});
    	}
    	else {
    		contextMenu_btn3.setOnMouseClicked(eventContextMenu -> {
        		if (contextMenu.isShowing()) {
                    contextMenu.hide();
                    return;
                }
                contextMenu.getItems().clear();
                MenuItem editItem = new MenuItem("Chỉnh sửa");
                MenuItem detailItem = new MenuItem("Chi tiết");
                
                editItem.setOnAction(editEvent -> {
                	try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("editRoomType.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        showTypeRoom();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        	    
        	    detailItem.setOnAction(detailEvent -> {
        	    	try {                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("roomTypeDetails.fxml"));
                        Parent root = loader.load();

                        root.setOnMousePressed((MouseEvent event) -> {            
                            x = event.getSceneX();            
                            y = event.getSceneY();    
                        });
                        
                        Stage stage = new Stage();        
                        stage.initStyle(StageStyle.TRANSPARENT);        
                        Scene scene = new Scene(root);

                        roomTypeDetails_Controller roomTypeDetails = loader.getController();
                        roomTypeDetails.setRoomType(id);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        	    });
                	
                contextMenu.getItems().addAll(editItem, detailItem);  	
            	contextMenu.show(contextMenu_btn1, eventContextMenu.getScreenX(), eventContextMenu.getScreenY());
                
            });
    	}
	}
    
    
    public void showTypeRoom() {
    	ObservableList<Object[]> listRoomType = LOAIPHONG_BLL.roomType();
    	for(Object[] item : listRoomType){
    		if (Integer.parseInt(item[5].toString()) == 1) {
    	    	standardType(item);
    	    	continue;
    		}
    		if (Integer.parseInt(item[5].toString()) == 2) {
    	    	deluxeType(item);
    	    	continue;
    		}
    		if (Integer.parseInt(item[5].toString()) == 3) {
    			premiumType(item);
    	    	continue;
    		}
    		if (Integer.parseInt(item[5].toString()) == 4) {
    			laoperaType(item);
    	    	continue;
    		}			
    	}
    	int emptyRoom = Integer.parseInt(availabelRoomLabel1.getText()) + 
                Integer.parseInt(availabelRoomLabel2.getText()) + 
                Integer.parseInt(availabelRoomLabel3.getText()) + 
                Integer.parseInt(availabelRoomLabel4.getText());
    	numberEmptyRoomLabel.setText(String.valueOf(emptyRoom));
    	int[] bookingsAndCustomersToday = LOAIPHONG_BLL.countBookingsAndCustomersToday();
    	numberCheckInLabel.setText(String.valueOf(bookingsAndCustomersToday[0]));
    	numberCustomerLabel.setText(String.valueOf(bookingsAndCustomersToday[1]));

        int[] invoicesAndUnbookedRooms = LOAIPHONG_BLL.countCheckedOutInvoicesAndUnbookedRooms();
        numberCheckOutLabel.setText(String.valueOf(invoicesAndUnbookedRooms[0]));
        numberBookedRoomLabel.setText(String.valueOf(invoicesAndUnbookedRooms[1]));
    }
   

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	showTypeRoom();
	}
}

package UI.DashBoard;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BLL.DICHVU_BLL;
import BLL.KHACHHANG_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.THAMSO_BLL;
import DTO.LOAIPHONG;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    private double x = 0;
    private double y = 0;
    
    private ContextMenu contextMenu = new ContextMenu();
    
    public void standardType(ObservableList<Object[]> list) {
    	for(Object[] item : list)
    	{
    		int id = Integer.parseInt(item[5].toString());
        	areaLabel1.setText(item[4].toString());
        	//availabelRoomLabel1.setText(item[2].toString());
        	maxPeopleLabel1.setText(item[3].toString());   
        	
        	Double gia = Double.parseDouble(item[2].toString());
        	String formattedPrice = String.format("đ %.0f", gia);
        	StringBuilder sb = new StringBuilder(formattedPrice);
        	int length = sb.length();
        	for (int i = length - 3; i > 0; i -= 3) {
        	    sb.insert(i, ".");
        	}
        	//sb.append(" VND");
        	String finalPrice = sb.toString();
        	//String formattedPrice = String.format("đ %,.0f", gia);
        	moneyPerNightLabel1.setText(finalPrice);
        	
        	//numberOfPaymentLabel1.setText(item[2].toString());
        	roomTypeLabel1.setText(item[0].toString());
        		
        	if (item[1].toString().equalsIgnoreCase("Giường King & Giường Queen"))
        		typeBedLabel1.setText("King & Queen");
        	else
        		typeBedLabel1.setText(item[1].toString());
        	    	    
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
                        stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(dashBoard_form.getScene().getWindow());
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        refreshStandardType();
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
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(dashBoard_form.getScene().getWindow());
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
    
    public void laoperaType(ObservableList<Object[]> list) {
    	for(Object[] item : list)
    	{
    		int id = Integer.parseInt(item[5].toString());
        	areaLabel4.setText(item[4].toString());
        	//availabelRoomLabel1.setText(item[2].toString());
        	maxPeopleLabel4.setText(item[3].toString()); 
        	
        	Double gia = Double.parseDouble(item[2].toString());
        	String formattedPrice = String.format("đ %.0f", gia);
        	StringBuilder sb = new StringBuilder(formattedPrice);
        	int length = sb.length();
        	for (int i = length - 3; i > 0; i -= 3) {
        	    sb.insert(i, ".");
        	}
        	//sb.append(" VND");
        	String finalPrice = sb.toString();
        	//String formattedPrice = String.format("đ %,.0f", gia);
        	moneyPerNightLabel4.setText(finalPrice);
        	
        	//numberOfPaymentLabel1.setText(item[2].toString());
        	roomTypeLabel4.setText(item[0].toString());
        		
        	if (item[1].toString().equalsIgnoreCase("Giường King & Giường Queen"))
        		typeBedLabel4.setText("King & Queen");
        	else
        		typeBedLabel4.setText(item[1].toString());
        	    	    
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
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(dashBoard_form.getScene().getWindow());
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        refreshStandardType();
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
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(dashBoard_form.getScene().getWindow());
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
    
    public void deluxeType(ObservableList<Object[]> list) {
    	for(Object[] item : list)
    	{
    		int id = Integer.parseInt(item[5].toString());
        	areaLabel2.setText(item[4].toString());
        	//availabelRoomLabel1.setText(item[2].toString());
        	maxPeopleLabel2.setText(item[3].toString());   
        	
        	Double gia = Double.parseDouble(item[2].toString());
        	String formattedPrice = String.format("đ %.0f", gia);
        	StringBuilder sb = new StringBuilder(formattedPrice);
        	int length = sb.length();
        	for (int i = length - 3; i > 0; i -= 3) {
        	    sb.insert(i, ".");
        	}
        	//sb.append(" VND");
        	String finalPrice = sb.toString();
        	//String formattedPrice = String.format("đ %,.0f", gia);
        	moneyPerNightLabel2.setText(finalPrice);
        	
        	//numberOfPaymentLabel2.setText(item[2].toString());
        	roomTypeLabel2.setText(item[0].toString());
        		
        	if (item[1].toString().equalsIgnoreCase("Giường King & Giường Queen"))
        		typeBedLabel2.setText("King & Queen");
        	else
        		typeBedLabel2.setText(item[1].toString());
        	    	    
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
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(dashBoard_form.getScene().getWindow());
                        Scene scene = new Scene(root);

                        editRoomType_Controller editRoomType = loader.getController();
                        editRoomType.setRoomType(item);

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);            
                            stage.setY(event.getScreenY() - y);       
                        });
                        
                        stage.setScene(scene);     
                        stage.showAndWait();
                        
                        refreshStandardType();
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
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(dashBoard_form.getScene().getWindow());
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
    
    public void premiumType(ObservableList<Object[]> list) {
    	for(Object[] item : list)
    	{
    		int id = Integer.parseInt(item[5].toString());
        	areaLabel3.setText(item[4].toString());
        	//availabelRoomLabel1.setText(item[2].toString());
        	maxPeopleLabel3.setText(item[3].toString()); 
        	
        	Double gia = Double.parseDouble(item[2].toString());
        	String formattedPrice = String.format("đ %.0f", gia);
        	StringBuilder sb = new StringBuilder(formattedPrice);
        	int length = sb.length();
        	for (int i = length - 3; i > 0; i -= 3) {
        	    sb.insert(i, ".");
        	}
        	//sb.append(" VND");
        	String finalPrice = sb.toString();
        	//String formattedPrice = String.format("đ %,.0f", gia);
        	moneyPerNightLabel3.setText(finalPrice);
        	
        	//numberOfPaymentLabel1.setText(item[2].toString());
        	roomTypeLabel3.setText(item[0].toString());
        		
        	if (item[1].toString().equalsIgnoreCase("Giường King & Giường Queen"))
        		typeBedLabel3.setText("King & Queen");
        	else
        		typeBedLabel3.setText(item[1].toString());
        	    	    
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
                        
                        refreshStandardType();
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
    
    public void refreshStandardType() {
    	ObservableList<Object[]> listRoomType1 = LOAIPHONG_BLL.roomType(1);
    	standardType(listRoomType1);
    }
    
    public void refreshDeluxeType() {
    	ObservableList<Object[]> listRoomType2 = LOAIPHONG_BLL.roomType(2);
    	deluxeType(listRoomType2);
    }
    
    public void refreshPremiumType() {
    	ObservableList<Object[]> listRoomType3 = LOAIPHONG_BLL.roomType(3);
    	premiumType(listRoomType3);
    }
    
    public void refreshLaoperaType() {
    	ObservableList<Object[]> listRoomType4 = LOAIPHONG_BLL.roomType(4);
    	laoperaType(listRoomType4);
    }
    

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	ObservableList<Object[]> listRoomType1 = LOAIPHONG_BLL.roomType(1);
    	ObservableList<Object[]> listRoomType2 = LOAIPHONG_BLL.roomType(2);
    	ObservableList<Object[]> listRoomType3 = LOAIPHONG_BLL.roomType(3);
    	ObservableList<Object[]> listRoomType4 = LOAIPHONG_BLL.roomType(4);
    	standardType(listRoomType1);
    	deluxeType(listRoomType2);
    	premiumType(listRoomType3);
    	laoperaType(listRoomType4);
	}
}

package UI;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import BLL.NHANVIEN_BLL;
import DTO.KHACHHANG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class customerWindow_Controller implements Initializable {
	
	@FXML
    private Button addCustomer_btn;
    
    @FXML
    private AnchorPane customerWindow_form;

    @FXML
    private VBox listCustomer_vbox;

    @FXML
    private TextField searchCustomer;
    
    private MainWindow_Controller mainWindowController;

    // Phương thức để thiết lập tham chiếu của main window controller từ bên ngoài
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
	private double x = 0;
	private double y = 0;
	
//	private String ngaysinh;
//	
//	public String getFormattedNgaySinh() {
//        try {
//            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = originalFormat.parse(this.ngaysinh);
//            return targetFormat.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return this.ngaysinh;
//        }
//    }

	public void refreshCustomerList() {
        // Xóa tất cả các nút con trong VBox
        listCustomer_vbox.getChildren().clear();
        ObservableList<KHACHHANG> listCustomer = KHACHHANG_BLL.listCustomer();
        showListCustomer(listCustomer);
    }

	public void showListCustomer(ObservableList<KHACHHANG> list) {	
	    for(KHACHHANG item : list) {
	    	HBox hBox = new HBox();
	    	hBox.setSpacing(25);
	    	hBox.setPrefHeight(30);
	    	hBox.setAlignment(Pos.CENTER);
	    	Label maKH = new Label(item.getMAKH());
	    	maKH.setPrefWidth(90); //106
	    	maKH.setAlignment(Pos.CENTER);
	    	Label tenKH = new Label(item.getTENKH());
	    	tenKH.setPrefWidth(140);
	    	Label cccd = new Label(item.getCCCD());
	    	cccd.setPrefWidth(110);
	    	Label gender = new Label(item.getGIOITINH());
	    	gender.setPrefWidth(50);
	    	Label birthday = new Label(item.getNGAYSINH());
	    	birthday.setPrefWidth(80);
	    	Label phone = new Label(item.getSDT());
	    	phone.setPrefWidth(85);
	    	Label country = new Label(item.getQUOCTICH());
	    	country.setPrefWidth(60);
	    	ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/ellipsis_v.png")));
	    	imageView.setFitWidth(12);
	    	imageView.setFitHeight(12);
	    	Button button = new Button();
	    	button.setStyle("-fx-background-color: transparent;");
	    	button.setGraphic(imageView);
	    	button.setOnMouseClicked(event -> {
	    		KHACHHANG clickedCustomer = item;
	    	    ContextMenu contextMenu = new ContextMenu();
	    	    
	    	    // Tạo các mục menu
	    	    MenuItem editItem = new MenuItem("Chỉnh sửa");
	    	    MenuItem deleteItem = new MenuItem("Xóa");
	    	    MenuItem detailItem = new MenuItem("Chi tiết");
	    	    		    	   		 	    
	    	    editItem.setOnAction(eventEditCustomer -> {
	                try {
	                	AnchorPane anchorPane = mainWindowController.getAnchorPane();
	            	    anchorPane.setVisible(true);
	            	    
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
	                    Parent root = loader.load();

	                    root.setOnMousePressed((MouseEvent event1) -> {            
	                        x = event1.getSceneX();            
	                        y = event1.getSceneY();    
	                    });
	                    
	                    Stage stage = new Stage();        
	                    stage.initStyle(StageStyle.TRANSPARENT);        
	                    Scene scene = new Scene(root);

	                    editCustomer_Controller editCustomer = loader.getController();
	                    editCustomer.setCustomer(clickedCustomer);

	                    root.setOnMouseDragged((MouseEvent event1) -> {
	                        stage.setX(event1.getScreenX() - x);            
	                        stage.setY(event1.getScreenY() - y);       
	                    });
	                    
	                    stage.setScene(scene);     
	                    stage.showAndWait();
	                    
	                    anchorPane.setVisible(false);
	                    refreshCustomerList();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
	    	    
	    	    deleteItem.setOnAction(deleteEvent -> {
	    	    	KHACHHANG_BLL.deleteCustomer(clickedCustomer);
	    	    	refreshCustomerList();
	    	    });
	    	    
	    	    detailItem.setOnAction(detailEvent -> {
	    	    	try {
	                	AnchorPane anchorPane = mainWindowController.getAnchorPane();
	            	    anchorPane.setVisible(true);
	            	    
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("customerDetails.fxml"));
	                    Parent root = loader.load();

	                    root.setOnMousePressed((MouseEvent event1) -> {            
	                        x = event1.getSceneX();            
	                        y = event1.getSceneY();    
	                    });
	                    
	                    Stage stage = new Stage();        
	                    stage.initStyle(StageStyle.TRANSPARENT);        
	                    Scene scene = new Scene(root);

	                    customerDetails_Controller customerDetails = loader.getController();
	                    customerDetails.setCustomer(clickedCustomer);

	                    root.setOnMouseDragged((MouseEvent event1) -> {
	                        stage.setX(event1.getScreenX() - x);            
	                        stage.setY(event1.getScreenY() - y);       
	                    });
	                    
	                    stage.setScene(scene);     
	                    stage.showAndWait();
	                    
	                    anchorPane.setVisible(false);
	                    //refreshCustomerList();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	    	    });
	    	    
	    	    contextMenu.getItems().addAll(editItem, deleteItem, detailItem);
	    	    
	    	    contextMenu.show(button, event.getScreenX(), event.getScreenY());
	    	    
	    	});
	    	hBox.getChildren().addAll(maKH, tenKH, cccd, gender, birthday, phone, country,button);
	    	hBox.setStyle("	-fx-background-color: #FFFFFF;\r\n"	    			
	    			+ "	-fx-border-color:  #E8F1FD;\r\n"	    			
	    			+ "-fx-font-size: 14px; \r\n"
	    			+ "-fx-border-width: 0 0 2 0;");
	    	listCustomer_vbox.getChildren().add(hBox);
	    }		
	}	   
   

	
	public void addCustomer() throws IOException {		
		AnchorPane anchorPane = mainWindowController.getAnchorPane();
	    anchorPane.setVisible(true);
		
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
		
		stage.setScene(scene);     
		stage.showAndWait();
        
		anchorPane.setVisible(false);
        refreshCustomerList();
	}

    //Tìm kiếm khách hàng
	public void search() {
		searchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
		    String searchText = newValue.trim();
		    ObservableList<KHACHHANG> filteredList = FXCollections.observableArrayList();

		    if (searchText.isEmpty()) {
		        filteredList.addAll(KHACHHANG_BLL.listCustomer());
		    } else {
		        for (KHACHHANG customer : KHACHHANG_BLL.listCustomer()) {
		            if (customer.getTENKH().toLowerCase().contains(searchText.toLowerCase())) {
		                filteredList.add(customer);
		            }
		        }
		    }
		    // Xóa tất cả các nút con trong VBox
		    listCustomer_vbox.getChildren().clear();
		    showListCustomer(filteredList);
		});
	}

		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		setupCustomerTableContextMenu();
		ObservableList<KHACHHANG> listCustomer = KHACHHANG_BLL.listCustomer();
		showListCustomer(listCustomer);
		search();
	}
	

}

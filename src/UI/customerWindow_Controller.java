package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import DAO.KHACHHANG_DAO;
import UI.MainWindow_Controller;
import DTO.KHACHHANG;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
	
	private double x = 0;
	private double y = 0;
	
//	private void setupCustomerTableContextMenu(){
//		ContextMenu contextMenu = new ContextMenu();
//		MenuItem editMenuItem = new MenuItem("Edit");
//		MenuItem deleteMenuItem = new MenuItem("Delete");		
//		contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);
//		
//		editMenuItem.setOnAction(eventEditCustomer -> {
//			KHACHHANG item = customer_table.getSelectionModel().getSelectedItem();
//			if (item != null) {
//				 try {
//	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
//	                    Parent root = loader.load();
//	                    
//	                    root.setOnMousePressed((MouseEvent event) -> {            
//	                        x = event.getSceneX();            
//	                        y = event.getSceneY();    
//	                    });
//	                    
//	                    Stage stage = new Stage();        
//	                    stage.initStyle(StageStyle.TRANSPARENT);        
//	                    Scene scene = new Scene(root);
//	                    
//	                    editCustomer_Controller editCustomer = loader.getController();
//						editCustomer.setCustomer(item);	              
//	                    
//	                    root.setOnMouseDragged((MouseEvent event) -> {
//	                        stage.setX(event.getScreenX() - x);            
//	                        stage.setY(event.getScreenY() - y);       
//	                    });
//	                    
//	                    stage.setScene(scene);     
//	                    stage.show();
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }
//			}				
//			
//		});
//		
//		deleteMenuItem.setOnAction(event -> {
//			KHACHHANG item = customer_table.getSelectionModel().getSelectedItem();
//			if (item != null) {
//				// Xu ly xoa khach hang
//				customer_table.getItems().remove(item);
//			}
//		});
//		
//		colControlCustomer.setCellFactory(col -> {
//			return new TableCell<KHACHHANG, Void>() {
//				private final ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/ellipsis_v.png")));				
//				private final Button button = new Button();			
//				{
//		            button.setOnAction(event -> {
//		                KHACHHANG item = getTableView().getItems().get(getIndex());
//		                if (item != null) {
//		                    Scene scene = button.getScene();
//		                    Stage stage = (Stage) scene.getWindow();
//		                    Bounds bounds = button.localToScreen(button.getBoundsInLocal());
//		                    contextMenu.show(stage, bounds.getMaxX(), bounds.getMaxY());
//		                }
//		            });
//		            imageView.setFitWidth(12);
//		            imageView.setFitHeight(12);
//		            button.setGraphic(imageView);
//		            button.setStyle("-fx-background-color: transparent;");
//		            setAlignment(Pos.CENTER);
//				}
//				
//				@Override
//				protected void updateItem(Void item, boolean empty) {
//					super.updateItem(item, empty);
//					setGraphic(empty ? null : button);
//				}
//			};
//		});		
//	}
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
	    	maKH.setPrefWidth(106);
	    	maKH.setAlignment(Pos.CENTER);
	    	Label tenKH = new Label(item.getTENKH());
	    	tenKH.setPrefWidth(130);
	    	Label cccd = new Label(item.getCCCD());
	    	cccd.setPrefWidth(123);
	    	Label gender = new Label(item.getGIOITINH());
	    	gender.setPrefWidth(53);
	    	Label birthday = new Label(item.getNGAYSINH());
	    	birthday.setPrefWidth(82);
	    	Label phone = new Label(item.getSDT());
	    	phone.setPrefWidth(99);
	    	Label country = new Label(item.getQUOCTICH());
	    	country.setPrefWidth(65);
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
	                    
	                    refreshCustomerList();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
	    	    
	    	    deleteItem.setOnAction(deleteEvent -> {
	    	        // Thực hiện hành động xóa ở đây
	    	        // Ví dụ: xóa mục khách hàng
	    	    });
	    	    
	    	    detailItem.setOnAction(detailEvent -> {
	    	        // Thực hiện hành động hiển thị chi tiết ở đây
	    	        // Ví dụ: mở màn hình chi tiết khách hàng
	    	    });
	    	    
	    	    // Thêm các mục menu vào ContextMenu
	    	    contextMenu.getItems().addAll(editItem, deleteItem, detailItem);
	    	    
	    	    // Hiển thị ContextMenu khi nút được nhấp
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
//		MainWindow_Controller mainWindow_Controller = new MainWindow_Controller();
//		mainWindow_Controller.unvisible();
		
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

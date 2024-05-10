package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import DTO.KHACHHANG;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
   
    
	public void showListCustomer() {	
		
		Platform.runLater(() -> {
			ObservableList<HBox> hBoxList = FXCollections.observableArrayList();
		    ObservableList<KHACHHANG> listCustomer = KHACHHANG_BLL.listCustomer();
		    
		    for(KHACHHANG item : listCustomer) {
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
		    	button.setOnAction(event -> {
			    	
		    	});
		    	hBox.getChildren().addAll(maKH, tenKH, cccd, gender, birthday, phone, country,button);
		    	hBox.setStyle("	-fx-background-color: #FFFFFF;\r\n"	    			
		    			+ "	-fx-border-color:  #E8F1FD;\r\n"	    			
		    			+ "-fx-font-size: 14px; \r\n"
		    			+ "-fx-border-width: 0 0 2 0;");
		    	hBoxList.add(hBox);
		    	listCustomer_vbox.getChildren().add(hBox);
		    }		    
		    
		});
		
		
		
	}
	    
	    private KHACHHANG extractCustomerFromHBox(HBox hBox) throws SQLException {
	    	
	        Label maKHLabel = (Label) hBox.getChildren().get(0);	      

	        return KHACHHANG_BLL.getCustomerById(maKHLabel.getText());
	        
	    }
   
	
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
		
		stage.setScene(scene);     
		stage.show();
	}

	//Tìm kiếm khách hàng
//	public void search() {
//		searchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
//		    String searchTerm = newValue.trim();
//		    if (!searchTerm.isEmpty()) {
//		        // Thực hiện tìm kiếm khách hàng với từng ký tự nhập vào
//		        ObservableList<KHACHHANG> filteredList = KHACHHANG_BLL.searchCustomerByName(searchTerm);
//		        customer_table.setItems(filteredList);
//		    } else {
//		        // Nếu ô nhập liệu trống, hiển thị toàn bộ danh sách khách hàng
//		        showListCustomer();
//		    }
//		});
//	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		setupCustomerTableContextMenu();
		showListCustomer();
//		search();
	}
	

}

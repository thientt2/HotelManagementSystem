package UI;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import DTO.KHACHHANG;
import UI.Resource.itemCustomer_Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    
    public MainWindow_Controller getMainWindowController() {
		return mainWindowController;
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
	
	private ContextMenu contextMenu = new ContextMenu();

	public void showListCustomer(ObservableList<KHACHHANG> list) {	
	    for(KHACHHANG item : list) {
	    	try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemCustomer.fxml"));
				Parent customerDataPane = loader.load();
				itemCustomer_Controller controller = loader.getController();
				controller.setCustomer(item);
				Button contextMenu_btn = controller.getContextMenu_btn();
	            contextMenu_btn.setOnMouseClicked(event -> {
	            	if (contextMenu.isShowing()) {
	                    contextMenu.hide();
	                    return;
	                }
	                contextMenu.getItems().clear();

	                MenuItem editItem = new MenuItem("Chỉnh sửa");
	                MenuItem deleteItem = new MenuItem("Xóa");
	                MenuItem detailItem = new MenuItem("Chi tiết");

	                editItem.setOnAction(eventEditCustomer -> {
	                    try {
	                    	AnchorPane anchorPane = mainWindowController.getAnchorPane();
	                	    anchorPane.setVisible(true);
	                	    
	                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
	                        Parent root = loader2.load();

	                        root.setOnMousePressed((MouseEvent event1) -> {            
	                            x = event1.getSceneX();            
	                            y = event1.getSceneY();    
	                        });
	                        
	                        Stage stage = new Stage();        
	                        stage.initStyle(StageStyle.TRANSPARENT);        
	                        Scene scene = new Scene(root);

	                        editCustomer_Controller editCustomer = loader2.getController();
	                        editCustomer.setCustomer(item);

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
	        	    
	        	    deleteItem.setOnAction(deleteEvent -> {
	        	    	KHACHHANG_BLL.deleteCustomer(item);
	        	    	//refreshCustomerList();
	        	    });
	        	    
	        	    detailItem.setOnAction(detailEvent -> {
	        	    	try {
	                    	AnchorPane anchorPane = mainWindowController.getAnchorPane();
	                	    anchorPane.setVisible(true);
	                	    
	                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("customerDetails.fxml"));
	                        Parent root = loader1.load();

	                        root.setOnMousePressed((MouseEvent event1) -> {            
	                            x = event1.getSceneX();            
	                            y = event1.getSceneY();    
	                        });
	                        
	                        Stage stage = new Stage();        
	                        stage.initStyle(StageStyle.TRANSPARENT);        
	                        Scene scene = new Scene(root);

	                        customerDetails_Controller customerDetails = loader1.getController();
	                        customerDetails.setCustomer(item);

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
	        	    contextMenu.show(contextMenu_btn, event.getScreenX(), event.getScreenY());
	            });
	            Platform.runLater(() -> listCustomer_vbox.getChildren().add(customerDataPane));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
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
		ObservableList<KHACHHANG> listCustomer = KHACHHANG_BLL.listCustomer();
		showListCustomer(listCustomer);
		search();
	}
	

}

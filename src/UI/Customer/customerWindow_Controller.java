package UI.Customer;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class customerWindow_Controller implements Initializable {
	
	@FXML
    private Button addCustomer_btn;
    
    @FXML
    private AnchorPane customerWindow_form;
    
    @FXML
    private Pagination pagination;

    @FXML
    private TextField searchCustomer;
    
    
    private static final int ITEMS_PER_PAGE = 8;
    private ObservableList<KHACHHANG> listCustomer= FXCollections.observableArrayList();
    
    private int calculatePageCount() {
        return (int) Math.ceil((double) listCustomer.size() / ITEMS_PER_PAGE);
    }
    
    private VBox createPage(int pageIndex) {	    
		VBox page = new VBox();
	    int startIndex = pageIndex * ITEMS_PER_PAGE;
	    int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, listCustomer.size());

	    for (int i = startIndex; i < endIndex; i++) {
	        KHACHHANG customer = listCustomer.get(i);	        
	        try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemCustomer.fxml"));
				Parent customerDataPane = loader.load();
				itemCustomer_Controller controller = loader.getController();
				controller.setCustomer(customer);
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
	                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
	                        Parent root = loader2.load();

	                        root.setOnMousePressed((MouseEvent event1) -> {            
	                            x = event1.getSceneX();            
	                            y = event1.getSceneY();    
	                        });
	                        
	                        Stage stage = new Stage();        
	                        stage.initStyle(StageStyle.TRANSPARENT);        
	                        stage.initModality(Modality.WINDOW_MODAL);
	                        stage.initOwner(addCustomer_btn.getScene().getWindow());
	                        Scene scene = new Scene(root);

	                        editCustomer_Controller editCustomer = loader2.getController();
	                        editCustomer.setCustomer(customer);

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
	        	    	KHACHHANG_BLL.deleteCustomer(customer);
	        	    	refreshCustomerList();
	        	    });
	        	    
	        	    detailItem.setOnAction(detailEvent -> {
	        	    	try {
               	    
	                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("customerDetails.fxml"));
	                        Parent root = loader1.load();

	                        root.setOnMousePressed((MouseEvent event1) -> {            
	                            x = event1.getSceneX();            
	                            y = event1.getSceneY();    
	                        });
	                        
	                        Stage stage = new Stage();        
	                        stage.initStyle(StageStyle.TRANSPARENT);  
	                        stage.initModality(Modality.WINDOW_MODAL);
	                        stage.initOwner(addCustomer_btn.getScene().getWindow());
	                        Scene scene = new Scene(root);

	                        customerDetails_Controller customerDetails = loader1.getController();
	                        customerDetails.setCustomer(customer);

	                        root.setOnMouseDragged((MouseEvent event1) -> {
	                            stage.setX(event1.getScreenX() - x);            
	                            stage.setY(event1.getScreenY() - y);       
	                        });
	                        
	                        stage.setScene(scene);     
	                        stage.show();	                        
	                        //refreshCustomerList();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	        	    });
	        	    
	        	    contextMenu.getItems().addAll(editItem, deleteItem, detailItem);  	
	        	    contextMenu.show(contextMenu_btn, event.getScreenX(), event.getScreenY());
	            });
	            page.getChildren().add(customerDataPane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }

	    return page; // Return an empty VBox as a placeholder
	}
    
	private double x = 0;
	private double y = 0;
	

	public void refreshCustomerList() {
        listCustomer = KHACHHANG_BLL.listCustomer();
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);	
    }
	
	private ContextMenu contextMenu = new ContextMenu();
	
	public void addCustomer() throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addCustomer.fxml"));
		Parent root = loader.load();
		
		root.setOnMousePressed((MouseEvent event)->{            
			x = event.getSceneX();            
			y = event.getSceneY();    
		});
		
		Stage stage = new Stage();        
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(addCustomer_btn.getScene().getWindow());
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
	public void search(String newValue) {
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
	    listCustomer = filteredList;
	    pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);	
	}

		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		searchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
	        search(newValue);
	    });
		listCustomer = KHACHHANG_BLL.listCustomer();
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);	
	}
	

}

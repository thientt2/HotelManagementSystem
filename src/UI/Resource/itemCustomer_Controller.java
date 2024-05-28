package UI.Resource;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import DTO.KHACHHANG;
import UI.MainWindow_Controller;
import UI.customerDetails_Controller;
import UI.editCustomer_Controller;
import UI.editStaff_Controller;
import UI.staffDetails_Controller;
import UI.staffWindow_Controller;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;

public class itemCustomer_Controller implements Initializable{
	
	@FXML
    private Label birthDay_txt;

    @FXML
    private Label cccd_txt;

    @FXML
    private Button contextMenu_btn;

    @FXML
    private Label gender_txt;

    @FXML
    private Label phoneNumber_txt;

    @FXML
    private Label customerId_txt;

    @FXML
    private Label customerName_txt;

    @FXML
    private Label country_txt;
    
    private double x = 0;
	private double y = 0;
    
    private MainWindow_Controller mainWindowController;
    
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
    private staffWindow_Controller staffController;
	
	public void setStaffController(staffWindow_Controller controller) {
		this.staffController = controller;
	}
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public void showContextMenu() {
		
	}

	private ContextMenu contextMenu = new ContextMenu();

    public void setStaff(KHACHHANG item) {
        customerId_txt.setText(item.getMAKH());
        customerName_txt.setText(item.getTENKH());
        birthDay_txt.setText(item.getNGAYSINH());
        gender_txt.setText(item.getGIOITINH());
        cccd_txt.setText(item.getCCCD());
        phoneNumber_txt.setText(item.getSDT());
        country_txt.setText(item.getQUOCTICH());
        contextMenu_btn.setOnMouseClicked(event -> {
            // Tắt menu khi đã hiện
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
			    	
	}

	public void refreshStaffList() {
		staffController.refreshStaffList();
	}
}
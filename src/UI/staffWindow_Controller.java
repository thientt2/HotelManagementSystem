package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.NHANVIEN_BLL;
import BLL.PHONG_BLL;
import DTO.NHANVIEN;
import UI.Resource.itemRoom_Controller;
import UI.Resource.itemStaff_Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class staffWindow_Controller implements Initializable {
	
	@FXML
    private Button addStaff_btn;

    @FXML
    private Button addUser_btn;

    @FXML
    private VBox listStaff_vbox;

    @FXML
    private TextField searchStaff;
    
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
	
	public void refreshStaffList() {
        // Xóa tất cả các nút con trong VBox
        listStaff_vbox.getChildren().clear();
        ObservableList<NHANVIEN> listStaff = NHANVIEN_BLL.listStaff();
        showListStaff(listStaff);
    }
	
	private ContextMenu contextMenu = new ContextMenu();

	public void showListStaff(ObservableList<NHANVIEN> list) {
 		for(NHANVIEN item : list) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemStaff.fxml"));
				Parent employeeDataPane = loader.load();
				itemStaff_Controller controller = loader.getController();
				controller.setStaff(item);
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

	                editItem.setOnAction(eventEditStaff -> {
	                    try {
	                    	AnchorPane anchorPane = mainWindowController.getAnchorPane();
	                    	anchorPane.setVisible(true);
	                    	           	
	                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("editStaff.fxml"));
	                        Parent root = loader2.load();	                   
	                        
	                        root.setOnMousePressed((MouseEvent event1) -> {
	                            x = event1.getSceneX();
	                            y = event1.getSceneY();
	                        });

	                        Stage stage = new Stage();
	                        stage.initStyle(StageStyle.TRANSPARENT);
	                        Scene scene = new Scene(root);                    	                      

	                        editStaff_Controller editStaff = loader2.getController();
	                        editStaff.setStaff(item);

	                        root.setOnMouseDragged((MouseEvent event1) -> {
	                            stage.setX(event1.getScreenX() - x);
	                            stage.setY(event1.getScreenY() - y);
	                        });

	                        stage.setScene(scene);
	                        stage.showAndWait();

	                        anchorPane.setVisible(false);
	                        refreshStaffList();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                });

	                deleteItem.setOnAction(deleteEvent -> {
	                    if (item != null) {
	                        NHANVIEN_BLL.deleteStaff(item);
	                        refreshStaffList();
	                    }
	                });

	                detailItem.setOnAction(detailEvent -> {
	                    try {
	                        AnchorPane anchorPane = mainWindowController.getAnchorPane();
	                        anchorPane.setVisible(true);

	                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("staffDetails.fxml"));
	                        Parent root = loader1.load();

	                        root.setOnMousePressed((MouseEvent event1) -> {
	                            x = event1.getSceneX();
	                            y = event1.getSceneY();
	                        });

	                        Stage stage = new Stage();
	                        stage.initStyle(StageStyle.TRANSPARENT);
	                        Scene scene = new Scene(root);

	                        staffDetails_Controller staffDetails = loader1.getController();
	                        staffDetails.setStaff(item);

	                        root.setOnMouseDragged((MouseEvent event1) -> {
	                            stage.setX(event1.getScreenX() - x);
	                            stage.setY(event1.getScreenY() - y);
	                        });

	                        stage.setScene(scene);
	                        stage.showAndWait();

	                        anchorPane.setVisible(false);
	                        // refreshStaffList();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                });

	                contextMenu.getItems().addAll(editItem, deleteItem, detailItem);
	                contextMenu.show(contextMenu_btn, event.getScreenX(), event.getScreenY());
	            });
			
	            
				Platform.runLater(() -> listStaff_vbox.getChildren().add(employeeDataPane));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    }		
	}
    
    public void createUser() throws IOException {		
		AnchorPane anchorPane = mainWindowController.getAnchorPane();
	    anchorPane.setVisible(true);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("createUser.fxml"));
		Parent root = loader.load();
		
		root.setOnMousePressed((MouseEvent event)->{            
			x = event.getSceneX();            
			y = event.getSceneY();    
		});
		
		Stage stage = new Stage();        
		stage.initStyle(StageStyle.TRANSPARENT);        
		Scene scene = new Scene(root);
		
		//createUser_Controller controller = loader.getController();
		//controller.setUser(item);
    
		root.setOnMouseDragged((MouseEvent event)->{
			stage.setX(event.getScreenX() - x);            
			stage.setY(event.getScreenY() - y);       
		});			
		
		stage.setScene(scene);     
		stage.showAndWait();
        
		anchorPane.setVisible(false);
        refreshStaffList();
	}
    
    public void addStaff() throws IOException {		
		AnchorPane anchorPane = mainWindowController.getAnchorPane();
	    anchorPane.setVisible(true);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addStaff.fxml"));
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
        refreshStaffList();
	}
	
    //Tìm kiếm nhân viên
  	public void search() {
  		searchStaff.textProperty().addListener((observable, oldValue, newValue) -> {
  		    String searchText = newValue.trim();
  		    ObservableList<NHANVIEN> filteredList = FXCollections.observableArrayList();

  		    if (searchText.isEmpty()) {
  		        filteredList.addAll(NHANVIEN_BLL.listStaff());
  		    } else {
  		        for (NHANVIEN staff : NHANVIEN_BLL.listStaff()) {
  		            if (staff.getTENNV().toLowerCase().contains(searchText.toLowerCase())) {
  		                filteredList.add(staff);
  		            }
  		        }
  		    }
  		    // Xóa tất cả các nút con trong VBox
  		    listStaff_vbox.getChildren().clear();
  		    showListStaff(filteredList);
  		});
  	}
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		Task<ObservableList<NHANVIEN>> task = new Task<>() {
			
			@Override
			protected ObservableList<NHANVIEN> call() throws Exception {
				return NHANVIEN_BLL.listStaff();
			}			
		};
		
		task.setOnSucceeded(event -> {
			ObservableList<NHANVIEN> listStaff = task.getValue();
			showListStaff(listStaff);
		});
		new Thread(task).start();
		
		search();
	}

}

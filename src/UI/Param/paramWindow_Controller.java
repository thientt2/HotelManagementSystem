package UI.Param;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BLL.DICHVU_BLL;
import BLL.THAMSO_BLL;
import DTO.THAMSO;
import UI.MainWindow_Controller;
import UI.Resource.itemParam_Controller;
import UI.Resource.itemService_Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class paramWindow_Controller implements Initializable {
    
    @FXML
    private VBox listParam_vbox;

    @FXML
    private VBox listService_vbox;

    @FXML
    private Button addService_btn;
    
    private ObservableList<Object[]> listService = FXCollections.observableArrayList();
    private ObservableList<THAMSO> listParam = FXCollections.observableArrayList();
    
    private double x = 0;
    private double y = 0;
    
    public void refreshServiceList() {
        listService_vbox.getChildren().clear();
        listService = DICHVU_BLL.listService();
        showListService();
    }
    
    public void refreshParamList() {
        listParam_vbox.getChildren().clear();
        listParam = THAMSO_BLL.listParam();
        showListParam();
    }
    
    public void showListService() {
    	listService_vbox.getChildren().clear();
		listService = DICHVU_BLL.listService();		
        for(Object[] item : listService) {
        	try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemService.fxml"));
				Parent serviceDataPane = loader.load();
				itemService_Controller controller = loader.getController();
				controller.setService(item);
	            Button edit_btn = controller.getEditService_btn();
	            edit_btn.setOnAction(eventEditService -> {
	            	try {
	                    
	                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("editService.fxml"));
	                    Parent root = loader1.load();
	
	                    root.setOnMousePressed((MouseEvent event1) -> {            
	                        x = event1.getSceneX();            
	                        y = event1.getSceneY();    
	                    });
	                    
	                    Stage stage = new Stage();        
	                    stage.initStyle(StageStyle.TRANSPARENT);  
	                    stage.initModality(Modality.WINDOW_MODAL);
	                    stage.initOwner(edit_btn.getScene().getWindow());
	                    Scene scene = new Scene(root);
	
	                    editService_Controller editService = loader1.getController();
	                    editService.setService(item);
	
	                    root.setOnMouseDragged((MouseEvent event1) -> {
	                        stage.setX(event1.getScreenX() - x);            
	                        stage.setY(event1.getScreenY() - y);       
	                    });
	                    
	                    stage.setScene(scene);     
	                    stage.showAndWait();

	                    refreshServiceList();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
                });
			
	      
				listService_vbox.getChildren().add(serviceDataPane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	         	

        }        
    }
    
    public void addService(){              
        try{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("addService.fxml"));
        	Parent root = loader.load();
            
            root.setOnMousePressed((MouseEvent event)->{            
                x = event.getSceneX();            
                y = event.getSceneY();    
            });
            
            Stage stage = new Stage();        
            stage.initStyle(StageStyle.TRANSPARENT);  
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(addService_btn.getScene().getWindow());
            Scene scene = new Scene(root);
        
            root.setOnMouseDragged((MouseEvent event)->{
                stage.setX(event.getScreenX() - x);            
                stage.setY(event.getScreenY() - y);       
            });            
            
            stage.setScene(scene);     
            stage.showAndWait();
            
            refreshServiceList();
		} catch (IOException e) {
			e.printStackTrace();
        }        
    }
    
    public void showListParam() {
		listParam_vbox.getChildren().clear();
		listParam = THAMSO_BLL.listParam();
        for(THAMSO item : listParam) {
        	try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemParam.fxml"));
				Parent paramDataPane = loader.load();
				itemParam_Controller controller = loader.getController();
				controller.setParam(item);
	            Button edit_btn = controller.getEditParam_btn();
	            edit_btn.setOnAction(eventEditService -> {
	            	try {
	                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("editParam.fxml"));
	                    Parent root = loader1.load();
	
	                    root.setOnMousePressed((MouseEvent event1) -> {            
	                        x = event1.getSceneX();            
	                        y = event1.getSceneY();    
	                    });
	                    
	                    Stage stage = new Stage();        
	                    stage.initStyle(StageStyle.TRANSPARENT);       
	                    stage.initModality(Modality.WINDOW_MODAL);
	                    stage.initOwner(edit_btn.getScene().getWindow());
	                    Scene scene = new Scene(root);
	
	                    editParam_Controller editParam = loader1.getController();
	                    editParam.setParam(item);
	
	                    root.setOnMouseDragged((MouseEvent event1) -> {
	                        stage.setX(event1.getScreenX() - x);            
	                        stage.setY(event1.getScreenY() - y);       
	                    });
	                    
	                    stage.setScene(scene);     
	                    stage.showAndWait();
	                    
	                    refreshParamList();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
                });
				         
				Platform.runLater(() -> listParam_vbox.getChildren().add(paramDataPane));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
        }        
    }
   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showListService();
        showListParam();
    }
}

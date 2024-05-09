package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.NHANVIEN_BLL;
import DTO.NHANVIEN;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class staffWindow_Controller implements Initializable {
	
    @FXML
    private TableColumn<NHANVIEN, String> colBeginStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colBirthStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colCccdStaff;

    @FXML
    private TableColumn<NHANVIEN, Void> colControlStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colGenderStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colIdStaff;

    @FXML
    private TableColumn<NHANVIEN, String> colNameStaff;   

    @FXML
    private TableView<NHANVIEN> staff_table;
    
    private double x = 0;
	private double y = 0;
    
    private void setupStaffTableContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/ellipsis_v.png")));
        imageView.setFitWidth(12);
        imageView.setFitHeight(12);
        contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

        editMenuItem.setOnAction(eventEditStaff -> {
            NHANVIEN item = staff_table.getSelectionModel().getSelectedItem();           
            if (item != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editStaff.fxml"));
                    Parent root = loader.load();
                    
                    root.setOnMousePressed((MouseEvent event) -> {            
                        x = event.getSceneX();            
                        y = event.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);
                    
                    editStaff_Controller editStaff = loader.getController();
					editStaff.setStaff(item); 	
                    
                    
                    stage.initOwner(staff_table.getScene().getWindow());
                    
                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);            
                        stage.setY(event.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteMenuItem.setOnAction(event -> {
            NHANVIEN item = staff_table.getSelectionModel().getSelectedItem();
            if (item != null) {
            	NHANVIEN_BLL.deleteStaff(item);
                staff_table.getItems().remove(item);
            }
        });

        colControlStaff.setCellFactory(col -> {
			return new TableCell<NHANVIEN, Void>() {
				private final ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/ellipsis_v.png")));				
				private final Button button = new Button();			
				{
		            button.setOnAction(event -> {
		                NHANVIEN item = getTableView().getItems().get(getIndex());
		                if (item != null) {
		                    Scene scene = button.getScene();
		                    Stage stage = (Stage) scene.getWindow();
		                    Bounds bounds = button.localToScreen(button.getBoundsInLocal());
		                    contextMenu.show(stage, bounds.getMaxX(), bounds.getMaxY());
		                }
		            });
		            imageView.setFitWidth(12);
		            imageView.setFitHeight(12);
		            button.setGraphic(imageView);
		            button.setStyle("-fx-background-color: transparent;");
		            setAlignment(Pos.CENTER);
				}
				
				@Override
				protected void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					setGraphic(empty ? null : button);
				}
			};
		});
    }
    
    public void showListStaff() {
		ObservableList<NHANVIEN> list = NHANVIEN_BLL.listStaff();
		
		if(colIdStaff != null && colNameStaff != null 
				&& colBirthStaff != null && colGenderStaff != null 
				&& colCccdStaff != null && colBeginStaff != null) {
			
			colIdStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("MANV"));
			colNameStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("TENNV"));			
			colBirthStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("NGAYSINH"));
			colGenderStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("GIOITINH"));			
			colCccdStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("CCCD"));	
			colBeginStaff.setCellValueFactory(new PropertyValueFactory<NHANVIEN, String>("NGAYVAOLAM"));
			staff_table.setItems(list);			
		}			
	}
    
    public void createUser() throws IOException {		
		NHANVIEN item = staff_table.getSelectionModel().getSelectedItem();
		if (item != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("createUser.fxml"));
                Parent root = loader.load();
                
                root.setOnMousePressed((MouseEvent event) -> {            
                    x = event.getSceneX();            
                    y = event.getSceneY();    
                });
                
                Stage stage = new Stage();        
                stage.initStyle(StageStyle.TRANSPARENT);        
                Scene scene = new Scene(root);
                
                createUser_Controller controller = loader.getController();
				controller.setUser(item);
                
                
                stage.initOwner(staff_table.getScene().getWindow());
                
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);            
                    stage.setY(event.getScreenY() - y);       
                });
                
                stage.setScene(scene);     
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
    
    public void addStaff() throws IOException {		
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
		stage.show();
	}
	
	
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showListStaff();
		setupStaffTableContextMenu();
		
	}

}

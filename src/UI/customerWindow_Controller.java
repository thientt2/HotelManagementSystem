package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.KHACHHANG_BLL;
import DTO.KHACHHANG;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class customerWindow_Controller implements Initializable {
	
	@FXML
    private Button addCustomer_btn;

    @FXML
    private TableColumn<KHACHHANG, String> colBirthdayCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colCccdCustomer;

    @FXML
    private TableColumn<KHACHHANG, Void> colControlCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colCountryCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colGenderCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colIdCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colNameCustomer;

    @FXML
    private TableColumn<KHACHHANG, String> colPhoneCustomer;

    @FXML
    private AnchorPane customerWindow_form;
    
	@FXML
    private TableView<KHACHHANG> customer_table;
	
    @FXML
    private TextField searchCustomer;
	
	private double x = 0;
	private double y = 0;
	
	private void setupCustomerTableContextMenu(){
		ContextMenu contextMenu = new ContextMenu();
		MenuItem editMenuItem = new MenuItem("Edit");
		MenuItem deleteMenuItem = new MenuItem("Delete");		
		contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);
		
		editMenuItem.setOnAction(eventEditCustomer -> {
			KHACHHANG item = customer_table.getSelectionModel().getSelectedItem();
			if (item != null) {
				 try {
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
	                    Parent root = loader.load();
	                    
	                    root.setOnMousePressed((MouseEvent event) -> {            
	                        x = event.getSceneX();            
	                        y = event.getSceneY();    
	                    });
	                    
	                    Stage stage = new Stage();        
	                    stage.initStyle(StageStyle.TRANSPARENT);        
	                    Scene scene = new Scene(root);
	                    
	                    editCustomer_Controller editCustomer = loader.getController();
						editCustomer.setCustomer(item);	              
	                    
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
			KHACHHANG item = customer_table.getSelectionModel().getSelectedItem();
			if (item != null) {
				// Xu ly xoa khach hang
				customer_table.getItems().remove(item);
			}
		});
		
		colControlCustomer.setCellFactory(col -> {
			return new TableCell<KHACHHANG, Void>() {
				private final ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Images/ellipsis_v.png")));				
				private final Button button = new Button();			
				{
		            button.setOnAction(event -> {
		                KHACHHANG item = getTableView().getItems().get(getIndex());
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
	
	public void showListCustomer() {		
	    ObservableList<KHACHHANG> list = KHACHHANG_BLL.listCustomer();
		if(colIdCustomer != null && colNameCustomer != null 
				&& colPhoneCustomer != null && colBirthdayCustomer != null
				&& colGenderCustomer != null && colCountryCustomer != null 
				&& colCccdCustomer != null) {
			
			colIdCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("MAKH"));
			colNameCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("TENKH"));
			colPhoneCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("SDT"));						
			colBirthdayCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("NGAYSINH"));
			colGenderCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("GIOITINH"));
			colCountryCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("QUOCTICH"));
			colCccdCustomer.setCellValueFactory(new PropertyValueFactory<KHACHHANG, String>("CCCD"));
			
			customer_table.setItems(list);			
		}		
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
	public void search() {
		searchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
		    String searchTerm = newValue.trim();
		    if (!searchTerm.isEmpty()) {
		        // Thực hiện tìm kiếm khách hàng với từng ký tự nhập vào
		        ObservableList<KHACHHANG> filteredList = KHACHHANG_BLL.searchCustomerByName(searchTerm);
		        customer_table.setItems(filteredList);
		    } else {
		        // Nếu ô nhập liệu trống, hiển thị toàn bộ danh sách khách hàng
		        showListCustomer();
		    }
		});
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupCustomerTableContextMenu();
		showListCustomer();
		search();
	}
	

}

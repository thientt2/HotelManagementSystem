package UI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DTO.*;
import BLL.*;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class MainWindow_Controller implements Initializable {


	@FXML
    private Button baoCao_btn;

    @FXML
    private Button caiDat_btn;    

    @FXML
    private Button dichVu_btn;
    
    @FXML
    private Button logout_btn;

    @FXML
    private Button hoaDon_btn;

    @FXML
    private Button khachHang_btn;

    @FXML
    private Button nhanPhong_btn;

    @FXML
    private Button nhanVien_btn;

    @FXML
    private Button phong_btn;

    @FXML
    private AnchorPane phong_form;

    @FXML
    private Button suaLoai_btn;

    @FXML
    private Button suaPhong_btn;

    @FXML
    private Button themLoaiP_btn;

    @FXML
    private Button themPhong_btn;
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField timKiemPhong;

    @FXML
    private Label toDay;

    @FXML
    private Button reload_btn;
    
    @FXML
    private Button trangChu_btn;

    @FXML
    private Label username_label;

    @FXML
    private Button xoaLoaiP_btn;

    @FXML
    private Button xoaPhong_btn;
    
    @FXML
    private TableColumn<Object[], Void> colChucNang;

    @FXML
    private TableColumn<Object[], Double> colDienTich;

    @FXML
    private TableColumn<Object[], Double> colGia;

    @FXML
    private TableColumn<Object[], String> colLoaiGiuong;

    @FXML
    private TableColumn<Object[], String> colLoaiPhong;

    @FXML
    private TableColumn<Object[], Integer> colNguoiToiDa;

    @FXML
    private TableColumn<Object[], String> colSoPhong;

    @FXML
    private TableColumn<Object[], String> colTrangThai;
    @FXML
    private TableView<Object[]> table_room;
    
      
	public void initData(String username) {
		NHANVIEN nhanVien = DANGNHAP_BLL.layTenNhanVien(username);
        username_label.setText(nhanVien.getTENNV()); // Hiển thị tên nhân viên trong màn hình chính
    }

	
    public void showRoom() {	
		ObservableList<Object[]> dataList = PHONG_BLL.showRoom();
	 	
    	 		
    		if (colSoPhong != null && colLoaiPhong != null && colGia != null && colLoaiGiuong != null && colNguoiToiDa != null && colTrangThai != null && colDienTich != null) {
        	    colSoPhong.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue()[0]));
        	    colLoaiPhong.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue()[1]));
			    colLoaiGiuong.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue()[2]));
			    colDienTich.setCellValueFactory(cellData -> new SimpleDoubleProperty((Double) cellData.getValue()[3]).asObject());
			    colNguoiToiDa.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[4]).asObject());
			    colGia.setCellValueFactory(cellData -> new SimpleDoubleProperty((Double) cellData.getValue()[5]).asObject());
			    colTrangThai.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue()[6]));
        	}
        	table_room.setItems(dataList);               
        	
    }
    
//    public void selectData() {
//    	Object[] roomData = show_room_table.getSelectionModel().getSelectedItem();
//    	int num = show_room_table.getSelectionModel().getSelectedIndex();
//    	
//    	if((num - 1) < -1) {
//    		return;
//    	}
//    }
    
    
    
    public void exit(){
        System.exit(0);
    }
    
    public void minimize(){
        ((Stage) main_form.getScene().getWindow()).setIconified(true);
    }
    
    private double x = 0;
    private double y = 0;
    public void logout() {    	
    	try {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Massage");
    		alert.setHeaderText(null);
    		alert.setContentText("Bạn có muốn đăng xuất không?");
    		Optional<ButtonType> option = alert.showAndWait();
    		
    		if(option.get().equals(ButtonType.OK)) {
    			logout_btn.getScene().getWindow().hide();   
        		Parent root = FXMLLoader.load(getClass().getResource("DANGNHAP_UI.fxml"));
        		
        		
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
    		}else 
    			return;
    		
    				
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
   
    @FXML
    private void initialize() {
        // Tạo ContextMenu và các MenuItem
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

        // Thiết lập xử lý sự kiện cho MenuItem Edit
        editMenuItem.setOnAction(event -> {
        	Object[] item = table_room.getSelectionModel().getSelectedItem();
            if (item != null) {
                System.out.println("Editing row: " + item);
                // Thêm logic để chỉnh sửa hàng ở đây
                
            }
        });

        // Thiết lập xử lý sự kiện cho MenuItem Delete
        deleteMenuItem.setOnAction(event -> {
            Object[] item = table_room.getSelectionModel().getSelectedItem();
            if (item != null) {
                System.out.println("Deleting row: " + item);
                // Thêm logic để xóa hàng ở đây
                table_room.getItems().remove(item);
            }
        });

        // Thiết lập cách hiển thị ContextMenu khi nhấp chuột
        colChucNang.setCellFactory(col -> {
            return new TableCell<Object[], Void>() {
                private final Button button = new Button("⋮");                

                {
                	button.setOnAction(event -> {
                	    // Lấy ra Scene từ Button
                	    Scene scene = button.getScene();
                	    // Lấy ra Stage từ Scene
                	    Stage stage = (Stage) scene.getWindow();
                	    // Lấy ra vị trí tương đối của nút trong Stage
                	    Bounds bounds = button.localToScreen(button.getBoundsInLocal());                	   
                	    contextMenu.show(stage, bounds.getMaxX(), bounds.getMaxY());
                	});    
                	
                    button.setStyle("-fx-background-color: transparent;"
                    		+ "-fx-alignment:center-right;");
                    
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : button);
                }
            };
        });
    }


    public void addRoom() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("addRoom.fxml"));
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
    
    public void setTime() {
    	LocalDateTime now = LocalDateTime.now();        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        toDay.setText(formattedDateTime);
    }
    
    public void reload() {
    	showRoom();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		
        initialize();
        showRoom();		
		setTime();
	}

}

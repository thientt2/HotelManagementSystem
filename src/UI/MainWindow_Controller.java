package UI;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
    private Button trangChu_btn;

    @FXML
    private Label username_label;

    @FXML
    private Button xoaLoaiP_btn;

    @FXML
    private Button xoaPhong_btn;
    
    @FXML
    private TableColumn<Object[], Integer> colSoPhong1;

    @FXML
    private TableColumn<Object[], String> colLoaiPhong1;

    @FXML
    private TableColumn<Object[], Double> colGia1;

    @FXML
    private TableColumn<Object[], Integer> colNguoiToiDa1;

    @FXML
    private TableColumn<Object[], String> colTrangThai1;

    
    @FXML
    private TableView<Object[]> show_room_table;
    
    @FXML
    private TableView<LOAIPHONG> show_room_type;
    
    @FXML
    private TableColumn<LOAIPHONG, Double> colGia;

    @FXML
    private TableColumn<LOAIPHONG, Integer> colNguoiToiDa;

    @FXML
    private TableColumn<LOAIPHONG, String> colTenLoai;
    
	public void initData(String username) {
		NHANVIEN nhanVien = DANGNHAP_BLL.layTenNhanVien(username);
        username_label.setText(nhanVien.getTENNV()); // Hiển thị tên nhân viên trong màn hình chính
    }
    
    
    public void showRoom() {
    	ObservableList<Object[]> dataList = PHONG_BLL.showRoom();
	 	
    	
    	if (colSoPhong1 != null && colLoaiPhong1 != null && colGia1 != null && colNguoiToiDa1 != null && colTrangThai1 != null) {
    	    colSoPhong1.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[0]).asObject());
    	    colLoaiPhong1.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue()[1]));
    	    colGia1.setCellValueFactory(cellData -> new SimpleDoubleProperty((Double) cellData.getValue()[2]).asObject());
    	    colNguoiToiDa1.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[3]).asObject());
    	    colTrangThai1.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue()[4]));
    	}

    	show_room_table.setItems(dataList);    	
    }
    
    public void selectData() {
    	Object[] roomData = show_room_table.getSelectionModel().getSelectedItem();
    	int num = show_room_table.getSelectionModel().getSelectedIndex();
    	
    	if((num - 1) < -1) {
    		return;
    	}
    }
    
    public void showRoomType() {
    	ObservableList<LOAIPHONG> dataList = LOAIPHONG_BLL.showRoomType();
    	
    	if (colTenLoai != null && colGia != null && colNguoiToiDa != null) {
    		colTenLoai.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTENLOAI()));
        	colGia.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getGIA()).asObject());
        	colNguoiToiDa.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNGUOITOIDA()).asObject());    	    
    	}
    	show_room_type.setItems(dataList);
 
    }
    
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		LocalDateTime now = LocalDateTime.now();

        // Định dạng thời gian thành chuỗi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        toDay.setText(formattedDateTime);
        showRoom();
		showRoomType();
	}

}

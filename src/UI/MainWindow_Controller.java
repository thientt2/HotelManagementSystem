package UI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainWindow_Controller implements Initializable {

	@FXML
    private Button baoCao_btn;

    @FXML
    private Button caiDat_btn;

    @FXML
    private TableColumn<?, ?> colGia;

    @FXML
    private TableColumn<?, ?> colNguoiToiDa;

    @FXML
    private TableColumn<?, ?> colTenLoai;

    @FXML
    private Button dichVu_btn;

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
    private TextField timKiemPhong;

    @FXML
    private Label toDay;

    @FXML
    private Button trangChu_btn;

    @FXML
    private Label username;

    @FXML
    private Button xoaLoaiP_btn;

    @FXML
    private Button xoaPhong_btn;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

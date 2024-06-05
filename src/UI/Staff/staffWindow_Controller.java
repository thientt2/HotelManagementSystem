package UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.HOADONDICHVU_BLL;
import BLL.NHANVIEN_BLL;
import BLL.PHONG_BLL;
import DTO.NHANVIEN;
import UI.MainWindow_Controller;
import UI.Resource.itemBill_Controller;
import UI.Resource.itemRoom_Controller;
import UI.Resource.itemStaff_Controller;
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

public class staffWindow_Controller implements Initializable {
    
    @FXML
    private Button addStaff_btn;

    @FXML
    private Button addUser_btn;

    @FXML
    private VBox listStaff_vbox;

    @FXML
    private TextField searchStaff;
    
    @FXML
    private Pagination pagination;
    
    public Button getAddUser_btn() {
		return addUser_btn;
	}	
    
    private static final int ITEMS_PER_PAGE = 8;
    private ObservableList<NHANVIEN> listStaff = FXCollections.observableArrayList();
    
    private int calculatePageCount() {
        return (int) Math.ceil((double) listStaff.size() / ITEMS_PER_PAGE);
    }
    
    private VBox createPage(int pageIndex) {
        VBox pageBox = new VBox(); // Create a new VBox for the current page

        int startIndex = pageIndex * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, listStaff.size());

        for (int i = startIndex; i < endIndex; i++) {
            NHANVIEN staff = listStaff.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemStaff.fxml"));
                Parent employeeDataPane = loader.load();
                itemStaff_Controller controller = loader.getController();
                controller.setStaff(staff);
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
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("editStaff.fxml"));
                            Parent root = loader2.load();

                            root.setOnMousePressed((MouseEvent event1) -> {
                                x = event1.getSceneX();
                                y = event1.getSceneY();
                            });

                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(addStaff_btn.getScene().getWindow());
                            Scene scene = new Scene(root);

                            editStaff_Controller editStaff = loader2.getController();
                            editStaff.setStaff(staff);

                            root.setOnMouseDragged((MouseEvent event1) -> {
                                stage.setX(event1.getScreenX() - x);
                                stage.setY(event1.getScreenY() - y);
                            });

                            stage.setScene(scene);
                            stage.showAndWait();

                            refreshStaffList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    deleteItem.setOnAction(deleteEvent -> {
                        if (staff != null) {
                            NHANVIEN_BLL.deleteStaff(staff);
                            refreshStaffList();
                        }
                    });

                    detailItem.setOnAction(detailEvent -> {
                        try {
                            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("staffDetails.fxml"));
                            Parent root = loader1.load();

                            root.setOnMousePressed((MouseEvent event1) -> {
                                x = event1.getSceneX();
                                y = event1.getSceneY();
                            });

                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(addStaff_btn.getScene().getWindow());
                            Scene scene = new Scene(root);

                            staffDetails_Controller staffDetails = loader1.getController();
                            staffDetails.setStaff(staff);

                            root.setOnMouseDragged((MouseEvent event1) -> {
                                stage.setX(event1.getScreenX() - x);
                                stage.setY(event1.getScreenY() - y);
                            });

                            stage.setScene(scene);
                            stage.showAndWait();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    contextMenu.getItems().addAll(editItem, deleteItem, detailItem);
                    contextMenu.show(contextMenu_btn, event.getScreenX(), event.getScreenY());
                });

                pageBox.getChildren().add(employeeDataPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return pageBox;
    }
    
    private double x = 0;
    private double y = 0;
    
    public void refreshStaffList() {
        listStaff = NHANVIEN_BLL.listStaff();
        pagination.setPageCount(calculatePageCount());
        pagination.setPageFactory(this::createPage);
    }
    
    private ContextMenu contextMenu = new ContextMenu();
    
    public void createUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createUser.fxml"));
        Parent root = loader.load();

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addStaff_btn.getScene().getWindow());
        Scene scene = new Scene(root);

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.showAndWait();
        
        refreshStaffList();
    }
    
    public void addStaff() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addStaff.fxml"));
        Parent root = loader.load();

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addStaff_btn.getScene().getWindow());
        Scene scene = new Scene(root);

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.showAndWait();

        refreshStaffList();
    }

    // Tìm kiếm nhân viên
    public void search(String newValue) {
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
        listStaff_vbox.getChildren().clear();
        listStaff = filteredList;
        pagination.setPageCount(calculatePageCount());
        pagination.setPageFactory(this::createPage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	searchStaff.textProperty().addListener((observable, oldValue, newValue) -> {
    		search(newValue);
    	});
        refreshStaffList();
    }
}

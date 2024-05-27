package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BLL.DICHVU_BLL;
import BLL.LOAIDICHVU_BLL;
import DTO.DICHVU;
import BLL.THAMSO_BLL;
import DTO.THAMSO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class paramWindow_Controller implements Initializable {
    
    @FXML
    private VBox listParam_vbox;

    @FXML
    private VBox listService_vbox;

    @FXML
    private Button addService_btn;
    
    private MainWindow_Controller mainWindowController;
    
    // Phương thức để thiết lập tham chiếu của main window controller từ bên ngoài
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }
    
    private double x = 0;
    private double y = 0;
    
    public void refreshServiceList() {
        // Xóa tất cả các nút con trong VBox
        listService_vbox.getChildren().clear();
        ObservableList<DICHVU> listService = DICHVU_BLL.listService();
        showListService(listService);
    }
    
    public void refreshParamList() {
        // Xóa tất cả các nút con trong VBox
        listParam_vbox.getChildren().clear();
        ObservableList<THAMSO> listParam = THAMSO_BLL.listParam();
        showListParam(listParam);
    }
    
    public void showListService(ObservableList<DICHVU> list) {
        for(DICHVU item : list) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setPrefHeight(30);
            hBox.setAlignment(Pos.CENTER_LEFT);

            // hiển thị tên loại dịch vụ từ mã loại
            Label loaiDV = new Label();
            loaiDV.setText(LOAIDICHVU_BLL.getServiceTypeName(item.getLOAIDV()));
            loaiDV.setPrefWidth(80);
            loaiDV.setAlignment(Pos.CENTER_LEFT);

            Label tenDV = new Label(item.getTENDV());
            tenDV.setPrefWidth(140);
            tenDV.setAlignment(Pos.CENTER_LEFT);

            Label gia = new Label(item.getGIA().toString());
            gia.setPrefWidth(80);
            gia.setAlignment(Pos.CENTER);

            ImageView imageEdit = new ImageView(new Image(getClass().getResourceAsStream("/Images/editcolor.png")));
            imageEdit.setFitWidth(15);
            imageEdit.setFitHeight(15);
            Button buttonEdit = new Button();
            buttonEdit.setStyle("-fx-background-color: transparent;");
            buttonEdit.setGraphic(imageEdit);
            buttonEdit.setOnMouseClicked(event -> {
                DICHVU clickedService = item;
                try {
                    AnchorPane anchorPane = mainWindowController.getAnchorPane();
                    anchorPane.setVisible(true);
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editService.fxml"));
                    Parent root = loader.load();

                    root.setOnMousePressed((MouseEvent event1) -> {            
                        x = event1.getSceneX();            
                        y = event1.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);

                    editService_Controller editService = loader.getController();
                    editService.setService(clickedService);

                    root.setOnMouseDragged((MouseEvent event1) -> {
                        stage.setX(event1.getScreenX() - x);            
                        stage.setY(event1.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.showAndWait();
                    
                    anchorPane.setVisible(false);
                    refreshServiceList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    
            });
            
            ImageView imageDelete = new ImageView(new Image(getClass().getResourceAsStream("/Images/recycle.png")));
            imageDelete.setFitWidth(15);
            imageDelete.setFitHeight(15);
            Button buttonDelete = new Button();
            buttonDelete.setStyle("-fx-background-color: transparent;");
            buttonDelete.setGraphic(imageDelete);
            buttonDelete.setOnMouseClicked(event -> {
                DICHVU clickedService = item;
                try {
                    DICHVU_BLL.deleteService(clickedService);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                refreshServiceList();
                    
            });

            hBox.getChildren().addAll(loaiDV, tenDV, gia, buttonEdit, buttonDelete);
            hBox.setStyle("	-fx-background-color: #FFFFFF;\r\n"	    			
                    + "	-fx-border-color:  #E8F1FD;\r\n"	    			
                    + "-fx-font-size: 14px; \r\n"
                    + "-fx-border-width: 0 0 2 0;");
            listService_vbox.getChildren().add(hBox);
        }        
    }
    
    public void addService() throws IOException {        
        AnchorPane anchorPane = mainWindowController.getAnchorPane();
        anchorPane.setVisible(true);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addService.fxml"));
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
        refreshServiceList();
    }
    
    public void showListParam(ObservableList<THAMSO> list) {
        for(THAMSO item : list) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setPrefHeight(30);
            hBox.setAlignment(Pos.CENTER_LEFT);
            
            Label ten = new Label();
            ten.setText(THAMSO_BLL.getParamName(item.getTENTHAMSO()));
            ten.setPrefWidth(220);
            ten.setAlignment(Pos.CENTER_LEFT);
            
            Label tile = new Label(item.getGIATRI().toString());
            tile.setPrefWidth(50);
            tile.setAlignment(Pos.CENTER);
            
            ImageView imageEdit = new ImageView(new Image(getClass().getResourceAsStream("/Images/editcolor.png")));
            imageEdit.setFitWidth(15);
            imageEdit.setFitHeight(15);
            Button buttonEdit = new Button();
            buttonEdit.setStyle("-fx-background-color: transparent;");
            buttonEdit.setGraphic(imageEdit);
            buttonEdit.setOnMouseClicked(event -> {
                THAMSO clickedParam = item;
                try {
                    AnchorPane anchorPane = mainWindowController.getAnchorPane();
                    anchorPane.setVisible(true);
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editParam.fxml"));
                    Parent root = loader.load();

                    root.setOnMousePressed((MouseEvent event1) -> {            
                        x = event1.getSceneX();            
                        y = event1.getSceneY();    
                    });
                    
                    Stage stage = new Stage();        
                    stage.initStyle(StageStyle.TRANSPARENT);        
                    Scene scene = new Scene(root);

                    editParam_Controller editParam = loader.getController();
                    editParam.setParam(clickedParam);

                    root.setOnMouseDragged((MouseEvent event1) -> {
                        stage.setX(event1.getScreenX() - x);            
                        stage.setY(event1.getScreenY() - y);       
                    });
                    
                    stage.setScene(scene);     
                    stage.showAndWait();
                    
                    anchorPane.setVisible(false);
                    refreshParamList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    
            });

            hBox.getChildren().addAll(ten, tile, buttonEdit);
            hBox.setStyle("	-fx-background-color: #FFFFFF;\r\n"	    			
                    + "	-fx-border-color:  #E8F1FD;\r\n"	    			
                    + "-fx-font-size: 14px; \r\n"
                    + "-fx-border-width: 0 0 2 0;");
            listParam_vbox.getChildren().add(hBox);
        }        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<DICHVU> listService = DICHVU_BLL.listService();
        ObservableList<THAMSO> listParam = THAMSO_BLL.listParam();
        showListService(listService);
        showListParam(listParam);
    }
}

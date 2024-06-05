package UI.Room;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.PHONG_BLL;
import UI.MainWindow_Controller;
import UI.Resource.itemRoom_Controller;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class roomWindow_Controller implements Initializable {
	@FXML
    private AnchorPane roomWindow_form;

    @FXML
    private Button select1Floor_btn;

    @FXML
    private Button select2Floor_btn;

    @FXML
    private Button select3Floor_btn;

    @FXML
    private Button select4Floor_btn;

    @FXML
    private Button select5Floor_btn;

    @FXML
    private Button select6Floor_btn;

    @FXML
    private Button select7Floor_btn;

    @FXML
    private Button select8Floor_btn;

    @FXML
    private Button selectAllRoom_btn;
    
    @FXML
    private VBox showRoom_vbox;

    @FXML
    private Button unselect1Floor_btn;

    @FXML
    private Button unselect2Floor_btn;

    @FXML
    private Button unselect3Floor_btn;

    @FXML
    private Button unselect4Floor_btn;

    @FXML
    private Button unselect5Floor_btn;

    @FXML
    private Button unselect6Floor_btn;

    @FXML
    private Button unselect7Floor_btn;

    @FXML
    private Button unselect8Floor_btn;

    @FXML
    private Button unselectAllRoom_btn;
    
        	 
    private MainWindow_Controller mainWindowController;   
    
    public void setMainWindowController(MainWindow_Controller controller) {
        this.mainWindowController = controller;
    }   
    
	public void showAllRoom() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(true);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(false);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		
		Task<ObservableList<Object[]>> task = new Task<>() {
			
			@Override
			protected ObservableList<Object[]> call() throws Exception {
				return PHONG_BLL.getAllRoom();
			}			
		};
		
		task.setOnSucceeded(event -> {
			ObservableList<Object[]> result = task.getValue();
			showRoom_vbox.getChildren().clear();
			for(Object[] room : result) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
					Parent roomData = loader.load();
					itemRoom_Controller controller = loader.getController();
					controller.setData(room[0],room[1],room[2],0);
					
					Button bookService = controller.getBookServiceBtn();
					bookService.setOnAction(event1 -> {
						try {
							FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
							Parent root = loaderServices.load();
							servicesRoom_Controller servicesController = loaderServices.getController();
							servicesController.setData(room);
							Scene scene = new Scene(root);
							Stage stage = new Stage();
							stage.initStyle(StageStyle.TRANSPARENT);
							stage.setScene(scene);
							stage.show();
						}catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					showRoom_vbox.getChildren().add(roomData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		new Thread(task).start();
	}
	
	public void showFirstFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(true);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(false);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(1);
		
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void showSecondFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(true);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(false);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(2);
		
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void showThirdFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(true);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(false);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(3);
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void showFourthFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(true);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(false);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(4);
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void showFifthFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(true);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(false);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(5);		
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void showSixthFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(true);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(false);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(6);
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void showSeventhFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(true);
			select8Floor_btn.setVisible(false);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(false);
			unselect8Floor_btn.setVisible(true);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(7);
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void showEighthFloor() {
		Platform.runLater(() -> {
			selectAllRoom_btn.setVisible(false);
			select1Floor_btn.setVisible(false);
			select2Floor_btn.setVisible(false);
			select3Floor_btn.setVisible(false);
			select4Floor_btn.setVisible(false);
			select5Floor_btn.setVisible(false);
			select6Floor_btn.setVisible(false);
			select7Floor_btn.setVisible(false);
			select8Floor_btn.setVisible(true);
			
			unselectAllRoom_btn.setVisible(true);
			unselect1Floor_btn.setVisible(true);
			unselect2Floor_btn.setVisible(true);
			unselect3Floor_btn.setVisible(true);
			unselect4Floor_btn.setVisible(true);
			unselect5Floor_btn.setVisible(true);
			unselect6Floor_btn.setVisible(true);
			unselect7Floor_btn.setVisible(true);
			unselect8Floor_btn.setVisible(false);			
		});
		showRoom_vbox.getChildren().clear();
		ObservableList<Object[]> listRoom = PHONG_BLL.getListRoomByFloor(8);
		for(Object[] room : listRoom) {			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemRoom.fxml"));
				Parent roomData = loader.load();
				itemRoom_Controller controller = loader.getController();
				controller.setData(room[0],room[1],room[2],0);
				
				Button bookService = controller.getBookServiceBtn();
				bookService.setOnAction(event -> {
					try {
						FXMLLoader loaderServices = new FXMLLoader(getClass().getResource("/UI/Room/servicesRoom.fxml"));
						Parent root = loaderServices.load();
						servicesRoom_Controller servicesController = loaderServices.getController();
						servicesController.setData(room);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(scene);
						stage.show();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

				showRoom_vbox.getChildren().add(roomData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showAllRoom();
		
	}
}

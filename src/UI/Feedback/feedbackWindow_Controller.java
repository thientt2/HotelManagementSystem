package UI.Feedback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.DANHGIA_BLL;
import UI.Resource.itemFeedBack_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;


public class feedbackWindow_Controller implements Initializable{
	
	@FXML
    private Button five_btn;

    @FXML
    private Label five_txt;

    @FXML
    private Button four_btn;

    @FXML
    private Label four_txt;

    @FXML
    private Button one_btn;

    @FXML
    private Label one_txt;

    @FXML
    private Button three_btn;

    @FXML
    private Label three_txt;

    @FXML
    private Label total_txt;

    @FXML
    private Button two_btn;

    @FXML
    private Label two_txt;
    
    @FXML
    private Pagination pagination;
 
    private static final int ITEMS_PER_PAGE = 2;
    private ObservableList<Object[]> listFeedBack;
    
    private int calculatePageCount() {
        return (int) Math.ceil((double) listFeedBack.size() / ITEMS_PER_PAGE);
    }
	
	public void refreshFeedBackList() {
		listFeedBack = DANHGIA_BLL.listFeedBack();
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
    }
    
	
	private VBox createPage(int pageIndex) {	    
		VBox page = new VBox();
	    int startIndex = pageIndex * ITEMS_PER_PAGE;
	    int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, listFeedBack.size());

	    for (int i = startIndex; i < endIndex; i++) {
	        Object[] fb = listFeedBack.get(i);
	        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemFeedBack.fxml"));
                Parent feedBackData = loader.load();
                itemFeedBack_Controller controller = loader.getController();
                controller.setData(fb);

                page.getChildren().add(feedBackData);
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	    
	    return page; 
	}
	
	
	public void filterOneList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    listFeedBack = DANHGIA_BLL.listFeedBack();
	    for (Object[] item : listFeedBack) { 
	        if (Integer.parseInt(item[0].toString()) == 1) {
	            filteredList.add(item);
	        }
	    }
	    listFeedBack = filteredList;
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}
	
	public void filterTwoList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    listFeedBack = DANHGIA_BLL.listFeedBack();
	    for (Object[] item : listFeedBack) { 
	        if (Integer.parseInt(item[0].toString()) == 2) {
	            filteredList.add(item);
	        }
	    }
	    listFeedBack = filteredList;
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}
	
	public void filterThreeList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    listFeedBack = DANHGIA_BLL.listFeedBack();
	    for (Object[] item : listFeedBack) { 
	        if (Integer.parseInt(item[0].toString()) == 3) {
	            filteredList.add(item);
	        }
	    }
	    listFeedBack = filteredList;
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}
	
	public void filterFourList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    listFeedBack = DANHGIA_BLL.listFeedBack();
	    for (Object[] item : listFeedBack) { 
	        if (Integer.parseInt(item[0].toString()) == 4) {
	            filteredList.add(item);
	        }
	    }
	    listFeedBack = filteredList;
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}
	
	public void filterFiveList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    listFeedBack = DANHGIA_BLL.listFeedBack();
	    for (Object[] item : listFeedBack) { 
	        if (Integer.parseInt(item[0].toString()) == 5) {
	            filteredList.add(item);
	        }
	    }
	    listFeedBack = filteredList;
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listFeedBack = DANHGIA_BLL.listFeedBack();
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);
	}
}

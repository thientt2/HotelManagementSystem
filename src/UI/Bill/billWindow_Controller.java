package UI.Bill;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.HOADONDICHVU_BLL;
import UI.Resource.itemBill_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class billWindow_Controller implements Initializable {	

    @FXML
    private VBox bill_vbox;

    @FXML
    private Pagination pagination;
    
    private static final int ITEMS_PER_PAGE = 7;
    private ObservableList<Object[]> listData= FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listData = HOADONDICHVU_BLL.listBill();		
		pagination.setPageCount(calculatePageCount(listData));
	    pagination.setPageFactory(this::createPage);	    
	}
	
	private int calculatePageCount(ObservableList<Object[]> listData) {
        return (int) Math.ceil((double) listData.size() / ITEMS_PER_PAGE);
    }
	

	private VBox createPage(int pageIndex) {
		VBox page = new VBox();

	    int startIndex = pageIndex * ITEMS_PER_PAGE;
	    int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, listData.size());

	    for (int i = startIndex; i < endIndex; i++) {
	        Object[] billService = listData.get(i);	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBill.fxml"));
	        try {
	            Parent billData = loader.load();
	            itemBill_Controller controller = loader.getController();
	            controller.setData(billService);
	            page.getChildren().add(billData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return page;
	}



	
}

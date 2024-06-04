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
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class billWindow_Controller implements Initializable {	

    @FXML
    private VBox bill_vbox;

    @FXML
    private Pagination pagination;
    
    private static final int ITEMS_PER_PAGE = 8;
    private ObservableList<Object[]> listBill= FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listBill = HOADONDICHVU_BLL.listBill();
		pagination.setPageCount(calculatePageCount());
	    pagination.setPageFactory(this::createPage);	    
	}
	
	private int calculatePageCount() {
        return (int) Math.ceil((double) listBill.size() / ITEMS_PER_PAGE);
    }
	

	private VBox createPage(int pageIndex) {	    
	    bill_vbox.getChildren().clear(); // Clear old items

	    int startIndex = pageIndex * ITEMS_PER_PAGE;
	    int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, listBill.size());

	    for (int i = startIndex; i < endIndex; i++) {
	        Object[] billService = listBill.get(i);	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBill.fxml"));
	        try {
	            Parent billData = loader.load();
	            itemBill_Controller controller = loader.getController();
	            controller.setData(billService);
	            bill_vbox.getChildren().add(billData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return new VBox(); // Return an empty VBox as a placeholder
	}
	
	private void changePaginationButtonLabels() {
        pagination.lookupAll(".arrow-button").forEach(node -> {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                hbox.getChildren().forEach(button -> {
                    if (button.getStyleClass().contains("left-arrow")) {
                        ((javafx.scene.control.Button) button).setText("Previous"); // Đổi tên nút trái
                    } else if (button.getStyleClass().contains("right-arrow")) {
                        ((javafx.scene.control.Button) button).setText("Next"); // Đổi tên nút phải
                    }
                });
            }
        });
    }


}

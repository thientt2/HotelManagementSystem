package UI.Bill;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import BLL.HOADONDICHVU_BLL;
import BLL.PHIEUDATPHONG_BLL;
import UI.Resource.itemBill_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private Button Paid_btn;

    @FXML
    private Button notPaid_btn;

    @FXML
    private Pagination pagination;
    
    @FXML
    private void handleNotPaidButtonAction(ActionEvent event) {
        toggleStatus("Chưa thanh toán", notPaid_btn);
    }

    @FXML
    private void handlePaidButtonAction(ActionEvent event) {
        toggleStatus("Đã thanh toán", Paid_btn);
    }

    private Set<String> selectedStatuses = new HashSet<>();
	
	private void toggleStatus(String status, Button button) {
	    if (selectedStatuses.contains(status)) {
	        selectedStatuses.remove(status);
	        button.setStyle(getStatusStyle(status)); 
	    } else {
	        selectedStatuses.add(status);
	        button.setStyle("-fx-background-color: #FF6347;"); 
	    }
	    filterBillList();
	}
    
	private void filterBillList() {
	    ObservableList<Object[]> filteredList = FXCollections.observableArrayList();
	    listData = HOADONDICHVU_BLL.listBill();		
	    for (Object[] item : listData) { 
	        String status = getStatus(Integer.parseInt(item[6].toString()));
	        if (selectedStatuses.isEmpty() || selectedStatuses.contains(status)) {
	            filteredList.add(item);
	        }
	    }
	    listData = filteredList;
		pagination.setPageCount(calculatePageCount(listData));
	    pagination.setPageFactory(this::createPage);
	}
	
	public void refreshBillList() {
		listData = HOADONDICHVU_BLL.listBill();	
		pagination.setPageCount(calculatePageCount(listData));
	    pagination.setPageFactory(this::createPage);
    }
	
    private static final int ITEMS_PER_PAGE = 7;
    private ObservableList<Object[]> listData= FXCollections.observableArrayList();

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
	            
	            Button pay_btn = controller.getPay_btn();
	            pay_btn.setOnAction(eventPay -> {
	            	controller.printBill();
	            	refreshBillList();
	            });
	            
	            
	            page.getChildren().add(billData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return page;
	}
	
	public static String getStatus(int trangThaiHoaDon) {
		if(trangThaiHoaDon == 0){
			return "Chưa thanh toán";
		}else {
			return "Đã thanh toán";
		}            
	}
	
    private String getStatusStyle(String status) {
	    String textColor = "";
	    String bgColor = "";
	    switch (status) {
	        case "Đã thanh toán":
	            textColor = "#41C588";
	            bgColor = "#E7F8F0";
	            break;
	        case "Chưa thanh toán":
	            textColor = "#F36960";
	            bgColor = "#FEECEB";
	            break;
	    }
	    return String.format("-fx-background-color: %s; -fx-text-fill: %s;", bgColor, textColor);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listData = HOADONDICHVU_BLL.listBill();		
		pagination.setPageCount(calculatePageCount(listData));
	    pagination.setPageFactory(this::createPage);	    
	}
	
}

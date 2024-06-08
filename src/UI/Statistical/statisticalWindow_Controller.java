package UI.Statistical;

import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;

import BLL.BAOCAO_BLL;
import DTO.LOAIPHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis; 
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class statisticalWindow_Controller implements Initializable {

    @FXML
    private PieChart pieChart;

    @FXML
    private AreaChart<String, Number> areaChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private ComboBox<Integer> day_cb;

    @FXML
    private ComboBox<Integer> startDay_cb;
    
    @FXML
    private TextField endDay_txt;

    @FXML
    private ComboBox<Integer> month_cb;

    @FXML
    private Label value_txt;

    @FXML
    private ComboBox<Integer> year_cb;
    
    public void showListYear() {
    	List<Integer> listYear = BAOCAO_BLL.getYears();
		ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
		for(Integer i : listYear) {
			listQuantity.add(i);
		}
		year_cb.setItems(listQuantity);
	}
    
    public void showListMoth(int year) {
    	List<Integer> listMonth = BAOCAO_BLL.getMonths(year);
		ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
		for(Integer i : listMonth) {
			listQuantity.add(i);
		}
		month_cb.setItems(listQuantity);
	}
    
//    public void showListDay(int month, int year) {
//    	List<Integer> listDay = BAOCAO_BLL.getDays(month, year);
//		ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
//		for(Integer i : listDay) {
//			listQuantity.add(i);
//		}
//		day_cb.setItems(listQuantity);
//	}
    
    public void showListDay(int month, int year) {
    	ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
        int maxDays;
        
        // Determine the maximum days in the month
        YearMonth yearMonth = YearMonth.of(year, month);
        maxDays = yearMonth.lengthOfMonth();
        
        // Determine the limit for the ComboBox
        int limit;
        if (maxDays == 28) {
            limit = 21;
        } else if (maxDays == 29) {
            limit = 22;
        } else if (maxDays == 30) {
            limit = 23;
        } else {
            limit = 24;
        }
        
        for (int i = 1; i <= limit; i++) {
            listQuantity.add(i);
        }
        startDay_cb.setItems(listQuantity);
	}
    
    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	showListYear();
    	year_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
            	showListMoth(newVal);
            }
        });
    	
    	month_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
            	showListDay(newVal, year_cb.getValue());
            }
        });
    		
    	//showListDay(month_cb.getValue(), year_cb.getValue());
    	//showListDay();
    	startDay_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
            	endDay_txt.setText(String.valueOf(newVal + 7));
            }
        });
    	
    	// Pie chart setup
        PieChart.Data slice1 = new PieChart.Data("Standard", 42);
        PieChart.Data slice2 = new PieChart.Data("Premium", 20);
        PieChart.Data slice3 = new PieChart.Data("Deluxe", 26);
        PieChart.Data slice4 = new PieChart.Data("Laopera", 12);

        pieChart.getData().addAll(slice1, slice2, slice3, slice4);

        for (PieChart.Data data : pieChart.getData()) {
            switch (data.getName()) {
                case "Standard":
                    data.getNode().setStyle("-fx-pie-color: #F94144;");
                    break;
                case "Premium":
                    data.getNode().setStyle("-fx-pie-color: #F8961E;");
                    break;
                case "Deluxe":
                    data.getNode().setStyle("-fx-pie-color: #F3722C;");
                    break;
                case "Laopera":
                    data.getNode().setStyle("-fx-pie-color: #F9C74F;");
                    break;
            }
            data.nameProperty().set(data.getName());
        }

        pieChart.setLabelLineLength(10);
        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(true);

        // Tạo một loạt dữ liệu
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Doanh thu");

        // Tạo một loạt dữ liệu
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Doanh thu");

        // Thêm dữ liệu vào loạt
        series.getData().add(new XYChart.Data<>("Trường", 200)); // Ví dụ: (Tháng 1, Doanh thu 100)
        series.getData().add(new XYChart.Data<>("Thông", 20)); // Ví dụ: (Tháng 2, Doanh thu 150)
        series.getData().add(new XYChart.Data<>("Thiện", 60)); // Ví dụ: (Tháng 3, Doanh thu 200)
        // Tiếp tục thêm dữ liệu cho các tháng tiếp theo nếu cần

        // Thêm loạt dữ liệu vào AreaChart
        areaChart.getData().add(series);

        // Truy cập trực tiếp vào trục x và thiết lập các thuộc tính của nó
        CategoryAxis xAxis = (CategoryAxis) areaChart.getXAxis();
        xAxis.setCategories(FXCollections.observableArrayList("Trường", "Thông", "Thiện")); // Đặt các nhãn trục x


        //barchart
        CategoryAxis xAxis1 = new CategoryAxis();
        NumberAxis yAxis1 = new NumberAxis();
        
        xAxis1.setLabel("Category");
        yAxis1.setLabel("Value");
        
        barChart.setTitle("Sample Bar Chart");
        barChart.getXAxis().setLabel("Category");
        barChart.getYAxis().setLabel("Value");

        // Dữ liệu mẫu
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("2019");
        series1.getData().add(new XYChart.Data<>("A", 1));
        series1.getData().add(new XYChart.Data<>("B", 4));
        series1.getData().add(new XYChart.Data<>("C", 3));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("2020");
        series2.getData().add(new XYChart.Data<>("A", 3));
        series2.getData().add(new XYChart.Data<>("B", 5));
        series2.getData().add(new XYChart.Data<>("C", 6));

        barChart.getData().addAll(series1, series2);
    }
}

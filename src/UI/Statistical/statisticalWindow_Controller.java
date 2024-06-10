package UI.Statistical;

import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;

import BLL.BAOCAO_BLL;
import DAO.BAOCAO_DAO;
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
    
    private long value = 0;
    
//    public void showListYear() {
//    	List<Integer> listYear = BAOCAO_BLL.getYears();
//		ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
//		for(Integer i : listYear) {
//			listQuantity.add(i);
//		}
//		year_cb.setItems(listQuantity);
//	}
//    
//    public void showListMoth(int year) {
//    	List<Integer> listMonth = BAOCAO_BLL.getMonths(year);
//		ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
//		for(Integer i : listMonth) {
//			listQuantity.add(i);
//		}
//		month_cb.setItems(listQuantity);
//	}
//    
////    public void showListDay(int month, int year) {
////    	List<Integer> listDay = BAOCAO_BLL.getDays(month, year);
////		ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
////		for(Integer i : listDay) {
////			listQuantity.add(i);
////		}
////		day_cb.setItems(listQuantity);
////	}
//    
//    public void showListDay(int month, int year) {
//    	ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
//        int maxDays;
//        
//        // Determine the maximum days in the month
//        YearMonth yearMonth = YearMonth.of(year, month);
//        maxDays = yearMonth.lengthOfMonth();
//        
//        // Determine the limit for the ComboBox
//        int limit;
//        if (maxDays == 28) {
//            limit = 21;
//        } else if (maxDays == 29) {
//            limit = 22;
//        } else if (maxDays == 30) {
//            limit = 23;
//        } else {
//            limit = 24;
//        }
//        
//        for (int i = 1; i <= limit; i++) {
//            listQuantity.add(i);
//        }
//        startDay_cb.setItems(listQuantity);
//	}
    
    public void showListYear() {
        List<Integer> listYear = BAOCAO_BLL.getYears();
        ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
        listQuantity.add(null); 
        listQuantity.addAll(listYear);
        year_cb.setItems(listQuantity);
        year_cb.setValue(null); 
    }
    
    public void showListMonth(int year) {
        List<Integer> listMonth = BAOCAO_BLL.getMonths(year);
        ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
        listQuantity.add(null); 
        listQuantity.addAll(listMonth);
        month_cb.setItems(listQuantity);
        month_cb.setValue(null); 
    }
    
    public void showListDay(int month, int year) {
        ObservableList<Integer> listQuantity = FXCollections.observableArrayList();
        listQuantity.add(null); 
        int maxDays = YearMonth.of(year, month).lengthOfMonth();
        int limit = Math.min(maxDays, 24); 
        for (int i = 1; i <= limit; i++) {
            listQuantity.add(i);
        }
        startDay_cb.setItems(listQuantity);
        startDay_cb.setValue(null); 
    }
    
    public void setUpPieChartByYear(int year) {
    	value = 0;
        ObservableList<Object[]> data = BAOCAO_BLL.getRoomDataByTypeAndYear(year);
        pieChart.getData().clear();

        for (Object[] row : data) {
            String roomType = (String) row[0];
            long totalValue = (Long) row[1];
            value += totalValue;
            PieChart.Data slice = new PieChart.Data(roomType, totalValue);
            pieChart.getData().add(slice);
        }
        String midPriceString = String.valueOf(value);
    	StringBuilder sbMidPrice = new StringBuilder(midPriceString);
    	int lngMidPrice = sbMidPrice.length();
    	for (int i = lngMidPrice - 3; i > 0; i -= 3) {
    		sbMidPrice.insert(i, ".");
    	}
    	String finalMidPrice = sbMidPrice.toString();
    	value_txt.setText(finalMidPrice);
    	
        setPieChartColors();
    }

    public void setUpPieChartByMonth(int month, int year) {
    	value = 0;
        ObservableList<Object[]> data = BAOCAO_BLL.getRoomDataByTypeAndMonth(year, month);
        pieChart.getData().clear();

        for (Object[] row : data) {
            String roomType = (String) row[0];
            long totalValue = (Long) row[1];
            value += totalValue;
            PieChart.Data slice = new PieChart.Data(roomType, totalValue);
            pieChart.getData().add(slice);
        }
        String midPriceString = String.valueOf(value);
    	StringBuilder sbMidPrice = new StringBuilder(midPriceString);
    	int lngMidPrice = sbMidPrice.length();
    	for (int i = lngMidPrice - 3; i > 0; i -= 3) {
    		sbMidPrice.insert(i, ".");
    	}
    	String finalMidPrice = sbMidPrice.toString();
    	value_txt.setText(finalMidPrice);

        setPieChartColors();
    }

    public void setUpPieChartBy7Days(int startDay, int endDays, int month, int year) {
    	value = 0;
        ObservableList<Object[]> data = BAOCAO_BLL.getRoomDataByTypeAndDateRange(startDay, endDays, month, year);
        pieChart.getData().clear();

        for (Object[] row : data) {
            String roomType = (String) row[0];
            long totalValue = (Long) row[1];
            value += totalValue;
            PieChart.Data slice = new PieChart.Data(roomType, totalValue);
            pieChart.getData().add(slice);
        }
 
    	String midPriceString = String.valueOf(value);
    	StringBuilder sbMidPrice = new StringBuilder(midPriceString);
    	int lngMidPrice = sbMidPrice.length();
    	for (int i = lngMidPrice - 3; i > 0; i -= 3) {
    		sbMidPrice.insert(i, ".");
    	}
    	String finalMidPrice = sbMidPrice.toString();
    	value_txt.setText(finalMidPrice);

        setPieChartColors();
    }

    private void setPieChartColors() {
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
    }
    
    public void setUpAreaChartByMonth(int year) {
        ObservableList<Object[]> data = BAOCAO_BLL.getMonthlyReport(year);
        areaChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Doanh thu");

        for (Object[] row : data) {
            int day = (int) row[0];
            double totalValue = (double) row[1];
            series.getData().add(new XYChart.Data<>(String.valueOf(day), totalValue));
        }

        areaChart.getData().add(series);
    }

    public void setUpAreaChartBy7Days(int startDay, int endDays, int month, int year) {
        ObservableList<Object[]> data = BAOCAO_BLL.getDailyReport(startDay, endDays, month, year);
        areaChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Doanh thu");

        for (Object[] row : data) {
            int day = (int) row[0];
            double totalValue = (double) row[1];
            series.getData().add(new XYChart.Data<>(String.valueOf(day), totalValue));
        }

        areaChart.getData().add(series);
    }
    
    public void setUpBarChartByMonth(int year) {
        ObservableList<Object[]> data = BAOCAO_BLL.getMonthlyReport(year);
        barChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Số lượt thuê");

        for (Object[] row : data) {
            int day = (int) row[0];
            int totalRent = (int) row[2];
            series.getData().add(new XYChart.Data<>(String.valueOf(day), totalRent));
        }

        barChart.getData().add(series);
    }

    public void setUpBarChartBy7Days(int startDay, int endDays, int month, int year) {
        ObservableList<Object[]> data = BAOCAO_BLL.getDailyReport(startDay, endDays, month, year);
        barChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Số lượt thuê");

        for (Object[] row : data) {
            int day = (int) row[0];
            int totalRent = (int) row[2];
            series.getData().add(new XYChart.Data<>(String.valueOf(day), totalRent));
        }

        barChart.getData().add(series);
    }
    
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//getRoomDataByType()
	    showListYear();
	    year_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
	        if (newVal != null) {
	            showListMonth(newVal);
	            setUpPieChartByYear(newVal);
	            setUpAreaChartByMonth(year_cb.getValue());
	            setUpBarChartByMonth(year_cb.getValue());
	        }
	    });

	    month_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
	        if (newVal != null) {
	            showListDay(newVal, year_cb.getValue());
	            setUpPieChartByMonth(newVal, year_cb.getValue());
	        }
	    });

	    startDay_cb.valueProperty().addListener((obs, oldVal, newVal) -> {
	        if (newVal != null) {
	            int endDay = newVal + 9;
	            endDay_txt.setText(String.valueOf(endDay));
	            setUpPieChartBy7Days(newVal, Integer.parseInt(endDay_txt.getText()), month_cb.getValue(), year_cb.getValue());
	            setUpAreaChartBy7Days(newVal, Integer.parseInt(endDay_txt.getText()), month_cb.getValue(), year_cb.getValue());
	            setUpBarChartBy7Days(newVal, Integer.parseInt(endDay_txt.getText()), month_cb.getValue(), year_cb.getValue());
	        }
	    });

	    // Example data for PieChart
	    //setUpPieChartByYear(year_cb.getValue() != null ? year_cb.getValue() : 2023);

	    // Example data for AreaChart
	    //setUpAreaChartByMonth(1, 2023);

	    // Example data for BarChart
	    //setUpBarChartByMonth(1, 2023);
	}
}

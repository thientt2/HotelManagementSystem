package UI.Statistical;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis; // Import CategoryAxis
import javafx.scene.chart.NumberAxis; // Import NumberAxis
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;

public class statisticalWindow_Controller implements Initializable {

    @FXML
    private PieChart pieChart;

    @FXML
    private AreaChart<Number, Number> areaChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

//        // Area chart setup
//        XYChart.Series<Number, Number> series = new XYChart.Series<>();
//        series.getData().add(new XYChart.Data<>(1, 5000));
//        series.getData().add(new XYChart.Data<>(2, 10000));
//        series.getData().add(new XYChart.Data<>(3, 15000));
//        series.getData().add(new XYChart.Data<>(4, 20000));
//        series.getData().add(new XYChart.Data<>(5, 25000));
//
//        areaChart.getData().add(series);
//
//        CategoryAxis xAxis = new CategoryAxis(); // Sử dụng CategoryAxis cho trục X
//        xAxis.setCategories(FXCollections.observableArrayList(
//            "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"
//        ));
//        xAxis.setLabel("Ngày");
//
//        areaChart.setXAxis(xAxis); // Thiết lập trục X của biểu đồ diện tích
//
//        ValueAxis<Number> yAxis = (ValueAxis<Number>) areaChart.getYAxis();
//        yAxis.setAutoRanging(false);
//        yAxis.setLowerBound(0);
//        yAxis.setUpperBound(25000);
//        yAxis.setTickLength(10); // Set the tick length
//        yAxis.setLabel("Doanh thu (VNĐ)");
    }
}

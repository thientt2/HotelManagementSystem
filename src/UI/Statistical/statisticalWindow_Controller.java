package UI.Statistical;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import BLL.BAOCAO_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.PHIEUNHANPHONG_BLL;
import BLL.PHONG_BLL;
import DAO.BAOCAO_DAO;
import DTO.LOAIPHONG;
import DTO.PHIEUNHANPHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis; 
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class statisticalWindow_Controller implements Initializable {

    @FXML
    private Button print_btn;
    
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
    
//    public static void exportReportToExcel() {
//        // Define column headers
//        String[] columns = {"Ngay", "Thang", "Nam", "Ten Loai Phong", "Doanh Thu", "So Luot Thue"};
//
//        // Create a Workbook
//        Workbook workbook = new XSSFWorkbook();
//
//        // Create a Sheet
//        Sheet sheet = workbook.createSheet("Bao Cao");
//
//        // Create a Font for styling header cells
//        Font headerFont = workbook.createFont();
//        headerFont.setBold(true);
//        headerFont.setFontHeightInPoints((short) 12);
//        headerFont.setColor(IndexedColors.RED.getIndex());
//
//        // Create a CellStyle with the font
//        CellStyle headerCellStyle = workbook.createCellStyle();
//        headerCellStyle.setFont(headerFont);
//
//        // Create a Row
//        Row headerRow = sheet.createRow(0);
//
//        // Create cells
//        for (int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//            cell.setCellStyle(headerCellStyle);
//        }
//
//        // Fetch data from the database (replace with your data fetching logic)
//        List<Object[]> reportData = BAOCAO_DAO.getReportData();
//
//        // Create Other rows and cells with report data
//        int rowNum = 1;
//        for (Object[] rowData : reportData) {
//            Row row = sheet.createRow(rowNum++);
//
//            row.createCell(0).setCellValue((Integer) rowData[0]);
//            row.createCell(1).setCellValue((Integer) rowData[1]);
//            row.createCell(2).setCellValue((Integer) rowData[2]);
//            row.createCell(3).setCellValue((String) rowData[3]);
//            row.createCell(4).setCellValue((Double) rowData[4]);
//            row.createCell(5).setCellValue((Integer) rowData[5]);
//        }
//
//        // Resize all columns to fit the content size
//        for (int i = 0; i < columns.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//        // Write the workbook content to a temporary file
//        File tempFile = null;
//        try {
//            tempFile = File.createTempFile("BaoCao", ".xlsx");
//            try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
//                workbook.write(fileOut);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                workbook.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Open the temporary file using Desktop
//        if (tempFile != null && Desktop.isDesktopSupported()) {
//            try {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.open(tempFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    
    public static void exportReportToExcel(String filePath) {
        // Define column headers
        String[] columns = {"Ngày", "Tháng", "Năm", "Tên Loại Phòng", "Doanh Thu", "Số Lượt Thuê"};
        
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();
        
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Bao Cao");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        // Fetch data from the database
        List<Object[]> reportData = BAOCAO_DAO.getReportData();
        
        // Create Other rows and cells with report data
        int rowNum = 1;
        for (Object[] rowData : reportData) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue((Integer) rowData[0]);
            row.createCell(1).setCellValue((Integer) rowData[1]);
            row.createCell(2).setCellValue((Integer) rowData[2]);
            row.createCell(3).setCellValue((String) rowData[3]);
            row.createCell(4).setCellValue((Double) rowData[4]);
            row.createCell(5).setCellValue((Integer) rowData[5]);
        }
        
        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static void exportReportToExcel(String filePath) throws IOException {
//        List<Object[]> reportData = BAOCAO_DAO.getReportData();
//        
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("BAOCAO");
//
//        // Create headers
//        String[] headers = {"Ngày", "Tháng", "Năm", "Tên Loại Phòng", "Doanh Thu", "Số Lượt Thuê"};
//        Row headerRow = sheet.createRow(0);
//        for (int i = 0; i < headers.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(headers[i]);
//        }
//
//        // Write data
//        int rowNum = 1;
//        for (Object[] row : reportData) {
//            Row dataRow = sheet.createRow(rowNum++);
//            for (int colNum = 0; colNum < row.length; colNum++) {
//                Cell cell = dataRow.createCell(colNum);
//                cell.setCellValue(row[colNum].toString());
//            }
//        }
//
//        // Write the output to a file
//        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//            workbook.write(fileOut);
//        }
//
//        // Closing the workbook
//        workbook.close();
//    }
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//getRoomDataByType(
		print_btn.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Xác nhận thống kê");
	        alert.setHeaderText(null);
	        alert.setContentText("Xác nhận in bảng thống kê?");
	        Optional<ButtonType> option = alert.showAndWait();

	        if (option.get().equals(ButtonType.OK)) {
				exportReportToExcel("baocao.xlsx");
	        	alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Thông báo");
	            alert.setHeaderText(null);
	            alert.setContentText("Xuất bảng thống kê thành công!");
	            alert.showAndWait();
		    	
	        } else {
	            return;
	        }
		});
		
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

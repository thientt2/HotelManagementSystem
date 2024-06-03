package UI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DTO.BAOCAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class statisticalWindow_Controller implements Initializable{
	
    @FXML
    private PieChart pieChart;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//    	int day = 1;  // Example values
//        int month = 6;
//        int year = 2024;
//
//        List<BAOCAO> roomDataList = DatabaseUtil.getRoomData(day, month, year);
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//
//        for (RoomData roomData : roomDataList) {
//            pieChartData.add(new PieChart.Data(roomData.getRoomType() + " (" + roomData.getValue() + ")", roomData.getPercentage()));
//        }
//
//        pieChart.setData(pieChartData);
	}

}

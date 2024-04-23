package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PHONG_DAO {
	
   
    public static ObservableList<Object[]> showRoom() {
    	
    	ObservableList<Object[]> dataList = FXCollections.observableArrayList();   	    	 

    	 
    	 try (Connection connection = DatabaseConnection.connectDb();
                 Statement statement = connection.createStatement()) {
                String query = "SELECT P.MAPHONG, L.TENLOAI, L.GIA, L.NGUOITOIDA, T.TENTRANGTHAI " +
                               "FROM PHONG P " +
                               "JOIN LOAIPHONG L ON P.MALOAIP = L.MALOAI " +
                               "JOIN TRANGTHAIPHONG T ON P.MATRANGTHAI = T.MATRANGTHAI";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Object[] rowData = new Object[5];
                    rowData[0] = resultSet.getInt("MAPHONG");
                    rowData[1] = resultSet.getString("TENLOAI");
                    rowData[2] = resultSet.getDouble("GIA");
                    rowData[3] = resultSet.getInt("NGUOITOIDA");
                    rowData[4] = resultSet.getString("TENTRANGTHAI");

                    dataList.add(rowData);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    	 
		return dataList;
    	
    }

}

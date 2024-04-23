package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.SystemMessage;

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
    
    public static void addRoom(Map<String, String> data) {
    	
    	int sophong = Integer.parseInt(data.get("sophong"));
    	int maloai = Integer.parseInt(data.get("maloai"));
    	int matrangthai = Integer.parseInt(data.get("matrangthai"));
		
    	String query = "INSERT INTO PHONG VALUES(?,?,?)";
    	try (Connection connection = DatabaseConnection.connectDb();
    		PreparedStatement prepare = connection.prepareStatement(query)) {
               
    	
               prepare.setInt(1, sophong);
               prepare.setInt(2, maloai);
               prepare.setInt(3, matrangthai);
               
               int rowsAffected = prepare.executeUpdate(); 
               
               if (!(rowsAffected > 0)) {
                  SystemMessage.ERROR_MESSAGE = "ERROR_2";
               } 

           } catch (SQLException e) {
               e.printStackTrace();
           }
    	
    }
	

}

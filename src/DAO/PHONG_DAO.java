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
                String query = "SELECT P.MAPHONG, L.TENLOAI, L.LOAIGIUONG, L.DIENTICH, L.GIA, L.NGUOITOIDA, T.TENTRANGTHAI " +
                               "FROM PHONG P " +
                               "JOIN LOAIPHONG L ON P.MALOAIP = L.MALOAIP " +
                               "JOIN TRANGTHAIPHONG T ON P.MATRANGTHAI = T.MATRANGTHAI";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Object[] rowData = new Object[7];
                    rowData[0] = resultSet.getString("MAPHONG");
                    rowData[1] = resultSet.getString("TENLOAI");
                    rowData[2] = resultSet.getString("LOAIGIUONG");
                    rowData[3] = resultSet.getDouble("DIENTICH");
                    rowData[4] = resultSet.getInt("NGUOITOIDA");
                    rowData[5] = resultSet.getDouble("GIA");
                    rowData[6] = resultSet.getString("TENTRANGTHAI");

                    dataList.add(rowData);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    	 
		return dataList;    	
    }
    
    public static void addRoom(Map<String, String> data) {
    	

    	String maphong = data.get("maphong");
    	int maloai = Integer.parseInt(data.get("maloai"));
    	int matrangthai = Integer.parseInt(data.get("matrangthai"));
		
    	String query = "INSERT INTO PHONG VALUES(?,?,?)";
    	try (Connection connection = DatabaseConnection.connectDb();
    		PreparedStatement prepare = connection.prepareStatement(query)) {
               
    	
               prepare.setString(1, maphong);
               prepare.setInt(2, maloai);
               prepare.setInt(3, matrangthai);
               
               prepare.executeUpdate();                
               
           } catch (SQLException e) {
               e.printStackTrace();
           }    	
    }
    
    public static boolean checkIDRoom(Map<String,String> data) throws SQLException {
    	String maphong = data.get("maphong");
    	String sql = "Select MAPHONG from PHONG where MAPHONG = ?";
    	try(Connection connect = DatabaseConnection.connectDb();
    		PreparedStatement prepare = connect.prepareStatement(sql)){
    		prepare.setString(1, maphong);
    		ResultSet result = prepare.executeQuery();
    		while(result.next()) {
	    		SystemMessage.ERROR_MESSAGE = "ERROR_3";
	    		return true;
    		}
    	} catch (SQLException e) {
            e.printStackTrace();
        }   
    	return false;
    	
    }
	

}

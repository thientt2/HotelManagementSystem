package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.LOAIPHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LOAIPHONG_DAO {
	public static ObservableList<LOAIPHONG> showRoomType(){
		ObservableList<LOAIPHONG> dataList = FXCollections.observableArrayList();   	
		
		try (Connection connection = DatabaseConnection.connectDb();
                Statement statement = connection.createStatement()) {
               String query = "SELECT * FROM LOAIPHONG";
               
               ResultSet result = statement.executeQuery(query);

               while (result.next()) {
                   LOAIPHONG data = new LOAIPHONG(result.getInt("MALOAIP")
                		   ,result.getString("TENLOAI")
                		   ,result.getString("LOAIGIUONG")
                		   ,result.getDouble("GIA")
                		   ,result.getInt("NGUOITOIDA")
                		   ,result.getDouble("DIENTICH"));

                   dataList.add(data);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
		return dataList;		
	}
	
	public static List<LOAIPHONG> getRoomTypes() {
		List<LOAIPHONG> dataList = new ArrayList<>();
		
		try (Connection connection = DatabaseConnection.connectDb();
                Statement statement = connection.createStatement()) {
               String query = "SELECT * FROM LOAIPHONG";
               ResultSet result = statement.executeQuery(query);
               
               while (result.next()) {
            	   LOAIPHONG data = new LOAIPHONG(result.getInt("MALOAIP")
                		   ,result.getString("TENLOAI")
                		   ,result.getString("LOAIGIUONG")
                		   ,result.getDouble("GIA")
                		   ,result.getInt("NGUOITOIDA")
                		   ,result.getDouble("DIENTICH"));

                   dataList.add(data);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
		return dataList;
	}

	public static int getRoomTypeId(String name) {
		int maloai = 0;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAIPHONG WHERE TENLOAI = N'" + name + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return maloai = resultSet.getInt("MALOAIP");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maloai;
	}
	
}

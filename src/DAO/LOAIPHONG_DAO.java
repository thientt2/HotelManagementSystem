package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                   LOAIPHONG data = new LOAIPHONG(result.getInt("MALOAI")
                		   ,result.getString("TENLOAI")
                		   ,result.getDouble("GIA")
                		   ,result.getInt("NGUOITOIDA"));

                   dataList.add(data);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
		return dataList;
		
	}

}

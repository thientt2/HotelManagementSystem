package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DTO.TRANGTHAIPHONG;

public class TRANGTHAIPHONG_DAO {

	public static List<TRANGTHAIPHONG> getStatusRoom(){
		List<TRANGTHAIPHONG> dataList = new ArrayList<>(); 
		try (Connection connection = DatabaseConnection.connectDb();
                Statement statement = connection.createStatement()) {
               String query = "SELECT * FROM TRANGTHAIPHONG";
               ResultSet resultSet = statement.executeQuery(query);
               
               while (resultSet.next()) {
            	   
                   Integer data1 = resultSet.getInt("MATRANGTHAI");     
                   String data2 = resultSet.getString("TENTRANGTHAI");

                   dataList.add(new TRANGTHAIPHONG(data1,data2));
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }		
		return dataList;
	}
}

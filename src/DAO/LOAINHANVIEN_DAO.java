package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LOAINHANVIEN_DAO {
	
	public static List<String> listStaffType(){

		List<String> list = new ArrayList<>();		
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAINHANVIEN";
			ResultSet resultSet = statement.executeQuery(query);			
			while (resultSet.next()) {
				list.add(resultSet.getString("TENLOAINV"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
		
	}

}

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
	
	public static String getStaffTypeName(int id) {
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAINHANVIEN WHERE MALOAINV = " + id;
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				return resultSet.getString("TENLOAINV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getStaffTypeId(String name) {
		int maloainv = 0;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAINHANVIEN WHERE TENLOAINV = N'" + name + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return maloainv = resultSet.getInt("MALOAINV");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return maloainv;
	}
}

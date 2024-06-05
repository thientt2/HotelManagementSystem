package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LOAIDICHVU_DAO {
	
	public static ObservableList<String> listServiceType(){

		ObservableList<String> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAIDICHVU";
			ResultSet resultSet = statement.executeQuery(query);			
			while (resultSet.next()) {
				list.add(resultSet.getString("TENLOAIDV"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
		
	}
	
	public static String getServiceTypeName(int id) {
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAIDICHVU WHERE MALOAIDV = " + id;
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				return resultSet.getString("TENLOAIDV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getServiceTypeId(String name) {
		int maloaidv = 0;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAIDICHVU WHERE TENLOAIDV = N'" + name + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				maloaidv = resultSet.getInt("MALOAIDV");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return maloaidv;
	}
	
	public static ObservableList<String> listServiceName(int serviceType){
		ObservableList<String> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM DICHVU WHERE MALOAIDV = " + serviceType;
			ResultSet resultSet = statement.executeQuery(query);			
			while (resultSet.next()) {
				list.add(resultSet.getString("TENDV"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;	
	}
}

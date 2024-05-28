package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import DTO.HOADONDICHVU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HOADONDICHVU_DAO {
	
	public static ObservableList<HOADONDICHVU> listBillService() {
		ObservableList<HOADONDICHVU> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT * FROM HOADONDICHVU";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				list.add(new HOADONDICHVU(resultSet.getString("MAHD")   
						,resultSet.getString("MAPNP")    
	   					,resultSet.getString("NVNHAP")                		   					
	   					,resultSet.getString("NGAYTAO")
	   					,resultSet.getInt("GIADICHVU")
	   					,resultSet.getInt("PHUTHU")
	   					,resultSet.getInt("TRANGTHAI")
	   					,resultSet.getInt("TONGTIEN")));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
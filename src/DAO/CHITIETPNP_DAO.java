package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class CHITIETPNP_DAO {

	public static void insertDetailRecieveRoom(Map<String, Object> data) {
		String maPDP = (String) data.get("maPDP");
		String maKH = (String) data.get("maKH");
		
		String query = "INSERT INTO CHITIETPDP VALUES(?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maPDP);
			prepare.setString(2, maKH);
			
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCustomerId(String recieveRoomId) {
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM CHITIETPNP WHERE MAPNP = '" + recieveRoomId + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return resultSet.getString("MAKH");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return null;
	}
	
}

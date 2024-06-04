package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class CHITIETPNP_DAO {

	public static void insertDetailRecieveRoom(String maPNP, List<String> listOthers) {

		for(String customer: listOthers) {
			String query = "INSERT INTO CHITIETPNP VALUES(?,?)";
			try (Connection connection = DatabaseConnection.connectDb()) {
				PreparedStatement prepare = connection.prepareStatement(query);
				prepare.setString(1, maPNP);
				prepare.setString(2, customer);				
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

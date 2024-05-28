package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class CHITIETPDP_DAO {

	public static void insertDetailBookRoom(Map<String, Object> data) {
		String maPDP = (String) data.get("maPDP");
		int maLoaiPhong = (int) data.get("maLoaiPhong");
		int soLuong = (int) data.get("soLuong");
		
		String query = "INSERT INTO CHITIETPDP VALUES(?,?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maPDP);
			prepare.setInt(2, maLoaiPhong);
			prepare.setInt(3, soLuong);
			
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getTypeRoomId(String bookRoomId) {
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM CHITIETPDP WHERE MAPDP = '" + bookRoomId + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return resultSet.getInt("MALOAIP");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return 0;
	}
	
}

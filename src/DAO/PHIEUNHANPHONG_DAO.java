package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import DTO.PHIEUNHANPHONG;

public class PHIEUNHANPHONG_DAO {

	public static void insertReceiveRoom(Map<String, Object> data) {
		// TODO Auto-generated method stub
		String maPDP = (String) data.get("maPDP");
		String maPH = (String) data.get("maPhong");
		String ngayNhan = (String) data.get("ngayNhan");
		String ngayTra = (String) data.get("ngayTra");
		String query = "INSERT INTO PHIEUNHANPHONG(MAPDP, MAPHONG, TGNHAN, TGTRA) VALUES(?,?,?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maPDP);
			prepare.setString(2, maPH);
			prepare.setString(3, ngayNhan);
			prepare.setString(4, ngayTra);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getLastReceiveRoom() {
		String lastReceiveRoom = null;		
		String query = "SELECT TOP 1 MAPNP FROM PHIEUNHANPHONG ORDER BY MAPDP DESC";
		try(Connection connection = DatabaseConnection.connectDb();
			PreparedStatement prepare = connection.prepareStatement(query);) {
			ResultSet resultSet = prepare.executeQuery();
			while(resultSet.next()) {
				lastReceiveRoom = resultSet.getString("MAPNP");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lastReceiveRoom;
	}
	
	public static String getReceiveRoomIDByRoomID(String roomID) {
		String receiveRoomID = null;
		String query = "SELECT * FROM PHIEUNHANPHONG WHERE MAPHONG = '" + roomID + "' AND GETDATE() >= TGNHAN AND GETDATE() <= TGTRA";
		try(Connection connection = DatabaseConnection.connectDb();
			PreparedStatement prepare = connection.prepareStatement(query);) {			
			ResultSet resultSet = prepare.executeQuery();
			while(resultSet.next()) {
				receiveRoomID = resultSet.getString("MAPNP");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return receiveRoomID;
	}

}

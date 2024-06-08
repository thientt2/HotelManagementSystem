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
		String query = "SELECT TOP 1 MAPNP FROM PHIEUNHANPHONG ORDER BY MAPNP DESC";
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
	
	public static PHIEUNHANPHONG getReceiveRoomIDByRoomID(String roomID) {
		PHIEUNHANPHONG receiveRoomID = null;
		String query = "SELECT * FROM PHIEUNHANPHONG WHERE MAPHONG = ? AND CAST(GETDATE() AS date) >= CAST(TGNHAN AS date) AND CAST(GETDATE() AS date) <= CAST(TGTRA AS date)";
		try(Connection connection = DatabaseConnection.connectDb();
			PreparedStatement prepare = connection.prepareStatement(query);) {		
			prepare.setString(1, roomID);
			ResultSet resultSet = prepare.executeQuery();
			while(resultSet.next()) {
				receiveRoomID = new PHIEUNHANPHONG(resultSet.getString("MAPNP"),
						resultSet.getString("MAPDP"),
						resultSet.getString("MAPHONG"), 
						resultSet.getString("TGNHAN"),
						resultSet.getString("TGTRA"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return receiveRoomID;
	}

	public static void updateCheckOut(String mapdp, String checkout) {
		// TODO Auto-generated method stub
		String query = "UPDATE PHIEUNHANPHONG SET TGTRA = ? WHERE MAPNP = ?";
		try (Connection connection = DatabaseConnection.connectDb();) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, checkout);
			prepare.setString(2, mapdp);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class CHITIETHOADON_DAO {

	public static void insertBillDetailService(Map<String, Object> data) {
		String maHD = (String) data.get("maHD");
		String maDV = (String) data.get("maDV");
		String soLuong = (String) data.get("soLuong");
		double tongTien = (double) data.get("tongTien");
		String query = "INSERT INTO CHITIETHOADON VALUES(?,?,?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maHD);
			prepare.setString(2, maDV);
			prepare.setString(3, soLuong);
			prepare.setDouble(4, tongTien);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

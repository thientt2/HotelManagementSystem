package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	
	public static List<Object[]> getServiceDetailsByMapnp(String mapnp) {
        List<Object[]> list = new ArrayList<>();
        
        String query = "SELECT DV.TENDV, CTHD.SOLUONG, CTHD.TONGTIEN " +
                       "FROM CHITIETHOADON CTHD " +
                       "JOIN DICHVU DV ON CTHD.MADV = DV.MADV " +
                       "JOIN HOADONDICHVU HD ON CTHD.MAHD = HD.MAHD " +
                       "WHERE HD.MAPNP = ?";
        
        try (Connection connection = DatabaseConnection.connectDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, mapnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String tenDV = resultSet.getString("TENDV");
                int soLuong = resultSet.getInt("SOLUONG");
                double tongTien = resultSet.getDouble("TONGTIEN");
                Object[] row = {tenDV, soLuong, tongTien};
                list.add(row);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}

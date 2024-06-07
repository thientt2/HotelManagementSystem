package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CHITIETHOADON_DAO {

	public static void insertBillDetailService(Map<String, Object> data) {
		String maHD = (String) data.get("maHD");
		String maDV = (String) data.get("maDV");
		int soLuong = (int) data.get("soLuong");
		int tongTien = (int) data.get("tongTien");
		String query = "INSERT INTO CHITIETHOADON VALUES(?,?,?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maHD);
			prepare.setString(2, maDV);
			prepare.setInt(3, soLuong);
			prepare.setInt(4, tongTien);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<Object[]> getListDetailService(String mahd) {
		// TODO Auto-generated method stub
		ObservableList<Object[]> list = FXCollections.observableArrayList();
		String query = "SELECT LDV.TENLOAIDV,DV.TENDV, DV.GIA, CT.SOLUONG, CT.TONGTIEN "
				+ "FROM CHITIETHOADON CT, DICHVU DV, LOAIDICHVU LDV "
				+ "WHERE CT.MADV = DV.MADV AND DV.LOAIDV = LDV.MALOAIDV AND MAHD = '" + mahd + "'";
		try (Connection connection = DatabaseConnection.connectDb();
				PreparedStatement prepare = connection.prepareStatement(query);
				ResultSet resultSet = prepare.executeQuery()) {
			while (resultSet.next()) {
				Object[] rowData = new Object[5];
				rowData[0] = resultSet.getString("TENLOAIDV");
				rowData[1] = resultSet.getString("TENDV");
				double price = resultSet.getDouble("GIA");
				String formattedPrice = String.format("%.0f", price);
				rowData[2] = formattedPrice;
				rowData[3] = resultSet.getString("SOLUONG");
				double total = resultSet.getDouble("TONGTIEN");
				String formattedTotal = String.format("%.0f", total);
				rowData[4] = formattedTotal;
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void updateDetailBillService(Map<String, Object> billDetailService) {
		// TODO Auto-generated method stub
		String maHD = (String) billDetailService.get("maHD");
		String maDV = (String) billDetailService.get("maDV");
		int soLuong = (int) billDetailService.get("soLuong");
		int tongTien = (int) billDetailService.get("tongTien");
		String query = "UPDATE CHITIETHOADON SET SOLUONG = SOLUONG + ?, TONGTIEN = TONGTIEN + ? WHERE MAHD = ? AND MADV = ?";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setInt(1, soLuong);
			prepare.setInt(2, tongTien);
			prepare.setString(3, maHD);
			prepare.setString(4, maDV);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isBillDetailServiceExist(String maHD, String maDV) {
		// TODO Auto-generated method stub
		String query = "SELECT 1 FROM CHITIETHOADON WHERE MAHD = ? AND MADV = ?";
		try (Connection connection = DatabaseConnection.connectDb();
				PreparedStatement prepare = connection.prepareStatement(query)) {
			prepare.setString(1, maHD);
			prepare.setString(2, maDV);
			ResultSet resultSet = prepare.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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

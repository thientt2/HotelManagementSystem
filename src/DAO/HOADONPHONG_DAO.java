package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import DTO.HOADONPHONG;
import DTO.PHIEUDATPHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HOADONPHONG_DAO {
	
	public static ObservableList<HOADONPHONG> listBillBookRoom() {
		ObservableList<HOADONPHONG> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT * FROM HOADONPHONG";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				list.add(new HOADONPHONG(resultSet.getString("MAHDP")   
						,resultSet.getString("MAPDP")    
	   					,resultSet.getString("NVNHAP")                		   					
	   					,resultSet.getString("NGAYTAO")
	   					,resultSet.getInt("TRANGTHAI")
	   					,resultSet.getInt("GIAMGIA")
	   					,resultSet.getInt("TONGTIEN")));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void insertBillBookRoom(Map<String, Object> data) {
		String maPDP = (String) data.get("maPDP");
		String maNV = (String) data.get("maNV");
		String ngayTao = (String) data.get("ngTao");
		int trangThai = 1;
		int giamGia = 0;
		double tongTien = (double) data.get("tongTien");
		String query = "INSERT INTO HOADONPHONG(MAPDP, NVNHAP, NGAYTAO, TRANGTHAI, GIAMGIA, TONGTIEN) VALUES(?,?,?,?,?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maPDP);
			prepare.setString(2, maNV);
			prepare.setString(3, ngayTao);
			prepare.setInt(4, trangThai);
			prepare.setInt(5, giamGia);
			prepare.setDouble(6, tongTien);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static HOADONPHONG getLastBill() {
		String sql = "SELECT TOP 1 * FROM HOADONPHONG ORDER BY MAHDP DESC ";
		PreparedStatement pst;
		HOADONPHONG hoaDonPhong = null;
		try(Connection con = DatabaseConnection.connectDb();
		) {
			pst = con.prepareStatement(sql);
			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				hoaDonPhong = new HOADONPHONG(rs.getString("MAHDP")   
						,rs.getString("MAPDP")    
	   					,rs.getString("NVNHAP")                		   					
	   					,rs.getString("NGAYTAO")
	   					,rs.getInt("TRANGTHAI")
	   					,rs.getInt("GIAMGIA")
	   					,rs.getInt("TONGTIEN"));}	
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoaDonPhong;
	}
	
}

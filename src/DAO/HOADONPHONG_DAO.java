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
		String query = "INSERT INTO HOADONPHONG(MAPDP, NVNHAP, NGAYTAO, TRANGTHAI) VALUES(?,?,?,?)";
		try (Connection connection = DatabaseConnection.connectDb()) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maPDP);
			prepare.setString(2, maNV);
			prepare.setString(3, ngayTao);
			prepare.setInt(4, trangThai);
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


	public static HOADONPHONG getRoomBill(String bookRoomID) {
		// TODO Auto-generated method stub
		HOADONPHONG hoaDonPhong = null;
		String query = "SELECT * FROM HOADONPHONG WHERE MAPDP = ?";
		try(Connection con = DatabaseConnection.connectDb();
		PreparedStatement pst = con.prepareStatement(query);){			
			pst.setString(1, bookRoomID);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoaDonPhong;
	}


	public static ObservableList<Object[]> listDetailBill(String roomID) {
		// TODO Auto-generated method stub
		String query = "SELECT"
				+ " LP.TENLOAI,DATEDIFF(day, PDP.NGAYNHAN, PDP.NGAYTRA) AS SoNgayO,"
				+ " LP.GIA, CT.SOLUONG,(DATEDIFF(day, PDP.NGAYNHAN, PDP.NGAYTRA)  * LP.GIA * CT.SOLUONG) AS TongChiPhi "
				+ "FROM PHIEUDATPHONG PDP, CHITIETPDP CT, LOAIPHONG LP "
				+ "WHERE PDP.MAPDP = CT.MAPDP AND CT.MALOAIP = LP.MALOAIP AND PDP.MAPDP = '"+ roomID +"'";
		ObservableList<Object[]> list = FXCollections.observableArrayList();
		try(Connection con = DatabaseConnection.connectDb();
		PreparedStatement pst = con.prepareStatement(query);){
			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Object[] rowData = new Object[5];
				rowData[0] = rs.getString("TENLOAI");				
				rowData[1] = rs.getInt("GIA");
				rowData[2] = rs.getInt("SOLUONG");
				rowData[3] = rs.getInt("SoNgayO");
				rowData[4] = rs.getInt("TongChiPhi");
				list.add(rowData);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

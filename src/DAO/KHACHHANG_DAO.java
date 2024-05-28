package DAO;

import java.sql.*;
import java.util.Map;

import DTO.KHACHHANG;
import DTO.NHANVIEN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KHACHHANG_DAO {
	
	public static ObservableList<KHACHHANG> listCustomer() {
		ObservableList<KHACHHANG> list = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM KHACHHANG WHERE TINHTRANG = 1";
			Connection con = DatabaseConnection.connectDb();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				KHACHHANG kh = new KHACHHANG(rs.getString("MAKH")
						,rs.getString("TENKH")
						,rs.getString("CCCD")
						,rs.getString("GIOITINH")
						,rs.getString("NGAYSINH")
						,rs.getString("EMAIL")
						,rs.getInt("LOAIKH")
						,rs.getString("DIACHI")
						,rs.getString("SDT")
						,rs.getString("QUOCTICH")
						,rs.getInt("TINHTRANG"));					
				list.add(kh);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void insertCustomer(Map<String, String> data) throws SQLException {
		String tenKH = data.get("name");
		String cccd = data.get("cccd");
		String gender = data.get("gender");
		String birthday = data.get("birthday");
		String email = data.get("email");
		String phone = data.get("phone");
		String address = data.get("address");
		String country = data.get("country");
		Integer loaiKH;
		if(country.equals("Việt Nam")) {
			loaiKH = 1;
		} else {
			loaiKH = 2;
		}
		String sql = "INSERT INTO KHACHHANG(TENKH, CCCD, GIOITINH, NGAYSINH, EMAIL, LOAIKH, DIACHI, SDT, QUOCTICH, TINHTRANG)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection con = DatabaseConnection.connectDb();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);			
			pst.setString(1, tenKH);
			pst.setString(2, cccd);
			pst.setString(3, gender);
			pst.setString(4, birthday);
			pst.setString(5, email);
			pst.setInt(6, loaiKH);
			pst.setString(7, address);
			pst.setString(8, phone);
			pst.setString(9, country);
			pst.setInt(10, 1);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void editCustomer(Map<String, String> data) throws SQLException {
		String maKH = data.get("id");
		String tenKH = data.get("name");
	    String cccd = data.get("cccd");
	    String gender = data.get("gender");
	    String birthday = data.get("birthday");
	    String email = data.get("email");
	    String phone = data.get("phone");
	    String address = data.get("address");
	    String country = data.get("country");
	    Integer loaiKH;
	    if(country.equals("Việt Nam")) {
	        loaiKH = 1;
	    } else {
	        loaiKH = 2;
	    }
	    String sql = "UPDATE KHACHHANG SET TENKH=?, CCCD=?, GIOITINH=?, NGAYSINH=?, EMAIL=?, LOAIKH=?, DIACHI=?, SDT=?, QUOCTICH=? WHERE MAKH=?";
	    Connection con = DatabaseConnection.connectDb();
	    PreparedStatement pst;
	    try {
	        pst = con.prepareStatement(sql);
	        pst.setString(1, tenKH);
	        pst.setString(2, cccd);
	        pst.setString(3, gender);
	        pst.setString(4, birthday);
	        pst.setString(5, email);
	        pst.setInt(6, loaiKH);
	        pst.setString(7, address);
	        pst.setString(8, phone);
	        pst.setString(9, country);
	        pst.setString(10, maKH);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void deleteCustomer(KHACHHANG khachHang){
//		String sql = "DELETE FROM KHACHHANG WHERE MAKH=?";
//	    Connection con = DatabaseConnection.connectDb();
//	    PreparedStatement pst;
//	    try {
//	        pst = con.prepareStatement(sql);
//	        pst.setString(1, customerId);
//	        pst.executeUpdate();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "UPDATE KHACHHANG SET TINHTRANG = 0  WHERE MAKH = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, khachHang.getMAKH());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		String maKH = data.get("id");
//		String sql = "UPDATE KHACHHANG SET TINHTRANG=? WHERE MAKH=?";
//	    Connection con = DatabaseConnection.connectDb();
//	    PreparedStatement pst;
//	    try {
//	        pst = con.prepareStatement(sql);
//	        pst.setString(1, maKH);
//	        pst.setInt(2, 0);
//	        pst.executeUpdate();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
	}
	
	public static String getCustomerName(String customerId) {
		String tenKH = null;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM KHACHHANG WHERE MAKH = '" + customerId + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				tenKH = resultSet.getString("TENKH");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenKH;
	}
	
	public static KHACHHANG getCustomerById(String customerId) {
		KHACHHANG kh = null;
		String sql = "SELECT * FROM KHACHHANG WHERE MAKH=?";
		
		PreparedStatement pst;
		try(Connection con = DatabaseConnection.connectDb();) {
			pst = con.prepareStatement(sql);
			pst.setString(1, customerId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				kh = new KHACHHANG(rs.getString("MAKH")
						,rs.getString("TENKH")
						,rs.getString("CCCD")
						,rs.getString("GIOITINH")
						,rs.getString("NGAYSINH")
						,rs.getString("EMAIL")
						,rs.getInt("LOAIKH")
						,rs.getString("DIACHI")
						,rs.getString("SDT")
						,rs.getString("QUOCTICH")
						,rs.getInt("TINHTRANG"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
	
	public static KHACHHANG getCustomerByCCCD(String customerId) {
		KHACHHANG kh = null;
		String sql = "SELECT * FROM KHACHHANG WHERE CCCD=?";
		
		PreparedStatement pst;
		try(Connection con = DatabaseConnection.connectDb();) {
			pst = con.prepareStatement(sql);
			pst.setString(1, customerId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				kh = new KHACHHANG(rs.getString("MAKH")
						,rs.getString("TENKH")
						,rs.getString("CCCD")
						,rs.getString("GIOITINH")
						,rs.getString("NGAYSINH")
						,rs.getString("EMAIL")
						,rs.getInt("LOAIKH")
						,rs.getString("DIACHI")
						,rs.getString("SDT")
						,rs.getString("QUOCTICH")
						,rs.getInt("TINHTRANG"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
    
}

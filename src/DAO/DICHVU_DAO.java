package DAO;

import java.sql.*;
import java.util.Map;

import DTO.DICHVU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DICHVU_DAO {
	
	public static ObservableList<DICHVU> listService() {
		ObservableList<DICHVU> list = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM DICHVU";
			Connection con = DatabaseConnection.connectDb();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				DICHVU dv = new DICHVU(rs.getString("MADV")
						,rs.getString("TENDV")
						,rs.getInt("LOAIDV")
						,rs.getInt("GIA"));			
				list.add(dv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void insertService(Map<String, String> data) throws SQLException {
		String tenDV = data.get("name");
		int loaiDV = Integer.parseInt(data.get("type"));
		String gia = data.get("price");
		String sql = "INSERT INTO DICHVU(TENDV, LOAIDV, GIA)"
				+ " VALUES(?,?,?)";
		Connection con = DatabaseConnection.connectDb();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);			
			pst.setString(1, tenDV);
			pst.setInt(2, loaiDV);
			pst.setString(3, gia);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void editService(Map<String, String> data) throws SQLException {
		String maDV = data.get("id");
		String tenDV = data.get("name");
		int loaiDV = Integer.parseInt(data.get("type"));
		String gia = data.get("price");

	    String sql = "UPDATE DICHVU SET TENDV=?, LOAIDV=?, GIA=? WHERE MADV=?";
	    Connection con = DatabaseConnection.connectDb();
	    PreparedStatement pst;
	    try {
	        pst = con.prepareStatement(sql);
	        pst.setString(1, tenDV);
	        pst.setInt(2, loaiDV);
	        pst.setString(3, gia);
	        pst.setString(4, maDV);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void deleteService(DICHVU dichVu) throws SQLException{
		String sql = "DELETE FROM KHACHHANG WHERE MADV=?";
	    Connection con = DatabaseConnection.connectDb();
	    PreparedStatement pst;
	    try {
	        pst = con.prepareStatement(sql);
	        pst.setString(1, dichVu.getMADV());
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
//	public static KHACHHANG getCustomerById(String customerId) {
//		KHACHHANG kh = null;
//		String sql = "SELECT * FROM KHACHHANG WHERE MAKH=?";
//		
//		PreparedStatement pst;
//		try(Connection con = DatabaseConnection.connectDb();) {
//			pst = con.prepareStatement(sql);
//			pst.setString(1, customerId);
//			ResultSet rs = pst.executeQuery();
//			while(rs.next()) {
//				kh = new KHACHHANG(rs.getString("MAKH")
//						,rs.getString("TENKH")
//						,rs.getString("CCCD")
//						,rs.getString("GIOITINH")
//						,rs.getString("NGAYSINH")
//						,rs.getString("EMAIL")
//						,rs.getInt("LOAIKH")
//						,rs.getString("DIACHI")
//						,rs.getString("SDT")
//						,rs.getString("QUOCTICH")
//						,rs.getInt("TINHTRANG"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return kh;
//	}
	
    
}

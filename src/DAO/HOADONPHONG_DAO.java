package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import DTO.HOADONPHONG;
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
	
	
//	public static PHIEUDATPHONG getLastBookRoom() {
//		String sql = "SELECT TOP 1 * FROM PHIEUDATPHONG ORDER BY MAPDP DESC ";
//		PreparedStatement pst;
//		PHIEUDATPHONG phieuDatPhong = null;
//		try(Connection con = DatabaseConnection.connectDb();
//		) {
//			pst = con.prepareStatement(sql);
//			pst.executeQuery();
//			ResultSet rs = pst.executeQuery();
//			while(rs.next()) {
//				phieuDatPhong = new PHIEUDATPHONG(rs.getString("MAPDP")
//						,rs.getString("MAKH")
//						,rs.getString("TGDAT")
//						,rs.getString("NGAYNHAN")
//						,rs.getString("NGAYTRA")
//						,rs.getString("HINHTHUC"));}	
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return phieuDatPhong;
//	}
	
}

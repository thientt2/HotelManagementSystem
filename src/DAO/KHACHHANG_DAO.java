package DAO;

import java.sql.*;

import DTO.KHACHHANG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KHACHHANG_DAO {

	
	

	public static ObservableList<KHACHHANG> listCustomer() {
		ObservableList<KHACHHANG> list = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM KHACHHANG";
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
}

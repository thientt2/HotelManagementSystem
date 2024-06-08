package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import DTO.THAMSO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class THAMSO_DAO {
	
	public static ObservableList<THAMSO> listParam() {
		ObservableList<THAMSO> list = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM THAMSO";
			Connection con = DatabaseConnection.connectDb();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				THAMSO ts = new THAMSO(rs.getString("TENTHAMSO")
						,rs.getFloat("GIATRI")
						,rs.getString("MOTA")
						,rs.getString("NGAYSUA"));			
				list.add(ts);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void editParam(Map<String, String> data) throws SQLException {
		String ten = data.get("ten");
		Float tile = Float.parseFloat(data.get("tile"));
		String ngaysua = data.get("ngaysua");
		
	    String sql = "UPDATE THAMSO SET GIATRI=?, NGAYSUA=? WHERE TENTHAMSO=?";
	    Connection con = DatabaseConnection.connectDb();
	    PreparedStatement pst;
	    try {
	        pst = con.prepareStatement(sql);
	        pst.setFloat(1, tile);
	        pst.setString(2, ngaysua);
	        pst.setString(3, ten);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}	
	
    
}
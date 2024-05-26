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
		Float tile = Float.parseFloat(data.get("giatri"));
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String ngaysua = now.format(dateFormatter);

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
	
	public static String getParamName(String ten)
	{
		switch(ten)
		{
		case "PhuThuVuotSoNguoi": 
			return "Phụ thu vượt số người tối đa";
		case "PhuThuKhachNuocNgoai":
			return "Phụ thu khách nước ngoài";
		case "PhuThuCheckIn59": 
			return "Phụ thu check-in sớm 5h-9h";
		case "PhuThuCheckIn914":
			return "Phụ thu check-in sớm 9h-14h";
		case "PhuThuCheckOut1215": 
			return "Phụ thu check-out muộn 12h-15h";
		case "PhuThuCheckOut1518":
			return "Phụ thu check-out muộn 15h-18h";
		case "PhuThuCheckOutSau18": 
			return "Phụ thu check-out muộn sau 18h";
		case "GiamGiaKhachTheoDoan2":
			return "Giảm giá khách thuê 2 phòng";
		case "GiamGiaKhachTheoDoan3": 
			return "Giảm giá khách thuê 3-5 phòng";
		case "GiamGiaKhachTheoDoan5":
			return "Giảm giá khách thuê hơn 5 phòng";
		default:
            return "Tham số không xác định";
		}
	}
    
}
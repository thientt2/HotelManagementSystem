package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import DTO.PHIEUDATPHONG;

public class PHIEUDATPHONG_DAO {
	
	public static void insertBookRoom(Map<String, Object> data) {
		String maKH = (String) data.get("maKH");
		String ngayNhan = (String) data.get("ngayNhan");
		String ngayTra = (String) data.get("ngayTra");
		String ngayDat = (String) data.get("tgDat");
		String hinhThuc = "Offline";
		String sql = "INSERT INTO PHIEUDATPHONG(MAKH, TGDAT, NGAYNHAN, NGAYTRA, HINHTHUC) VALUES(?,?,?,?,?)";
		PreparedStatement pst;
		
		try(Connection con = DatabaseConnection.connectDb();
		) {
			pst = con.prepareStatement(sql);
			pst.setString(1, maKH);
			pst.setString(2, ngayDat);
			pst.setString(3, ngayNhan);
			pst.setString(4, ngayTra);
			pst.setString(5, hinhThuc);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static PHIEUDATPHONG getLastBookRoom() {
		String sql = "SELECT TOP 1 * FROM PHIEUDATPHONG ORDER BY MAPDP DESC ";
		PreparedStatement pst;
		PHIEUDATPHONG phieuDatPhong = null;
		try(Connection con = DatabaseConnection.connectDb();
		) {
			pst = con.prepareStatement(sql);
			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				phieuDatPhong = new PHIEUDATPHONG(rs.getString("MAPDP")
						,rs.getString("MAKH")
						,rs.getString("TGDAT")
						,rs.getString("NGAYNHAN")
						,rs.getString("NGAYTRA")
						,rs.getString("HINHTHUC"));}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phieuDatPhong;
	}

}

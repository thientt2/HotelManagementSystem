package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import DTO.NHANVIEN;
import DTO.PHIEUDATPHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PHIEUDATPHONG_DAO {
	
//	public static ObservableList<PHIEUDATPHONG> listBookRoom() {
//		ObservableList<PHIEUDATPHONG> list = FXCollections.observableArrayList();
//		try (Connection connection = DatabaseConnection.connectDb();) {
//			String query = "SELECT * FROM PHIEUDATPHONG";
//			PreparedStatement prepare = connection.prepareStatement(query);
//			ResultSet resultSet = prepare.executeQuery();
//			while (resultSet.next()) {
//				list.add(new PHIEUDATPHONG(resultSet.getString("MAPDP")                 		   					
//	   					,resultSet.getString("MAKH")                		   					
//	   					,resultSet.getString("TGDAT")
//	   					,resultSet.getString("NGAYNHAN")
//	   					,resultSet.getString("NGAYTRA")
//	   					,resultSet.getString("HINHTHUC")));			
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	public static ObservableList<Object[]> listBookRoom() {
    	ObservableList<Object[]> dataList = FXCollections.observableArrayList();   	    	 
    	 try (Connection connection = DatabaseConnection.connectDb();
                Statement statement = connection.createStatement()) {
                String query = "SELECT PDP.MAPDP, KH.TENKH , LP.TENLOAI, PDP.NGAYNHAN, PDP.NGAYTRA, PDP.HINHTHUC "+
                				"FROM PHIEUDATPHONG PDP " +
                				"JOIN CHITIETPDP CTP ON PDP.MAPDP = CTP.MAPDP " +
                				"JOIN LOAIPHONG LP ON CTP.MALOAIP = LP.MALOAIP " +
                				"JOIN KHACHHANG KH ON PDP.MAKH = KH.MAKH";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Object[] rowData = new Object[7];
                    rowData[0] = resultSet.getString("MAPDP");
                    rowData[1] = resultSet.getString("TENKH");
                    rowData[2] = resultSet.getString("TENLOAI");
                    rowData[3] = resultSet.getString("NGAYNHAN");
                    rowData[4] = resultSet.getString("NGAYTRA");
                    rowData[5] = resultSet.getString("HINHTHUC");
                    //rowData[6] = resultSet.getString("TENTRANGTHAI");
                    dataList.add(rowData);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    	 
		return dataList;    	
    }
	
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

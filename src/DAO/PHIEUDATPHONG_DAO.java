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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import DTO.LOAIPHONG;
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
//	public static ObservableList<Object[]> listBookRoom() {
//    	ObservableList<Object[]> dataList = FXCollections.observableArrayList();   	    	 
//    	 try (Connection connection = DatabaseConnection.connectDb();
//                Statement statement = connection.createStatement()) {
//                String query = "SELECT PDP.MAPDP, KH.TENKH , LP.TENLOAI, PDP.NGAYNHAN, PDP.NGAYTRA, PDP.HINHTHUC, CTP.SOLUONG "+
//                				"FROM PHIEUDATPHONG PDP " +
//                				"JOIN CHITIETPDP CTP ON PDP.MAPDP = CTP.MAPDP " +
//                				"JOIN LOAIPHONG LP ON CTP.MALOAIP = LP.MALOAIP " +
//                				"JOIN KHACHHANG KH ON PDP.MAKH = KH.MAKH";
//                ResultSet resultSet = statement.executeQuery(query);
//                while (resultSet.next()) {
//                    Object[] rowData = new Object[7];
//                    rowData[0] = resultSet.getString("MAPDP");
//                    rowData[1] = resultSet.getString("TENKH");
//                    rowData[2] = resultSet.getString("TENLOAI");
//                    rowData[3] = resultSet.getString("NGAYNHAN");
//                    rowData[4] = resultSet.getString("NGAYTRA");
//                    rowData[5] = resultSet.getString("HINHTHUC");
//                    rowData[6] = resultSet.getInt("SOLUONG");
//                    dataList.add(rowData);
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//    	 
//		return dataList;    	
//    }
//	
//	public static int getReceiveRoomNumber(String idBookRoom) {
//        String sql = "SELECT COUNT(*) AS roomCount FROM PHIEUNHANPHONG WHERE MAPDP = ?";
//        int roomCount = 0;
//
//        try (Connection con = DatabaseConnection.connectDb();
//             PreparedStatement pst = con.prepareStatement(sql)) {
//             
//            pst.setString(1, idBookRoom);
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                roomCount = rs.getInt("roomCount");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return roomCount;
//    }
	public static ObservableList<Object[]> listBookRoomWithReceiveCount() {
	    ObservableList<Object[]> dataList = FXCollections.observableArrayList();
	    String query = "SELECT PDP.MAPDP, KH.TENKH, LP.TENLOAI, PDP.NGAYNHAN, PDP.NGAYTRA, PDP.HINHTHUC, CTP.SOLUONG, " +
	                   "(SELECT COUNT(*) FROM PHIEUNHANPHONG PNP WHERE PNP.MAPDP = PDP.MAPDP) AS RECEIVEDROOM " +
	                   "FROM PHIEUDATPHONG PDP " +
	                   "JOIN CHITIETPDP CTP ON PDP.MAPDP = CTP.MAPDP " +
	                   "JOIN LOAIPHONG LP ON CTP.MALOAIP = LP.MALOAIP " +
	                   "JOIN KHACHHANG KH ON PDP.MAKH = KH.MAKH";

	    try (Connection connection = DatabaseConnection.connectDb();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(query)) {
	         
	        while (resultSet.next()) {
	            Object[] rowData = new Object[8];
	            rowData[0] = resultSet.getString("MAPDP");
	            rowData[1] = resultSet.getString("TENKH");
	            rowData[2] = resultSet.getString("TENLOAI");
	            rowData[3] = resultSet.getString("NGAYNHAN");
	            rowData[4] = resultSet.getString("NGAYTRA");
	            rowData[5] = resultSet.getString("HINHTHUC");
	            rowData[6] = resultSet.getInt("SOLUONG");
	            rowData[7] = resultSet.getInt("RECEIVEDROOM"); 
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
	
	public static ObservableList<Object[]> showBookRoom(int id) {
	    ObservableList<Object[]> dataList = FXCollections.observableArrayList();
	    String query = "SELECT pdp.*, lp.MALOAIP, lp.TENLOAI, c.SOLUONG " +
	                   "FROM PHIEUDATPHONG pdp " +
	                   "INNER JOIN CHITIETPDP c ON pdp.MAPDP = c.MAPDP " +
	                   "INNER JOIN LOAIPHONG lp ON c.MALOAIP = lp.MALOAIP " +
	                   "LEFT JOIN PHIEUNHANPHONG pnp ON pdp.MAPDP = pnp.MAPDP " +
	                   "WHERE lp.MALOAIP = ? AND pnp.MAPDP IS NULL";

	    try (Connection connection = DatabaseConnection.connectDb();
	         PreparedStatement pst = connection.prepareStatement(query)) {
	         
	        pst.setInt(1, id);
	        ResultSet resultSet = pst.executeQuery();
	        
	        while (resultSet.next()) {
	            Object[] rowData = new Object[9];
	            rowData[0] = resultSet.getString("MAPDP");
	            rowData[1] = resultSet.getString("MAKH");
	            rowData[2] = resultSet.getString("TGDAT");
	            rowData[3] = resultSet.getString("NGAYNHAN");
	            rowData[4] = resultSet.getString("NGAYTRA");
	            rowData[5] = resultSet.getString("HINHTHUC");
	            rowData[6] = resultSet.getInt("MALOAIP");
	            rowData[7] = resultSet.getString("TENLOAI");
	            rowData[8] = resultSet.getInt("SOLUONG");
	            dataList.add(rowData);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dataList;        
	}


	public static Object[] getRoomDetails(String maPhong) {
        Object[] rowData = null;
        String query = "SELECT TOP 1 P.MAPHONG, LP.TENLOAI, KH.TENKH, PNP.TGNHAN, PDP.NGAYTRA, PNP.MAPNP " +
                "FROM PHONG P " +
                "JOIN LOAIPHONG LP ON P.MALOAIP = LP.MALOAIP " +
                "JOIN CHITIETPDP CTP ON LP.MALOAIP = CTP.MALOAIP " +
                "JOIN PHIEUDATPHONG PDP ON CTP.MAPDP = PDP.MAPDP " +
                "JOIN PHIEUNHANPHONG PNP ON PDP.MAPDP = PNP.MAPDP " +
                "JOIN KHACHHANG KH ON PDP.MAKH = KH.MAKH " +
                "WHERE P.MAPHONG = ? " +
                "ORDER BY PNP.MAPNP DESC";

        try (Connection connection = DatabaseConnection.connectDb();
             PreparedStatement pst = connection.prepareStatement(query)) {
             
            pst.setString(1, maPhong);
            ResultSet resultSet = pst.executeQuery();
            
            if (resultSet.next()) {
                rowData = new Object[6];
                rowData[0] = resultSet.getString("MAPHONG");
                rowData[1] = resultSet.getString("TENLOAI");
                rowData[2] = resultSet.getString("TENKH");
                rowData[3] = resultSet.getTimestamp("TGNHAN");
                rowData[4] = resultSet.getDate("NGAYTRA");
                rowData[5] = resultSet.getString("MAPNP");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowData != null ? rowData : new Object[0];
    }


	public static PHIEUDATPHONG getBookRoom(String bookRoomId) {
		// TODO Auto-generated method stub
		PHIEUDATPHONG phieuDatPhong = null;
		String query = "SELECT * FROM PHIEUDATPHONG WHERE MAPDP = ?";
		try(Connection con = DatabaseConnection.connectDb();
			PreparedStatement pst = con.prepareStatement(query);){			
			pst.setString(1, bookRoomId);
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

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.BAOCAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BAOCAO_DAO {
	
	public static List<Object[]> getReportData() {
        List<Object[]> reportData = new ArrayList<>();
        String query = "SELECT NGAY, THANG, NAM, LP.TENLOAIPHONG, BC.GIATRI, BC.SOLUOTTHUE " +
                       "FROM BAOCAO BC " +
                       "JOIN LOAIPHONG LP ON BC.MALOAIP = LP.MALOAIP " +
                       "ORDER BY NAM, THANG, NGAY";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("NGAY");
                row[1] = rs.getInt("THANG");
                row[2] = rs.getInt("NAM");
                row[3] = rs.getString("TENLOAIPHONG");
                row[4] = rs.getDouble("GIATRI");
                row[5] = rs.getInt("SOLUOTTHUE");
                reportData.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }
	
	// Phương thức để lấy danh sách các năm có trong database
    public static List<Integer> getYears() {
        List<Integer> yearsList = new ArrayList<>();
        String query = "SELECT DISTINCT NAM FROM BAOCAO";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                yearsList.add(rs.getInt("NAM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yearsList;
    }

    // Phương thức để lấy danh sách các tháng trong một năm từ database
    public static List<Integer> getMonths(int year) {
        List<Integer> monthsList = new ArrayList<>();
        String query = "SELECT DISTINCT THANG FROM BAOCAO WHERE NAM = ?";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                monthsList.add(rs.getInt("THANG"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthsList;
    }

    // Phương thức để lấy danh sách các ngày trong một tháng từ database
    public static List<Integer> getDays(int month, int year) {
        List<Integer> daysList = new ArrayList<>();
        String query = "SELECT DISTINCT NGAY FROM BAOCAO WHERE THANG = ? AND NAM = ?";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                daysList.add(rs.getInt("NGAY"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daysList;
    }

    // Phương thức để lấy thông tin giá trị tất cả các loại phòng được nhóm và tính tổng giá trị của chúng
    public static ObservableList<Object[]> getRoomDataByType() {
        ObservableList<Object[]> roomDataList = FXCollections.observableArrayList();
        String query = "SELECT lp.TENLOAI, SUM(b.GIATRI) AS TONGGIATRI " +
                       "FROM BAOCAO b " +
                       "JOIN LOAIPHONG lp ON b.MALOAIP = lp.MALOAIP " +
                       "GROUP BY lp.TENLOAI";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] data = new Object[]{rs.getString("TENLOAI"), rs.getLong("TONGGIATRI")};
                roomDataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomDataList;
    }
    
 // Nhóm giá trị theo loại phòng và năm
    public static ObservableList<Object[]> getRoomDataByTypeAndYear(int year) {
        ObservableList<Object[]> roomDataList = FXCollections.observableArrayList();
        String query = "SELECT lp.TENLOAI, SUM(b.GIATRI) AS TONGGIATRI " +
                       "FROM BAOCAO b " +
                       "JOIN LOAIPHONG lp ON b.MALOAIP = lp.MALOAIP " +
                       "WHERE b.NAM = ? " +
                       "GROUP BY lp.TENLOAI";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] data = new Object[]{rs.getString("TENLOAI"), rs.getLong("TONGGIATRI")};
                roomDataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomDataList;
    }

    // Nhóm giá trị theo loại phòng và tháng trong năm
    public static ObservableList<Object[]> getRoomDataByTypeAndMonth(int year, int month) {
        ObservableList<Object[]> roomDataList = FXCollections.observableArrayList();
        String query = "SELECT lp.TENLOAI, SUM(b.GIATRI) AS TONGGIATRI " +
                       "FROM BAOCAO b " +
                       "JOIN LOAIPHONG lp ON b.MALOAIP = lp.MALOAIP " +
                       "WHERE b.NAM = ? AND b.THANG = ? " +
                       "GROUP BY lp.TENLOAI";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] data = new Object[]{rs.getString("TENLOAI"), rs.getLong("TONGGIATRI")};
                roomDataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomDataList;
    }

    // Nhóm giá trị theo loại phòng trong giai đoạn ngày
    public static ObservableList<Object[]> getRoomDataByTypeAndDateRange(int startDay, int endDay, int month, int year) {
        ObservableList<Object[]> roomDataList = FXCollections.observableArrayList();
        String query = "SELECT lp.TENLOAI, SUM(b.GIATRI) AS TONGGIATRI " +
                       "FROM BAOCAO b " +
                       "JOIN LOAIPHONG lp ON b.MALOAIP = lp.MALOAIP " +
                       "WHERE b.NAM = ? AND b.THANG = ? AND b.NGAY BETWEEN ? AND ? " +
                       "GROUP BY lp.TENLOAI";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, startDay);
            stmt.setInt(4, endDay);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] data = new Object[]{rs.getString("TENLOAI"), rs.getLong("TONGGIATRI")};
                roomDataList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomDataList;
    }

    public static ObservableList<Object[]> getDailyReport(int startDay, int endDay, int month, int year) {
        ObservableList<Object[]> dailyReportList = FXCollections.observableArrayList();
        String query = "SELECT NGAY, COALESCE(SUM(GIATRI), 0) AS TONGGIATRI, COALESCE(SUM(SOLUOTTHUE), 0) AS TONGSOLUOTTHUE " +
                       "FROM BAOCAO " +
                       "WHERE NAM = ? AND THANG = ? AND NGAY BETWEEN ? AND ? " +
                       "GROUP BY NGAY " +
                       "ORDER BY NGAY";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, startDay);
            stmt.setInt(4, endDay);
            ResultSet rs = stmt.executeQuery();

            // Create a map to store the results with NGAY as the key
            Map<Integer, Object[]> resultMap = new HashMap<>();
            for (int i = startDay; i <= endDay; i++) {
                resultMap.put(i, new Object[]{i, 0.0, 0});
            }

            // Populate the map with the query results
            while (rs.next()) {
                int ngay = rs.getInt("NGAY");
                double tongGiaTri = rs.getDouble("TONGGIATRI");
                int tongSoLuotThue = rs.getInt("TONGSOLUOTTHUE");
                resultMap.put(ngay, new Object[]{ngay, tongGiaTri, tongSoLuotThue});
            }

            // Add the results to the list
            dailyReportList.addAll(resultMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailyReportList;
    }
    
    public static ObservableList<Object[]> getMonthlyReport(int year) {
        ObservableList<Object[]> monthlyReportList = FXCollections.observableArrayList();
        String query = "SELECT THANG, COALESCE(SUM(GIATRI), 0) AS TONGGIATRI, COALESCE(SUM(SOLUOTTHUE), 0) AS TONGSOLUOTTHUE " +
                       "FROM BAOCAO " +
                       "WHERE NAM = ? " +
                       "GROUP BY THANG " +
                       "ORDER BY THANG";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            // Create a map to store the results with THANG as the key
            Map<Integer, Object[]> resultMap = new HashMap<>();
            for (int i = 1; i <= 12; i++) {
                resultMap.put(i, new Object[]{i, 0.0, 0});
            }

            // Populate the map with the query results
            while (rs.next()) {
                int thang = rs.getInt("THANG");
                double tongGiaTri = rs.getDouble("TONGGIATRI");
                int tongSoLuotThue = rs.getInt("TONGSOLUOTTHUE");
                resultMap.put(thang, new Object[]{thang, tongGiaTri, tongSoLuotThue});
            }

            // Add the results to the list
            monthlyReportList.addAll(resultMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyReportList;
    }
//    // Phương thức để gộp các cột THANG, tính tổng GIATRI và SOLUOTTHUE, và nhóm theo THANG
//    public static ObservableList<Object[]> getMonthlyReport(int year) {
//    	ObservableList<Object[]> monthlyReportList = FXCollections.observableArrayList();
//        String query = "SELECT THANG, SUM(GIATRI) AS TONGGIATRI, SUM(SOLUOTTHUE) AS TONGSOLUOTTHUE " +
//                       "FROM BAOCAO " +
//                       "WHERE NAM = ? " +
//                       "GROUP BY THANG";
//
//        try (Connection conn = DatabaseConnection.connectDb();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, year);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Object[] data = new Object[]{rs.getInt("THANG"), rs.getDouble("TONGGIATRI"), rs.getInt("TONGSOLUOTTHUE")};
//                monthlyReportList.add(data);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return monthlyReportList;
//    }
//    
// // Phương thức để gộp các cột NGAY, tính tổng GIATRI và SOLUOTTHUE, và nhóm theo NGAY
//    public static ObservableList<Object[]> getDailyReport(int month, int year) {
//        ObservableList<Object[]> dailyReportList = FXCollections.observableArrayList();
//        String query = "SELECT NGAY, SUM(GIATRI) AS TONGGIATRI, SUM(SOLUOTTHUE) AS TONGSOLUOTTHUE " +
//                       "FROM BAOCAO " +
//                       "WHERE NAM = ? AND THANG = ? " +
//                       "GROUP BY NGAY";
//
//        try (Connection conn = DatabaseConnection.connectDb();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, year);
//            stmt.setInt(2, month);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Object[] data = new Object[]{rs.getInt("NGAY"), rs.getDouble("TONGGIATRI"), rs.getInt("TONGSOLUOTTHUE")};
//                dailyReportList.add(data);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dailyReportList;
//    }

	public static void updateReport(int day, int month, int year, int roomTypeId) {
		// TODO Auto-generated method stub
		String query = "UPDATE BAOCAO SET SOLUOTTHUE = SOLUOTTHUE + 1 WHERE NGAY = ? AND THANG = ? AND NAM = ? AND MALOAIP = ?";
		try (Connection conn = DatabaseConnection.connectDb();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, day);
			stmt.setInt(2, month);
			stmt.setInt(3, year);
			stmt.setInt(4, roomTypeId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertReport(int day, int month, int year, int roomTypeId) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO BAOCAO (NGAY, THANG, NAM, MALOAIP, SOLUOTTHUE) VALUES (?, ?, ?, ?, 1)";
		try (Connection conn = DatabaseConnection.connectDb();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, day);
			stmt.setInt(2, month);
			stmt.setInt(3, year);
			stmt.setInt(4, roomTypeId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkReport(int day, int month, int year, int roomTypeId) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM BAOCAO WHERE NGAY = ? AND THANG = ? AND NAM = ? AND MALOAIP = ?";
		try (Connection conn = DatabaseConnection.connectDb();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, day);
			stmt.setInt(2, month);
			stmt.setInt(3, year);
			stmt.setInt(4, roomTypeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    
//	public static List<BAOCAO> getRoomData(int day, int month, int year) {
//  List<BAOCAO> roomDataList = new ArrayList<>();
//  String query = "SELECT lp.TENLOAI, b.GIATRI, b.TILE " +
//                 "FROM BAOCAO b " +
//                 "JOIN LOAIPHONG lp ON b.MALOAIP = lp.MALOAIP " +
//                 "WHERE b.NGAY = ? AND b.THANG = ? AND b.NAM = ?";
//
//  try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//       PreparedStatement stmt = conn.prepareStatement(query)) {
//      stmt.setInt(1, day);
//      stmt.setInt(2, month);
//      stmt.setInt(3, year);
//      ResultSet rs = stmt.executeQuery();
//      while (rs.next()) {
//          roomDataList.add(new BAOCAO(rs.getString("TENLOAI"), rs.getLong("GIATRI"), rs.getInt("TILE")));
//      }
//  } catch (Exception e) {
//      e.printStackTrace();
//  }
//  return roomDataList;
//}
}

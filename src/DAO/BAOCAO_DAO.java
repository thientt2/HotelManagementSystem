package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.BAOCAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BAOCAO_DAO {
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

    // Phương thức để gộp các cột THANG, tính tổng GIATRI và SOLUOTTHUE, và nhóm theo THANG
    public static ObservableList<Object[]> getMonthlyReport(int year) {
    	ObservableList<Object[]> monthlyReportList = FXCollections.observableArrayList();
        String query = "SELECT THANG, SUM(GIATRI) AS TONGGIATRI, SUM(SOLUOTTHUE) AS TONGSOLUOTTHUE " +
                       "FROM BAOCAO " +
                       "WHERE NAM = ? " +
                       "GROUP BY THANG";

        try (Connection conn = DatabaseConnection.connectDb();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] data = new Object[]{rs.getInt("THANG"), rs.getDouble("TONGGIATRI"), rs.getInt("TONGSOLUOTTHUE")};
                monthlyReportList.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyReportList;
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

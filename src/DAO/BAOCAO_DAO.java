package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.BAOCAO;

public class BAOCAO_DAO {
//	public static List<BAOCAO> getRoomData(int day, int month, int year) {
//        List<BAOCAO> roomDataList = new ArrayList<>();
//        String query = "SELECT lp.TENLOAI, b.GIATRI, b.TILE " +
//                       "FROM BAOCAO b " +
//                       "JOIN LOAIPHONG lp ON b.MALOAIP = lp.MALOAIP " +
//                       "WHERE b.NGAY = ? AND b.THANG = ? AND b.NAM = ?";
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, day);
//            stmt.setInt(2, month);
//            stmt.setInt(3, year);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                roomDataList.add(new BAOCAO(rs.getString("TENLOAI"), rs.getLong("GIATRI"), rs.getInt("TILE")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return roomDataList;
//    }
}

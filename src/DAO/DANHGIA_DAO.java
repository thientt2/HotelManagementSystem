package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DANHGIA_DAO {
	public static ObservableList<Object[]> listFeedBack() {
        ObservableList<Object[]> dataList = FXCollections.observableArrayList();

        String query = "SELECT " +
                       "    dg.SAO, " +
                       "    dg.EMAIL, " +
                       "    dg.NOIDUNG, " +
                       "    dg.NGAYTAOFEEDBACK, " +
                       "    kh.TENKH, " +
                       "    p.MAPHONG, " +
                       "    lp.TENLOAI " +
                       "FROM " +
                       "    DANHGIA dg " +
                       "JOIN " +
                       "    KHACHHANG kh ON dg.EMAIL = kh.EMAIL " +
                       "JOIN " +
                       "    CHITIETPNP ctpnp ON kh.MAKH = ctpnp.MAKH " +
                       "JOIN " +
                       "    PHIEUNHANPHONG pnp ON ctpnp.MAPNP = pnp.MAPNP " +
                       "JOIN " +
                       "    PHONG p ON pnp.MAPHONG = p.MAPHONG " +
                       "JOIN " +
                       "    LOAIPHONG lp ON p.MALOAIP = lp.MALOAIP";

        try (Connection connection = DatabaseConnection.connectDb();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] rowData = new Object[7];
                rowData[0] = rs.getInt("SAO");
                rowData[1] = rs.getString("EMAIL");
                rowData[2] = rs.getString("NOIDUNG");
                rowData[3] = rs.getTimestamp("NGAYTAOFEEDBACK");
                rowData[4] = rs.getString("TENKH");
                rowData[5] = rs.getString("MAPHONG");
                rowData[6] = rs.getString("TENLOAI");

                dataList.add(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataList;
    }
	
	public static Object[] getReviewStars() {
        Object[] result = new Object[6];
        int totalReviews = 0;
        int totalSao1 = 0;
        int totalSao2 = 0;
        int totalSao3 = 0;
        int totalSao4 = 0;
        int totalSao5 = 0;

        String query = "SELECT " +
                       "    COUNT(*) AS TotalReviews, " +
                       "    SUM(CASE WHEN SAO = 1 THEN 1 ELSE 0 END) AS TotalSao1, " +
                       "    SUM(CASE WHEN SAO = 2 THEN 1 ELSE 0 END) AS TotalSao2, " +
                       "    SUM(CASE WHEN SAO = 3 THEN 1 ELSE 0 END) AS TotalSao3, " +
                       "    SUM(CASE WHEN SAO = 4 THEN 1 ELSE 0 END) AS TotalSao4, " +
                       "    SUM(CASE WHEN SAO = 5 THEN 1 ELSE 0 END) AS TotalSao5 " +
                       "FROM DANHGIA";

        try (Connection connection = DatabaseConnection.connectDb();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                totalReviews = rs.getInt("TotalReviews");
                totalSao1 = rs.getInt("TotalSao1");
                totalSao2 = rs.getInt("TotalSao2");
                totalSao3 = rs.getInt("TotalSao3");
                totalSao4 = rs.getInt("TotalSao4");
                totalSao5 = rs.getInt("TotalSao5");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        result[0] = totalReviews;
        result[1] = totalSao1;
        result[2] = totalSao2;
        result[3] = totalSao3;
        result[4] = totalSao4;
        result[5] = totalSao5;
        return result;
    }
	
}

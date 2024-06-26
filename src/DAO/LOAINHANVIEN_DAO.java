package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LOAINHANVIEN_DAO {
	
	public static List<String> listStaffType(){

		List<String> list = new ArrayList<>();		
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAINHANVIEN";
			ResultSet resultSet = statement.executeQuery(query);			
			while (resultSet.next()) {
				list.add(resultSet.getString("TENLOAINV"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
		
	}
	
	public static String getStaffTypeName(int id) {
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAINHANVIEN WHERE MALOAINV = " + id;
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				return resultSet.getString("TENLOAINV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getStaffTypeId(String name) {
		int maloainv = 0;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAINHANVIEN WHERE TENLOAINV = N'" + name + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return maloainv = resultSet.getInt("MALOAINV");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return maloainv;
	}
	
	public static List<String> getStaffTypeScreens(int staffTypeId) {
        List<String> screens = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connectDb();
             Statement statement = connection.createStatement()) {
            String query = "SELECT CV.MANHINH FROM LOAINHANVIEN LN " +
                           "JOIN TRUYCAP TC ON LN.MALOAINV = TC.MALOAINV " +
                           "JOIN CONGVIEC CV ON TC.MACONGVIEC = CV.MACONGVIEC " +
                           "WHERE LN.MALOAINV = " + staffTypeId;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                screens.add(resultSet.getString("MANHINH"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return screens;
    }
	
	public static boolean hasPermission(int userId, String screenName) {
        String query = "SELECT COUNT(*) AS count FROM NHANVIEN nv " +
                       "JOIN TRUYCAP tc ON nv.MALOAINV = tc.MALOAINV " +
                       "JOIN CONGVIEC cv ON tc.MACONGVIEC = cv.MACONGVIEC " +
                       "WHERE nv.MANV = ? AND cv.MANHINH = ?";
        try (Connection connection = DatabaseConnection.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, screenName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

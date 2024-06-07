package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import DTO.KHACHHANG;

public class CHITIETPNP_DAO {

	public static void insertDetailRecieveRoom(String maPNP, List<String> listOthers) {

		for(String customer: listOthers) {
			String query = "INSERT INTO CHITIETPNP VALUES(?,?)";
			try (Connection connection = DatabaseConnection.connectDb()) {
				PreparedStatement prepare = connection.prepareStatement(query);
				prepare.setString(1, maPNP);
				prepare.setString(2, customer);				
				prepare.executeUpdate();
				System.out.println(maPNP + " " + customer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static List<Object[]> getCustomerId(String receiveRoomId) {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT KH.TENKH, KH.NGAYSINH " +
                       "FROM CHITIETPNP CTP " +
                       "JOIN KHACHHANG KH ON CTP.MAKH = KH.MAKH " +
                       "WHERE CTP.MAPNP = '" + receiveRoomId + "'";
        
        try (Connection connection = DatabaseConnection.connectDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                String tenKH = resultSet.getString("TENKH");
                Date ngaySinh = resultSet.getDate("NGAYSINH");
                Object[] customerData = {tenKH, ngaySinh};
                list.add(customerData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}

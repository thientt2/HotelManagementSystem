package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.NHANVIEN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NHANVIEN_DAO {
	
	public static ObservableList<NHANVIEN> listStaff() {
		ObservableList<NHANVIEN> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT * FROM NHANVIEN";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				list.add(new NHANVIEN(resultSet.getString("MANV")                 		   					
	   					,resultSet.getString("TENNV")                		   					
	   					,resultSet.getString("EMAIL")
	   					,resultSet.getInt("MALOAINV")
	   					,resultSet.getString("CCCD")
	   					,resultSet.getString("NGAYSINH")
	   					,resultSet.getString("GIOITINH")                		   					
	   					,resultSet.getString("DIACHI")
	   					,resultSet.getString("SDT")
	   					,resultSet.getString("NGAYVAOLAM")
	   					,resultSet.getString("TENDANGNHAP")
	   					,resultSet.getString("MATKHAU")
	   					,resultSet.getString("PHOTOURL")
	   					,resultSet.getInt("TINHTRANG")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static NHANVIEN getStaff(String user) {
    	NHANVIEN nhanVien = null;
    	try (Connection connection = DatabaseConnection.connectDb();) {
               String query = "SELECT * FROM NHANVIEN WHERE TENDANGNHAP = ?";
               PreparedStatement prepare = connection.prepareStatement(query);
               prepare.setString(1,user);
               ResultSet resultSet = prepare.executeQuery();

               while (resultSet.next()) {
                   nhanVien = new NHANVIEN(resultSet.getString("MANV")                 		   					
                		   					,resultSet.getString("TENNV")                		   					
                		   					,resultSet.getString("EMAIL")
                		   					,resultSet.getInt("MALOAINV")
                		   					,resultSet.getString("CCCD")
                		   					,resultSet.getString("NGAYSINH")
                		   					,resultSet.getString("GIOITINH")                		   					
                		   					,resultSet.getString("DIACHI")
                		   					,resultSet.getString("SDT")
                		   					,resultSet.getString("NGAYVAOLAM")
                		   					,resultSet.getString("TENDANGNHAP")
                		   					,resultSet.getString("MATKHAU")
                		   					,resultSet.getString("PHOTOURL")
                		   					,resultSet.getInt("TINHTRANG"));
               }
               

           } catch (SQLException e) {
               e.printStackTrace();
           }
    	return nhanVien;
    }

}

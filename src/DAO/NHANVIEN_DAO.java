package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import DTO.NHANVIEN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NHANVIEN_DAO {
	
	public static ObservableList<NHANVIEN> listStaff() {
		ObservableList<NHANVIEN> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT * FROM NHANVIEN WHERE TINHTRANG = 1";
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
	
	
	public static NHANVIEN getStaffById(String id) {
		NHANVIEN nhanVen = null;
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT * FROM NHANVIEN WHERE MANV = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, id);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				 nhanVen = new NHANVIEN(resultSet.getString("MANV")                 		   					
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
		return nhanVen;		
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

	
	public static void createUser(Map<String, String> data) {
		String staffName = data.get("staffName");
		String username = data.get("username");
		String password = data.get("password");
		String photoUrl = data.get("photoUrl");
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "UPDATE NHANVIEN SET TENDANGNHAP = ?, MATKHAU = ?, PHOTOURL = ? WHERE TENNV = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, username);
			prepare.setString(2, password);
			prepare.setString(3, photoUrl);
			prepare.setString(4, staffName);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	
	public static void insertStaff(Map<String, String> data) {
		String staffName = data.get("name");
		String birthday = data.get("birthday");
		String gender = data.get("gender");
		String cccd = data.get("cccd");
		String email = data.get("email");
		String phone = data.get("phone");
		String address = data.get("address");	
		String startDay = data.get("startDay");
		int type = Integer.parseInt(data.get("job"));
		int status = 1;
		
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "INSERT INTO NHANVIEN (TENNV, EMAIL, MALOAINV, CCCD, NGAYSINH, GIOITINH, DIACHI, SDT, NGAYVAOLAM, TINHTRANG)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, staffName);
			prepare.setString(2, email);
			prepare.setInt(3, type);
			prepare.setString(4,cccd);
			prepare.setString(5, birthday);
			prepare.setString(6, gender);
			prepare.setString(7, address);
			prepare.setString(8, phone);
			prepare.setString(9, startDay);
			prepare.setInt(10, status);
			
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteStaff(NHANVIEN nhanVien) {	
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "UPDATE NHANVIEN SET TINHTRANG = 0  WHERE MANV = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, nhanVien.getMANV());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void editStaff(Map<String, String> data) {
		String staffName = data.get("name");
		String birthday = data.get("birthday");
		String gender = data.get("gender");
		String cccd = data.get("cccd");
		String email = data.get("email");
		String phone = data.get("phone");
		String address = data.get("address");	
		String startDay = data.get("startDay");
		int type = Integer.parseInt(data.get("job"));
		String staffid = data.get("staffid");
		int status = 1;
		
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "UPDATE NHANVIEN SET TENNV = ?, EMAIL = ?, MALOAINV = ?, CCCD = ?, NGAYSINH = ?, GIOITINH = ?, DIACHI = ?, SDT = ?, NGAYVAOLAM = ?, TINHTRANG = ? WHERE MANV = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, staffName);
			prepare.setString(2, email);
			prepare.setInt(3, type);
			prepare.setString(4,cccd);
			prepare.setString(5, birthday);
			prepare.setString(6, gender);
			prepare.setString(7, address);
			prepare.setString(8, phone);
			prepare.setString(9, startDay);
			prepare.setInt(10, status);
			prepare.setString(11, staffid);
			
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

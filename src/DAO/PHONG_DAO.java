package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

import DTO.PHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class PHONG_DAO {
	
	public static int getAvailableRooms(int roomTypeId, LocalDate checkinDate, LocalDate checkoutDate) throws SQLException {
        Connection con = DatabaseConnection.connectDb();
        int totalRooms = 0;
        int bookedRooms = 0;
        
        String totalRoomsQuery = "SELECT COUNT(*) FROM PHONG WHERE MALOAIP = ?";
        PreparedStatement totalRoomsStmt = con.prepareStatement(totalRoomsQuery);
        totalRoomsStmt.setInt(1, roomTypeId);
        ResultSet totalRoomsRs = totalRoomsStmt.executeQuery();
        if (totalRoomsRs.next()) {
            totalRooms = totalRoomsRs.getInt(1);
        }
        
        String bookedRoomsQuery = "SELECT COUNT(*) FROM PHIEUDATPHONG p INNER JOIN CHITIETPDP c ON p.MAPDP = c.MAPDP "
                                + "WHERE c.MALOAIP = ? AND ((p.NGAYNHAN <= ? AND p.NGAYTRA >= ?) OR (p.NGAYNHAN <= ? AND p.NGAYTRA >= ?))";
        PreparedStatement bookedRoomsStmt = con.prepareStatement(bookedRoomsQuery);
        bookedRoomsStmt.setInt(1, roomTypeId);
        bookedRoomsStmt.setDate(2, java.sql.Date.valueOf(checkoutDate));
        bookedRoomsStmt.setDate(3, java.sql.Date.valueOf(checkinDate));
        bookedRoomsStmt.setDate(4, java.sql.Date.valueOf(checkoutDate));
        bookedRoomsStmt.setDate(5, java.sql.Date.valueOf(checkinDate));
        ResultSet bookedRoomsRs = bookedRoomsStmt.executeQuery();
        if (bookedRoomsRs.next()) {
            bookedRooms = bookedRoomsRs.getInt(1);
        }
        
        int availableRooms = totalRooms - bookedRooms;
        
        totalRoomsStmt.close();
        bookedRoomsStmt.close();
        con.close();
        
        return availableRooms;
    }
   
    public static ObservableList<Object[]> getListRoomByFloor(int floorNumber) {
    	
    	ObservableList<Object[]> dataList = FXCollections.observableArrayList();   	    	 

    	 
    	 try (Connection connection = DatabaseConnection.connectDb();
                 Statement statement = connection.createStatement()) {
                String query = "SELECT P.MAPHONG, L.TENLOAI, T.TENTRANGTHAI "
                		+ "FROM PHONG P, LOAIPHONG L, TRANGTHAIPHONG T "
                		+ "WHERE P.MALOAIP = L.MALOAIP AND P.MATRANGTHAI = T.MATRANGTHAI AND MAPHONG LIKE 'P"+ floorNumber + "%'";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Object[] rowData = new Object[3];
                    rowData[0] = resultSet.getString("MAPHONG");
                    rowData[1] = resultSet.getString("TENLOAI");
                    rowData[2] = resultSet.getString("TENTRANGTHAI");                    
                    dataList.add(rowData);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	 
		return dataList;    	
    }
    
    public static ObservableList<Object[]> getAllRoom() {
		
		ObservableList<Object[]> dataList = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();
			Statement statement = connection.createStatement()) {
			String query = "SELECT P.MAPHONG, L.TENLOAI, T.TENTRANGTHAI "
					+ "FROM PHONG P, LOAIPHONG L, TRANGTHAIPHONG T "
					+ "WHERE P.MALOAIP = L.MALOAIP AND P.MATRANGTHAI = T.MATRANGTHAI";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Object[] rowData = new Object[3];
				rowData[0] = resultSet.getString("MAPHONG");
				rowData[1] = resultSet.getString("TENLOAI");
				rowData[2] = resultSet.getString("TENTRANGTHAI");
				dataList.add(rowData);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
    }
    
    public static void addRoom(Map<String, String> data) {
    	

    	String maphong = data.get("maphong");
    	int maloai = Integer.parseInt(data.get("maloai"));
    	int matrangthai = Integer.parseInt(data.get("matrangthai"));
		
    	String query = "INSERT INTO PHONG VALUES(?,?,?)";
    	try (Connection connection = DatabaseConnection.connectDb();
    		PreparedStatement prepare = connection.prepareStatement(query)) {
               
    	
               prepare.setString(1, maphong);
               prepare.setInt(2, maloai);
               prepare.setInt(3, matrangthai);
               
               prepare.executeUpdate();                
               
           } catch (SQLException e) {
               e.printStackTrace();
           }    	
    }
    
    public static boolean checkIDRoom(Map<String,String> data) throws SQLException {
    	String maphong = data.get("maphong");
    	String sql = "Select MAPHONG from PHONG where MAPHONG = ?";
    	try(Connection connect = DatabaseConnection.connectDb();
    		PreparedStatement prepare = connect.prepareStatement(sql)){
    		prepare.setString(1, maphong);
    		ResultSet result = prepare.executeQuery();
    		while(result.next()) {
	    		SystemMessage.ERROR_MESSAGE = "ERROR_3";
	    		return true;
    		}
    	} catch (SQLException e) {
            e.printStackTrace();
        }   
    	return false;    	
    }
    
    public static void editRoom(Map<String, String> data) {
    	String maphong = data.get("maphong");
		int maloai = Integer.parseInt(data.get("maloai"));		
		
		String query = "UPDATE PHONG SET MALOAIP = ? WHERE MAPHONG = ?";
		try (Connection connection = DatabaseConnection.connectDb();
			PreparedStatement prepare = connection.prepareStatement(query)) {
			prepare.setInt(1, maloai);			
			prepare.setString(2, maphong);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    
    public static PHONG layPhong(String maphong) {
		
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
				String query = "SELECT * FROM PHONG WHERE MAPHONG = ?";
				PreparedStatement prepare = connection.prepareStatement(query);
				prepare.setString(1, maphong);
				ResultSet resultSet = prepare.executeQuery();

				while (resultSet.next()) {
					PHONG phong = new PHONG(resultSet.getString("MAPHONG")
							,resultSet.getInt("MALOAIP")
							,resultSet.getInt("MATRANGTHAI"));
					return phong;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

}

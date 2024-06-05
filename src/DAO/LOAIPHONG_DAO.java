package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DTO.LOAIPHONG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LOAIPHONG_DAO {
	public static ObservableList<LOAIPHONG> showRoomType(){
		ObservableList<LOAIPHONG> dataList = FXCollections.observableArrayList();   	
		
		try (Connection connection = DatabaseConnection.connectDb();
                Statement statement = connection.createStatement()) {
               String query = "SELECT * FROM LOAIPHONG";
               
               ResultSet result = statement.executeQuery(query);

               while (result.next()) {
                   LOAIPHONG data = new LOAIPHONG(result.getInt("MALOAIP")
                		   ,result.getString("TENLOAI")
                		   ,result.getString("LOAIGIUONG")
                		   ,result.getDouble("GIA")
                		   ,result.getInt("NGUOITOIDA")
                		   ,result.getDouble("DIENTICH"));

                   dataList.add(data);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
		return dataList;		
	}
	
//	public static ObservableList<LOAIPHONG> roomType(int id){
//		ObservableList<LOAIPHONG> dataList = FXCollections.observableArrayList();   	
//		
//		try (Connection connection = DatabaseConnection.connectDb();
//                Statement statement = connection.createStatement()) {
//               String query = "SELECT * FROM LOAIPHONG WHERE MALOAIP = '" + id + "'";
//               
//               ResultSet result = statement.executeQuery(query);
//
//               while (result.next()) {
//                   LOAIPHONG data = new LOAIPHONG(result.getInt("MALOAIP")
//                		   ,result.getString("TENLOAI")
//                		   ,result.getString("LOAIGIUONG")
//                		   ,result.getDouble("GIA")
//                		   ,result.getInt("NGUOITOIDA")
//                		   ,result.getDouble("DIENTICH"));
//
//                   dataList.add(data);
//               }
//
//           } catch (SQLException e) {
//               e.printStackTrace();
//           }
//		return dataList;		
//	}
	
	// CODE THÊM SỐ PHÒNG ĐANG SỬ DỤNG/ALL
	public static ObservableList<Object[]> roomType(int id) {
    	
    	ObservableList<Object[]> dataList = FXCollections.observableArrayList();   	    	 

    	 try (Connection connection = DatabaseConnection.connectDb();
                 Statement statement = connection.createStatement()) {
                String query = "SELECT LP.TENLOAI, LP.LOAIGIUONG, LP.GIA, LP.NGUOITOIDA, LP.DIENTICH, LP.MALOAIP "
                		+ "FROM PHONG P, LOAIPHONG LP, TRANGTHAIPHONG T "
                		+ "WHERE P.MALOAIP = LP.MALOAIP AND P.MATRANGTHAI = T.MATRANGTHAI AND LP.MALOAIP = '" + id + "'";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Object[] rowData = new Object[9];
                    rowData[0] = resultSet.getString("TENLOAI");
                    rowData[1] = resultSet.getString("LOAIGIUONG");
                    rowData[2] = resultSet.getDouble("GIA");    
                    rowData[3] = resultSet.getInt("NGUOITOIDA");
                    rowData[4] = resultSet.getDouble("DIENTICH");
                    rowData[5] = resultSet.getString("MALOAIP");       
                    //rowData[6] = resultSet.getString("MAPHONG");
                    //rowData[7] = resultSet.getString("TENLOAI");
                    //rowData[8] = resultSet.getString("TENTRANGTHAI");       
                    dataList.add(rowData);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	 
		return dataList;    	
    }
	
	public static List<LOAIPHONG> getRoomTypes() {
		List<LOAIPHONG> dataList = new ArrayList<>();
		
		try (Connection connection = DatabaseConnection.connectDb();
                Statement statement = connection.createStatement()) {
               String query = "SELECT * FROM LOAIPHONG";
               ResultSet result = statement.executeQuery(query);
               
               while (result.next()) {
            	   LOAIPHONG data = new LOAIPHONG(result.getInt("MALOAIP")
                		   ,result.getString("TENLOAI")
                		   ,result.getString("LOAIGIUONG")
                		   ,result.getDouble("GIA")
                		   ,result.getInt("NGUOITOIDA")
                		   ,result.getDouble("DIENTICH"));

                   dataList.add(data);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
		return dataList;
	}

	public static int getRoomTypeId(String name) {
		int maloai = 0;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAIPHONG WHERE TENLOAI = N'" + name + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return maloai = resultSet.getInt("MALOAIP");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maloai;
	}
	
	public static String getRoomTypeName(int id) {
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM LOAIPHONG WHERE MALOAIP = '" + id + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 return resultSet.getString("TENLOAI");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void editRoomType(Map<String, String> data) throws SQLException {
		int maLoai = Integer.parseInt(data.get("type"));
		String tenLoai = data.get("name");
		int maxNguoi = Integer.parseInt(data.get("max"));
		Double gia = Double.parseDouble(data.get("price"));
		Double dienTich = Double.parseDouble(data.get("area"));
		String loaiGiuong = data.get("bed");

	    String sql = "UPDATE LOAIPHONG SET TENLOAI=?, LOAIGIUONG=?, GIA=?, NGUOITOIDA=?, DIENTICH=? WHERE MALOAI=?";
	    Connection con = DatabaseConnection.connectDb();
	    PreparedStatement pst;
	    try {
	        pst = con.prepareStatement(sql);
	        pst.setString(1, tenLoai);
	        pst.setString(2, loaiGiuong);
	        pst.setDouble(3, gia);
	        pst.setInt(4, maxNguoi);
	        pst.setDouble(5, dienTich);
	        pst.setInt(6, maLoai);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}

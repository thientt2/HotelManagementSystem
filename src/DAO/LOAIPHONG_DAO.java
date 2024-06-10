package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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
	
//	public static ObservableList<Object[]> roomType() {
//    	
//    	ObservableList<Object[]> dataList = FXCollections.observableArrayList();   	    	 
//
//    	 try (Connection connection = DatabaseConnection.connectDb();
//                 Statement statement = connection.createStatement()) {
//                String query = "SELECT LP.TENLOAI, LP.LOAIGIUONG, LP.GIA, LP.NGUOITOIDA, LP.DIENTICH, LP.MALOAIP, " +
//                        "COUNT(P.MAPHONG) AS TotalRooms, " +
//                        "SUM(CASE WHEN P.MATRANGTHAI = 3 THEN 1 ELSE 0 END) AS ActiveRooms, " +
//                        "COUNT(P.MAPHONG) - SUM(CASE WHEN P.MATRANGTHAI = 3 THEN 1 ELSE 0 END) AS AvailableRooms " +
//                        "FROM PHONG P " +
//                        "JOIN LOAIPHONG LP ON P.MALOAIP = LP.MALOAIP " +
//                        "JOIN TRANGTHAIPHONG T ON P.MATRANGTHAI = T.MATRANGTHAI " +
//                        "GROUP BY LP.TENLOAI, LP.LOAIGIUONG, LP.GIA, LP.NGUOITOIDA, LP.DIENTICH, LP.MALOAIP";
//                ResultSet resultSet = statement.executeQuery(query);
//
//                while (resultSet.next()) {
//                	Object[] rowData = new Object[9];
//                    rowData[0] = resultSet.getString("TENLOAI");
//                    rowData[1] = resultSet.getString("LOAIGIUONG");
//                    rowData[2] = resultSet.getDouble("GIA");
//                    rowData[3] = resultSet.getInt("NGUOITOIDA");
//                    rowData[4] = resultSet.getDouble("DIENTICH");
//                    rowData[5] = resultSet.getInt("MALOAIP");
//                    rowData[6] = resultSet.getInt("TotalRooms");
//                    rowData[7] = resultSet.getInt("ActiveRooms");
//                    rowData[8] = resultSet.getInt("AvailableRooms");    
//                    dataList.add(rowData);
//                }
//
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//    	 
//		return dataList;    	
//    }
	
	//gộp 3 câu truy vấn 
	public static ObservableList<Object[]> roomType() {
	    ObservableList<Object[]> dataList = FXCollections.observableArrayList();

	    try (Connection connection = DatabaseConnection.connectDb()) {
	        // Truy vấn chính để lấy thông tin các loại phòng và trạng thái
	        String query = "SELECT LP.TENLOAI, LP.LOAIGIUONG, LP.GIA, LP.NGUOITOIDA, LP.DIENTICH, LP.MALOAIP, " +
	                "COUNT(P.MAPHONG) AS TotalRooms, " +
	                "SUM(CASE WHEN P.MATRANGTHAI = 3 THEN 1 ELSE 0 END) AS ActiveRooms, " +
	                "COUNT(P.MAPHONG) - SUM(CASE WHEN P.MATRANGTHAI = 3 THEN 1 ELSE 0 END) AS AvailableRooms " +
	                "FROM PHONG P " +
	                "JOIN LOAIPHONG LP ON P.MALOAIP = LP.MALOAIP " +
	                "JOIN TRANGTHAIPHONG T ON P.MATRANGTHAI = T.MATRANGTHAI " +
	                "GROUP BY LP.TENLOAI, LP.LOAIGIUONG, LP.GIA, LP.NGUOITOIDA, LP.DIENTICH, LP.MALOAIP";

	        String subQuery = "SELECT CT.MALOAIP, COUNT(CT.MAPDP) AS SoLuong " +
	                "FROM PHIEUDATPHONG PDP " +
	                "JOIN CHITIETPDP CT ON PDP.MAPDP = CT.MAPDP " +
	                "LEFT JOIN PHIEUNHANPHONG PNP ON PDP.MAPDP = PNP.MAPDP " +
	                "WHERE PNP.MAPDP IS NULL " +
	                "GROUP BY CT.MALOAIP";

	        try (Statement statement1 = connection.createStatement();
	             Statement statement2 = connection.createStatement();
	             ResultSet resultSet = statement1.executeQuery(query);
	             ResultSet subResultSet = statement2.executeQuery(subQuery)) {

	            // Tạo một map để lưu trữ kết quả của truy vấn phụ
	            Map<Integer, Integer> unbookedCounts = new HashMap<>();
	            while (subResultSet.next()) {
	                unbookedCounts.put(subResultSet.getInt("MALOAIP"), subResultSet.getInt("SoLuong"));
	            }

	            // Xử lý kết quả của truy vấn chính và kết hợp với kết quả của truy vấn phụ
	            while (resultSet.next()) {
	                Object[] rowData = new Object[10];
	                int maLoaiP = resultSet.getInt("MALOAIP");
	                rowData[0] = resultSet.getString("TENLOAI");
	                rowData[1] = resultSet.getString("LOAIGIUONG");
	                rowData[2] = resultSet.getDouble("GIA");
	                rowData[3] = resultSet.getInt("NGUOITOIDA");
	                rowData[4] = resultSet.getDouble("DIENTICH");
	                rowData[5] = maLoaiP;
	                rowData[6] = resultSet.getInt("TotalRooms");
	                rowData[7] = resultSet.getInt("ActiveRooms");
	                rowData[8] = resultSet.getInt("AvailableRooms");
	                rowData[9] = unbookedCounts.getOrDefault(maLoaiP, 0);  // Thêm số lượng phiếu đặt phòng chưa nhận
	                dataList.add(rowData);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dataList;
	}

	
	//Số lượng phiếu đặt phòng nhận hôm nay
    //Số lượng khách hàng nhận phòng hôm nay
	public static int[] countBookingsAndCustomersToday() {
	    int[] counts = new int[2];
	    String query = "SELECT " +
	                   "(SELECT COUNT(*) " +
	                   " FROM PHIEUDATPHONG PDP " +
	                   " JOIN PHIEUNHANPHONG PNP ON PDP.MAPDP = PNP.MAPDP " +
	                   " WHERE PDP.NGAYNHAN = CAST(GETDATE() AS DATE)) AS SoLuongDatPhong, " +
	                   "(SELECT COUNT(DISTINCT PDP.MAKH) " +
	                   " FROM PHIEUNHANPHONG PNP " +
	                   " JOIN PHIEUDATPHONG PDP ON PNP.MAPDP = PDP.MAPDP " +
	                   " WHERE PDP.NGAYNHAN = CAST(GETDATE() AS DATE)) AS SoLuongKhach";

	    try (Connection connection = DatabaseConnection.connectDb();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(query)) {

	        if (resultSet.next()) {
	            counts[0] = resultSet.getInt("SoLuongDatPhong");
	            counts[1] = resultSet.getInt("SoLuongKhach");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return counts;
	}
	
	
	//Số lượng hóa đơn thanh toán hôm nay
    //Số lượng phiếu đặt phòng chưa nhận
	public static int[] countCheckedOutInvoicesAndUnbookedRooms() {
	    int[] counts = new int[2];
	    String query = "SELECT " +
	                   "(SELECT COUNT(*) " +
	                   " FROM PHIEUNHANPHONG PNP " +
	                   " JOIN HOADONDICHVU HD ON PNP.MAPNP = HD.MAPNP " +
	                   " JOIN PHIEUDATPHONG PDP ON PNP.MAPDP = PDP.MAPDP " +
	                   " WHERE PDP.NGAYTRA = CAST(GETDATE() AS DATE) AND HD.TRANGTHAI = 1) AS SoLuongHoaDon, " +
	                   "(SELECT COUNT(*) " +
	                   " FROM PHIEUDATPHONG PDP " +
	                   " LEFT JOIN PHIEUNHANPHONG PNP ON PDP.MAPDP = PNP.MAPDP " +
	                   " WHERE PNP.MAPDP IS NULL) AS SoLuongPhongChuaNhan";

	    try (Connection connection = DatabaseConnection.connectDb();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(query)) {

	        if (resultSet.next()) {
	            counts[0] = resultSet.getInt("SoLuongHoaDon");
	            counts[1] = resultSet.getInt("SoLuongPhongChuaNhan");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return counts;
	}
//	public static int[] countCheckedOutInvoicesAndUnbookedRooms() {
//	    int[] counts = new int[2];
//	    String query = "SELECT " +
//	                   "(SELECT COUNT(*) " +
//	                   " FROM PHIEUNHANPHONG PNP " +
//	                   " JOIN HOADONDICHVU HD ON PNP.MAPNP = HD.MAPNP " +
//	                   " JOIN PHIEUDATPHONG PDP ON PNP.MAPDP = PDP.MAPDP " +
//	                   " WHERE PDP.NGAYTRA = CAST(GETDATE() AS DATE)) AS SoLuongHoaDon, " +
//	                   "(SELECT COUNT(*) " +
//	                   " FROM PHIEUDATPHONG PDP " +
//	                   " LEFT JOIN PHIEUNHANPHONG PNP ON PDP.MAPDP = PNP.MAPDP " +
//	                   " WHERE PNP.MAPDP IS NULL) AS SoLuongPhongChuaNhan";
//
//	    try (Connection connection = DatabaseConnection.connectDb();
//	         Statement statement = connection.createStatement();
//	         ResultSet resultSet = statement.executeQuery(query)) {
//
//	        if (resultSet.next()) {
//	            counts[0] = resultSet.getInt("SoLuongHoaDon");
//	            counts[1] = resultSet.getInt("SoLuongPhongChuaNhan");
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return counts;
//	}
	
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

	    String sql = "UPDATE LOAIPHONG SET TENLOAI=?, LOAIGIUONG=?, GIA=?, NGUOITOIDA=?, DIENTICH=? WHERE MALOAIP=?";
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

	public static LOAIPHONG getRoomTypeByRoomNumber(String roomNumber) {
		// TODO Auto-generated method stub
		LOAIPHONG data = null;
		try (Connection connection = DatabaseConnection.connectDb();
				Statement statement = connection.createStatement()) {
			String query = "SELECT LP.MALOAIP,LP.TENLOAI,LP.GIA,LP.DIENTICH,LP.LOAIGIUONG,LP.NGUOITOIDA "
					+ "FROM PHONG P, LOAIPHONG LP "
					+ "WHERE P.MALOAIP = LP.MALOAIP AND MAPHONG =  '" + roomNumber + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				 data = new LOAIPHONG(resultSet.getInt("MALOAIP")
						 ,resultSet.getString("TENLOAI")
						 ,resultSet.getString("LOAIGIUONG")
						 ,resultSet.getDouble("GIA")
						 ,resultSet.getInt("NGUOITOIDA")
						 ,resultSet.getDouble("DIENTICH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
//	public static ObservableList<Object[]> getRoomCountByTypeWithStatus() {
//        ObservableList<Object[]> dataList = FXCollections.observableArrayList();
//
//        String query = "SELECT " +
//                       "    LP.MALOAIP, " +
//                       "    COUNT(P.MAPHONG) AS TotalRooms, " +
//                       "    SUM(CASE WHEN P.MATRANGTHAI = 1 THEN 1 ELSE 0 END) AS ActiveRooms, " +
//                       "    COUNT(P.MAPHONG) - SUM(CASE WHEN P.MATRANGTHAI = 1 THEN 1 ELSE 0 END) AS AvailableRooms " +
//                       "FROM PHONG P " +
//                       "JOIN LOAIPHONG LP ON P.MALOAIP = LP.MALOAIP " +
//                       "GROUP BY LP.MALOAIP";
//
//        try (Connection connection = DatabaseConnection.connectDb();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//
//            while (resultSet.next()) {
//                int maloai = resultSet.getInt("MALOAIP");
//                int totalRooms = resultSet.getInt("TotalRooms");
//                int activeRooms = resultSet.getInt("ActiveRooms");
//                int availableRooms = resultSet.getInt("AvailableRooms");
//                dataList.add(new Object[]{maloai, totalRooms, activeRooms, availableRooms});
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return dataList;
//    }
}

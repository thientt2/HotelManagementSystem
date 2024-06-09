package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import DTO.HOADONDICHVU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HOADONDICHVU_DAO {
	
	public static ObservableList<HOADONDICHVU> listBillService() {
		ObservableList<HOADONDICHVU> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT * FROM HOADONDICHVU";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				list.add(new HOADONDICHVU(resultSet.getString("MAHD")   
						,resultSet.getString("MAPNP")    
	   					,resultSet.getString("NVNHAP")                		   					
	   					,resultSet.getString("NGAYTAO")
	   					,resultSet.getInt("GIADICHVU")
	   					,resultSet.getInt("PHUTHU")
	   					,resultSet.getInt("TRANGTHAI")
	   					,resultSet.getInt("TONGTIEN")));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static ObservableList<Object[]> listBill(){
		
		ObservableList<Object[]> list = FXCollections.observableArrayList();
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT HDV.MAHD, P.MAPHONG, HDV.GIADICHVU,HDV.PHUTHU,PNP.TGNHAN,PNP.TGTRA, HDV.TRANGTHAI "
					+ "FROM HOADONDICHVU HDV, PHIEUNHANPHONG PNP, PHONG P "
					+ "WHERE HDV.MAPNP = PNP.MAPNP AND P.MAPHONG = PNP.MAPHONG ";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				Object[] rowData = new Object[7];
				rowData[0] = resultSet.getString("MAHD");
				rowData[1] = resultSet.getString("MAPHONG");
				rowData[2] = resultSet.getInt("GIADICHVU");
				rowData[3] = resultSet.getInt("PHUTHU");
				rowData[4] = resultSet.getString("TGNHAN");
				rowData[5] = resultSet.getString("TGTRA");
				rowData[6] = resultSet.getString("TRANGTHAI");
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;		
	}
	
	public static void insertBillService(Map<String, Object> data) {
		String maPNP = data.get("maPNP").toString();
		String maNVNhap = data.get("maNVNhap").toString();
		String ngayTao = data.get("ngayTao").toString();
		int tienDichVu = (int) data.get("giaDV");
		int phuThu = 0;
		int trangThai = 0;
		double tongTien = tienDichVu + phuThu;
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "INSERT INTO HOADONDICHVU(MAPNP,NVNHAP,NGAYTAO,GIADICHVU,PHUTHU,TRANGTHAI,TONGTIEN) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, maPNP);
			prepare.setString(2, maNVNhap);
			prepare.setString(3, ngayTao);
			prepare.setDouble(4, tienDichVu);
			prepare.setInt(5, phuThu);
			prepare.setInt(6, trangThai);
			prepare.setDouble(7, tongTien);			
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static HOADONDICHVU getLastBill() {
		HOADONDICHVU billService = null;
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "SELECT TOP 1 * FROM HOADONDICHVU ORDER BY MAHD DESC";
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				billService = new HOADONDICHVU(rs.getString("MAHD")   
						,rs.getString("MAPNP")
						,rs.getString("NVNHAP")                		   					
						,rs.getString("NGAYTAO")
						,rs.getInt("GIADICHVU")
						,rs.getInt("PHUTHU")
						,rs.getInt("TRANGTHAI")
						,rs.getInt("TONGTIEN"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billService;
	}

	public static void updateBillService(Map<String, Object> data) {
		// TODO Auto-generated method stub
		String maHD = (String) data.get("maHD");
		double giaDV = (Double) data.get("giaDV");
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "UPDATE HOADONDICHVU SET GIADICHVU = ? WHERE MAHD = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setDouble(1, giaDV);
			prepare.setString(2, maHD);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HOADONDICHVU getBillServiceByReceiveRoomID(String receiveRoomID) {
		// TODO Auto-generated method stub
		HOADONDICHVU billService = null;
		String query = "SELECT * FROM HOADONDICHVU WHERE MAPNP = '" + receiveRoomID + "'";
		try(Connection connection = DatabaseConnection.connectDb();
			PreparedStatement prepare = connection.prepareStatement(query);) {			
			ResultSet resultSet = prepare.executeQuery();
			while(resultSet.next()) {
				billService = new HOADONDICHVU(resultSet.getString("MAHD"),
						resultSet.getString("MAPNP"),
						resultSet.getString("NVNHAP"),
						resultSet.getString("NGAYTAO"),
						resultSet.getInt("GIADICHVU"),
						resultSet.getInt("PHUTHU"),
						resultSet.getInt("TRANGTHAI"),
						resultSet.getInt("TONGTIEN"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return billService;
	}

	public static void updateBillService(String maHD, double totalPrice) {
		// TODO Auto-generated method stub
		String query = "UPDATE HOADONDICHVU SET TONGTIEN = ? WHERE MAHD = ?";
		try (Connection connection = DatabaseConnection.connectDb();) {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setDouble(1, totalPrice);
			prepare.setString(2, maHD);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HOADONDICHVU getBillServiceByBillId(String billId) {
		// TODO Auto-generated method stub
		HOADONDICHVU billService = null;
		String query = "SELECT * FROM HOADONDICHVU WHERE MAHD = '" + billId + "'";
		try(Connection connection = DatabaseConnection.connectDb();
			PreparedStatement prepare = connection.prepareStatement(query);) {			
			ResultSet resultSet = prepare.executeQuery();
			while(resultSet.next()) {
				billService = new HOADONDICHVU(resultSet.getString("MAHD"),
						resultSet.getString("MAPNP"),
						resultSet.getString("NVNHAP"),
						resultSet.getString("NGAYTAO"),
						resultSet.getInt("GIADICHVU"),
						resultSet.getInt("PHUTHU"),
						resultSet.getInt("TRANGTHAI"),
						resultSet.getInt("TONGTIEN"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return billService;
	}

	public static void updateBillServiceAfterPrint(Map<String, Object> data) {
		// TODO Auto-generated method stub
		String billId = (String) data.get("billId");
		String staffId = (String) data.get("staffId");
		int statusBill = (int) data.get("statusBill");
		try (Connection connection = DatabaseConnection.connectDb();) {
			String query = "UPDATE HOADONDICHVU SET TRANGTHAI = ?, NVNHAP = ? WHERE MAHD = ?";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setInt(1, statusBill);
			prepare.setString(2, staffId);
			prepare.setString(3, billId);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
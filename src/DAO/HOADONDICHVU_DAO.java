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
			String query = "SELECT HDV.MAHD, P.MAPHONG, HDV.GIADICHVU,HDV.PHUTHU,PNP.TGNHAN,PNP.TGTRA "
					+ "FROM HOADONDICHVU HDV, PHIEUNHANPHONG PNP, PHONG P "
					+ "WHERE HDV.MAPNP = PNP.MAPNP AND P.MAPHONG = PNP.MAPHONG ";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				Object[] rowData = new Object[6];
				rowData[0] = resultSet.getString("MAHD");
				rowData[1] = resultSet.getString("MAPHONG");
				rowData[2] = resultSet.getInt("GIADICHVU");
				rowData[3] = resultSet.getInt("PHUTHU");
				rowData[4] = resultSet.getString("TGNHAN");
				rowData[5] = resultSet.getString("TGTRA");
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;		
	}
	
}
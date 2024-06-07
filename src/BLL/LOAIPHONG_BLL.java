package BLL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import DAO.DICHVU_DAO;
import DAO.LOAIPHONG_DAO;
import DTO.*;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class LOAIPHONG_BLL {
	
	public static ObservableList<LOAIPHONG> showRoomType(){ 
		return LOAIPHONG_DAO.showRoomType(); 
	}
	
	public static ObservableList<Object[]> roomType(int id){ 
		return LOAIPHONG_DAO.roomType(id); 
	}
	
	public static List<LOAIPHONG> getRoomTypes(){ 
		return LOAIPHONG_DAO.getRoomTypes();
	}
	
	public static int getRoomTypeId(String name) { 
		return LOAIPHONG_DAO.getRoomTypeId(name);
	}
	
	public static String getRoomTypeName(int id) { 
		return LOAIPHONG_DAO.getRoomTypeName(id);
	}
	
	public static LOAIPHONG getRoomTypeByRoomNumber(String roomNumber) {
		return LOAIPHONG_DAO.getRoomTypeByRoomNumber(roomNumber);
	}

	public static void editRoomType(Map<String, String> data) throws SQLException {
		int maLoai = Integer.parseInt(data.get("type"));
		String tenLoai = data.get("name");
		int maxNguoi = Integer.parseInt(data.get("max"));
		Double gia = Double.parseDouble(data.get("price"));
		Double dienTich = Double.parseDouble(data.get("area"));
		String loaiGiuong = data.get("bed");
		
		//String regexGia = "^[1-9][0-9]{8,9}$";
		
		//còn maxNguoi
		if(tenLoai.isEmpty() || Objects.isNull(gia) || loaiGiuong.isEmpty() || Objects.isNull(dienTich)) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
//		} else if (Pattern.matches(regexGia, gia) == false) {
//			SystemMessage.ERROR_MESSAGE = "ERROR_GIA";
		} else {
			LOAIPHONG_DAO.editRoomType(data);
		}	
	}
}

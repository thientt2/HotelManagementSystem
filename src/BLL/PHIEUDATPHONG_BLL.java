package BLL;

import java.util.Map;

import DAO.PHIEUDATPHONG_DAO;
import DTO.PHIEUDATPHONG;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class PHIEUDATPHONG_BLL {
	public static ObservableList<Object[]> listBookRoomWithReceiveCount() {
		return PHIEUDATPHONG_DAO.listBookRoomWithReceiveCount();
	}
	
//	public static int getReceiveRoomNumber(String idBookRoom) {
//		return PHIEUDATPHONG_DAO.getReceiveRoomNumber(idBookRoom);
//	}
	
	public static void insertBookRoom(Map<String, Object> data) {
		String maKH = (String) data.get("maKH");
		String ngayNhan = (String) data.get("ngayNhan");
		String ngayTra = (String) data.get("ngayTra");		
		String ngayDat = (String) data.get("tgDat");
		
		
		//điều kiện giá nữa
		if(maKH.isEmpty() ||ngayNhan.isEmpty() || ngayTra.isEmpty() || ngayDat.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else {
			PHIEUDATPHONG_DAO.insertBookRoom(data);
		}
	}
	
	public static PHIEUDATPHONG getLastBookRoom() {
		return PHIEUDATPHONG_DAO.getLastBookRoom();
	}
	
	public static ObservableList<Object[]> showBookRoom(int id) {
		return PHIEUDATPHONG_DAO.showBookRoom(id);
	}
	
	public static Object[] getRoomDetails(String maPhong) {
		return PHIEUDATPHONG_DAO.getRoomDetails(maPhong);
	}

	public static PHIEUDATPHONG getBookRoom(String bookRoomId) {
		// TODO Auto-generated method stub
		return PHIEUDATPHONG_DAO.getBookRoom(bookRoomId);
	}
}

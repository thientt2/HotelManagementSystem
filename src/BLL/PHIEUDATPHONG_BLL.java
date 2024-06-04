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
		Double gia = (Double) data.get("gia");
		
		//điều kiện giá nữa
		if(maKH.isEmpty() ||ngayNhan.isEmpty() || ngayTra.isEmpty() || ngayDat.isEmpty() || gia == 0) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else {
			PHIEUDATPHONG_DAO.insertBookRoom(data);
		}
	}
	
	public static PHIEUDATPHONG getLastBookRoom() {
		return PHIEUDATPHONG_DAO.getLastBookRoom();
	}
	
}

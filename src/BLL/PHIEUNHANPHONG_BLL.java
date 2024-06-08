package BLL;

import java.util.Map;

import DAO.PHIEUNHANPHONG_DAO;
import DTO.PHIEUNHANPHONG;
import system.SystemMessage;

public class PHIEUNHANPHONG_BLL {

	public static void insertReceiveRoom(Map<String, Object> data) {
		String maPDP = (String) data.get("maPDP");
		String maPhong = (String) data.get("maPhong");

		String ngayNhan = (String) data.get("ngayNhan");
		String ngayTra = (String) data.get("ngayTra");

		if(maPDP.isEmpty() || maPhong.isEmpty() || ngayNhan.isEmpty() || ngayTra.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else {
			PHIEUNHANPHONG_DAO.insertReceiveRoom(data);
		}		
	}
	
	public static String getLastReceiveRoom() {
		return PHIEUNHANPHONG_DAO.getLastReceiveRoom();
	}
	
	public static PHIEUNHANPHONG getReceiveRoomIDByRoomID(String roomID) {
		return PHIEUNHANPHONG_DAO.getReceiveRoomIDByRoomID(roomID);
	}
	
	public static void updateCheckOut(String mapdp, String checkout) {
		PHIEUNHANPHONG_DAO.updateCheckOut(mapdp, checkout);
	}
}

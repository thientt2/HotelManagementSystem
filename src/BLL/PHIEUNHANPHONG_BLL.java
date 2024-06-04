package BLL;

import java.util.Map;

import DAO.PHIEUNHANPHONG_DAO;
import system.SystemMessage;

public class PHIEUNHANPHONG_BLL {

	public static void insertReceiveRoom(Map<String, Object> data) {
		String maPNP = (String) data.get("maPNP");
		String maPhong = (String) data.get("maPhong");

		String ngayNhan = (String) data.get("ngayNhan");
		String ngayTra = (String) data.get("ngayTra");

		if(maPNP.isEmpty() || maPhong.isEmpty() || ngayNhan.isEmpty() || ngayTra.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else {
			PHIEUNHANPHONG_DAO.insertReceiveRoom(data);
		}		
	}
	
	public static String getLastReceiveRoom() {
		return PHIEUNHANPHONG_DAO.getLastReceiveRoom();
	}

}

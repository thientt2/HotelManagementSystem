package BLL;

import java.util.List;
import java.util.Map;

import DAO.CHITIETPNP_DAO;

public class CHITIETPNP_BLL {

	public static void insertDetailRecieveRoom(String maPNP,List<String> listOthers) {
		CHITIETPNP_DAO.insertDetailRecieveRoom(maPNP,listOthers);
	}
	
	public static String getTypeRoomId(String recieveRoomId) {
		return CHITIETPNP_DAO.getCustomerId(recieveRoomId);
	}
}

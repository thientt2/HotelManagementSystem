package BLL;

import java.util.Map;

import DAO.CHITIETPNP_DAO;

public class CHITIETPNP_BLL {

	public static void insertDetailRecieveRoom(Map<String, Object> data) {
		CHITIETPNP_DAO.insertDetailRecieveRoom(data);
	}
	
	public static String getTypeRoomId(String recieveRoomId) {
		return CHITIETPNP_DAO.getCustomerId(recieveRoomId);
	}
}

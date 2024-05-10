package BLL;

import java.util.Map;

import DAO.CHITIETPDP_DAO;

public class CHITIETPDP_BLL {

	public static void insertDetailBookRoom(Map<String, Object> data) {
		CHITIETPDP_DAO.insertDetailBookRoom(data);
	}
}

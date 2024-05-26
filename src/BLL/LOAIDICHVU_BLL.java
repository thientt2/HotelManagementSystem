package BLL;

import java.util.List;

import DAO.LOAIDICHVU_DAO;

public class LOAIDICHVU_BLL {
	
	public static List<String> listServiceType() {
		return LOAIDICHVU_DAO.listServiceType();
	}
	
	public static String getServiceTypeName(int id) {
		return LOAIDICHVU_DAO.getServiceTypeName(id);
	}
	
	public static int getServiceTypeId(String name) {
		return LOAIDICHVU_DAO.getServiceTypeId(name);
	}

}

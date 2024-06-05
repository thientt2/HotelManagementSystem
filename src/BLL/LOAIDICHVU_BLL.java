package BLL;

import DAO.LOAIDICHVU_DAO;
import javafx.collections.ObservableList;

public class LOAIDICHVU_BLL {
	
	public static ObservableList<String> listServiceType() {
		return LOAIDICHVU_DAO.listServiceType();
	}
	
	public static ObservableList<String> listServiceName(int serviceType) {
		return LOAIDICHVU_DAO.listServiceName(serviceType);
	}
	
	public static String getServiceTypeName(int id) {
		return LOAIDICHVU_DAO.getServiceTypeName(id);
	}
	
	public static int getServiceTypeId(String name) {
		return LOAIDICHVU_DAO.getServiceTypeId(name);
	}

}

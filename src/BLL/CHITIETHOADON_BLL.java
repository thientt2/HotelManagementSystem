package BLL;

import java.util.List;
import java.util.Map;

import DAO.CHITIETHOADON_DAO;
import javafx.collections.ObservableList;

public class CHITIETHOADON_BLL {
	
	public static void insertBillDetailService(Map<String, Object> billDetailService) {
		CHITIETHOADON_DAO.insertBillDetailService(billDetailService);
	}
	
	public static ObservableList<Object[]> getListDetailService(String mahd) {
		// TODO Auto-generated method stub
		return CHITIETHOADON_DAO.getListDetailService(mahd);
	}

	public static void updateDetailBillService(Map<String, Object> billDetailService) {
		// TODO Auto-generated method stub
		CHITIETHOADON_DAO.updateDetailBillService(billDetailService);
	}
	
	public static boolean isBillDetailServiceExist(String maHD, String maDV) {
		// TODO Auto-generated method stub
		return CHITIETHOADON_DAO.isBillDetailServiceExist(maHD, maDV);
	}
	
	public static List<Object[]> getServiceDetailsByMapnp(String mapnp) {
		return CHITIETHOADON_DAO.getServiceDetailsByMapnp(mapnp);
	}
}

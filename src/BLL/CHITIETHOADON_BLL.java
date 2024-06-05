package BLL;

import java.util.Map;

import DAO.CHITIETHOADON_DAO;

public class CHITIETHOADON_BLL {
	
	public static void insertBillDetailService(Map<String, Object> billDetailService) {
		CHITIETHOADON_DAO.insertBillDetailService(billDetailService);
	}
}

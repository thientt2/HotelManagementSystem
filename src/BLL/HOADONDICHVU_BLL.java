package BLL;

import java.util.Map;

import DAO.HOADONDICHVU_DAO;
import DTO.HOADONDICHVU;
import javafx.collections.ObservableList;

public class HOADONDICHVU_BLL {
	public static ObservableList<HOADONDICHVU> listBillService() {
		return HOADONDICHVU_DAO.listBillService();
	}
	
	public static ObservableList<Object[]> listBill() {
		return HOADONDICHVU_DAO.listBill();
	}
	
	public static void insertBillService(Map<String, Object> data) {
		HOADONDICHVU_DAO.insertBillService(data);
	}
	
	public static HOADONDICHVU getLastBill() {
		return HOADONDICHVU_DAO.getLastBill();
	}
	
	public static void updateBillService(Map<String, Object> data) {
		HOADONDICHVU_DAO.updateBillService(data);
	}
}
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
	
}
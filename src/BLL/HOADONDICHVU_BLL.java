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
	
	public static HOADONDICHVU getBillServiceByReceiveRoomID(String receiveRoomID) {
		return HOADONDICHVU_DAO.getBillServiceByReceiveRoomID(receiveRoomID);
	}

	public static void updateBillService(String maHD, double totalPrice) {
		HOADONDICHVU_DAO.updateBillService(maHD,totalPrice);
	}

	public static HOADONDICHVU getBillServiceByBillId(String billId) {
		// TODO Auto-generated method stub
		return HOADONDICHVU_DAO.getBillServiceByBillId(billId);
	}

	public static void updateBillServiceAfterPrint(Map<String, Object> data) {
		// TODO Auto-generated method stub
		HOADONDICHVU_DAO.updateBillServiceAfterPrint(data);
	}
}
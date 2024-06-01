package BLL;

import java.util.Map;

import DAO.HOADONPHONG_DAO;
import DTO.HOADONPHONG;
import javafx.collections.ObservableList;

public class HOADONPHONG_BLL {
	public static ObservableList<HOADONPHONG> listBillBookRoom() {
		return HOADONPHONG_DAO.listBillBookRoom();
	}
	
	public static HOADONPHONG getLastBill() {
		return HOADONPHONG_DAO.getLastBill();
	}
	
	public static void insertBillBookRoom(Map<String, Object> data) {
		HOADONPHONG_DAO.insertBillBookRoom(data);
	}

	
}
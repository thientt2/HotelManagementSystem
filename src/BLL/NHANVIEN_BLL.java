package BLL;

import DAO.NHANVIEN_DAO;
import DTO.NHANVIEN;
import javafx.collections.ObservableList;

public class NHANVIEN_BLL {

	public static NHANVIEN getStaff(String user) {
		return NHANVIEN_DAO.getStaff(user);
	}
	
	public static ObservableList<NHANVIEN> listStaff() {
		return NHANVIEN_DAO.listStaff();
	}
}

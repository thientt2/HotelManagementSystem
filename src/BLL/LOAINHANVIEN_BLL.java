package BLL;

import java.util.List;

import DAO.LOAINHANVIEN_DAO;

public class LOAINHANVIEN_BLL {
	
	public static List<String> listStaffType() {
		return LOAINHANVIEN_DAO.listStaffType();
	}

}

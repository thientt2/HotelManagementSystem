package BLL;

import java.util.List;

import DAO.LOAINHANVIEN_DAO;

public class LOAINHANVIEN_BLL {
	
	public static List<String> listStaffType() {
		return LOAINHANVIEN_DAO.listStaffType();
	}
	
	public static String getStaffTypeName(int id) {
		return LOAINHANVIEN_DAO.getStaffTypeName(id);
	}
	
	public static int getStaffTypeId(String name) {
		return LOAINHANVIEN_DAO.getStaffTypeId(name);
	}

	public static List<String> getStaffTypeScreens(int staffTypeId) {
		return LOAINHANVIEN_DAO.getStaffTypeScreens(staffTypeId);
	}
}

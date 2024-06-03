package BLL;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import DAO.NHANVIEN_DAO;
import DTO.NHANVIEN;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class NHANVIEN_BLL {

	public static NHANVIEN getStaff(String user) {
		return NHANVIEN_DAO.getStaff(user);
	}
	
	public static NHANVIEN getStaffById(String id) {
		return NHANVIEN_DAO.getStaffById(id);
	}
	public static ObservableList<NHANVIEN> listStaff() {
		return NHANVIEN_DAO.listStaff();
	}
	
	public static void createUser(Map<String, String> data) {
		String staffName = data.get("staffName");
		String job = data.get("job");
		String username = data.get("username");
		String password = data.get("password");
		String confirmPassword = data.get("confirmPassword");
		if(staffName.isEmpty() || job.isEmpty() || username.isEmpty() || password.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if(password.equals(confirmPassword) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PASSWORD";
		}else {
			NHANVIEN_DAO.createUser(data);			
		}
	}
	
	public static void editStaff(Map<String, String> data) {
		String staffName = data.get("name");
		String birthday = data.get("birthday");
		String gender = data.get("gender");
		String cccd = data.get("cccd");
		String email = data.get("email");
		String phone = data.get("phone");	
		String address = data.get("address");
		String startDay = data.get("startDay");
		int type = Integer.parseInt(data.get("job"));
		
		String regexEmail = "^(.+)@(\\S+)$";
		String regexPhong = "^0[1-9][0-9]{8,9}$";
		
		if(staffName.isEmpty() || birthday.isEmpty() || gender.isEmpty() 
				|| cccd.isEmpty() || email.isEmpty() || phone.isEmpty() 
				|| address.isEmpty() || startDay.isEmpty() || type == 0) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if(Pattern.matches(regexEmail, email) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMAIL";
		}else if(Pattern.matches(regexPhong, phone) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PHONE";
		}else {
			NHANVIEN_DAO.editStaff(data);
		}	
	}
	
	public static void insertStaff(Map<String, String> data) {
		String staffName = data.get("name");
		String birthday = data.get("birthday");
		String gender = data.get("gender");
		String cccd = data.get("cccd");
		String email = data.get("email");
		String phone = data.get("phone");
		String address = data.get("address");
		String startDay = data.get("startDay");
		int type = Integer.parseInt(data.get("job"));
		
		String regexEmail = "^(.+)@(\\S+)$";
		String regexPhong = "^0[1-9][0-9]{8,9}$";
		
		if(staffName.isEmpty() || birthday.isEmpty() || gender.isEmpty() || cccd.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || startDay.isEmpty() || type == 0) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if(Pattern.matches(regexEmail, email) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMAIL";
		}else if(Pattern.matches(regexPhong, phone) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PHONE";		
		}else {
			NHANVIEN_DAO.insertStaff(data);	
		}	
	}
	
	public static void deleteStaff(NHANVIEN nhanVien) {
		NHANVIEN_DAO.deleteStaff(nhanVien);
	}
	
	public static List<String> getStaffNamesByJob(int jobType) {
		return NHANVIEN_DAO.getStaffNamesByJob(jobType);
	}
	
}

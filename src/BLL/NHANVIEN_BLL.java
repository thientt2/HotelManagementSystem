package BLL;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import DAO.NHANVIEN_DAO;
import DTO.NHANVIEN;
import javafx.collections.ObservableList;
import system.Encode;
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
		String photuUrl = data.get("photourl");
		
		if(staffName.isEmpty() || job.isEmpty() || photuUrl.isEmpty() || username.isEmpty() || password.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if(password.equals(confirmPassword) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PASSWORD";
		}else if(NHANVIEN_BLL.isExistUsername(username)==true) {
			SystemMessage.ERROR_MESSAGE = "ERROR_USERNAME_EXIST";
		} else {
			String passEncode = Encode.base64EncodeAndMd5Hash(password);
			data.put("password", passEncode);
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
		String regexPhone = "^0[1-9][0-9]{8,9}$";
		
		if(staffName.isEmpty() || birthday.isEmpty() || gender.isEmpty() 
				|| cccd.isEmpty() || email.isEmpty() || phone.isEmpty() 
				|| address.isEmpty() || startDay.isEmpty() || type == 0) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if(Pattern.matches(regexEmail, email) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMAIL";
		}else if(Pattern.matches(regexPhone, phone) == false) {
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
			System.out.println("Loi tai loi trong");
		}else if(Pattern.matches(regexEmail, email) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMAIL";
			System.out.println("Loi tai email");
		}else if(Pattern.matches(regexPhong, phone) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PHONE";		
			System.out.println("Loi tai phone");
		}else {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthDate = LocalDate.parse(birthday, dateFormatter);
			LocalDate startDate = LocalDate.parse(startDay, dateFormatter);
			System.out.println(startDate.isBefore(birthDate));
			if(startDate.isBefore(birthDate)) {
				SystemMessage.ERROR_MESSAGE = "ERROR_BIRTHDAY";				
			}else{
				NHANVIEN_DAO.insertStaff(data);	
			}
		}	
	}
	
	public static void deleteStaff(NHANVIEN nhanVien) {
		NHANVIEN_DAO.deleteStaff(nhanVien);
	}
	
	public static List<String> getStaffNamesByJob(int jobType) {
		return NHANVIEN_DAO.getStaffNamesByJob(jobType);
	}
	
	public static void changePassword(String staffId, String newPassword) {
		String password = Encode.base64EncodeAndMd5Hash(newPassword);
		NHANVIEN_DAO.changePassword(staffId, password);
	}
	
	public static boolean isExistUsername(String username) {
		return NHANVIEN_DAO.getStaff(username) != null;
	}
}

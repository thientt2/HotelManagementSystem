package BLL;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import DAO.KHACHHANG_DAO;
import DTO.KHACHHANG;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class KHACHHANG_BLL {

	public static ObservableList<KHACHHANG> listCustomer() {return KHACHHANG_DAO.listCustomer();}
	
	public static void addCustomer(Map<String, String> data) throws SQLException {
		String tenKH = data.get("name");
		String cccd = data.get("cccd");
		String gender = data.get("gender");
		String birthday = data.get("birthday");
		String email = data.get("email");
		String phone = data.get("phone");
		String address = data.get("address");
		String country = data.get("country");
		
		String regexEmail = "^(.+)@(\\S+)$";
		String regexPhong = "^0[1-9][0-9]{8,9}$";
		
		if(tenKH.isEmpty() || cccd.isEmpty() || gender.isEmpty() || birthday.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || country.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if (Pattern.matches(regexEmail, email) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMAIL";
		} else if (Pattern.matches(regexPhong, phone) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PHONE";
		} else {
			KHACHHANG_DAO.insertCustomer(data);
		}		
	}
	
	public static void editCustomer(Map<String, String> data) throws SQLException {
		String tenKH = data.get("name");
		String cccd = data.get("cccd");
		String gender = data.get("gender");
		String birthday = data.get("birthday");
		String email = data.get("email");
		String phone = data.get("phone");
		String address = data.get("address");
		String country = data.get("country");
		
		String regexEmail = "^(.+)@(\\S+)$";
		String regexPhong = "^0[1-9][0-9]{8,9}$";
		
		if(tenKH.isEmpty() || cccd.isEmpty() || gender.isEmpty() || birthday.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || country.isEmpty()) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		}else if (Pattern.matches(regexEmail, email) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMAIL";
		} else if (Pattern.matches(regexPhong, phone) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_PHONE";
		} else {
			KHACHHANG_DAO.editCustomer(data);
		}		
	}
	
	public static void deleteCustomer(Map<String, String> data) throws SQLException {
		//...
		KHACHHANG_DAO.deleteCustomer(data);
	}
	
	public static KHACHHANG getLastCustomer() throws SQLException {return KHACHHANG_DAO.getLastCustomer();}
}

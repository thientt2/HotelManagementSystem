package BLL;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import DAO.DICHVU_DAO;
import DTO.DICHVU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class DICHVU_BLL {

	public static ObservableList<DICHVU> listService() {
		return DICHVU_DAO.listService();
	}
	
	public static void addService(Map<String, String> data) throws SQLException {
		String tenDV = data.get("tenDV");
		int loaiDV = Integer.parseInt(data.get("loaiDV"));
		String gia = data.get("gia");
		
		String regexGia = "^[1-9][0-9]{8,9}$";
		
		if(tenDV.isEmpty() || loaiDV == 0 || gia.isEmpty() ) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		} else if (Pattern.matches(regexGia, gia) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_GIA";
		} else {
			DICHVU_DAO.insertService(data);
		}		
	}
	
	public static void editService(Map<String, String> data) throws SQLException {
		String tenDV = data.get("tenDV");
		int loaiDV = Integer.parseInt(data.get("loaiDV"));
		String gia = data.get("gia");
		
		String regexGia = "^[1-9][0-9]{8,9}$";
		
		if(tenDV.isEmpty() || loaiDV == 0 || gia.isEmpty() ) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		} else if (Pattern.matches(regexGia, gia) == false) {
			SystemMessage.ERROR_MESSAGE = "ERROR_GIA";
		} else {
			DICHVU_DAO.editService(data);
		}		
	}
	
	
	public static void deleteService(DICHVU dichVu) throws SQLException {
		DICHVU_DAO.deleteService(dichVu);
	}


	//public static KHACHHANG getCustomerById(String customerId) throws SQLException {return KHACHHANG_DAO.getCustomerById(customerId);}
	
	//public static KHACHHANG getCustomerByCCCD(String customerId) throws SQLException {return KHACHHANG_DAO.getCustomerByCCCD(customerId);}
}

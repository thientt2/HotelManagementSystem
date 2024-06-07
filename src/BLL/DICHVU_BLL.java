package BLL;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import DAO.DICHVU_DAO;
import DAO.PHONG_DAO;
import DTO.DICHVU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class DICHVU_BLL {

//	public static ObservableList<DICHVU> listService() {
//		return DICHVU_DAO.listService();
//	}
	
	public static ObservableList<Object[]> listService() {
		 return DICHVU_DAO.listService();
	}
	
	public static ObservableList<String> listServiceName(int id) {
		return DICHVU_DAO.listServiceName(id);
	}
	
	public static String getServiceIdByName(String serviceName) {
		return DICHVU_DAO.getServiceIdByName(serviceName);
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
	
	
	public static void deleteService(Object[] dichVu) throws SQLException {
		DICHVU_DAO.deleteService(dichVu);
	}

	public static int getPriceService(String serviceName) {
		return DICHVU_DAO.getPriceService(serviceName);
	}
}

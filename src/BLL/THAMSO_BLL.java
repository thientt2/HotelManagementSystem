package BLL;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import DAO.THAMSO_DAO;
import DTO.THAMSO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class THAMSO_BLL {

	public static ObservableList<THAMSO> listParam() {return THAMSO_DAO.listParam();}
	
	public static void editParam(Map<String, String> data) throws SQLException {
		String ten = data.get("ten");
		Float tile = Float.parseFloat(data.get("tile"));
		
		if(ten.isEmpty() || tile == 0) {
			SystemMessage.ERROR_MESSAGE = "ERROR_EMPTY";
		} else if (tile > 1 || tile < 0) {
			SystemMessage.ERROR_MESSAGE = "ERROR_TILE";
		} else {
			THAMSO_DAO.editParam(data);
		}		
	}
	
	public static String getParamName(String ten) {
		return THAMSO_DAO.getParamName(ten);
	}

	
}
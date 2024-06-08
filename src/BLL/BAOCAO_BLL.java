package BLL;

import java.util.List;

import DAO.BAOCAO_DAO;
import javafx.collections.ObservableList;

public class BAOCAO_BLL {
	public static List<Integer> getYears() {
		return BAOCAO_DAO.getYears();
	}
	
	public static List<Integer> getMonths(int year) {
		return BAOCAO_DAO.getMonths(year);
	}
	
	public static List<Integer> getDays(int month, int year) {
		return BAOCAO_DAO.getDays(month, year);
	}
	
	public static ObservableList<Object[]> getRoomDataByType() {
		return BAOCAO_DAO.getRoomDataByType();
	}
	
	public static ObservableList<Object[]> getMonthlyReport(int year) {
		return BAOCAO_DAO.getMonthlyReport(year);
	}
}

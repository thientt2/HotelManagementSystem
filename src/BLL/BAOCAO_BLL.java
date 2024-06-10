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
	
	public static ObservableList<Object[]> getRoomDataByTypeAndYear(int year) {
		return BAOCAO_DAO.getRoomDataByTypeAndYear(year);
	}
	
	public static ObservableList<Object[]> getRoomDataByTypeAndMonth(int year, int month) {
		return BAOCAO_DAO.getRoomDataByTypeAndMonth(year, month);
	}
	
	public static ObservableList<Object[]> getRoomDataByTypeAndDateRange(int startDay, int endDay, int month, int year) {
		return BAOCAO_DAO.getRoomDataByTypeAndDateRange(startDay, endDay, month, year);
	}
	
	public static ObservableList<Object[]> getMonthlyReport(int year) {
		return BAOCAO_DAO.getMonthlyReport(year);
	}
	
	public static ObservableList<Object[]> getDailyReport(int startDay, int endDay, int month, int year) {
		return BAOCAO_DAO.getDailyReport(startDay, endDay, month, year);
	}
	
	public static void updateReport(int day, int month, int year, int roomTypeId) {
		BAOCAO_DAO.updateReport(day, month, year, roomTypeId);
	}
	
	public static void insertReport(int day, int month, int year, int roomTypeId) {
		BAOCAO_DAO.insertReport(day, month, year, roomTypeId);
	}
	
	public static boolean checkReport(int day, int month, int year, int roomTypeId) {
		return BAOCAO_DAO.checkReport(day, month, year, roomTypeId);
	}	
}

package BLL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import DAO.*;
import DTO.PHONG;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class PHONG_BLL {
	
	 public static ObservableList<Object[]> getListRoomByFloor(int floorNumber) {return PHONG_DAO.getListRoomByFloor(floorNumber);}
	 public static ObservableList<Object[]> getAllRoom() {return PHONG_DAO.getAllRoom();}
	 public static void addRoom(Map<String, String> data) throws SQLException {
		 String maphong = data.get("maphong");
		 String maloai = data.get("maloai");
		 String matrangthai = data.get("matrangthai");
		 if(maphong.isEmpty() || maloai.isEmpty() || matrangthai.isEmpty()) {
			 SystemMessage.ERROR_MESSAGE = "ERROR_1";
		 }else {
			 boolean check = PHONG_DAO.checkIDRoom(data);
			 if(check == true) {
				 SystemMessage.ERROR_MESSAGE = "ERROR_3";				 
			 } else PHONG_DAO.addRoom(data);
		 }		 
	 }
	 
	 public static void editRoom(Map<String, String> data) {
		 String maphong = data.get("maphong");
		 String maloai = data.get("maloai");
		 if(maphong.isEmpty() || maloai.isEmpty()) {
			 SystemMessage.ERROR_MESSAGE = "ERROR_1";
		 }else {
			 PHONG_DAO.editRoom(data);
		 }
	 }
	 
	 public static PHONG getRoom(String maphong) {return PHONG_DAO.getRoom(maphong);}
	 
	 public static int getAvailableRooms(int roomTypeId, LocalDate checkinDate, LocalDate checkoutDate) throws SQLException
	 {
		 return PHONG_DAO.getAvailableRooms(roomTypeId, checkinDate, checkoutDate);
	 }
	 
	 public static ObservableList<String> getRoomNumbersByTypeAndStatus(String roomType, int status) throws SQLException {
		 return PHONG_DAO.getRoomNumbersByTypeAndStatus(roomType, status);
	 }
	 
	 public static void changeInUseRoomStatus(String maPhong) { PHONG_DAO.changeInUseRoomStatus(maPhong); }
	 public static void changeNotCleanRoomStatus(String maPhong) { PHONG_DAO.changeNotCleanRoomStatus(maPhong); }
	 public static void changeEmptyRoomStatus(String maPhong) { PHONG_DAO.changeEmptyRoomStatus(maPhong); }
	 
	 public static ObservableList<Object[]> getRoomsByStatus(Set<String> statuses) {
		 return PHONG_DAO.getRoomsByStatus(statuses);
	 }
}



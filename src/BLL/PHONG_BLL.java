package BLL;

import java.sql.SQLException;
import java.util.Map;

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
	 
	 public static PHONG layPhong(String maphong) {return PHONG_DAO.layPhong(maphong);}
	 
}



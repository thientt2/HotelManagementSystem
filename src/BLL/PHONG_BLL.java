package BLL;

import java.util.Map;

import DAO.*;
import javafx.collections.ObservableList;
import system.SystemMessage;

public class PHONG_BLL {
	
	 public static ObservableList<Object[]> showRoom() {return PHONG_DAO.showRoom();}
	 public static void addRoom(Map<String, String> data) {
		 String sophong = data.get("sophong");
		 String maloai = data.get("maloai");
		 String matrangthai = data.get("matrangthai");
		 if(sophong.isEmpty() || maloai.isEmpty() || matrangthai.isEmpty()) {
			 SystemMessage.ERROR_MESSAGE = "ERROR_1";
		 }else {
			 PHONG_DAO.addRoom(data);
		 }
		 
		 
	 }
	 
}



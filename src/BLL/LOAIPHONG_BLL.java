package BLL;
import java.util.List;

import DAO.LOAIPHONG_DAO;
import DTO.*;
import javafx.collections.ObservableList;

public class LOAIPHONG_BLL {
	
	public static ObservableList<LOAIPHONG> showRoomType(){ return LOAIPHONG_DAO.showRoomType(); }
	public static List<LOAIPHONG> getRoomTypes(){ return LOAIPHONG_DAO.getRoomTypes();}

}

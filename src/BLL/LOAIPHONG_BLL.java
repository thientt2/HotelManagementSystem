package BLL;
import DAO.LOAIPHONG_DAO;
import DTO.*;
import javafx.collections.ObservableList;

public class LOAIPHONG_BLL {
	
	public static ObservableList<LOAIPHONG> showRoomType(){ return LOAIPHONG_DAO.showRoomType(); }

}

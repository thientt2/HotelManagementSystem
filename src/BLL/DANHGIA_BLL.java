package BLL;

import DAO.DANHGIA_DAO;
import javafx.collections.ObservableList;

public class DANHGIA_BLL {
	public static ObservableList<Object[]> listFeedBack() {
		return DANHGIA_DAO.listFeedBack();
	}
	
	public static Object[] getReviewStars() {
		return DANHGIA_DAO.getReviewStars();
	}
}

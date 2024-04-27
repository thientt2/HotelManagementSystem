package BLL;

import DAO.KHACHHANG_DAO;
import DTO.KHACHHANG;
import javafx.collections.ObservableList;

public class KHACHHANG_BLL {

	public static ObservableList<KHACHHANG> listCustomer() {return KHACHHANG_DAO.listCustomer();}
}

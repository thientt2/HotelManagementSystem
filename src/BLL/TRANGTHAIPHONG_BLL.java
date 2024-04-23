package BLL;

import java.util.List;

import DAO.TRANGTHAIPHONG_DAO;
import DTO.TRANGTHAIPHONG;

public class TRANGTHAIPHONG_BLL {
	public static List<TRANGTHAIPHONG> getStatusRoom(){ return TRANGTHAIPHONG_DAO.getStatusRoom();}

}

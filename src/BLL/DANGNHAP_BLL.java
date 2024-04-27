package BLL;


import DAO.*;
import DTO.NHANVIEN;
import system.SystemMessage;


import java.sql.SQLException;
import java.util.Map;



public class DANGNHAP_BLL {   

    
	public static boolean checkLogin(Map<String,String> data) throws SQLException {
		
		String user = data.get("user");
		String pass = data.get("pass");
		if(user.isEmpty()||pass.isEmpty()) {
			SystemMessage.ERROR_MESSAGE  = "ERROR_1";
			return false;
		}
		else {		
			return DANGNHAP_DAO.checkLogin(user, pass);        
		}		
	}   
	
    
}

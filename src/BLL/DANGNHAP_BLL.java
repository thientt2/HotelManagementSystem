package BLL;


import DAO.*;
import system.SystemMessage;
import java.util.Map;



public class DANGNHAP_BLL {
	
	private DANGNHAP_DAO dangnhapDAO = null;
	
	public DANGNHAP_BLL(){
		this.dangnhapDAO = new DANGNHAP_DAO();
	}
    
	public boolean checkLogin(Map<String,String> data) {
		
		String user = data.get("user");
		String pass = data.get("pass");
		if(user.isEmpty()||pass.isEmpty()) {
			SystemMessage.ERROR_MESSAGE  = "ERROR_1";
			return false;
		}
		return true;
	}
   
    
}

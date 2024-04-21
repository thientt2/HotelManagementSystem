package BLL;


import DAO.*;
import application.AlertMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.SystemMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;



public class DANGNHAP_BLL {
	
	private DANGNHAP_DAO dangnhapDAO = null;
	    
	public DANGNHAP_BLL(){
		this.dangnhapDAO = new DANGNHAP_DAO();
	}
    
	public boolean checkLogin(Map<String,String> data) throws SQLException {
		
		String user = data.get("user");
		String pass = data.get("pass");
		if(user.isEmpty()||pass.isEmpty()) {
			SystemMessage.ERROR_MESSAGE  = "ERROR_1";
			return false;
		}
		else {		
			return dangnhapDAO.checkLogin(user, pass);        
		}		
	}
   
    
}

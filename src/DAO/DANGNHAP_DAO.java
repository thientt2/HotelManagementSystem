package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.NHANVIEN;
import system.SystemMessage;

public class DANGNHAP_DAO {

    
    public static boolean checkLogin(String user, String pass) throws SQLException { 
    	
    	String sql = "SELECT * FROM NHANVIEN WHERE TENDANGNHAP = ? AND MATKHAU = ?";    	
    
    	try(Connection connect = DatabaseConnection.connectDb();
    		PreparedStatement prepare = connect.prepareStatement(sql)){
    		
    		prepare.setString(1, user);
    		prepare.setString(2, pass);
        
    		ResultSet result = prepare.executeQuery();                
     
    		if(!result.next()){
    			//Nếu đăng nhập không thành công thì xuất ra màn hình 
    			SystemMessage.ERROR_MESSAGE = "ERROR_2";
    			return false;                
    		}else{
    			return true;

    		}
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }

    
    
}

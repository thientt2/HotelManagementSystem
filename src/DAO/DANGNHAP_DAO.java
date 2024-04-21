package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.SystemMessage;

public class DANGNHAP_DAO {
	private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    public boolean checkLogin(String user, String pass) throws SQLException {
    	String sql = "SELECT * FROM NHANVIEN WHERE TENDANGNHAP = ? AND MATKHAU = ?";

    
    	connect = DatabaseConnection.connectDb();
    
    	try{        
    		prepare = connect.prepareStatement(sql);
    		prepare.setString(1, user);
    		prepare.setString(2, pass);
        
    		result = prepare.executeQuery();                
     
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

package application;
import java.sql.*;

public class DatabaseConnection {
    public static Connection connectDb() throws SQLException{
        // Thông tin kết nối
    	Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLKS_NMCNPM";
        String username = "ThienTT";
        String password = "12345#";
        String strDbUrl = "jdbc:sqlserver://localhost:1433; databaseName=QLKS_NMCNPM;user=ThienTT;password=12345#;"
                + "encrypt=true;trustServerCertificate=true";

        try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(strDbUrl);
			System.out.println("Kết nối thành công!");
		    return conn;
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Không thể kết nối đến cơ sở dữ liệu.");     
			e.printStackTrace();
		}         

        return conn;
        
    }
}
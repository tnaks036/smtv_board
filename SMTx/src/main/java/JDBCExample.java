import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class JDBCExample {

	public static Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	//JDBC 드라이버 로드 
		String connectionUrl = "jdbc:sqlserver://smtv.iptime.org:2433;databaseName=Notice;integratedSecurity=false;"
				+ "encrypt=false;trustServerCertificate=true;"
				+ "user=sa;"
				+ "password=@admin9150;";

		Connection con = DriverManager.getConnection(connectionUrl);
		System.out.println("서버접속 성공");
		
		return con;
	}
	 public static void main(String[] args) {
		 JDBCExample testingDriver = new JDBCExample();

	        try {
	            Connection connection = testingDriver.getConnection();
	            // 이후 데이터베이스 작업을 수행하세요.
	            
	            // 연결 및 리소스 해제
	            connection.close();
	        } catch (Exception e) {
	        	//드라이버 연결 실패
	            e.printStackTrace();
	        }
	    }
}

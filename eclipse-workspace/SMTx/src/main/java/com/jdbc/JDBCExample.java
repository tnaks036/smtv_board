package com.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class JDBCExample {

	public static Connection getConnection() throws Exception{//한번 연결된 객체를 계속 사용 즉, 연결되지 ㅇ낳은 경우에만 연결을 시도하겠단 으미. -> 싱글톤(디자인패턴) 
		
		try {
			//드라이버 연결
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	//JDBC 드라이버 로드 
		} catch (ClassNotFoundException e) {
			//드라이버 연결 실패
			e.printStackTrace(); 
		}
		String connectionUrl = "jdbc:sqlserver://smtv.iptime.org:2433;databaseName=Notice;integratedSecurity=false;"
				+ "encrypt=false;trustServerCertificate=true;"
				+ "user=sa;"
				+ "password=@admin9150;";
		
		Connection con = null;
		
		try {
			//계정 연결
			con = DriverManager.getConnection(connectionUrl);
			con.setAutoCommit(false);
			System.out.println("서버접속 성공");
			
		} catch (SQLException e) {
			//계정 연결 실패
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	//연결된 상태인지 아닌지 확인 -> 연결 상태 확인되면 close
	private static boolean isConnection(Connection con) {
		boolean valid = true;
		
		try {
			if(con == null || con.isClosed()) {
				valid = false;
			}

		} catch (SQLException e) {
			valid = true;
			e.printStackTrace();
		}
		return valid;
	}
	
	
	//연결된 상태인지 아닌지 확인 -> 연결 상태 확인되면 close
	public static void close(Connection con) {
		if(isConnection(con)) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//stmt가 null  아닐때 close
	public static void close(Statement stmt) {
		if(stmt !=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//rs가 null 아닐때 close
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	//연결 상태라면 commit
	public static void commit(Connection con) {
		if(isConnection(con)) {
			try {
				con.commit();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//연결상태라면 rollback
	public static void rollback(Connection con) {
		if(isConnection(con)) {
			try {
				con.rollback();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
	
	
	
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class JDBCExample {

	public Connection getConnection() throws Exception {
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

 * 
 * */
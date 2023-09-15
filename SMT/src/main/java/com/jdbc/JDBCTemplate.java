package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String connectionUrl =
                "jdbc:sqlserver://smtv.iptime.org:2433;"
                        + "database=Notice;"
                		+ "encrypt=false;"
                        + "user=sa;"
                        + "password=@admin9150;";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(connectionUrl);
			System.out.println("연결 성공");
		} catch (SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static boolean isConnection(Connection con) {
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
	
	public static void close(Connection con) {
		if(isConnection(con)) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void commit(Connection con) {
		if(isConnection(con)) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection con) {
		if(isConnection(con)) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

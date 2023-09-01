package jsp.common.util;

import java.sql.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
			//Ensure a connection path to physically separated servers
			Connection conn = null;
			//Passes in a select statement written in JavaScript.
			Statement stmt = null;
			//We need to manipulate the cursor to get the result of the select statement passed to MSSQL.
		    //Interface that declares the methods needed to move the cursor.
			ResultSet rs = null;
			//This is the start of the DBConnection class declaration. It contains instance variables to manage the database connection, statement, and result set objects. These will be used for executing queries and managing the results.
			
			
			
			//Connection parameters for SQL Server
		    public static final String _DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //Load JDBC Drive, by your environ
			//Server ip, port num, sid ; constants 
		    public static final String _URL = "jdbc:sqlserver://smtv.iptime.org:2433;databaseName=Notice;integratedSecurity=false;encrypt=false;trustServerCertificate=true";
		    public static final String _USER = "sa";
		    public static final String _PW = "@admin9150";
		    
		    private static DBConnection db = null;
		    
		  //싱글톤 패턴 ->  It ensures that only one instance of DBConnection can be created. The getInstance method is used to obtain an instance of the class. If an instance doesn't exist, it creates one, otherwise, it returns the existing instance.
		    private DBConnection() {}
		    public static DBConnection getInstance() {
		        if(db == null) {
		           db = new DBConnection();
		        }
		        return db;
		    }
		    
		    public Connection getConnection() throws ClassNotFoundException { //establish a connection to the SQL Server database. 한번 연결된 객체를 계속 사용 즉, 연결되지 ㅇ낳은 경우에만 연결을 시도하겠단 으미. -> 싱글톤(디자인패턴) 
		        try {
		            Class.forName(_DRIVER);	//드라이버 연결 -> use ' Class.forName'  and load JDBC Driver to memory 
		            conn = DriverManager.getConnection(_URL, _USER, _PW);	//use 'DriverManager.getConnection()' and Connect with DB
		            conn.setAutoCommit(false);
		            System.out.println("서버접속 성공"); // It uses the loaded driver class, DriverManager, and connection URL to establish the connection. If the connection is successful, it returns the connection object.
		        } catch (SQLException e) {
		            System.out.println("Could not find driver class"); //드라이버 연결실패 If not, it prints an error message and the exception details.
		            e.printStackTrace();
		            //System.out.println(e.toString());
		        }
		        return conn;
		    }
		    

		/*
		 * 사용한 자원 반납하기 ( Objects such as Connection, PreparedStatement, and ResultSet used to connect to the DB must return resources after use. 
		 * 생성된 역순으로 반납 You must return the resource after use, so write a method to return the resource.
		 * These methods are used to release resources after executing queries.
		 */
		    
		    //freeConnection, is used when a select query is executed and a result set is used.
		    public void freeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {//select일때     
		        try {
		            if(rs !=null) rs.close(); //Both methods close the prepared statement and connection if they're not null. 
		            if(pstmt !=null) pstmt.close();
		            if(conn !=null) conn.close();
		        } catch (Exception e) {
		            e.printStackTrace(); //They also handle exceptions by printing the stack trace.
		        }
		    }
		    
		    //freeConnection, is used for queries that don't produce a result set (such as INSERT, UPDATE, DELETE).
		    //PreparedStatement 동적쿼리에서 사용(권장사항) SELECT * FROM member WHERE id=?
		    public void freeConnection(Connection conn, PreparedStatement pstmt) {//INSERT|UPDATE|DELETE
		        try {
		            if(pstmt !=null) pstmt.close();
		            if(conn !=null) conn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    
		 
	
	
				/*Overall, this class manages database connections and resource cleanup using the Singleton pattern and handles potential exceptions with try-catch blocks. It provides methods to establish connections, execute queries, and release resources.
				 * 연결된 상태인지 아닌지 확인 -> 연결 상태 확인되면 close
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
				}*/
}
	


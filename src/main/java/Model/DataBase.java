package Model;

import java.sql.*;

public class DataBase {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        
//        String connectionUrl =
//                "jdbc:sqlserver://smtv.iptime.org:1433;"
//                + "database=Notice;"
//                + "integratedSecurity=true;"
//                + "encrypt=true;"
//                + "trustServerCertificate=true;";
        
        String connectionUrl1 = "jdbc:sqlserver://smtv.iptime.org:1433;encrypt=true;database=AdventureWorks;integratedSecurity=true;";
        
//		Connection con = DriverManager.getConnection(connectionUrl);
        Connection con = DriverManager.getConnection(connectionUrl1);
        
        return con;
    }
}

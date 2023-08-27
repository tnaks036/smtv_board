package smtv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import dto.BoardDTO;

public class DBConn {
	
    private static Connection dbConn;

    public Connection getConnection() {
        
        if (dbConn == null) {
            
            try {
                String connectionUrl = "jdbc:sqlserver://smtv.iptime.org:2433;databaseName=Notice;integratedSecurity=false;"
                    + "encrypt=false;trustServerCertificate=true;"
                    + "user=sa;"
                    + "password=@admin9150;";
                
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                dbConn = DriverManager.getConnection(connectionUrl);
                System.out.println("서버접속");
                
            } catch (Exception e) {
                System.out.println("서버접속실패"+e.toString());
            }   
        }
        return dbConn;
    }

    public static void close() {
        if (dbConn != null) {
            
            try {
                if (!dbConn.isClosed()) {
                    dbConn.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        dbConn = null;
    }
    
    public void insertBoard(BoardDTO board) {
        Connection dbConn = null;
        PreparedStatement pstmt = null;
        boolean commit = false; // 커밋 여부 플래그
        
        try {
            dbConn = getConnection();
            
            String insertQuery = "INSERT INTO CS_Ans (Board_ID, File_Name, Ins_Date_Time) VALUES (?, ?, ?)";
            pstmt = dbConn.prepareStatement(insertQuery);
            
            pstmt.setInt(1, board.getBoard_ID());
            pstmt.setString(2, board.getFile_Name());
            pstmt.setString(3, board.getIns_Date_Time());
            
            pstmt.executeUpdate();
            System.out.println("데이터 삽입 성공");
            
            // 데이터 삽입 후에 커밋을 수행합니다.
            dbConn.commit();
            commit = true; // 커밋이 성공했을 경우에만 true로 설정
            
        } catch (Exception e) {
            System.out.println(e.toString());
            // 에러 발생 시 롤백을 수행합니다.
            if (dbConn != null) {
                try {
                    dbConn.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (dbConn != null) {
                try {
                    if (commit) {
                        dbConn.commit(); // 커밋이 된 경우에 다시 커밋
                    } else {
                        dbConn.rollback(); // 커밋이 안 된 경우에 롤백
                    }
                    dbConn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
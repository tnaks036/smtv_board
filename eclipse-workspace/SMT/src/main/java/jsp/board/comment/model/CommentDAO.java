package jsp.board.comment.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.HashMap;

import jsp.common.util.DBConnection;

public class CommentDAO {
	 	private Connection conn;
	    private PreparedStatement pstmt;
	    private ResultSet rs;
	    
	    private static CommentDAO instance;
	    
	    private CommentDAO(){}
	    public static CommentDAO getInstance(){
	        if(instance==null)
	            instance=new CommentDAO();
	        return instance;
	    }
	    
	    // 시퀀스를 가져온다.
	    public int getSeq() 
	    {
			DBConnection dbConnection = null;			//difference ? 
			Connection conn = null;			//java.sql	 //db connection
			PreparedStatement pstmt = null; //java.sql   //query 
			ResultSet rs = null;			//java.sql	 //result
			
	        int result = 1;
	        try {
	        	dbConnection = DBConnection.getInstance();		//singletone
	            
	            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT NEXT VALUE FOR COMMENT_SEQ");
	 
	            pstmt = conn.prepareStatement(sql.toString());
	            rs = pstmt.executeQuery(); // 쿼리 실행
	 
	            if (rs.next())    result = rs.getInt(1);
	 
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	 
	        close();
	        return result;
	    } // end getSeq
	    
	    
	    // 댓글 등록
	    public boolean insertComment(CommentBean comment)
	    {
	    	DBConnection dbConnection = null;			//difference ? 
			Connection conn = null;			//java.sql	 //db connection
			PreparedStatement pstmt = null; //java.sql   //query 
			ResultSet rs = null;			//java.sql	 //result
			
	        boolean result = false;
	        
	        try {
	        	dbConnection = DBConnection.getInstance();		//singletone
	 
	            // 자동 커밋을 false로 한다.
	            conn.setAutoCommit(false);
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("INSERT INTO CS_ANS");
	            //댓글 번호, 게시글 번호, 댓글 작성자, 댓글 작성일, 부모글, 내용
	            sql.append(" (COMMENT_ID, BOARD_ID, ANSWER_ID, INS_DATE_TIME, FILE_NAME, CONTENTS"); 	//delete boardID
	            sql.append(" VALUES(?,?,?,sysdate,?,?)");
//	            sql.append("INSERT INTO BOARD_COMMENT");
//	            sql.append(" (COMMENT_NUM, COMMENT_BOARD, COMMENT_ID, COMMENT_DATE");
//	            sql.append(" , COMMENT_PARENT, COMMENT_CONTENT)");
//	            sql.append(" VALUES(?,?,?,sysdate,?,?)");
	    
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, comment.getComment_ID());
	            pstmt.setInt(2, comment.getBoard_ID());
	            pstmt.setString(3, comment.getAnswer_ID());
	            pstmt.setDate(4, comment.getIns_Date_Time());
	            pstmt.setString(5, comment.getFile_Name());
	            pstmt.setString(6, comment.getContents());
	                 
//	            pstmt.setString(1, comment.getComment_ID());
//	            pstmt.setString(2, comment.getComment_ID());
//	            pstmt.setString(3, comment.getComment_id());
//	            pstmt.setInt(4, comment.getComment_parent());
//	            pstmt.setString(5, comment.getComment_content());
//	            
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                conn.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                conn.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	        
	        close();
	        return result;    
	    } // end boardInsert();    
	    
	    // 댓글 목록 가져오기
	    public ArrayList<CommentBean> getCommentList(int boardNum)
	    {
	    	DBConnection dbConnection = null;			//difference ? 
			Connection conn = null;			//java.sql	 //db connection
			PreparedStatement pstmt = null; //java.sql   //query 
			ResultSet rs = null;			//java.sql	 //result
			
	        ArrayList<CommentBean> list = new ArrayList<CommentBean>();
	        
	        try {
	            	dbConnection = DBConnection.getInstance();
	            
	            /* 댓글의 페이지 처리를 하고싶다면 이 쿼리를 사용하면 된다.
	             * SELECT * FROM
	             *            (SELECT  ROWNUM AS rnum,
	             *                   data.*
	             *             FROM
	             *                   (SELECT LEVEL,
	             *                           COMMENT_NUM,
	             *                             COMMENT_BOARD,
	             *                           COMMENT_ID,
	             *                           COMMENT_DATE,
	             *                           COMMENT_PARENT,
	             *                           COMMENT_CONTENT
	             *                    FROM BOARD_COMMENT
	             *                    WHERE COMMENT_BOARD = ?
	             *                   START WITH COMMENT_PARENT = 0
	             *                   CONNECT BY PRIOR COMMENT_NUM = COMMENT_PARENT) 
	             *              data)
	             *    WHERE rnum>=? and rnum<=? ;
	             */
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("    SELECT LEVEL, COMMENT_NUM, COMMENT_BOARD,");
	            sql.append("            COMMENT_ID, COMMENT_DATE,");
	            sql.append("            COMMENT_PARENT, COMMENT_CONTENT");
	            sql.append("    FROM BOARD_COMMENT");
	            sql.append("    WHERE COMMENT_BOARD = ?");
	            sql.append("    START WITH COMMENT_PARENT = 0");
	            sql.append("    CONNECT BY PRIOR COMMENT_NUM = COMMENT_PARENT");    
//	            sql.append("    SELECT LEVEL, COMMENT_NUM, COMMENT_BOARD,");
//	            sql.append("            COMMENT_ID, COMMENT_DATE,");
//	            sql.append("            COMMENT_PARENT, COMMENT_CONTENT");
//	            sql.append("    FROM BOARD_COMMENT");
//	            sql.append("    WHERE COMMENT_BOARD = ?");
//	            sql.append("    START WITH COMMENT_PARENT = 0");
//	            sql.append("    CONNECT BY PRIOR COMMENT_NUM = COMMENT_PARENT");         
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, boardNum);
	            
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                CommentBean comment = new CommentBean();
	                comment.setComment_level(rs.getInt("LEVEL"));
	                comment.setComment_ID(rs.getInt("COMMENT_ID"));
	                comment.setBoard_ID(rs.getInt("Board_ID"));
	                comment.setAnswer_ID(rs.getString("ANSWER_ID"));
	                comment.setFile_Name(rs.getString("FILE_NAME"));
	                comment.setIns_Date_Time(rs.getDate("INS_DATE_TIME"));	                            
	                comment.setContents(rs.getString("CONTENTS"));
	                	              
//	                comment.setComment_level(rs.getInt("LEVEL"));
//	                comment.setComment_num(rs.getInt("COMMENT_NUM"));
//	                comment.setComment_board(rs.getInt("COMMENT_BOARD"));
//	                comment.setComment_id(rs.getString("COMMENT_ID"));
//	                comment.setComment_date(rs.getDate("COMMENT_DATE"));
//	                comment.setComment_parent(rs.getInt("COMMENT_PARENT"));
//	                comment.setComment_content(rs.getString("COMMENT_CONTENT"));
	                list.add(comment);
	            }
	                
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	        
	        close();
	        return list;
	    } // end getCommentList
	    
	    
	    // DB 자원해제
	    private void close()
	    {
	        try {
	            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	            if ( conn != null ){ conn.close(); conn=null;    }
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    } // end close()    
	        
	}

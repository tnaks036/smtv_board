package jsp.board.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import jsp.common.util.DBConnection;

public class BoardDAO { //data access object ;DB에 접근하기 위한 객체인 BoardDAO, CRUD method! 
	
	private static BoardDAO instance; // singletone ?
	private BoardDAO() {}
	
	//SingleTone Pattern
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		} return instance;
	}
	
	//★method with bring sequence. ?????
	public int getSeq() {
		DBConnection dbConnection = null;			//difference ? 
		Connection conn = null;			//java.sql	 //db connection
		PreparedStatement pstmt = null; //java.sql   //query 
		ResultSet rs = null;			//java.sql	 //result
		
		//기본값 설정
		int result = 1;
		try {
			dbConnection = DBConnection.getInstance();		//singletone
			StringBuffer sql = new StringBuffer(); 		//On multithreads, StringBuffer is a safe; Constructs a string buffer with no characters in it and aninitial capacity of 16 characters.
			sql.append("SELECT NEXT VALUE FOR BOARD_ID_SEQUENCE");
			//sql.append("SELECT BOARD_ID.NEXTVAL FROM DUAL"); //aDD= Appends the specified string to this character sequence. 
			
			conn = dbConnection.getConnection();
			pstmt = conn.prepareStatement(sql.toString()); //Stringbuffer ; Returns a string containing the characters in this sequence in the sameorder as this sequence. The length of the string will be the length ofthis sequence.
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//result will store the final value incremented by NEXTVAL ?????  //
							//boolean java.sql.ResultSet.next() throws SQLException ; Moves the cursor forward one row from its current position.A ResultSet cursor is initially positionedbefore the first row; the first call to the method next makes the first row the current row; thesecond call makes the second row the current row, and so on. 
				result = rs.getInt(1);  //Retrieves the value of the designated column in the current rowof this ResultSet object asan int in the Java programming language.    
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.freeConnection(conn, pstmt, rs); 
		}
		return result;
	}

	//★Methods for saving your posts to the database
	public boolean boardInsert(BoardBean board) { //why bool?
		DBConnection dbConnection = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false; 
		
		try {
			dbConnection = DBConnection.getInstance();
			conn = dbConnection.getConnection();
			
			StringBuffer sql = new StringBuffer(); 
			sql.append("INSERT INTO CS_QUES");
			sql.append("(Board_Id, Comment_ID, Title, Contents, File_Name, Ins_Date_Time, Upd_Date_Time, Del_Date_Time, Del_Yn)");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            int num = board.getBoard_ID();
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, board.getBoard_ID());
            pstmt.setString(2, board.getComment_ID());
            pstmt.setString(3, board.getTitle());
            pstmt.setString(4, board.getContents());
            pstmt.setString(5, board.getFile_Name());
            pstmt.setString(6, board.getIns_Date_Time());
            pstmt.setString(7, board.getUpd_Date_Time());
            pstmt.setString(8, board.getDel_Date_Time());
            pstmt.setString(9, board.getDel_Yn());
            
            //If any of them have been updated
            int updateCount = pstmt.executeUpdate();
            if(updateCount >0) {
            	//store true to 'result
            	result = true;
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	//★Methods for getting a list of posts on a board
	public ArrayList<BoardBean> getBoardList(HashMap<String, Object> list){ //파라미터 값으로 HashMap 객체를 전달받음
		DBConnection dbConnection = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        // 글 목록을 가져오기 우ㅣ해 배열 선언
        ArrayList<BoardBean> arrayList = new ArrayList<BoardBean>();
        
        //HashMap 객체에 저장되어 있는 option, condition 값 가져오기
        String option = (String)list.get("option");
        String condition = (String)list.get("condition");
        
        //HaspMap 객체에 저장되어 있는 start 값 가져오기
        int start = (Integer)list.get("start");
        
        try {
			dbConnection = DBConnection.getInstance();
			conn = dbConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			
			
			// option 값이 없다면 == 글 목록 전체 보기
			if(option == null) {
				sql.append("SELECT * FROM CS_QUES (");
				sql.append("SELECT ROW_NUMBER() OVER(DRDER BY BOARD_ID DESC) AS rNUM, * FROM CS_QUES");
				sql.append(") AS N WHERE rNUM BETWEEN ? AND ?");
//				sql.append("( SELECT ROWNUM rNUM, N.* FROM ");
//				sql.append("( SELECT * FROM CS_QUES ORDER BY BOARD_ID DESC) N) ");
//              sql.append("WHERE rNUM BETWEEN ? AND ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                //10개 단위로 글목록을 보여주기 위한 시작값 설정
                pstmt.setInt(1, start);
                pstmt.setInt(2, start+9);
                
                //StringBuffer 비우기
                sql.delete(0,  sql.toString().length());
			}
			//option 값이 0이라면, 제목으로 검색하기
			else if(option.equals("0")) {
				sql.append("SELECT * FROM CS_QUES (");
	            sql.append("    SELECT ROW_NUMBER() OVER(ORDER BY BOARD_ID DESC) rNUM, * ");
	            sql.append("    FROM CS_QUES ");
	            sql.append("    WHERE TITLE LIKE ? ");
	            sql.append(") AS N ");
	            sql.append("WHERE rNUM BETWEEN ? AND ?");
//                sql.append("( SELECT ROWNUM rNUM, N.* FROM ");
//                sql.append("( SELECT * FROM BOARD WHERE TITLE LIKE ? ORDER BY BOARD_ID DESC) N) ");
//                sql.append(" WHERE rNUM BETWEEN ? AND ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                //condition 값을 이용해 검색하기
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }//option 값이 1이라면 내용으로 검색하기
            else if(option.equals("1")) { 
                sql.append("SELECT * FROM CS_QUES ( ");
                sql.append("    SELECT ROW_NUMBER() OVER(ORDER BY BOARD_ID DESC) rNUM, * ");
                sql.append("    FROM CS_QUES");
                sql.append("    WHERE CONTENTS LIKE ? ");
                sql.append(") AS N ");
                sql.append("WHERE rNUM BETWEEN ? AND ?");
//                sql.append("SELECT * FROM ");
//                sql.append("( SELECT ROWNUM rNUM, N.* FROM ");
//                sql.append("( SELECT * FROM BOARD WHERE CONTENTS LIKE ? ORDER BY BOARD_ID DESC) N) ");
//                sql.append(" WHERE rNUM BETWEEN ? AND ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                //condition 값을 이용해 검색하기
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }//option 값이 2라면 제목+내용으로 검색하기    
            else if(option.equals("2")) {
                sql.append("SELECT * FROM ( ");
                sql.append("    SELECT ROW_NUMBER() OVER(ORDER BY BOARD_ID DESC) rNUM, * ");
                sql.append("    FROM BOARD ");
                sql.append("    WHERE TITLE LIKE ? OR CONTENTS LIKE ? ");
                sql.append(") AS N ");
                sql.append("WHERE rNUM BETWEEN ? AND ?");
//                sql.append("SELECT * FROM CS_QUES");
//                sql.append("( SELECT ROWNUM rNUM, N.* FROM ");
//                sql.append("( SELECT * FROM BOARD WHERE TITLE LIKE ? OR CONTENTS LIKE ?");
//                sql.append(" ORDER BY BOARD_ID DESC) N) ");
//                sql.append(" WHERE rNUM BETWEEN ? AND ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                //condition 값을 이용해 검색하기
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, "%"+condition+"%");
                pstmt.setInt(3, start);
                pstmt.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }//option 값이 3이라면 아이디로 검색하기
            else if(option.equals("3")) {
            	sql.append("SELECT * FROM ( ");
            	sql.append("    SELECT ROW_NUMBER() OVER(ORDER BY BOARD_ID DESC) rNUM, * ");
            	sql.append("    FROM CS_QUES ");
            	sql.append("    WHERE BOARD_ID LIKE ? ");
            	sql.append(") AS N ");
            	sql.append("WHERE rNUM BETWEEN ? AND ?");
//                sql.append("SELECT * FROM CS_QUES");
//                sql.append("( SELECT ROWNUM rNUM, N.* FROM ");
//                sql.append("( SELECT * FROM CS_QUES WHERE BOARD_ID LIKE ?");
//                sql.append(" ORDER BY BOARD_ID DESC) N) ");
//                sql.append(" WHERE rNUM BETWEEN ? AND ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                //condition 값을 이용해 검색하기
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
			 rs = pstmt.executeQuery();
	            
	            while(rs.next()) { //쿼리문의 실행 결과를 가져와 BoardBean 객체에 저장하고 이를 arrayList 객체에 저장
	                BoardBean board = new BoardBean();
	                board.setBoard_ID(rs.getInt("BOARD_ID"));
	                board.setComment_ID(rs.getString("Comment_ID"));
	                board.setTitle(rs.getString("Title"));
	                board.setContents(rs.getString("Contents"));
	                board.setFile_Name(rs.getString("File_Name"));
	                board.setIns_Date_Time(rs.getString("Ins_Date_Time"));
	                board.setUpd_Date_Time(rs.getString("Upd_Date_Time"));
	                board.setDel_Date_Time(rs.getString("Del_Date_Time"));
	                board.setDel_Yn(rs.getString("Del_Yn"));
	                
//	                board.setBoardNum(rs.getInt("BOARD_NUM"));
//	                board.setBoardID(rs.getString("BOARD_ID"));
//	                board.setBoardSubject(rs.getString("BOARD_SUBJECT"));
//	                board.setBoardContent(rs.getString("BOARD_CONTENT"));
//	                board.setBoardFile(rs.getString("BOARD_FILE"));
//	                board.setBoardCount(rs.getInt("BOARD_COUNT"));
//	                board.setBoardDate(rs.getDate("BOARD_DATE"));
	                arrayList.add(board);
	            }     
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt, rs);
	        }
	        
	        return arrayList;
	    }
	    
	    //글의 개수를 가져오기 위한 메서드
	    public int getBoardListCount(HashMap<String, Object> list) { //파라미터 값으로 HashMap 객체를 전달받음
	        DBConnection dbConnection = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        //초기값 설정
	        int result = 0;
	        //HashMap 객체에 저장되어 있는 option, condition 값 가져오기
	        String option = (String)list.get("option");
	        String condition = (String)list.get("condition");
	        
	        try {
	            dbConnection = DBConnection.getInstance();
	            conn = dbConnection.getConnection();
	            StringBuffer sql = new StringBuffer(); //멀티쓰레드에서는 StringBuffer가 안전
	            
	            if(option == null) { //전체 글의 개수
	                sql.append("SELECT COUNT(*) FROM CS_QUES");
	                pstmt = conn.prepareStatement(sql.toString());
	                
	                sql.delete(0, sql.toString().length());
	            } else if(option.equals("0")) { //제목으로 검색한 글의 개수
	                sql.append("SELECT COUNT(*) FROM CS_QUES WHERE TITLE LIKE ?");
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                
	                sql.delete(0, sql.toString().length());
	            } else if(option.equals("1")) { //내용으로 검색한 글의 개수
	                sql.append("SELECT COUNT(*) FROM CS_QUES WHERE CONTENTS LIKE ?");
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                
	                sql.delete(0, sql.toString().length());
	            } else if(option.equals("2")) { //제목+내용으로 검색한 글의 개수
	                sql.append("SELECT COUNT(*) FROM CS_QUES WHERE TITLE LIKE ? OR CONTENTS LIKE ?");
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setString(2, "%"+condition+"%");
	                
	                sql.delete(0, sql.toString().length());
	            } else if(option.equals("3")) { //아이디로 검색한 글의 개수
	                sql.append("SELECT COUNT(*) FROM BOARD WHERE COMMENT_ID LIKE ?");
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                
	                sql.delete(0, sql.toString().length());
	            }
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	                result = rs.getInt(1);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt, rs);
	        }
	        
	        return result;
	    }
	    
	    // 게시글 상세보기를 위한 메서드
	    public BoardBean getDetail(int num) { //전달받은 'num' 값을 이용해 게시글의 내용 조회. ->  조회한 내용의 값을 BoardBean 객체에 저장하고 반환.
	        BoardBean board = null;
	        
	        DBConnection dbConnection = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            dbConnection = DBConnection.getInstance();
	            conn = dbConnection.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT * FROM CS_QUES WHERE BOARD_ID = ?");
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, num);
	            
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	                board = new BoardBean();
	                board.setBoard_ID(rs.getInt("BOARD_ID"));
	                board.setComment_ID(rs.getString("Comment_ID"));
	                board.setTitle(rs.getString("Title"));
	                board.setContents(rs.getString("Contents"));
	                board.setFile_Name(rs.getString("File_Name"));
	                board.setIns_Date_Time(rs.getString("Ins_Date_Time"));
	                board.setUpd_Date_Time(rs.getString("Upd_Date_Time"));
	                board.setDel_Date_Time(rs.getString("Del_Date_Time"));
	                board.setDel_Yn(rs.getString("Del_Yn"));
	                
//	                board.setBoardNum(num);
//	                board.setBoardID(rs.getString("BOARD_ID"));
//	                board.setBoardSubject(rs.getString("BOARD_SUBJECT"));
//	                board.setBoardContent(rs.getString("BOARD_CONTENT"));
//	                board.setBoardFile(rs.getString("BOARD_FILE"));
//	                board.setBoardCount(rs.getInt("BOARD_COUNT"));
//	                board.setBoardDate(rs.getDate("BOARD_DATE"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt, rs);
	        }
	        
	        return board;
	    }
	    
	    
	    // 글을 수정하기 위한 메서드
	    public boolean updateBoard(BoardBean board) {
	        boolean result = false;
	        
	        DBConnection dbConnection = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	 
	        try {
	            dbConnection = DBConnection.getInstance();
	            conn = dbConnection.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
				sql.append("UPDATE CS_QUES SET");
				//sql.append("(Board_Id, Comment_ID, Title, Contents, File_Name, Ins_Date_Time, Upd_Date_Time, Del_Date_Time, Del_Yn)");
	            //sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
	            
	            sql.append("TITLE = ?,  ");
	            sql.append("CONTENTS = ?,  ");
	            sql.append("FILE_NAME = ?, ");
	            sql.append("Upd_DATE_TIME = SYSDATE ");
	            sql.append("WHERE BOARD_ID = ? ");
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            int num = board.getBoard_ID();
	            
	            pstmt.setString(1, board.getTitle());
	            pstmt.setString(2, board.getContents());
	            pstmt.setString(3, board.getFile_Name());
	            pstmt.setInt(4, board.getBoard_ID());

//	            pstmt.setString(1, board.getBoardSubject());
//	            pstmt.setString(2, board.getBoardContent());
//	            pstmt.setString(3, board.getBoardFile());
//	            pstmt.setInt(4, board.getBoardNum());
	            
	            int updateCount = pstmt.executeUpdate();
	            if(updateCount > 0) {
	                result = true;
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt);
	        }
	        
	        return result;
	    }
	    
	    // 삭제할 파일명을 가져오기 위한 메서드
	    public String getFile(int num) {
	        String file = null;
	        
	        DBConnection dbConnection = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            dbConnection = DBConnection.getInstance();
	            conn = dbConnection.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT File_Name FROM CS_QUES WHERE BOARD_ID = ?");
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, num);
	            
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	                file = rs.getString("FILE_NAME");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt, rs);
	        }
	        
	        return file;
	    }
	    
	    // 글을 삭제하기 위한 메서드
	    public boolean deleteBoard(int num) {
	        boolean result = false;
	        
	        DBConnection dbConnection = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            dbConnection = DBConnection.getInstance();
	            conn = dbConnection.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("DELETE FROM CS_QUES WHERE BOARD_ID = ? ");
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, num);
	            
	            int updateCount = pstmt.executeUpdate();
	            if(updateCount > 0) {
	                result = true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt);
	        }
	        
	        return result;
	    }
	    
	    /*
	     * // 조회수를 증가시키기 위한 메서드
	    public boolean viewCount(int num) { //전달받은 'num'값을 이용해 조회수를 증가하고, 조회수를 증가시켰다면 'true' 값을 반환
	        boolean result = false;
	        
	        DBConnection dbConnection = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	 
	        try {
	            dbConnection = DBConnection.getInstance();
	            conn = dbConnection.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE BOARD SET BOARD_COUNT = BOARD_COUNT+1 WHERE BOARD_ID = ?");
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, num);
	            
	            int updateCount = pstmt.executeUpdate();
	            if(updateCount > 0) {
	                result = true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dbConnection.freeConnection(conn, pstmt);
	        }
	        
	        return result;
	    }
	    
	     * 
	     * 
	     * */
	    
	    
	    
	    
	}

package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;
import dto.CommentDTO;

public class DBConnection {
	 public Connection getConnection() {
	        Connection dbConn = null;
	        try {
	            String connectionUrl = "jdbc:sqlserver://smtv.iptime.org:2433;databaseName=Notice;integratedSecurity=false;"
	                    + "encrypt=false;trustServerCertificate=true;" + "user=sa;" + "password=@admin9150;"
	                    + "autoCommit=false"; // AutoCommit 모드 비활성화

	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	            dbConn = DriverManager.getConnection(connectionUrl);
	            //System.out.println("서버접속");
	        } catch (Exception e) {
	            System.out.println("서버접속실패" + e.toString() + '\n');
	            e.printStackTrace();
	        }
	        return dbConn;
	    }
		    

		    public List<BoardDTO> getBoardList() {
		        List<BoardDTO> boardList = new ArrayList<>();

		        Connection dbConn = getConnection();
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;

		        try {
		            String selectQuery = "SELECT Board_ID, Comment_ID, Title, Contents, File_Name, FORMAT(Ins_Date_Time, 'MM-dd')as Ins_Date_Time,Upd_Date_Time, Del_Date_Time, Del_Yn FROM CS_Ques"; 
		            pstmt = dbConn.prepareStatement(selectQuery);
		            rs = pstmt.executeQuery();
		            System.out.println(rs);

		            while (rs.next()) {
		                int boardID = rs.getInt("Board_ID");
		                String commentID = rs.getString("Comment_ID");
		                String title = rs.getString("Title");
		                String contents = rs.getString("Contents");
		                byte[] fileData = rs.getBytes("File_Name"); // 파일 데이터를 바이트 배열로 가져오기
		                
		                // 날짜 값을 가져와서 원하는 형식으로 변환
		                String insDateTime = rs.getString("Ins_Date_Time"); // 이미 원하는 형식으로 변환되어 있는 경우
		                String updDateTime = rs.getString("Upd_Date_Time");
		                String delDateTime = rs.getString("Del_Date_Time");
		                String delYN = rs.getString("Del_Yn");
		                
		                List<CommentDTO> comments = getCommentList(boardID);
		                
		             // 나머지 필드들도 적절하게 가져와서 BoardDTO 객체를 생성하고 boardList에 추가
		                BoardDTO board = new BoardDTO(boardID, commentID, title, contents, fileData, insDateTime, updDateTime, delDateTime, delYN);
		                boardList.add(board);
		                System.out.println(board);
		            }
		        } catch (Exception e) {
		            System.out.println(e.toString());
		        } finally {
		            // 리소스 해제
		            if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (dbConn != null) {
		                try {
		                    dbConn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }

		        return boardList;
		    }

		    public void insertBoard(BoardDTO board) {
		        Connection dbConn = null;
		        PreparedStatement pstmt = null;
		        boolean commit = false; // 커밋 여부 플래그

		        try {
		            dbConn = getConnection();
		            dbConn.setAutoCommit(false); // AutoCommit 모드 비활성화


		            String insertQuery = "INSERT INTO CS_Ques (Comment_ID, Title, Contents, File_Name, Ins_Date_Time, Upd_Date_Time, Del_Date_Time, Del_Yn) " +
		                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		            pstmt = dbConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

		            pstmt.setString(1, board.getComment_ID());
		            pstmt.setString(2, board.getTitle());
		            pstmt.setString(3, board.getContents());
		            pstmt.setBytes(4, board.getFile_Name());
		            pstmt.setString(5, board.getIns_Date_Time());
		            pstmt.setString(6, board.getUpd_Date_Time());
		            pstmt.setString(7, board.getDel_Date_Time());
		            pstmt.setString(8, board.getDel_Yn());

		            pstmt.executeUpdate();
		            System.out.println("데이터 삽입 성공");

		            // 삽입된 ID 값을 가져오기
		            ResultSet generatedKeys = pstmt.getGeneratedKeys();
		            if (generatedKeys.next()) {
		                int insertedID = generatedKeys.getInt(1);
		                board.setBoard_ID(insertedID); // 삽입된 ID를 BoardDTO에 설정
		            }

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
		    	                dbConn.commit(); // 커밋이 된 경우에만 커밋
		    	            } else {
		    	                dbConn.rollback(); // 커밋이 안 된 경우에 롤백
		    	            }
		    	            dbConn.setAutoCommit(true); // AutoCommit 모드 활성화
		    	            dbConn.close();
		    	        } catch (Exception e) {
		    	            e.printStackTrace();
		    	        }
		    	    }
		    	}
		    }

		    
		    public void deleteBoard(int boardID) {
		        Connection dbConn = null;
		        PreparedStatement pstmt = null;
		        boolean commit = false; // 커밋 여부 플래그

		        try {
		            dbConn = getConnection();
		            dbConn.setAutoCommit(false); // AutoCommit 모드 비활성화

		            String deleteQuery = "DELETE FROM CS_Ques WHERE Board_ID = ?";
		            pstmt = dbConn.prepareStatement(deleteQuery);
		            pstmt.setInt(1, boardID);

		            pstmt.executeUpdate();
		            System.out.println("데이터 삭제 성공");

		            // 데이터 삭제 후에 커밋을 수행합니다.
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
		                        dbConn.commit(); // 커밋이 된 경우에만 커밋
		                    } else {
		                        dbConn.rollback(); // 커밋이 안 된 경우에 롤백
		                    }
		                    dbConn.setAutoCommit(true); // AutoCommit 모드 활성화
		                    dbConn.close();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }

		    
		    
		    public int getLastBoardID() {
		        int lastBoardID = 0;

		        Connection dbConn = getConnection();
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;

		        try {
		            String selectQuery = "SELECT MAX(Board_ID) AS LastID FROM CS_Ques"; // 여기에 테이블명을 정확히 입력해주세요
		            pstmt = dbConn.prepareStatement(selectQuery);
		            rs = pstmt.executeQuery();

		            if (rs.next()) {
		                lastBoardID = rs.getInt("LastID");
		            }
		        } catch (Exception e) {
		            System.out.println(e.toString());
		        } finally {
		            // 리소스 해제
		            if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (dbConn != null) {
		                try {
		                    dbConn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }

		        return lastBoardID;
		    }
		    
		    
		    
		    public List<CommentDTO> getCommentList(int boardID) {
		        List<CommentDTO> commentList = new ArrayList<>();

		        Connection dbConn = getConnection();
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;

		        try {
		            String selectQuery = "SELECT Comment_ID, Answer_ID, Contents, File_Name, CONVERT(VARCHAR, Ins_Date_Time, 101) AS Ins_Date_Time, CONVERT(VARCHAR, Upd_Date_Time, 101) AS Upd_Date_Time,"
		                    + "CONVERT(VARCHAR, Del_Date_Time, 101) AS Del_Date_Time, Del_Yn FROM CS_Ans WHERE Board_ID = ?";
		            pstmt = dbConn.prepareStatement(selectQuery);
		            pstmt.setInt(1, boardID);
		            rs = pstmt.executeQuery();

		            while (rs.next()) {
		                String commentID = rs.getString("Comment_ID");
		                String answerID = rs.getString("Answer_ID");
		                String contents = rs.getString("Contents");
		                String fileName = rs.getString("File_Name");
		                
		                // 날짜 값을 가져와서 원하는 형식으로 변환
		                String insDateTime = rs.getString("Ins_Date_Time"); // 이미 원하는 형식으로 변환되어 있는 경우
		                String updDateTime = rs.getString("Upd_Date_Time");
		                String delDateTime = rs.getString("Del_Date_Time");
		                String delYN = rs.getString("Del_Yn");
		                
		                CommentDTO comment = new CommentDTO(boardID, commentID, answerID, contents, fileName, insDateTime, updDateTime, delDateTime, delYN);
		                commentList.add(comment);
		                System.out.println("\n");
		                System.out.println(comment);
		            }
		        } catch (Exception e) {
		            System.out.println(e.toString());
		        } finally {
		            // 리소스 해제
		            if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (dbConn != null) {
		                try {
		                    dbConn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }

		        return commentList;
		    }

		    public CommentDTO getComment(int boardID, String commentID) {
		        Connection dbConn = getConnection();
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;
		        CommentDTO comment = null;

		        try {
		            String selectQuery = "SELECT Comment_ID, Answer_ID, Contents, File_Name, CONVERT(VARCHAR, Ins_Date_Time, 101) AS Ins_Date_Time, CONVERT(VARCHAR, Upd_Date_Time, 101) AS Upd_Date_Time,"
		                    + "CONVERT(VARCHAR, Del_Date_Time, 101) AS Del_Date_Time, Del_Yn FROM CS_Ans WHERE Board_ID = ? AND Comment_ID = ?";
		            pstmt = dbConn.prepareStatement(selectQuery);
		            pstmt.setInt(1, boardID);
		            pstmt.setString(2, commentID);
		            rs = pstmt.executeQuery();

		            if (rs.next()) {
		                String fetchedCommentID = rs.getString("Comment_ID");
		                String answerID = rs.getString("Answer_ID");
		                String contents = rs.getString("Contents");
		                String fileName = rs.getString("File_Name");
		                String insDateTime = rs.getString("Ins_Date_Time");
		                String updDateTime = rs.getString("Upd_Date_Time");
		                String delDateTime = rs.getString("Del_Date_Time");
		                String delYN = rs.getString("Del_Yn");

		                comment = new CommentDTO(boardID, fetchedCommentID, answerID, contents, fileName, insDateTime, updDateTime, delDateTime, delYN);
		            }
		        } catch (Exception e) {
		            System.out.println(e.toString());
		        } finally {
		            // 리소스 해제
		            if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (dbConn != null) {
		                try {
		                    dbConn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }

		        return comment;
		    }
		    
		    public void updateComment(int boardID, String commentID, String newContents) {
		        Connection dbConn = getConnection();
		        PreparedStatement pstmt = null;

		        try {
		            String updateQuery = "UPDATE CS_Ans SET Contents = ? WHERE Board_ID = ? AND Comment_ID = ?";
		            pstmt = dbConn.prepareStatement(updateQuery);
		            pstmt.setString(1, newContents);
		            pstmt.setInt(2, boardID);
		            pstmt.setString(3, commentID);

		            pstmt.executeUpdate();
		            System.out.println("댓글 수정 성공");

		        } catch (Exception e) {
		            System.out.println(e.toString());
		        } finally {
		            // 리소스 해제
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (dbConn != null) {
		                try {
		                    dbConn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }

		    public void deleteComment(String commentID) {
		        Connection dbConn = getConnection();
		        PreparedStatement pstmt = null;

		        try {
		            // 게시물 ID 찾기
		            String selectQuery = "SELECT Board_ID FROM CS_Ans WHERE Comment_ID = ?";
		            pstmt = dbConn.prepareStatement(selectQuery);
		            pstmt.setString(1, commentID); // commentID를 문자열로 설정
		            ResultSet rs = pstmt.executeQuery();
		            
		            int boardID = -1; // 초기값으로 임의의 값 설정
		            if (rs.next()) {
		                boardID = rs.getInt("Board_ID");
		            }

		            if (boardID != -1) { // 올바른 게시물 ID를 찾았을 경우에만 삭제 수행
		                String deleteQuery = "DELETE FROM CS_Ans WHERE Comment_ID = ?";
		                pstmt = dbConn.prepareStatement(deleteQuery);
		                pstmt.setString(1, commentID); // commentID를 문자열로 설정

		                pstmt.executeUpdate();
		                System.out.println("댓글 삭제 성공");
		            } else {
		                System.out.println("해당 댓글이 속한 게시물을 찾을 수 없습니다.");
		            }
		        } catch (Exception e) {
		            System.out.println(e.toString());
		        } finally {
		            // 리소스 해제
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (dbConn != null) {
		                try {
		                    dbConn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }
		    
		    public void insertComment(int boardID, CommentDTO comment) {
		        Connection dbConn = null;
		        PreparedStatement pstmt = null;
		        boolean commit = false; // 커밋 여부 플래그

		        try {
		            dbConn = getConnection();
		            dbConn.setAutoCommit(false); // AutoCommit 모드 비활성화

		            String insertQuery = "INSERT INTO CS_Ans (Board_ID, Comment_ID, Answer_ID, Contents, File_Name, Ins_Date_Time, Upd_Date_Time, Del_Date_Time, Del_Yn) " +
		                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		            pstmt = dbConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

		            pstmt.setInt(1, boardID); // 게시물의 Board_ID를 삽입
		            pstmt.setString(2, comment.getComment_ID()); // 실제 Comment_ID 값 입력
		            pstmt.setString(3, comment.getAnswer_ID());
		            pstmt.setString(4, comment.getContents());
		            pstmt.setBytes(5, comment.getFile_Name());
		            pstmt.setString(6, comment.getIns_Date_Time());
		            pstmt.setString(7, comment.getUpd_Date_Time());
		            pstmt.setString(8, comment.getDel_Date_Time());
		            pstmt.setString(9, comment.getDel_Yn());

		            pstmt.executeUpdate();
		            System.out.println("댓글 삽입 성공");

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
		                        dbConn.commit(); // 커밋이 된 경우에만 커밋
		                    } else {
		                        dbConn.rollback(); // 커밋이 안 된 경우에 롤백
		                    }
		                    dbConn.setAutoCommit(true); // AutoCommit 모드 활성화
		                    dbConn.close();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }
		    public void updateBoard(BoardDTO updatedBoard) {
		        System.out.println("수정화면");
		        System.out.println("Board_ID: " + updatedBoard.getBoard_ID());
		        Connection dbConn = null;
		        PreparedStatement pstmt = null;
		        boolean commit = false; // 커밋 여부 플래그

		        try {
		            dbConn = getConnection();
		            dbConn.setAutoCommit(false); // AutoCommit 모드 비활성화

		            // 수정 쿼리
		            String updateQuery = "UPDATE CS_Ques SET Comment_ID = ?, Title = ?, Contents = ?, File_Name = ?, "
		                                + "Ins_Date_Time = ?, Upd_Date_Time = ?, Del_Date_Time = ?, Del_Yn = ? WHERE Board_ID = ?";
		            pstmt = dbConn.prepareStatement(updateQuery);
		            pstmt.setString(1, updatedBoard.getComment_ID());
		            pstmt.setString(2, updatedBoard.getTitle());
		            pstmt.setString(3, updatedBoard.getContents());
		            pstmt.setBytes(4, updatedBoard.getFile_Name()); // 파일 이름을 문자열로 설정
		            pstmt.setString(5, updatedBoard.getIns_Date_Time());
		            pstmt.setString(6, updatedBoard.getUpd_Date_Time());
		            pstmt.setString(7, updatedBoard.getDel_Date_Time());
		            pstmt.setString(8, updatedBoard.getDel_Yn());
		            pstmt.setInt(9, updatedBoard.getBoard_ID());

		            pstmt.executeUpdate();
		            System.out.println("게시물 수정 성공");

		            // 데이터 수정 후에 커밋을 수행합니다.
		            dbConn.commit();
		            commit = true; // 커밋이 성공했을 경우에만 true로 설정

		        } catch (Exception e) {
		            e.printStackTrace();
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
		                        dbConn.commit(); // 커밋이 된 경우에만 커밋
		                    } else {
		                        dbConn.rollback(); // 커밋이 안 된 경우에 롤백
		                    }
		                    dbConn.setAutoCommit(true); // AutoCommit 모드 활성화
		                    dbConn.close();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }

		}
		    
		    
		    
		    
		   






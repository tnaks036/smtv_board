package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.env.IGenericField;

import dto.BoardDTO;
import dto.CommentDTO;
import db.DBConnection;
 
/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    // 클래스에서 변수는 생성만 해주고 다른곳(생성자)에서 값을 주는게 좋다.
    private int board_ID;
    private List<BoardDTO> boardList;
    
    int boardID = 0;
    
    public BoardController() {
        super();
        boardList = new ArrayList<>();
      
    }
 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    	doProcess(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	doProcess(request, response);
    }
 
    public void doProcess(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
 
 
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        System.out.println("command = " + command);
        
        //응답 페이지
        String page = "";
        // 맨 밑 페이지 이동시 쓰는거.
        boolean isRedirect = false;
        
        
        
        if(command.equals("/boardList.do"))
        {
        	DBConnection dbConn = new DBConnection();
            
            // getBoardList 메서드를 호출하여 데이터를 가져와서 boardList에 설정
            boardList = dbConn.getBoardList();
            
            // 가져온 데이터를 request에 설정
            request.setAttribute("list", boardList);
            System.out.println(boardList);
            
            page = "BoardListForm.jsp";
        }
        
        
        //글쓰기 페이지로 이동
        if(command.equals("/regBoardForm.do"))
        {
            page="BoardWriteForm.jsp";
        }
        
        //글 등록 실행
        if (command.equals("/regBoard.do")) {
            // 데이터 받기
        	String comment_ID = request.getParameter("comment_ID");
        	
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");
            String file_name = request.getParameter("file_name");
            String ins_Date_Time = request.getParameter("ins_Date_Time");
            String upd_Date_Time = request.getParameter("upd_Date_Time");
            String del_Date_Time = request.getParameter("del_Date_Time");
            String del_Yn = request.getParameter("del_Yn");
            
            DBConnection dbConn = new DBConnection();

            // 게시글 번호를 데이터베이스에서 가져오기
            // int board_ID = dbConn.getLastBoardID() + 1; // 실제 데이터베이스와 연동되어야 합니다.
            
            BoardDTO board = new BoardDTO(board_ID, comment_ID, title, contents, null, ins_Date_Time, upd_Date_Time,
                    del_Date_Time, del_Yn);
            
            System.out.println(" Hello " + board);
            
            dbConn.insertBoard(board);
            
            // boardList로 돌아가서 
            // 데이터를 적재할 request.setAtrribute("list", boardlist)를 추가해줘야한다. 게시글 등록 후 db에서 최신 데이터를 가져와서 업데이트
            boardList = dbConn.getBoardList();

            page = "boardList.do";
            isRedirect = true;
        }
        
        //게시글 상세보기
        if(command.equals("/boardDetail.do"))
        {
        	       	
            int num = Integer.parseInt(request.getParameter("board_ID"));
//            if (num != null) {
//            	try {
//					int boardID = Integer.parseInt(requestURI);
//					boardID = Integer.parseInt(requestURI);              
//                	System.out.println("정슈 num 들어가유");
//				} catch (NumberFormatException e) {
//					System.out.println("실팽용");
//	            	e.printStackTrace();
//				}	
//            }
//            else {
//        		System.out.println("null 값이용 비었어욤....");
//        	}
            
            
            // 게시글 목록에서 해당 게시글의 댓글을 가져옴
            DBConnection dbConn = new DBConnection();
            List<CommentDTO> commentList = dbConn.getCommentList(num);
            
            
            //게시글 목록에서 넘겨온 글번호와 같은 게시글 찾기.
            // boardList로 반복할거다. 반복해서 뽑으면 그게 BoardDTO.
            for(BoardDTO board: boardList) {
                if(board.getBoard_ID() == num) {
                    request.setAttribute("board", board );
                    break; //break
                }
            }
            request.setAttribute("commentList", commentList);
         // 최신 데이터로 업데이트
            boardList = dbConn.getBoardList();
            page="BoardDetailForm.jsp";
        }
        
        
        
        //게시글 삭제하기
        if (command.equals("/delete.do"))
        {
            int num = Integer.parseInt(request.getParameter("board_ID"));

            // 데이터베이스에서 해당 게시글을 삭제하는 코드를 추가합니다.
            DBConnection dbConn = new DBConnection();
            dbConn.deleteBoard(num);

            // 삭제 후 데이터베이스에서 최신 데이터를 가져와서 업데이트
            boardList = dbConn.getBoardList();
            
            // 삭제 후 게시글 목록으로 이동
            page = "boardList.do";
            isRedirect = true;
        }
        
        //게시글 수정 페이지로 이동
        if(command.equals("/updateBoard.do"))
        {
            //int num = Integer.parseInt(request.getParameter("board_ID"));
            
            String boardIDParameter = request.getParameter("board_ID");    
            String comment_ID= request.getParameter("comment_ID");   
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");
            String file_name = request.getParameter("file_name");
            String ins_Date_Time = request.getParameter("ins_Date_Time");
            String upd_Date_Time = request.getParameter("upd_Date_Time");
            String del_Date_Time = request.getParameter("del_Date_Time");
            String del_Yn = request.getParameter("del_Yn");

            
    	    int boardID = 0; // set default value 
    	    if (boardIDParameter != null && !boardIDParameter.isEmpty()) {
            try {
                boardID = Integer.parseInt(boardIDParameter);
                
            } catch (NumberFormatException e) {
                // 정수로 변환할 수 없는 경우 예외 처리
                e.printStackTrace(); // 또는 로깅 처리
            }
    	    }
             
    	    BoardDTO updatedBoard = new BoardDTO(board_ID, comment_ID, title, contents, null, ins_Date_Time, upd_Date_Time,
                    del_Date_Time, del_Yn);
    	    
            DBConnection dbConn = new DBConnection();	// Create an instance of DbConnection
            dbConn.updateBoard(updatedBoard);
    	    
    	    // Assuming you have a boardList containing the posts you want to edit
    	    // Loop through the boardList and find the post by its ID ~~~~
            
            for(BoardDTO board : boardList) {
                if(board.getBoard_ID() == boardID) {
                    request.setAttribute("board", board);
                    break;
                }
            }
            // Set the boardToUpdate as an attribute to send to the JSP 
            request.setAttribute("updatedBoard", updatedBoard);
            // Now, forward to the update form JSP
            page = "BoardUpdateForm.jsp";
            isRedirect = false; // Modified Part
            
            //response.sendRedirect("boardDetail.do?board_ID=" + boardID);
            
        }
        
      

        // 페이지 이동.
        if (isRedirect) {
            response.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        
        
    }
    
    /*
     * 
     * 댓글 수 표시
     * public List<BoardDTO> calculateCommentCounts(List<BoardDTO> posts) {
    for (BoardDTO post : posts) {
        int postId = post.getBoard_ID();
        int commentCount = yourDaoMethodToGetCommentCount(postId); // Implement this method in your DAO
        post.setRecent(commentCount);
    }
    return posts;
		}
     * 
     * */
    
}
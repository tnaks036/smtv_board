package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentDTO;
import db.DBConnection; 

@WebServlet("/writeCommentForm.do")
public class WriteCommentFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardIDString = request.getParameter("board_ID");
        String commentIDString = request.getParameter("comment_ID"); // comment_ID 파라미터를 얻어옵니다.
        
        int boardID = 0; // 기본값 설정
        // Check if boardIDString is a valid integer before parsing
        if (boardIDString != null && !boardIDString.isEmpty()) {
            try {
                	boardID = Integer.parseInt(boardIDString);              
                	System.out.println("정슈 num 들어가유");
            } catch (NumberFormatException e) {
            	System.out.println("실팽용");
            	e.printStackTrace();
            }
        } else {
        	System.out.println("null값이용 비었어용");
            // Handle the case where boardIDString is empty or null
        }
        
     // comment_ID 파라미터 값을 request에 저장
        request.setAttribute("board_ID", boardID);
        request.setAttribute("comment_ID", commentIDString); // comment_ID를 request에 저장
        
     // CommentDTO를 가져오기 위해 DBConnection을 사용합니다.
        DBConnection dbConn = new DBConnection();
        CommentDTO comment = dbConn.getComment(boardID, commentIDString);
        request.setAttribute("comment", comment); // CommentDTO를 request에 저장
        
        // Continue with your code...
        RequestDispatcher dispatcher = request.getRequestDispatcher("CommentWriteForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8"); // 한글 데이터 인코딩 설정

    	String boardIDString = request.getParameter("board_ID");
    	int boardID = 0;  // 기본값 설정
    	
    	if ( boardIDString != null && !boardIDString.isEmpty()) {
    		try {
    			boardID = Integer.parseInt(boardIDString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
    	}
    	
    	String contents = request.getParameter("contents");
        String commentID = request.getParameter("comment_ID"); 
        String answerID = request.getParameter("answer_ID"); 
        String insDateTime = request.getParameter("Ins_Date_Time");
        

        CommentDTO comment = new CommentDTO(boardID, commentID, answerID, contents, insDateTime, null, null, null, null);
        System.out.println("댓글삽입 시작");
        DBConnection dbConn = new DBConnection(); // DBConn 객체 생성
        dbConn.insertComment(boardID, comment); // 댓글 삽입 메소드 호출
        
        // 댓글 삽입 후에 게시물 상세 페이지로 리다이렉트
        response.sendRedirect("boardDetail.do?board_ID=" + boardID);
    }
}
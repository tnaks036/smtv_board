package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import dto.BoardDTO;
import dto.CommentDTO;
import db.DBConnection; 

@WebServlet("/writeCommentForm.do")
public class WriteCommentFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardIDString = request.getParameter("board_ID");
        // Check if boardIDString is a valid integer before parsing
        if (boardIDString != null && !boardIDString.isEmpty()) {
            try {
                int boardID = Integer.parseInt(boardIDString);
                
                // Now you have a valid integer value for boardID
                request.setAttribute("board_ID", boardID); // Set it as an attribute if needed
                System.out.println("정슈 num 들어가유");
                // Continue with your code...
                RequestDispatcher dispatcher = request.getRequestDispatcher("CommentWriteForm.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
            	System.out.println("실팽용");
            	e.printStackTrace();
                // Handle the case where boardIDString is not a valid integer
                // You can log an error, redirect to an error page, or handle it as needed.
            }
        } else {
        	System.out.println("null값이용 비었어용");
            // Handle the case where boardIDString is empty or null
        }
    	
    	
//    	int boardID = Integer.parseInt(request.getParameter("board_ID"));
//        request.setAttribute("board_ID", boardID); // 댓글 작성 후 리다이렉트할 때 사용

        RequestDispatcher dispatcher = request.getRequestDispatcher("CommentWriteForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8"); // 한글 데이터 인코딩 설정
        int boardID = Integer.parseInt(request.getParameter("board_ID"));

        String contents = request.getParameter("contents");
        String commentID = request.getParameter("comment_ID"); 
        String answerID = request.getParameter("answer_ID"); 
        

        CommentDTO comment = new CommentDTO(boardID, commentID, answerID, contents, null, null, null, null, null);
        System.out.println("댓글삽입 시작");
        DBConnection dbConn = new DBConnection(); // DBConn 객체 생성
        dbConn.insertComment(boardID, comment); // 댓글 삽입 메소드 호출
        
        // 댓글 삽입 후에 게시물 상세 페이지로 리다이렉트
        response.sendRedirect("boardDetail.do?board_ID=" + boardID);
    }
    

}
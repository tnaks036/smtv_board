package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentDTO;
import smtv.DBConn; // DBConn 클래스의 경로에 따라 수정

@WebServlet("/writeCommentForm.do")
public class WriteCommentFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardID = Integer.parseInt(request.getParameter("board_ID"));
        request.setAttribute("board_ID", boardID); // 댓글 작성 후 리다이렉트할 때 사용

        RequestDispatcher dispatcher = request.getRequestDispatcher("write_comment_form.jsp");
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
        DBConn dbConn = new DBConn(); // DBConn 객체 생성
        dbConn.insertComment(boardID, comment); // 댓글 삽입 메소드 호출
        
        // 댓글 삽입 후에 게시물 상세 페이지로 리다이렉트
        response.sendRedirect("boardDetail.do?board_ID=" + boardID);
    }
}

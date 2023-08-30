package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentDTO;
import smtv.DBConn;

@WebServlet("/updateCommentForm.do")
public class UpdateCommentFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardID = Integer.parseInt(request.getParameter("board_ID"));
        String commentID = request.getParameter("comment_ID");

        // 댓글 정보를 가져와서 댓글 수정 페이지로 전달
        DBConn dbConn = new DBConn();
        CommentDTO comment = dbConn.getComment(boardID, commentID);
        request.setAttribute("comment", comment);

        RequestDispatcher dispatcher = request.getRequestDispatcher("update_comment_form.jsp");
        dispatcher.forward(request, response);
    }
}
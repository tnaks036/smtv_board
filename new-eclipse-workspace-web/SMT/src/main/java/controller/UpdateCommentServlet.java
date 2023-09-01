package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;

@WebServlet("/updateComment.do")
public class UpdateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardID = Integer.parseInt(request.getParameter("board_ID"));
        String commentID = request.getParameter("comment_ID");
        String contents = request.getParameter("contents");

        // 댓글 정보 업데이트
        DBConnection dbConn = new DBConnection();
        dbConn.updateComment(boardID, commentID, contents);

        // 댓글 수정 후 댓글 목록 페이지로 이동
        response.sendRedirect("boardDetail.do?board_ID=" + boardID);
        
    }
}
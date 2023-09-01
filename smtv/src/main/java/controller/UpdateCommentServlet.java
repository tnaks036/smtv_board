package controller;

import java.io.IOException;
import java.net.URLEncoder; // URLEncoder import 추가

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smtv.DBConn;

@WebServlet("/updateComment.do")
public class UpdateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 한글 데이터 인코딩 설정
        int boardID = Integer.parseInt(request.getParameter("board_ID"));
        String commentID = request.getParameter("comment_ID"); // 여기서 commentID 가져옴
        String contents = request.getParameter("contents");

        // 댓글 정보 업데이트
        DBConn dbConn = new DBConn();
        dbConn.updateComment(boardID, commentID, contents);

        // 댓글 수정 후 댓글 상세 페이지로 이동
        response.sendRedirect("boardDetail.do?board_ID=" + boardID);
    }
}

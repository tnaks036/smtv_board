package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;

@WebServlet("/deleteComment.do")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardIDParam = request.getParameter("board_ID");
        String commentIDParam = request.getParameter("comment_ID");
        System.out.println("HERE IT IS");
        System.out.println(boardIDParam);
        System.out.println(commentIDParam);

        if (boardIDParam != null && !boardIDParam.isEmpty() && commentIDParam != null && !commentIDParam.isEmpty()) {
            int boardID = Integer.parseInt(boardIDParam);
            String commentID = commentIDParam;

            if (commentIDParam != null && !commentIDParam.isEmpty()) {
    	        try {
    	            // 트랜잭션 시작
    	            DBConnection dbConn = new DBConnection();
    	            try {
    	                dbConn.deleteComment(commentIDParam);

    	                System.out.println("댓글 삭제 후 작업을 수행할 수 있습니다.");
    	                // 삭제 후 원하는 동작을 수행하면 됩니다.
    	            } catch (Exception e) {
    	                System.out.println(e.toString());
    	                // 에러 발생 시 처리
    	            }

    	            // 댓글 삭제 후 어떤 페이지로 이동하거나 메시지를 표시할 수 있습니다.
    	            // 예를 들어, 댓글 목록 페이지로 이동
    	            response.sendRedirect("/smtv/boardDetail.do?board_ID=" + boardIDParam); // board_id 파라미터 추가
    	        } catch (NumberFormatException ex) {
    	            System.out.println("유효하지 않은 댓글 ID입니다.");
    	            // 유효하지 않은 댓글 ID 처리
    	        }
    	    } else { 
    	        // 빈 문자열 또는 null인 경우에 대한 처리
    	        // 예를 들어, 에러 페이지로 이동하거나 오류 메시지를 보여줄 수 있습니다.
    	    }
    	}
    }
}
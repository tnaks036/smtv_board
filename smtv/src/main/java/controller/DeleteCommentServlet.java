package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smtv.DBConn;

@WebServlet("/deleteComment.do")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardIDParam = request.getParameter("board_ID");
        String commentIDParam = request.getParameter("comment_ID");
        System.out.println("여기봐라");
        System.out.println(boardIDParam);
        System.out.println(commentIDParam);

        if (boardIDParam != null && !boardIDParam.isEmpty() && commentIDParam != null && !commentIDParam.isEmpty()) {
            int boardID = Integer.parseInt(boardIDParam);
            String commentID = commentIDParam;

            // 트랜잭션 시작
            DBConn dbConn = new DBConn();
            Connection conn = dbConn.getConnection();
            try {
                conn.setAutoCommit(false); // AutoCommit 모드 비활성화

                // 댓글 삭제
                dbConn.deleteComment(boardID, commentID);

                // 트랜잭션 커밋
                conn.commit();
            } catch (Exception e) {
                System.out.println(e.toString());
                // 에러 발생 시 롤백을 수행합니다.
                try {
                    conn.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            } finally {
                try {
                    conn.setAutoCommit(true); // AutoCommit 모드 활성화
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 댓글 삭제 후 댓글 목록 페이지로 이동
            response.sendRedirect("boardDetail.do?board_ID=" + boardID);
        } else {
            // 빈 문자열 또는 null인 경우에 대한 처리
            // 예를 들어, 에러 페이지로 이동하거나 오류 메시지를 보여줄 수 있습니다.
        }
    }
}

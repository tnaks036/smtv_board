package jsp.board.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = new CommentBean();
        
        // 파리미터 값을 가져온다.
        int comment_board = Integer.parseInt(request.getParameter("BOARD_ID"));
        int comment_id = Integer.parseInt(request.getParameter("COMMENT_ID"));
        //String comment_id = request.getParameter("COMMENT_ID");
        String contents = request.getParameter("CONTENTS");
        
        comment.setComment_ID(dao.getSeq());    // 댓글 번호는 시퀀스값으로
        comment.setBoard_ID(comment_board); 	//board_id
        comment.setComment_ID(comment_id);		
        comment.setContents(contents);
        
        
//        int comment_board = Integer.parseInt(request.getParameter("comment_board"));
//        String comment_id = request.getParameter("comment_id");
//        String comment_content = request.getParameter("comment_content");
//        
//        comment.setComment_ID(dao.getSeq());    // 댓글 번호는 시퀀스값으로
//        comment.setComment_board(comment_board);
//        comment.setComment_id(comment_id);
//        comment.setComment_content(comment_content);
        
        
             
        
        boolean result = dao.insertComment(comment);
 
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;
    }
}
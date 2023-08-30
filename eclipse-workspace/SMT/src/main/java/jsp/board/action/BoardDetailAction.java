package jsp.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardDetailAction implements Action { //조회수 count

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		//전달받은 글번호 num 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		//전달받은 page 파라미터 가져오기
		String page = request.getParameter("page");
		
		
		BoardDAO bDAO = BoardDAO.getInstance();
		//getDetail 메서드 실행
		BoardBean board = bDAO.getDetail(num);
		
		//조회수 증가를 위해 viewCount 메서드 실행
		//boolean result = bDAO.viewCount(num);
		
		// 대글 가져오기
		// 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
        CommentDAO commentDAO = CommentDAO.getInstance();
        ArrayList<CommentBean> commentList = commentDAO.getCommentList(num);
        
        // 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
        if(commentList.size() > 0)    request.setAttribute("commentList", commentList);
		
		//request 객체에 BoardBean 객체와 page 값 담기
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
//		if(result) { //조회수 증가
//			forward.setRedirect(false);
//			forward.setPath("BoardDetailForm.bo");
//		}
		
		
		return forward;
	}
	
}

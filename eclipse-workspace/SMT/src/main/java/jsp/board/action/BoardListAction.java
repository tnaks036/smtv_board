package jsp.board.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardListAction implements Action{ //액션 구현 Action class to get a list of board posts

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		int page = 1; //page 초기값 Default Value
		String pages = request.getParameter("page"); //get the "page" parameter value
		System.out.println("페이지는 ? "+pages);
		
		if(pages != null && !pages.equals("")) { //Set the page value to the page value passed in the parameter, if any.
            page = Integer.parseInt(pages);
        }
        
        String option = request.getParameter("option"); //"option"(검색조건)파라미터 값 가져오기
        String condition = request.getParameter("condition");//"condition"(검색내용)파라미터 값 가져오기
        
        HashMap<String, Object> list = new HashMap<String, Object>(); // 검색조건과 검색내용을 HashMap 객체에 담기
        list.put("option", option);
        list.put("condition", condition);
        
        BoardDAO bDAO = BoardDAO.getInstance();
        //게시글의 개수를 가져오기
        int listCount = bDAO.getBoardListCount(list); 
        
        //한 화면에 10개읠 게시글
        //페이지 번호는 총 5개
        //이후 다음으로 표시
        
        //전체 페이지 수
        int maxPage = (int)((listCount/10.0) + 0.9);
		
        //주소창에 maxPage보다 높은 값을 입력하면 maxPage를 보여줌.
        if(page > maxPage) {
        	page = maxPage;
        }
        
        //시작페이지(start) 번호 세팅 ex) 1페이지 1, 2페이지 11, 3페이지 21
        list.put("start", (page*10)-9);
        
        //글 목록 가져오기
        ArrayList<BoardBean> arrayList = bDAO.getBoardList(list);
        
        //시작 페이지 번호
        int startPage = (((int)((page/5.0) + 0.8)) * 5)-4;	//
        
        //마지막 페이지 번호
        int endPage = startPage+4;
        
        //마지막 페이지 번호가 maxPage보다 크면 endPage = maxPage
        if(endPage > maxPage) {
            endPage = maxPage;
        }
        
        request.setAttribute("page", page);
        System.out.println("page는 ? "+page);
        request.setAttribute("startPage", startPage);
        System.out.println("Start는 ? "+startPage);
        request.setAttribute("endPage", endPage);
        System.out.println("end는 ? "+endPage);
        request.setAttribute("maxPage", maxPage);
        System.out.println("max는 ? "+maxPage);
        
        //글의 총 수와 글목록 저장
        request.setAttribute("arrayList", arrayList);
        
        //forward하기
        forward.setRedirect(false);
        forward.setPath("BoardListForm.bo");
        
        return forward;
	}
	
	
	
	
}

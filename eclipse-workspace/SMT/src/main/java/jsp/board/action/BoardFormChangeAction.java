package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class BoardFormChangeAction implements Action{  //- Each screen transition is performed by the BoardFormChangeAction class.
									  //- The Action class is implemented by the following seven methods
	//게시판 화면전환 클래스, controller에서 board.properties 값 가져옴.
	//BoardController 클래스에서 전달받은 key값을 이용 ->전달받은 key값을 이용해서 변경할 화면의 주소를 설정
	private String form = "main.jsp?contentPage=views/board/"; 
	private String path;
	

	public void setCommand(String command) {
		int idx = command.indexOf("."); //ex) LoginForm.do에서 .의 인덱스를 가져온다.
		path = command.substring(0, idx)+".jsp"; // ex) 가져온 index를 이용해 LoginForm.jsp와 같이 만든다.
        System.out.println(path);
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false); //해당 클래스에 의해 호출될 페이지는 request 객체와 response 객체를 공유해야 하기 때문에 forward 방식으로 동작, false해야 합니다. 
		
		if(path.equals("main.jsp")) { //가져온 경로가 main.jsp와 같으면
			forward.setPath(path); //그대로 main.jsp를 경로로 설정한다.
			System.out.println(path);
		} else { //if diff
			forward.setPath(form+path); // ex) 'main.jsp?contentPage=view/member/LoginForm.jsp'와 같이 경로를 설정한다.
		}
		return forward;
	}
}

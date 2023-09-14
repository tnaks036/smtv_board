package Model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import VO.UserVO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private static Map<String, Object> users = new HashMap<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_ID = request.getParameter("user_ID");
        String user_PW = request.getParameter("user_PW");
        String phone_Num = request.getParameter("phone_Num");
        String corp_Name = request.getParameter("corp_Name");
        
     // 회원 가입 기능 구현
        
        if (users.containsKey(user_ID)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("이미 사용 중인 사용자 이름입니다.");
        } else {
            // 사용자 정보를 저장하는 방식을 여기에 추가
            // db에 저장 가능
            
            // 사용자 정보를 UserVO 객체에 저장
            UserVO user = new UserVO();
            user.setUser_ID(user_ID);
            user.setUser_PW(user_PW);
            user.setPhone_Num(phone_Num);
            user.setCorp_Name(corp_Name);
            
            // UserVO 객체를 어딘가에 저장하거나 데이터베이스에 저장
            users.put(user_ID, user_PW);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("회원 가입 성공");
        }
    }
}
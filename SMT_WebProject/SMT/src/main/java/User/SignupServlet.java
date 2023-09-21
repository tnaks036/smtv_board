package User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import Model.DataBase;
import VO.UserVO;
import User.UserDAO;
/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO; 
    
	
	private static Map<String, Object> users = new HashMap<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        this.userDAO = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
		String user_ID = request.getParameter("user_ID");
        String user_PW = request.getParameter("user_PW");
        String user_PW2 = request.getParameter("user_PW2");
        String phone_Num = request.getParameter("phone_Num");
        String corp_Name = request.getParameter("corp_Name");
        System.out.println("또잉서블");
        
        //String hashedPassword = BCrypt.hashpw(user_PW, BCrypt.gensalt()); // 비밀번호 해시화
        System.out.println("비번");
        
//        if(user_ID == null || user_ID.equals("") || user_PW == null || user_PW.equals("") ||
//        		user_PW2 == null || user_PW2.equals("") || phone_Num == null || phone_Num.equals("") || 
//        		corp_Name == null || corp_Name.equals("")) {
//        	request.getSession().setAttribute("messageType", "Error Message");
//        	request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
//        	response.sendRedirect("SignUp.jsp");
//        	return;
//        }    		
//        if(!user_PW.equals(user_PW2)) {
//        	request.getSession().setAttribute("messageType", "Error Message");
//        	request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다..");
//        	response.sendRedirect("SignUp.jsp");
//        	return;
//        }
//        int result = this.userDAO.Signup(user_ID, user_PW, phone_Num, corp_Name);
//        if(result == 1) {//new UserDAO()
//        	request.getSession().setAttribute("messageType", "Sucsess !!! ");
//        	request.getSession().setAttribute("messageContent", "회원가입에 성공했습니다.");
//        	response.sendRedirect("SignUp.jsp");
//        	return;
//        }
//        else {
//        	request.getSession().setAttribute("messageType", "Error Message ");
//        	request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다. ");
//        	response.sendRedirect("SignUp.jsp");
//        	return;
//        }
	}
}
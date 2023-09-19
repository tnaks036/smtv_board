package Model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DataBase;
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
        String phone_Num = request.getParameter("phone_Num");
        String corp_Name = request.getParameter("corp_Name");
        System.out.println("겟파람");
        DataBase db = new DataBase();
        String query;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        /// 회원 가입 기능 구현
        try {
        	conn = db.getConnection();
        	
        	//SQL 쿼리문 작성 (SQL SERVER 사용 예제)
        	query = "INSERT INTO SC_Join (User_ID, User_PW, Phone_Num, Corp_Name) VALUES (?, ?, ?, ?)";
        	pstmt = conn.prepareStatement(query);       
        	
        	pstmt.setString(1, user_ID);
        	pstmt.setString(2, user_PW);
        	pstmt.setString(3, phone_Num);
        	pstmt.setString(4, corp_Name);
        	
        	// check Duplicated User From Database
        	String checkQueryDuplicates = "SELECT User_ID FROM SC_Join WHERE User_ID = ?";
        	PreparedStatement checkStmt = conn.prepareStatement(checkQueryDuplicates);
        	checkStmt.setString(1,  user_ID);
        	ResultSet resultSet = checkStmt.executeQuery();
        	
        	int rowsAffected = pstmt.executeUpdate(); //db 에서 실행        	
        	System.out.println("working?");
        	if(rowsAffected > 0) {
        		// 사용자 정보를 저장하는 방식을 여기에 추가-db에 저장 가능
	            // 사용자 정보를 UserVO 객체에 저장
	            UserVO user = new UserVO();
	            user.setUser_ID(user_ID);
	            user.setUser_PW(user_PW);
	            user.setPhone_Num(phone_Num);		        
	            user.setCorp_Name(corp_Name);
	            System.out.println("working?");
	            // UserVO 객체를 어딘가에 저장하거나 데이터베이스에 저장
	            //users.put(user_ID, user_PW); 
	            response.setStatus(HttpServletResponse.SC_OK);
	            response.getWriter().write("회원 가입 성공");
        	}
        	else if (users.containsKey(user_ID)) {
	            response.setStatus(HttpServletResponse.SC_CONFLICT);
	            response.getWriter().write("이미 사용 중인 사용자 이름입니다.");
	        } else {
	        	//회원가입 실패
	        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("회원 가입 실패");
	        }
    } catch (Exception e) {
    	e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("회원 가입 중 오류 발생: " + e.getMessage());
    } finally {
    	// 자원 해제
        db.close(pstmt);
        db.close(conn);
        
//        map 방식 체크
//        else if (users.containsKey(user_ID)) {
//            response.setStatus(HttpServletResponse.SC_CONFLICT);
//            response.getWriter().write("이미 사용 중인 사용자 이름입니다.");
	    }
	}
}
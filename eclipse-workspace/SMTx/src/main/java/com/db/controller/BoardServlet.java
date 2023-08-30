package com.db.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.biz.BoardBiz;
import com.db.biz.BoardBizImpl;
import com.db.dto.BoardDto;

/**
 * Servlet implementation class BoardServlet -> controller를 Servlet으로 만들어준다. annotation 말고 xml 방법으로 한다는데, controller, servlet, annotation, xml 이 뭔지 알려줭!
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//controller로가서 main 요청을 받아줌.  그전에 utf-8로 인코딩하고, 요청받을 변수를 선언, 요청을 통해 가져다 쓸 biz도 먼저 변수 선언. 
		//인코딩.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//요청받을 변수 선언
		String command = request.getParameter("command");
		BoardBiz biz = new BoardBizImpl();
		
		//만약 요청이 main 이라면?
		if(command.equals("main")) {
			//biz의 selectAll 메소드 리턴값 받을 변수 선언 > biz와 bizimpl의 selectAll()은 게시글 전체 출력의 기능으로, dao의 selectAll()메소드를 이용하기.
			List<BoardDto> list = biz.selectAll();
			request.setAttribute("list", list);
			
			//main.jsp로 이동 forward 방식
			dispatch("main.jsp", request, response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//dispatcher -> forward 방식, 클라이언트 요청에 전송한 데이터 유지.
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request,  response);
	}
	//now getback to boardservlet, and use controller, -> put list into res, and  It uses a forward dispatcher to keep the data sent to the client request.
	//The dispatcher creates a separate method to avoid duplicating code. The dispatch method captures all the data and sends it to main.jsp.
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


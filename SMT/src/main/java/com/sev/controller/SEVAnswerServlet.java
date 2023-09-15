package com.sev.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.sev.biz.SEVAnswerBiz;
import com.sev.biz.SEVAnswerBizImpl;
import com.sev.dto.SEVAnswerDto;

/**
 * Servlet implementation class SEVAnswerServlet
 */
@WebServlet("/SEVAnswerServlet")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024*50
)
public class SEVAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		String command = request.getParameter("command");
		
		SEVAnswerBiz biz = new SEVAnswerBizImpl();
		
		if(command.equals("insert")) {
			String filePath = "C:\\Users\\SMT\\Desktop\\upload\\answer\\";
			byte[] fileData = null;
			
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			String comment_ID = request.getParameter("comment_ID");
			String answer_ID = request.getParameter("answer_ID");
			String contents = request.getParameter("contents");
			
			Part part = request.getPart("file_name");
			String fileName = getFilename(part);
			if(!fileName.isEmpty()) {
				part.write(filePath + fileName);
				
				File file = new File(filePath + fileName);
				FileInputStream fis = new FileInputStream(file);
				fileData = new byte[(int) file.length()];
				fis.read(fileData);
				fis.close();
			}
			
			SEVAnswerDto dto = new SEVAnswerDto(board_ID, comment_ID, answer_ID, contents, fileData);
			boolean res = biz.insert(dto);
			
			JSONObject jo = new JSONObject();
			
			
			response.getWriter().write(jo.toString());
		} else if(command.equals("selectAnswer")) {
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			List<SEVAnswerDto> list = biz.selectAnswer(board_ID);
			
			Gson gson = new Gson();
			String jsonData = gson.toJson(list);
			
			PrintWriter out = response.getWriter();
			out.print(jsonData);
			out.flush();
		} else if(command.equals("update")) {
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			String comment_ID = request.getParameter("comment_ID");
			String answer_ID = request.getParameter("answer_ID");
			String contents = request.getParameter("contents");
			
			SEVAnswerDto dto = new SEVAnswerDto(board_ID, comment_ID, answer_ID, contents);
			boolean res = biz.update(dto);
			
			JSONObject jsonResponse = new JSONObject();;
			response.getWriter().write(jsonResponse.toString());
		} else if(command.equals("delete")) {
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			String comment_ID = request.getParameter("comment_ID");
			
			SEVAnswerDto dto = new SEVAnswerDto(board_ID, comment_ID);
			Boolean res = biz.delete(dto);
			
			JSONObject jsonResponse = new JSONObject();;
			response.getWriter().write(jsonResponse.toString());
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private String getFilename(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] split = contentDisp.split(";");
		
		for(int i = 0; i < split.length; i++) {
			String temp = split[i];
			if(temp.trim().startsWith("filename")) {
				return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
			}
		}
		
		return "";
	}

}

package com.sev.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

import com.sev.biz.SEVBoardBiz;
import com.sev.biz.SEVBoardBizImpl;
import com.sev.dto.Criteria;
import com.sev.dto.PagingDTO;
import com.sev.dto.SEVBoardDto;

/**
 * Servlet implementation class SEVBoardServlet
 */
@WebServlet("/SEVBoardServlet")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024*50
)
public class SEVBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		String command = request.getParameter("command");
		
		SEVBoardBiz biz = new SEVBoardBizImpl();
		
		if(command.equals("main")) {
			HttpSession session = request.getSession();
			session.invalidate();
			Criteria cri = new Criteria();
			if(!(request.getParameter("pageNum") == null || request.getParameter("pageNum").equals(""))) {
				cri.setAmount(Integer.parseInt(request.getParameter("amount")));
				cri.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
			}
			
			int total = biz.selectAllTotal();
			
//			List<SEVBoardDto> list = biz.selectAll();
			List<SEVBoardDto> paging = biz.selectPaging(cri);
			
			request.setAttribute("pageMaker", new PagingDTO(cri, total));
			request.setAttribute("list", paging);
			
			dispatch("main.jsp", request, response);
			
		} else if(command.equals("write")) {
			response.sendRedirect("boardWrite.jsp");
			
		} else if(command.equals("insert")) {
			byte[] fileData = null;
			
			String comment_ID = request.getParameter("writer");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			String filePath = "C:\\Users\\SMT\\Desktop\\upload\\";
			
			Part part = request.getPart("fileName");
			String fileName = getFilename(part);
//			if(!fileName.isEmpty()) {
//				part.write(filePath + fileName);
//				
//				File file = new File(filePath + fileName);
//				FileInputStream fis = new FileInputStream(file);
//				fileData = new byte[(int) file.length()];
//				fis.read(fileData);
//				fis.close();
//			}
			
			SEVBoardDto dto = new SEVBoardDto(comment_ID, title, contents, fileName);
			boolean res = biz.insert(dto);
			
			if(res) {
				jsResponse("글 작성 성공", "Board.do?command=main", response);
			} else {
				dispatch("Board.do?command=insert", request, response);
			}
			
		} else if(command.equals("selectOne")) {
			HttpSession session = request.getSession();
			session.setAttribute("token", "파일");
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			SEVBoardDto dto = biz.selectOne(board_ID);
			
			request.setAttribute("dto", dto);
			
			if(dto.getComment_ID().equals(session.getAttribute("token"))) {
				dispatch("boardDetail.jsp", request, response);
			} else {
				jsResponse("비밀글", "Board.do?command=main", response);
			}
			
		} else if(command.equals("updateForm")) {
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			
			SEVBoardDto dto = biz.selectOne(board_ID);
			
			request.setAttribute("dto", dto);
			dispatch("boardUpdate.jsp", request, response);
			
		} else if(command.equals("update")) {
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			String comment_ID = request.getParameter("writer");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			SEVBoardDto dto = new SEVBoardDto(board_ID, comment_ID, title, contents);
			boolean res = biz.update(dto);
			
			if(res) {
				jsResponse("글 수정 성공", "Board.do?command=selectOne&board_ID=" + board_ID, response);
			} else {
				dispatch("Board.do?command=updateForm&board_ID=" + board_ID, request, response);
			}
			
		} else if(command.equals("delete")) {
			int board_ID = Integer.parseInt(request.getParameter("board_ID"));
			
			boolean res = biz.delete(board_ID);
			
			if(res) {
				jsResponse("글 삭제 성공", "Board.do?command=main", response);
			} else {
				dispatch("Board.do?command=selectOne&board_ID=" + board_ID, request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException{
		// 인코딩
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		String alert = "<script type='text/javascript'>" +
						"alert('" + msg + "');" +
						"location.href='" + url + "';" +
						"</script>";
		PrintWriter out = response.getWriter();
		out.print(alert);
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

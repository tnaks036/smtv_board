package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.BoardDTO;
import smtv.DBConn;
 
 
@WebServlet("*.do")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    // 클래스에서 변수는 생성만 해주고 다른곳(생성자)에서 값을 주는게 좋다.
    private int board_ID;
    private List<BoardDTO> boardList;
    
    
    
    public BoardController() {
        super();
        boardList = new ArrayList<>();
      
    }
 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    	doProcess(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	doProcess(request, response);
    }
 
    public void doProcess(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
 
 
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        System.out.println("command = " + command);
        
        //응답 페이지
        String page = "";
        // 맨 밑 페이지 이동시 쓰는거.
        boolean isRedirect = false;
        
        if(command.equals("/boardList.do"))
        {
        	DBConn dbConn = new DBConn();
            
            // getBoardList 메서드를 호출하여 데이터를 가져와서 boardList에 설정
            boardList = dbConn.getBoardList();
            
            // 가져온 데이터를 request에 설정
            request.setAttribute("list", boardList);
            System.out.println(boardList);
            
            page = "boardList.jsp";
        }
        
        //글쓰기 페이지로 이동
        if(command.equals("/regBoardForm.do"))
        {
            page="board_write_form.jsp";
        }
        
        //글 등록 실행
        if (command.equals("/regBoard.do")) {
            // 데이터 받기
        	String comment_ID = request.getParameter("comment_ID");
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");
            String file_name = request.getParameter("file_name");
            String ins_Date_Time = request.getParameter("ins_Date_Time");
            String upd_Date_Time = request.getParameter("upd_Date_Time");
            String del_Date_Time = request.getParameter("del_Date_Time");
            String del_Yn = request.getParameter("del_Yn");
            
            DBConn dbConn = new DBConn();

            // 게시글 번호를 데이터베이스에서 가져오기
            int board_ID = dbConn.getLastBoardID() + 1; // 실제 데이터베이스와 연동되어야 합니다.
            
            BoardDTO board = new BoardDTO(board_ID, comment_ID, title, contents, file_name, ins_Date_Time,
                    upd_Date_Time, del_Date_Time, del_Yn);
            
            System.out.println("여기에요" + board);
            
            dbConn.insertBoard(board);
            
            page = "boardList.do";
            isRedirect = true;
        }
        
        //게시글 상세보기
        if(command.equals("/boardDetail.do"))
        {
            int num = Integer.parseInt(request.getParameter("board_ID"));
            
            //게시글 목록에서 넘겨온 글번호와 같은 게시글 찾기.
            // boardList로 반복할거다. 반복해서 뽑으면 그게 BoardDTO.
            for(BoardDTO board: boardList) {
                if(board.getBoard_ID() == num) {
                    request.setAttribute("board", board );
                }
            }
            
            page="boardDetail.jsp";
        }
        
        //게시글 삭제하기
        if (command.equals("/delete.do"))
        {
            int num = Integer.parseInt(request.getParameter("board_ID"));

            // 데이터베이스에서 해당 게시글을 삭제하는 코드를 추가합니다.
            DBConn dbConn = new DBConn();
            dbConn.deleteBoard(num);

            // 삭제 후 게시글 목록으로 이동
            page = "boardList.do";
            isRedirect = true;
        }
        
        //게시글 수정 페이지로 이동
        if(command.equals("/updateBoardForm.do"))
        {
            int num = Integer.parseInt(request.getParameter("board_ID"));
            
            for(BoardDTO board : boardList) {
                if(board.getBoard_ID() == num) {
                    request.setAttribute("board", board);
                }
            }
            //수정하고자 하는 게시글의 정보를 jsp에 보내줘야함.
            page = "update_board_form.jsp";
        }
        
        //글 수정
        if(command.equals("/updateBoard.do"))
        {
            String comment_ID = request.getParameter("comment_ID");
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");
            Part filePart = request.getPart("file_name");
            InputStream fileContent = filePart.getInputStream();
            byte[] fileData = fileContent.readAllBytes();
            String ins_Date_Time = request.getParameter("ins_Date_Time");
            String upd_Date_Time = request.getParameter("upd_Date_Time");
            String del_Date_Time = request.getParameter("del_Date_Time");
            String del_Yn = request.getParameter("del_Yn");
            int num = Integer.parseInt(request.getParameter("board_ID"));

            for(BoardDTO board : boardList) {
                if(board.getBoard_ID()==num)
                {
                    board.setComment_ID(comment_ID);
                    board.setTitle(title);
                    board.setContents(contents);
                    board.setFile_Name(fileData); // 변경된 부분
                    board.setIns_Date_Time(ins_Date_Time);
                    board.setUpd_Date_Time(upd_Date_Time);
                    board.setDel_Date_Time(del_Date_Time);
                    board.setDel_Yn(del_Yn);
                }
            }

            page = "boardList.do";
        }
        
        // 페이지 이동.
        if(isRedirect) {
            response.sendRedirect(page);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}
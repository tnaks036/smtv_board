package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.BoardDTO;
import dto.CommentDTO;
import smtv.DBConn;
 
 
@WebServlet("*.do")

@MultipartConfig(
	    location = "C:/Users/V15 G3/Documents/GitHub/smtv_board/upload", // 업로드 디렉토리 경로
	    maxFileSize = 1024 * 1024, // 업로드 가능한 최대 크기 (1MB)
	    maxRequestSize = 2 * 1024 * 1024, // 전체 요청의 최대 크기 (2MB)
	    fileSizeThreshold = 0 // 파일 크기가 너무 큰 경우 예외 발생하지 않음
	)
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
 
    public void doProcess(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
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
            String ins_Date_Time = request.getParameter("ins_Date_Time");
            String upd_Date_Time = request.getParameter("upd_Date_Time");
            String del_Date_Time = request.getParameter("del_Date_Time");
            String del_Yn = request.getParameter("del_Yn");

            DBConn dbConn = new DBConn();

            // 게시글 번호를 데이터베이스에서 가져오기
            // int board_ID = dbConn.getLastBoardID() + 1; // 실제 데이터베이스와 연동되어야 합니다.

            BoardDTO board = new BoardDTO(board_ID, comment_ID, title, contents, null, ins_Date_Time,
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
            
            // 게시글 목록에서 해당 게시글의 댓글을 가져옴
            DBConn dbConn = new DBConn();
            List<CommentDTO> commentList = dbConn.getCommentList(num);
            
            
            //게시글 목록에서 넘겨온 글번호와 같은 게시글 찾기.
            // boardList로 반복할거다. 반복해서 뽑으면 그게 BoardDTO.
            for(BoardDTO board: boardList) {
                if(board.getBoard_ID() == num) {
                    request.setAttribute("board", board );
                }
            }
            request.setAttribute("commentList", commentList);
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
        if (command.equals("/updateBoardForm.do")) {
            int num = Integer.parseInt(request.getParameter("board_ID"));

            for(BoardDTO board : boardList) {
                if(board.getBoard_ID() == num) {
                    request.setAttribute("board", board);
                    break;
                }
            }

            //수정하고자 하는 게시글의 정보를 jsp에 보내줘야함.
            page = "update_board_form.jsp";
            isRedirect = false; // 수정된 부분
        }
        
        //글 수정
     // 게시글 수정
     // 파일 업로드 로직 추가
        if (command.equals("/updateBoard.do")) {
            String boardIdParam = request.getParameter("board_ID");
            if (boardIdParam != null && !boardIdParam.isEmpty()) {
                try {
                    int num = Integer.parseInt(boardIdParam);

                    String comment_ID = request.getParameter("comment_ID");
                    String title = request.getParameter("title");
                    String contents = request.getParameter("contents");
                    Part filePart = request.getPart("file_name");

                    // 수정된 부분: 파일 데이터를 받아서 byte[]로 변환
                    byte[] fileData = null;
                    if (filePart != null && filePart.getSize() > 0) {
                        try (InputStream inputStream = filePart.getInputStream()) {
                            fileData = inputStream.readAllBytes();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    String ins_Date_Time = request.getParameter("ins_Date_Time");
                    String upd_Date_Time = request.getParameter("upd_Date_Time");
                    String del_Date_Time = request.getParameter("del_Date_Time");
                    String del_Yn = request.getParameter("del_Yn");

                    // DBConn 인스턴스 생성
                    DBConn dbConn = new DBConn();

                    // 수정된 정보를 담은 BoardDTO 생성
                    BoardDTO updatedBoard = new BoardDTO(num, comment_ID, title, contents, fileData,
                            ins_Date_Time, upd_Date_Time, del_Date_Time, del_Yn);

                    System.out.println("Updated BoardDTO: " + updatedBoard.toString());
                    // DBConn의 updateBoard 메소드 호출
                    dbConn.updateBoard(updatedBoard);

                    // 수정 후 게시글 상세 페이지로 이동
                    page = "boardDetail.do?board_ID=" + num;
                    isRedirect = true;
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // 에러 메시지 출력
                }
            } else {
                // board_ID 파라미터가 없는 경우에 대한 처리
                // 예를 들어, 에러 메시지를 설정하거나 다른 동작을 수행할 수 있습니다.
            }
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

package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.BoardDTO;
import smtv.DBConn;

@WebServlet("/updateBoard.do")
@MultipartConfig(
    location = "C:/Users/V15 G3/Documents/GitHub/smtv_board/upload", // 업로드 디렉토리 경로
    maxFileSize = 1024 * 1024, // 업로드 가능한 최대 크기 (1MB)
    maxRequestSize = 2 * 1024 * 1024, // 전체 요청의 최대 크기 (2MB)
    fileSizeThreshold = 0 // 파일 크기가 너무 큰 경우 예외 발생하지 않음
)
public class UpdateBoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 멀티파트 요청인지 확인
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            try {
                // 파일 업로드 처리 코드
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);

                // BoardDTO의 필드를 담을 변수들
                int boardId = 0;
                String title = null;
                String contents = null;
                byte[] fileData = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // 일반 폼 필드 처리
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");

                        if ("board_ID".equals(fieldName)) {
                            boardId = Integer.parseInt(fieldValue);
                        } else if ("title".equals(fieldName)) {
                            title = fieldValue;
                        } else if ("contents".equals(fieldName)) {
                            contents = fieldValue;
                        }
                    } else {
                        // 파일 업로드 처리
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();

                        // fileName에는 클라이언트가 업로드한 파일명이 포함됩니다.
                        if (fileName != null && !fileName.isEmpty()) {
                            // 파일 데이터를 얻어서 byte 배열로 변환
                            try (InputStream inputStream = item.getInputStream()) {
                                fileData = inputStream.readAllBytes();
                            }
                        }
                    }
                }

                // 나머지 필드와 파일 데이터를 사용하여 BoardDTO 객체를 생성
                String comment_ID = ""; // 코멘트 ID 설정 필요
                String ins_Date_Time = ""; // 적절한 날짜 및 시간 설정 필요
                String upd_Date_Time = ""; // 적절한 날짜 및 시간 설정 필요
                String del_Date_Time = ""; // 삭제 날짜 및 시간 설정 필요
                String del_Yn = ""; // 삭제 여부 설정 필요

                BoardDTO updatedBoard = new BoardDTO(boardId, comment_ID, title, contents, fileData,
                        ins_Date_Time, upd_Date_Time, del_Date_Time, del_Yn);

                // DB 연동 및 게시글 업데이트 코드 추가
                DBConn dbConn = new DBConn();
                dbConn.updateBoard(updatedBoard);

                // 업데이트 후 어떤 페이지로 이동할지 설정 (예: 상세 페이지)
                String page = "boardDetail.do?board_ID=" + boardId + "&timestamp=" + System.currentTimeMillis();
                response.sendRedirect(page);
            } catch (Exception e) {
                e.printStackTrace();
                // 오류 처리 로직 추가
                // 오류 메시지를 클라이언트에게 보여주거나 다른 방식으로 처리
            }
        }
    }
}

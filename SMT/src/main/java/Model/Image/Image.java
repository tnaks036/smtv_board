package Model.Image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Model.DataBase;
import VO.BoardAVO;
import VO.BoardQVO;

public class Image {
	
	DataBase db = new DataBase();
	String query = "";
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public MultipartRequest uploadTest(HttpServletRequest request){
        
		MultipartRequest multi = null;
		// 저장할 경로
        String savePath = "C:\\Users\\SMT\\Desktop\\게시판 프로젝트\\SMT\\src\\main\\webapp\\img";
        // 파일 최대 크기
        int size = 50 * 1024 * 1024;

        try {
        	multi = new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}

        return multi;
    }
	
	public void downloadImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		int board_ID = Integer.parseInt(request.getParameter("board_ID")); // 이미지 파일 이름
		
		byte[] fileData;
		
		if(request.getParameter("reply") != null) {
			fileData = getFileDataFromDatabase(board_ID, "reply");
		}else {
			fileData = getFileDataFromDatabase(board_ID);
		}
		
        if (fileData == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        // 응답 설정
        response.setHeader("Content-Disposition", "attachment; filename=SMT_" + request.getParameter("board_ID") + ".png");
        //filename = "나올 파일 이름 (확장자 포함)"
        
        response.setContentType("application/octet-stream");

        // 파일 다운로드
        try (InputStream inputStream = new ByteArrayInputStream(fileData);
                OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    private byte[] getFileDataFromDatabase(int boardID) { // 게시글
        // DB에서 파일 데이터를 가져오는 로직을 여기에 작성
        // 예: SELECT FileData FROM CS_Ques WHERE File_Name = ?
        // 위 쿼리를 실행하여 파일 데이터를 byte[] 형태로 반환
    	query = "SELECT File_Name FROM CS_Ques "
    			+ " WHERE Board_ID = " + boardID;
    	
    	BoardQVO bqo = new BoardQVO();
    	
    	try {
			con = db.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bqo.setFile_Name(rs.getBytes("File_Name"));
				return bqo.getFile_Name();
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return null;
    }
    
    
    private byte[] getFileDataFromDatabase(int boardID, String reply) { // 댓글
        // DB에서 파일 데이터를 가져오는 로직을 여기에 작성
        // 예: SELECT FileData FROM CS_Ques WHERE File_Name = ?
        // 위 쿼리를 실행하여 파일 데이터를 byte[] 형태로 반환
    	query = "SELECT File_Name FROM CS_Ans "
    			+ " WHERE Board_ID = " + boardID;
    	
    	BoardAVO bao = new BoardAVO();
    	
    	try {
			con = db.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bao.setFile_Name(rs.getBytes("File_Name"));
				return bao.getFile_Name();
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return null;
    }
    
    
    public void delImg() {
    	String directoryPath = "C:/Users/SMT/Desktop/게시판 프로젝트/SMT/src/main/webapp/img";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
            	if (file.isFile()) {
            			file.delete();
            	}
            }
        }
    
        }
    }
}
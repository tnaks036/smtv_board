<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
글번호 : ${board.getBoard_ID()}<br>
제목 : ${board.getFile_Name()} <br>
내용 : ${board.getContents()}<br>
작성일 : ${board.getIns_Date_Time()}<br>
 
<input type="button" value="삭제하기" onclick="location.href='delete.do?board_ID=${board.getBoard_ID()}';">
<input type="button" value="수정" onclick="location.href='updateBoardForm.do?board_ID=${board.getBoard_ID()}';">
</body>
</html>

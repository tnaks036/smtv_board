<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
글번호 : ${board.board_ID }<br>
제목 : ${board.file_name } <br>
내용 : ${board.contents }<br>
작성일 : ${board.ins_Date_Time }<br>
 
<input type="button" value="삭제하기" onclick="location.href='delete.do?boardNum=${board.boardNum }';">
<!-- 어떤걸 삭제할건지 보드 번호를 가져가야해서 ?뒤에 문장이나옴. -->
<input type="button" value="수정" onclick="location.href='updateBoardForm.do?boardNum=${board.boardNum }';">
<!-- 상세보기 페이지로 넘어갈때도 글번호 데이터를 넘겨서 가져가야해서 ?뒤에 문장을쓴다. -->
</body>
</html>

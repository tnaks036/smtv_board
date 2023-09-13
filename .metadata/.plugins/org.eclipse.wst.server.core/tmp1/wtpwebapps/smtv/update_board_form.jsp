<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
<form action="updateBoard.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="board_ID" value="${board.getBoard_ID()}">
<table>
    <tr>
        <td>제목</td>
        <td><input type="text" name="title" value="${board.getTitle()}"></td>
    </tr>
    <tr>
        <td>내용</td>
        <td><textarea rows="5" cols="50" name="contents">${board.getContents()}</textarea></td>
    </tr>
    <tr>
        <td>첨부 파일</td>
        <td><input type="file" name="fileToUpload"></td>
    </tr>
    <!-- 파일 이름 출력 -->
    <tr>
        <td>파일 이름</td>
        <td>${board.getFileDataAsString()}</td>
    </tr>
</table>
<input type="submit" value="글 수정">
</form>
<p>board_ID 값: ${board.getBoard_ID()}</p>
</body>
</html>
 



<!-- 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
<form action="updateBoard.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="board_ID" value="${board.getBoard_ID()}">
<table>
    <tr>
        <td>제목</td>
        <td><input type="text" name="title" value="${board.getTitle()}"></td>
    </tr>
    <tr>
        <td>내용</td>
        <td><textarea rows="5" cols="50" name="contents">${board.getContents()}</textarea></td>
    </tr>
    <tr>
        <td>첨부 파일</td>
        <td><input type="file" name="file_name"></td>
    </tr>
</table>
<input type="button" value="글 수정" onclick="location.href='updateBoard.do?board_ID=${board.getBoard_ID()}';">
</form>
<p>board_ID 값: ${board.getBoard_ID()}</p> <!-- 추가 -->

 -->
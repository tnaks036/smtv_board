<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<h3>댓글 목록</h3>
<ul>
   <c:forEach items="${commentList}" var="comment">
    <li>
        작성자: ${comment.getComment_ID()}<br>
        작성일: ${comment.getIns_Date_Time()}<br>
        내용: ${comment.getContents()}<br>
        <form action="updateCommentForm.do" method="get">
            <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
            <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
            <input type="submit" value="수정">
        </form>
        <form action="deleteComment.do" method="post">
            <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
            <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
            <input type="submit" value="삭제하기">
        </form>
    </li>
	</c:forEach>
</ul>

 
<input type="button" value="삭제하기" onclick="location.href='delete.do?board_ID=${board.getBoard_ID()}';">
<input type="button" value="수정" onclick="location.href='updateBoardForm.do?board_ID=${board.getBoard_ID()}';">
<input type="button" value="댓글" onclick="">
</body>
</html>

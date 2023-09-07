<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Comment</title>
</head>
<body>
    <h3>댓글 작성</h3>
    
    <form action="writeCommentForm.do" method="post">
        <input type="hidden" name="board_ID" value="${param.board_ID}">
        <input type="hidden" name="comment_ID" value="${param.comment_ID}">
        <label for="writer">작성자:</label>
        <input type="text" name="writer" required value="${param.comment_ID}"><br>
        <label for="contents">내용:</label>
        <textarea name="contents" rows="4" cols="50" required></textarea><br>
        <input type="submit" value="작성">
    </form>
    
    <br>
    <a href="boardDetail.do?board_ID=${comment.getBoard_ID()}">게시물로 돌아가기</a>
</body>
</html>
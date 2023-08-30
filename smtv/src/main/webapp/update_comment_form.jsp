<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
    <form action="updateComment.do" method="post">
        <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
        <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
        작성자: ${comment.getComment_ID()}<br>
        작성일: ${comment.getIns_Date_Time()}<br>
        내용: <input type="text" name="contents" value="${comment.getContents()}"><br>
        <input type="submit" value="수정">
    </form>
</body>

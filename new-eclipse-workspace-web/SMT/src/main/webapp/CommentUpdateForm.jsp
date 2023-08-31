<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
		<style>
		#container{
		            position: absolute;
		            text-align: center;
		            margin-top: 60px;
		            width: 100%;
		            height: 100%;
		            top: ;
		            left: 0;
		        }
        #container .wrap{ 
            width: 90vw;
            display: inline-block;
            vertical-align: middle;
        }
        a{
            text-decoration: none;
            color: black;
        }
        .table-responsive{
            margin-top: 25px;
            text-align: center;
        }
        table{
            margin-left: auto;
            margin-right: auto;
        }
        thead, tbody{
            text-align: center;
        }
        </style>
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
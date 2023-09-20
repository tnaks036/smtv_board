<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>	
<link rel="stylesheet" type="text/css" href="css/Login_Join.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="UTF-8">
    <title>User Login</title>
</head>
<body>
    <h1>로그인</h1>
    <form name="myForm" method="post" action="/LoginServlet"> <!-- action을 서블릿 URL로 설정 -->
        <label for="userID">아이디:</label>
        <input type="text" id="userID" name="user_ID" required><br><br>
        <label for="userPW">패스워드:</label>
        <input type="password" id="userPW" name="user_PW" required><br><br>
        <input type="submit" value="로그인"> <!-- type을 submit으로 변경 -->
    </form>
    
    <h1>회원가입</h1>
    <button onclick="window.location.href='/SignUp.jsp'">회원가입</button>
<button onclick="">비밀번호 찾기</button>
</body>
</html>
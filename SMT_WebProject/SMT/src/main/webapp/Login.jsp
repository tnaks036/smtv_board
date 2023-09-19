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
    <center><h1>Login</h1>
    <br/><br/>
    <center>
<form action="" method="post" name="myForm">
 
<table align="center" cellpadding="0" cellspacing="0">
 
<tr height="2"><td colspan="2" bgcolor="#cccccc"></td></tr>
 
<tr height="30">
	<td colspan="2" align="center">
	<b>로그인</b>
	</td>
</tr>
 
<tr height="2"><td colspan="2" bgcolor="#cccccc"></td></tr>
 
<tr height="25">
	<td width="80" bgcolor="#e6e4e6" align="center">아이디</td>
	<td width="120" style="padding-left: 5px;">
	<input type="text" name="userID" maxlength="10" size="15"/>
	</td>
</tr>
 
<tr height="25">
	<td width="80" bgcolor="#e6e4e6" align="center">패스워드</td>
	<td width="120" style="padding-left: 5px;">
	<input type="password" name="userPW" maxlength="10" size="15"/>
	</td>
</tr>
 
<tr height="2"><td colspan="2" bgcolor="#cccccc"></td></tr>
 
<tr height="30">
	<td colspan="2" align="center">
	<input type="button" value="로그인" class="btn2" onclick="login();">
	<input type="button" value="취소" class="btm2" 
	onclick="javascript:location.href=/'">
	<input type="button" value=" 회원가입 " class="btm2" 
	onclick="javascript:location.href=/join/created.do';">
	</td>
</tr>
 
<tr height="30">
	<td colspan="2" align="center">
	<font color="red"><b>${message }</b></font>  <!-- el로 받았으니까 서블릿에서 받아올것이다 -->
	<font color="red"><b>${message2 }</b></font>
	<font color="red"><b>${message3 }</b></font>
	</td>
</tr>
 
<!-- 비밀번호 찾기 만들기 -->
<tr height="2" ><td colspan="2"  bgcolor="#cccccc"></td></tr>
 
<tr height="30">
	<td colspan="2" align="center">
	<a href="javascript:location.href=/join/searchpw.do';">비밀번호 찾기</a>
	</td>
</tr>
 
<tr height="2"><td colspan="2" bgcolor="#cccccc"></td></tr>
 
 
</table>
 
</form>

    <script type="text/javascript">
 
	function login() {
		
		var f = document.myForm;
		
		if(!f.userID.value) {
			
			alert("아이디를 입력하세요.");
			f.userId.focus();
			return;
		}
		
		if(!f.userPW.value) {
			
			alert("패스워드를 입력하세요.");
			f.userPwd.focus();
			return;
		}
		
		f.action = "login_ok.do";
		f.submit();
		
	}
 
</script>
</body>
</html>
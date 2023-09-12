<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>로그인 페이지</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="icon" href="${path }/resources/img/favicon.png">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${path }/resources/style/login.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<c:import url="header.jsp" />
<form method="post">
<div class="container" style="width: 700px;">
  <div class="row mb-3 mt-3">
    <div class="">
    <label for="inputEmail3" class="col-4 col-form-label mt-5">아이디</label>
      <input type="text" class="form-control" id="inputEmail3" name="user_id">
    </div>
  </div>
  <div class="row mb-3">
    <div class="">
    <label for="inputPassword3" class="col-4 col-form-label d-inline">비밀번호</label>
      <input type="password" class="form-control d-inline" id="inputPassword3" name="user_pw">
    </div>
  </div>
  <button type="submit" class="btn btn-primary">로그인</button>
  <a href="/join" style="font-size: 13px;">회원가입</a>
  <a href="" style="font-size: 13px;">비밀번호 잊음</a>
</div>
</form>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous">
</script>
</body>
</html>
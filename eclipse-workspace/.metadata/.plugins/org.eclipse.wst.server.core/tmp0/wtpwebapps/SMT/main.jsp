<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!-- JSTL & EL -->
<!DOCTYPE html>
<html>
    <head>
    <title>메인 화면</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"> <!-- 부트스트랩  css -->
    <style>
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
            background-color: #f5f5f5;
        }
        
        #header {
            text-align: center;
            width: 800px;
            height: 150px;
            padding: 60px 0px;
        }
        
        #main {
            float: left;
            width: 800px;
            height: 500px;
            text-align:center;
            vertical-align: middle;
        }
    </style>
    </head>
    <body>
        <jsp:include page="header.jsp" /> <!-- header page 공통적으로 포함 -->
        
        <c:set var="contentPage" value="${param.contentPage}"/> <!-- 파라미터로 전달된 contentPage 받아오기 -->
            <c:if test="${contentPage==null}">
                <jsp:include page="firstView.jsp" /> <!-- 전달된 contentPage가 없다면  -->
            </c:if>
        
        <jsp:include page="${contentPage}"/> <!-- 전달된 contentPage가 있다면 -->      
    </body>
    <!-- 절대경로 이용 --> 
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script> <!-- jQuery -->
    <!-- JAVASCRIPT FILES -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script> <!-- 부트스트랩 js -->
    <!-- etc -->
    <script type="text/javascrpit" src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascrpit" src="${pageContext.request.contextPath}/js/jquery.sticky.js"></script>
    <script type="text/javascrpit" src="${pageContext.request.contextPath}/js/click-scroll.js"></script>
    <script type="text/javascrpit" src="${pageContext.request.contextPath}/js/custom.js"></script>
</html>
 





























<!-- new css file! -->
<!-- <link rel="stylesheet" href="css/custom.css"> -->
 <!-- Required meta tags -->
 
 <!-- 
    <title>Bootstrap 4</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1>게시판 목록</h1>
	<table border="1">
		<col width="50px">
		<col width="100px">
		<col width="200px">
		<col width="100px">
		<col width="50px">
		<col width="50px">
		<tr>
			<th>No</th>
			<th>Name</th>
			<th>Title</th>
			<th>Date</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
	<!-- ForEach 태그로 테이블에 값 가져오기 -->
		<!--<c:forEach var="dto" items="${list }">
			<tr>
				<td>%{dto.bd_no}</td>
				<td>%{dto.bd_name}</td>
				<td>%{dto.bd_title}</td>
				<td>%{dto.bd_date}</td>
				<td><a href="">수정</a></td>
				<td><a href="">삭제</a></td>
			</tr>
			<tr>
				<td colspan="6">
					<input type="button" value="글쓰기" onclick="">
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>-->
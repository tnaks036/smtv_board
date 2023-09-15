<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="Board.do?command=main">게시판</a>
</body>
<script type="text/javascript">
	window.onload = function(){
		location.href = "Board.do?command=main";
	}
</script>
</html>
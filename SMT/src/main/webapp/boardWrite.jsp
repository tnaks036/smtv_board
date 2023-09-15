<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.container{
		width: 70%;
		margin-left: auto;
		margin-right: auto;
		min-width: 1000px;
	}
	h1{
		padding-left: 10px;
	}
	table{
		width: 100%;
		border-collapse: collapse;
		border-top: 2px solid black;
	}
	tr{
		height: 50px;
		border-bottom: 1px solid silver;
	}
	th{
		background-color: #DCDCDC;
	}
	td input{
		width: 100%;
		height: 100%;
		border: none;
	}
	input[type="text"]:focus, input[type="text"]:active{
		outline: none;
		border: none;
	}
	td{
		padding-left: 10px;
	}
	#contentsBox{
		height: 300px;
		padding: 10px;
	}
	#contentsBox textarea{
		width: 95%;
		height: 90%;
		border: none;
		resize: none;
	}
	#buttonArea{
		text-align: right;
		padding: 20px;
	}
	#buttonArea input{
		height: 40px;
		width: 90px;
		border-radius: 8px;
		font-size: 15px;
		font-weight: bold;
		border: none;
	}
	input[type="submit"]{
		background-color: #1a73e8;
		color: white;
	}
	#buttonArea input:hover{
		cursor: pointer;
	}
	
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%@ include file="./fix/header.jsp" %>
		<div id="pageTitle">
			<h1>글쓰기</h1>
		</div>
		<form action="Board.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="insert">
			<table id="boardWriteTable">
				<col width="250px">
				<col width="">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="fileName"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td id="contentsBox"><textarea name="contents"></textarea></td>
				</tr>
			</table>
			<div id="buttonArea">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="go_main()">
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	function go_main(){
		location.href = "Board.do?command=main";
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		display: inline;
		text-align: left;
	}
	table{
		width: 100%;
		border-top: 2px solid gray;
		border-collapse: collapse;
	}
	#boardMainTable td{
		text-align: center;
	}
	#boardMainTable td.boardTitle{
		text-align: left;
		padding-left: 30px;
	}
	.boardClass{
		border-bottom: 1px solid silver;
		height: 50px;
	}
	.boardClass:hover{
		cursor: pointer;
	}
	#pageTitle{
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	thead tr{
		height: 50px;
		background-color: #DCDCDC;
	}
	#writeBtnDiv{
		text-align: right;
		padding-top: 10px;
		padding-right: 15px;
	}
	#writeBtn{
		width: 90px;
		height: 40px;
		font-size: 15px;
		font-weight: bold;
		background-color: #1a73e8;
		color: white;
		border-color: #1a73e8;
		border-radius: 8px;
	}
	.pull-right{
		text-align: center;
		font-size:0;
		margin-top: 20px;
	}
	.pagination {
		display:inline-block;
	}
	.paginate_button{
		float: left;
	}
	.pagination a {
		display:block;
		margin:0 3px;
		float:left;
		border:1px solid #e6e6e6;
		width:28px;
		height:28px;
		line-height:28px;
		text-align:center;
		background-color:#fff;
		font-size:13px;
		color:#999999;
		text-decoration:none;
		border:1px solid #ccc;
	}
	.pagination li.active a {
		background-color: #b6b7b9;
		color:#fff;
		border:1px solid #b6b7b9;
	}
	#searchArea{
		display: inline;
	}
	#searchBox{
		display: inline;
		position: relative;
	}
	#searchBox img{
		position: absolute;
		width: 25px;
		height: 25px;
		top: 0;
		bottom: 0;
		right: 5px;
		margin: auto 0;
	}
	input[name=keyword]{
		width: 200px;
		height: 35px;
		border-radius: 5px;
		border: 1px solid gray;
	}
	#searchButton{
		cursor: pointer;
	}
	.cont-select{
		width: 120px;
		position: relative;
	}
	.btn-select{
		width: 100%;
		padding: 12px; 14px;
		font-size: 12px;
		line-height: 14px;
		background-color: white;
		border: 1px solid #b6b7b9;
		box-sizing: border-box;
		border-radius: 10px;
		cursor: pointer;
		text-align: left;
		background: url("./img/downArrow.png") center right 14px no-repeat;
	}
	.btn-select:hover,.btn-select:focus{
		border: 2px solid #b6b7b9;
	}
	.list-member{
		display: none;
		position: absolute;
		width: 100%;
		top: 49px;
		left: 0;
		border: 1px solid #b6b7b9;
		box-sizing: border-box;
		box-shadow: 4px 4px 14px gray;
		border-radius: 10px;
	}
	.list-member li{
		height: 40px;
		padding: 5px 8px;
		box-sizing: border-box;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<%@ include file="./fix/header.jsp" %>
		<div id="pageTitle">
			<h1>게시판</h1>
			<!-- 검색자리 -->
			<div id="searchArea">
				<article class="cont-select">
					<button class="btn-select">제목</button>
					<ul class="list-member">
						<li>
							<button>제목</button>
						</li>
						<li>
							<button>작성자</button>
						</li>
					</ul>
				</article>
				<div id="searchBox">
					<input name="keyword" type="text">
					<img id="searchButton" alt="검색" src="./img/searchButton.png">
				</div>
			</div>
		</div>
		<div>
			<table id="boardMainTable">
				<col width="15%">
				<col width="">
				<col width="15%">
				<col width="20%">
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Writer</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list }">
						<tr class="boardClass" onclick="go_selectOne('${dto.board_ID}')">
							<td>${dto.board_ID }</td>
							<td class="boardTitle">${dto.title }</td>
							<td>${dto.comment_ID }</td>
							<td>${dto.ins_Date_Time }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div id="writeBtnDiv">
			<a href="Board.do?command=write"><button id="writeBtn">글쓰기</button></a>
		</div>
		
		<form id="actionForm" action="Board.do" method="get">
			<input type="hidden" name="command" value="main">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
		</form>
		
		<div class="pull-right">
			<ul class="pagination">
				<c:if test="${pageMaker.prev }">
					<li class="paginate_button previous">
						<a href="${pageMaker.startPate - 1 }">◀</a>
					</li>
				</c:if>
				
				<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
					<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":"" }">
						<a href="${num }">${num }</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next }">
					<li class="paginate_button next">
						<a href="${pageMaker.endPage + 1 }">▶</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	function go_selectOne(board_ID){
		location.href = "Board.do?command=selectOne&board_ID=" + board_ID;
	}
	
	$(".paginate_button a").on("click", function(e){
		var actionForm = $("#actionForm");
		
		e.preventDefault();
		actionForm.find("input[name=pageNum]").val($(this).attr("href"));
		actionForm.submit();
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	#boardDetailTable{
		width: 100%;
		border-collapse: collapse;
		border-top: 2px solid black;
	}
	#boardDetailTable tr{
		height: 50px;
		border-bottom: 1px solid silver;
	}
	#boardDetailTable th{
		background-color: #DCDCDC;
	}
	#boardDetailTable td{
		padding-left: 10px;
	}
	#boardContentsBox{
		height: 300px;
		padding: 10px;
		vertical-align: top;
	}
	#popupBox {
	    display: none;
	    z-index: 9999;
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
	
	#buttonArea input:hover{
		cursor: pointer;
	}
	#boardUpdateBtn{
		background-color: #1a73e8;
		color: white;
	}
	#boardDeleteBtn{
		background-color: red;
		color: white;
	}
	#answerList{
		width: 100%;
		margin-top: 50px;
	}
	#answerListTable{
		width: 100%;
		border-collapse: collapse;
	}
	#answerListTable tr{
		height: 50px;
	}
	#answerListTable td.answer_ID{
		padding-left: 10px;
	}
	#answerListTable td.contents{
		height: 100px;
		vertical-align: top;
		padding: 10px;
	}
	#answerListTable textarea{
		height: 90%;
		width: 90%;
	}
	.answerIndex{
		border-top: 1px solid silver;
	}
	.menuButtonTD{
		vertical-align: top;
	}
	.menuButton {
		text-align: right;
		padding-right: 5px;
		font-size: 20px;
	}
	.updateTD {
		text-align: right;
		padding-right: 5px;
	}
	input[name=answer_ID]{
		width: 90%;
	}
	.cancelButton{
		cursor: pointer;
		color: gray;
		font-size: 15px;
		text-decoration: underline gray;
	}
	#update_Btn{
		height: 40px;
		width: 80px;
		border-radius: 6px;
		font-size: 13px;
		font-weight: bold;
		border: none;
	}
	.menu{
		padding-left: 10px;
		padding-right: 10px;
		font-size: 25px;
		cursor: pointer;
		color: gray;
	}
	#answerFormTable{
		width: 100%;
		margin-bottom: 150px;
		border-bottom: 1px solid black;
		border-top: 1px solid black;
	}
	#answerFormTable tr{
		height: 50px;
	}
	textarea{
		font-size: 15px;
		resize: none;
	}
	input[type=text]{
		font-size: 15px;
	}
	#answerFormTable textarea{
		width: 95%;
		height: 90%;
	}
	#answerFormTable input[name=answer_ID]{
		width: 95%;
	}
	#answerFormTable td.contents{
		height: 100px;
	}
	#answer_Btn{
		height: 80px;
		width: 80px;
		border-radius: 10px;
		font-size: 15px;
		font-weight: bold;
		border: none;
		cursor: pointer;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%@ include file="./fix/header.jsp" %>
		<div id="pageTitle">
			<h1>글 내용</h1>
		</div>
		<table id="boardDetailTable">
			<col width="250px">
			<col width="">
			<tr>
				<th>작성자</th>
				<td >${dto.comment_ID }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td >${dto.title }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td ><input type="file"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td id="boardContentsBox">${dto.contents }</td>
			</tr>
		</table>
		<div id="buttonArea">
			<input type="button" id="boardUpdateBtn" value="수정" onclick="go_update()">
			<input type="button" id="boardDeleteBtn" value="삭제" onclick="go_delete()">
			<input type="button" value="목록" onclick="go_main()">
		</div>
		
		<hr>
		
		<div id="answerList">
			<table id="answerListTable">
			</table>
		</div>
		
		<div>
			<form name="answerForm" enctype="multipart/form-data">
				<input type="hidden" name="board_ID" value="${dto.board_ID }">
				<input type="hidden" name="comment_ID" value="${dto.comment_ID }">
				<table id="answerFormTable">
					<col width="250px">
					<col width="">
					<col width="150px">
					<tr>
						<th>작성자</th>
						<td><input type="text" name="answer_ID"></td>
						<th rowspan="3">
							<input type="button" value="등록" id="answer_Btn">
						</th>
					</tr>
					<tr>
						<th>내용</th>
						<td class="contents"><textarea name="contents"></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><label for="fileInput">파일 선택</label><input type="file" id="fileInput" name="file_name"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div id="popupBox">
		<table border="1">
			<col width="50px">
			<tr>
				<th id="updateButton">수정</th>
			</tr>
			<tr>
				<th id="deleteButton">삭제</th>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	var menuButton;

	function go_main(){
		location.href = "Board.do?command=main";
	}
	function go_update(){
		location.href = "Board.do?command=updateForm&board_ID=${dto.board_ID}";
	}
	function go_delete(){
		location.href = "Board.do?command=delete&board_ID=${dto.board_ID}";
	}
	function answerList(){
		var data = "";
		var answerListTable = $('#answerListTable');
		
		$.ajax({
			url : "Answer.do?command=selectAnswer&board_ID=${dto.board_ID}",
			method : "GET",
			dataType : "json",
			async : false,
			success : function(result){
				data += "<colgroup><col width='250px'><col width='auto'><col width='60px'></colgroup>";
				for(var i = 0; i < result.length; i++){
					data += "<tbody class='answerIndex'>";
					data += "<tr>";
					data += "<th>작성자";
					data += "<input type='hidden' name='board_ID' value='${dto.board_ID}'>";
					data += "<input type='hidden' name='comment_ID' value='${dto.comment_ID}'>";
					data += "</th>";
					data += "<td class='answer_ID'>" + result[i].answer_ID + "</td>";
					data += "<td class='menuButtonTD'>";
					data += "<div class='menuButton'><a class='menu'>፧</a><a class='cancelButton'></a></div>";
					data += "</td>";
					data += "</tr>";
					data += "<tr>";
					data += "<th>내용</th>";
					data += "<td colspan='2' class=' contents'>" + result[i].contents + "</td>";
					data += "</tr>";
					data += "</tbody>";
				}
				answerListTable.html(data);
				$('form[name=answerForm]').find('input[name=answer_ID]').val("");
				$('form[name=answerForm]').find('textarea[name=contents]').val("");
			},
			error : function(){
				alert("실패");
			}
		});
	}
	
	$('#answerListTable').on('click', '.menu', function(){
		var element = $(this);
		var popupBox = $('#popupBox');
		
		var offset = element.offset();
		
		popupBox.css({
			'position': 'absolute',
			'left': offset.left + element.width() + 20 + 'px',
			'top': offset.top + 'px'
		});
		
		popupBox.toggle();
		menuButton = $('.answerIndex').index($(this).closest('.answerIndex'));
	});
	
	$('#updateButton').on('click', function(){
		answerList();
		
		var answerListTable = $('#answerListTable');
		var data = "";
		data += "<tr>";
		data += "<td colspan='3' class='updateTD'></td>";
		data += "</tr>";

		var index = menuButton;
		
		var popupBox = $('#popupBox');
		
		var answer_ID = $('.answerIndex').eq(index).find('.answer_ID').text();
		var contents = $('.answerIndex').eq(index).find('.contents').text();
		
		var answer_ID_html = "<input type='text' name='answer_ID' value='" + answer_ID + "'>";
		var contents_html = "<textarea name='contents'>" + contents + "</textarea>";
		var updateTD_html = "<input type='button' value='등록' id='update_Btn'>";
		var cancelButton = "취소";
		
		answerListTable.append(data);
		$('.answerIndex').eq(index).find('.answer_ID').html(answer_ID_html);
		$('.answerIndex').eq(index).find('.contents').html(contents_html);
		$('.answerIndex').eq(index).find('.updateTD').html(updateTD_html);
		
		popupBox.toggle();
		$('.answerIndex').eq(index).find('.cancelButton').text(cancelButton);
		$('.answerIndex').eq(index).find('.menu').text("");
	});
	
	$('#deleteButton').on('click', function(){
		var index = menuButton;
		var popupBox = $('#popupBox');
		
		var board_ID = $('.answerIndex').eq(index).find('input[name=board_ID]').val();
		var comment_ID = $('.answerIndex').eq(index).find('input[name=comment_ID]').val();
		
		var jsonData = {
			board_ID: board_ID,
			comment_ID: comment_ID
		};
		
		$.ajax({
			url : "Answer.do?command=delete",
			type : "POST",
			dataType : "json",
			data : jsonData,
			success : function(result){
				answerList();
			},
			error : function(){
				alert("실패");
			}
		});
		
		popupBox.toggle();
	});
	
	$('#answerListTable').on('click', '.cancelButton', function(){
		answerList();
	});
	
	$('#answerListTable').on('click', '#update_Btn', function(){
		var board_ID = $(this).closest('.answerIndex').find('input[name=board_ID]').val()
		var comment_ID = $(this).closest('.answerIndex').find('input[name=comment_ID]').val();
		var answer_ID = $(this).closest('.answerIndex').find('input[name=answer_ID]').val();
		var contents = $(this).closest('.answerIndex').find('textarea[name=contents]').val();
		
		var jsonData = {
			board_ID: board_ID,
			comment_ID: comment_ID,
			answer_ID: answer_ID,
			contents: contents
		};
		
		$.ajax({
			url : "Answer.do?command=update",
			type : "POST",
			dataType : "json",
			data : jsonData,
			success : function(result){
				answerList();
			},
			error : function(){
				alert("실패");
			}
		});
	});
	
	$(document).ready(function(){
		answerList();
	});
	
	$('#answer_Btn').on('click', function(){
		/* var answerForm = new FormData($("form[name=answerForm]")[0]); */
		
		var answerForm = $("form[name=answerForm]")[0];
		var formData = new FormData(answerForm);
		
		$.ajax({
			url : "Answer.do?command=insert",
			type : "POST",
			dataType : "json",
			data : formData,
			processData: false,
	        contentType: false,
			success : function(result){
				answerList();
			},
			error : function(){
				alert("실패");
			}
		});
	});
	
	
	
</script>
</html>
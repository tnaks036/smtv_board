<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/BoardList.css">
<meta charset="UTF-8">
<title>SMT 게시판</title>
</head>
<body>
<%@ include file = "../../../header.jsp" %>
<section class="board">

	<h3>등록하기</h3>

	<form id="frm" action="insertBoard" method="POST" encType = "multipart/form-data">
		<input type="text" name="comment_ID" class="form-control mt-4 mb-2" required>
		<input type="text" name="title" class="form-control mt-4 mb-2"
			placeholder="제목을 입력해주세요." required >
		<div class="form-group">
			<textarea class="form-control" rows="10" name="content"
				placeholder="내용을 입력해주세요" required ></textarea>
		</div>
			<input type="file" name="file_Name" accept="image/*">
		<button type="submit" class="btn btn-secondary mb-3" onClick="checkBoard();return = false;">등록하기</button>
	</form>
</section>
<script>
	function checkBoard(){
		var f = document.frm;
		
		if(f.comment_ID.val() == ""){
			alert("작성자를 입력해주세요.");
			f.comment_ID.focus();
			return false;
		}
		
		if(f.title.val() == ""){
			alert("제목을 입력해주세요.");
			f.title.focus();
			return false;
		}
		
		if(f.comment_ID.val() == ""){
			alert("내용을 입력해주세요.");
			f.comment_ID.focus();
			return false;
		}
		
		f.submit();
	}
</script>
</body>
</html>
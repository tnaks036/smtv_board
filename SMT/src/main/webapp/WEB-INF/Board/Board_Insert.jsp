<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/BoardList.css">
<link rel="stylesheet" href="css/BoardInsert.css">
<script src="/smartEditor/js/HuskyEZCreator.js" data-cfasync="false" charset="utf-8"></script>
<meta charset="UTF-8">
<title>SMT 게시판</title>
</head>
<body>
<%@ include file = "../../../header.jsp" %>
<section class="board">

<h2 class="boardInfoTitle"><span>문의하기</span></h2>

	<form id="frm" action="insertBoard" method="POST" encType = "multipart/form-data">
		<div id="insBoardBox">
			<div>
				<span>작성자</span><input type="text" name="comment_ID" class="form-control" placeholder="작성자명" required>
			</div>
			<div>
				<span>제목</span><input type="text" name="title" class="form-control"
					placeholder="제목을 입력해주세요." required >
			</div>
			<div>
				<span>문의<br>내용</span>
					<textarea id="quesContents" class="form-control" rows="10" name="content"
					placeholder="내용을 입력해주세요" required >
					</textarea>
					<img id="previewImg" style="height:auto; max-width:150px;">
					<label for="imageInput">
						<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#21252978" class="bi bi-paperclip" viewBox="0 0 16 16">
						  <path d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z"/>
						</svg>
					</label>
					<input type="file" id="imageInput" name="file_Name" onChange="previewImage(event)" accept="image/*" style="display:none;">
			</div>
			
		</div>
			<button type="submit" class="btn" id="insDBBtn" onClick="checkBoard();return = false;">등록</button>
		</form>
</section>

<script>
	function checkBoard(){
		if(confirm("해당 문의를 등록하시겠습니까?")){
			
			var f = document.frm;
			
			if(f.comment_ID.val().replace(" ","") == ""){
				alert("작성자를 입력해주세요.");
				f.comment_ID.focus();
				return false;
			}
			
			if(f.title.val().replace(" ","") == ""){
				alert("제목을 입력해주세요.");
				f.title.focus();
				return false;
			}
			
			if(f.comment_ID.val().replace(" ","") == ""){
				alert("내용을 입력해주세요.");
				f.comment_ID.focus();
				return false;
			}
			
			f.submit();
		}
	}
	
	function previewImage(event) {
		  var input = event.target;
		  if (input.files && input.files[0]) {
		    var reader = new FileReader();

		    reader.onload = function(e) {
		      var previewElement = document.getElementById('previewImg');
		      previewElement.src = e.target.result;
		    };

		    reader.readAsDataURL(input.files[0]);
		  }
		}
</script>
</body>
</html>
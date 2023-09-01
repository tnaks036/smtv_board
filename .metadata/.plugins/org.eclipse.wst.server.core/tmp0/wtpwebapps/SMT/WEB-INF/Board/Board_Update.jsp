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

	<h3>수정하기</h3>

	<form action="updateBoard" method="post" encType = "multipart/form-data">
		<input type="hidden" name="board_ID" value="${list.board_ID}" readonly>
		<input type="text" name="title" class="form-control mt-4 mb-2"
			placeholder="제목을 입력해주세요." value="${list.title}" required >
		<div class="form-group">
			<textarea class="form-control" rows="10" name="contents"
				placeholder="내용을 입력해주세요" required >${list.contents}</textarea>
		</div>
			<input type="file" name="file_Name" accept="image/*">
		<button type="submit" class="btn btn-secondary mb-3">등록하기</button>
	</form>
</section>
</body>
</html>
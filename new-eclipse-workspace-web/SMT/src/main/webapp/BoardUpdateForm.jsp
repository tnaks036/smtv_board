<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지Form to edit a post on a bulletin board</title>

	<style type="text/css">
		/*
		.container{
			margin-top: 100px;
		}*/
		.input-group{
			margin-bottom: 5px;
		}
		#btn{
			margin-top: 20px;
		}
	</style>
	
	<script type="text/javascript">
		function checkValue() {
			/* form 태그 가져오기 */
			const form = document.boardForm;
			/* form 태그의 boardSubject, boardContent의 value 가져오기 */
			const subject = form.boardSubject.value;
			const content = form.boardContent.value;
			const comment_ID = form.boardComment.value;
			
			/* 입력이 되지 않았다면 */
			if(!subject) {
				alert("제목을 입력해주세요.");
				return false;
			} 
			else if(!Comment_ID){
				alert("작성자를 입력해주세요.")
				return false;
			}
			else if(!content) {
				alert("내용을 입력해주세요.");
				return false;
			}
		}
		
		function goToList() {
			location.href = "BoardListAction.bo?page=${page}";
		}
		</script>
        <link href="css/navbar-top-fixed.css" rel="stylesheet">
    
    <!-- CSS FILES -->
    <!-- CSS FILES -->        
        <link rel="preconnect" href="https://fonts.googleapis.com">
        
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500;600;700&family=Open+Sans&display=swap" rel="stylesheet">
                        
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/bootstrap-icons.css" rel="stylesheet">

        <link href="css/templatemo-topic-listing.css" rel="stylesheet">    
        <!-- TemplateMo 590 topic listing https://templatemo.com/tm-590-topic-listing -->
</head>
<body>
	<div class="container">
		<%-- Assuming boardToUpdate is a BoardDTO object --%>
		<c:if test="${not empty boardToUpdate}">
		    <!-- Display the post information here -->
		    <p>${boardToUpdate.title}</p>
		    <p>${boardToUpdate.content}</p>
		    <!-- and so on... -->
		</c:if>
	
			<form method="post" action="updateBoard.do?page=${page}" name="boardForm" enctype="multipart/form-data" onsubmit="return checkValue()">
			<input type="hidden" name="boardNum" value="${board.getBoard_ID() }">
				<!-- 제목  -->
				<div class="input-group">
					<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
					<input name="boardSubject" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" 
					value="${board.getTitle()}"/>
				</div>
				<!-- 첨부파일 -->
				<div class="input-group mb-3">
					<input name="newBoardFile" type="file" class="form-control" id="inputGroupFile02">
					<label class="input-group-text" for="inputGroupFile02">Upload</label>
				</div>
				<!-- 내용 -->
				<div class="input-group">
					<span class="input-group-text">내용</span>
					<textarea name="boardContent" class="form-control" aria-label="With textarea" rows="20" cols="50">${board.getContents()}</textarea>
				</div>
				<!-- 작성일  -->
				<div class="input-group">
					<span class="input-group-text" id="inputGroup-sizing-default">작성일</span>
					<input name="boardDate" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" 
					value="${board.geIns_Date_Time()}" readonly/>
				</div>		
				<!-- 등록/취소 버튼 -->
				<div id="btn" class="d-grid gap-2 d-sm-flex justify-content-sm-center">
					<input type="submit" class="btn btn-primary btn-sm px-3 gap-3" value="등록"></button>
					<input type="button" class="btn btn-outline-secondary btn-sm px-3" value="취소" onclick="goToList()"></input>
					<!-- <input type="submit" value="글수정"> -->					
				</div>
			</form>
		</div>		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지 Posting form for bulletin boards</title>
<style type="text/css">

		#btn{
			margin-top: 20px;
		}
	</style>
	<script type="text/javascript">
		function checkValue() {
			/* boardForm 태그 선택 */
			const form = document.boardForm;
			/* boardForm의 boardSubject, boardContent 값 가져오기 */
			const subject = form.boardSubject.value;
			const content = form.boardContent.value;
			
			/* 값이 없으면 */
			if(!subject) 
			{
				alert("제목을 입력해주세요.");
				return false;
			} 
			else if(!content) 
			{
				alert("내용을 입력해주세요.");
				return false;
			}
		}
		
		/* 취소 버튼 클릭 시 */
		function goToList() {
			/* 게시판 목록으로 돌아가기  */
			location.href = "BoardListAction.bo";
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
        <!--
TemplateMo 590 topic listing
https://templatemo.com/tm-590-topic-listing
        -->
</head>
<body>
	<div class="container">
			<form method="post" action="BoardWriteAction.bo" name="boardForm" enctype="multipart/form-data" onsubmit="return checkValue()">
				<!-- memberID 파라미터 전달 용 숨은 input태그 , hidden xxxxx-->
				<!-- <input type="hidden" name="boardID" value="${sessionScope.memberID}">  -->
				<!-- 제목 -->
				<div class="input-group">
					<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
					<input name="boardSubject" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
				</div>
				<!-- 첨부파일 -->
				<div class="input-group mb-3">
					<input name="boardFile" type="file" class="form-control" id="inputGroupFile02">
					<label class="input-group-text" for="inputGroupFile02">Upload</label>
				</div>
				<!-- 내용 -->
				<div class="input-group">
					<span class="input-group-text">내용</span>
					<textarea name="boardContent" class="form-control" aria-label="With textarea" rows="20"></textarea>
				</div>
				<!-- 등록/취소 버튼  -->
				<div id="btn" class="d-grid gap-2 d-sm-flex justify-content-sm-center">
					<input type="submit" class="btn btn-primary btn-sm px-3 gap-3" value="등록"></button>
					<input type="button" class="btn btn-outline-secondary btn-sm px-3" value="취소" onclick="goToList()"></input>
				</div>
			</form>
		</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.Ins_Date_Time}</title> <!-- 글 상세보기 : 글 제목, 글 작성 날짜, 첨부 파일, 글 내용을 출력  -->
<style type="text/css">
		.container{
			margin-top: 100px;
		}
		.input-group{
			margin-bottom: 5px;
		}
		#btn{
			margin-top: 20px;
		}
		#subject, #content{
			background-color: white;
		}
	</style>
	<script type="text/javascript">
		function detailAction(value) {
			if(value == 0)
			{
				location.href = "BoardUpdateFormAction.bo?num=${board.Board_ID}&page=${page}";
			} 
			else if(value == 1) 
			{
				if(confirm("정말로 삭제하시겠습니까?"))
				{
					location.href="BoardDeleteAction.bo?num=${board.Board_ID}";
					return true;
				} 
				else 
				{
					return false;
				}
			} 
			else if(value == 2) 
			{
				location.href = "BoardListAction.bo?page=${page}";
			}
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
		<input type="hidden" name="Board_ID" value="${sessionScope.Board_ID}">
		<!-- 제목, 작성날짜  -->
		<div class="input-group">
			<input id="subject" name="Title" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${board.Title}" readonly/>
			<span class="input-group-text" id="basic-addon2">${board.Ins_Date_Time}</span>			
		</div>
		<!-- 첨부파일 -->
		<div class="input-group mb-3">
			<span type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
				<a href="BoardDownloadAction.bo?boardFile=${board.File_Name}">${board.File_Name}</a>
			</span>
			<label class="input-group-text" for="inputGroupFile02">첨부파일</label>
		</div>
		<!-- 내용 -->
		<div class="input-group">
			<textarea id="content" name="boardContent" class="form-control" aria-label="With textarea" rows="20" readonly>${board.Contents}</textarea>
		</div>
		<!-- 로그인 정보와 boardID 값이 일치하면 수정/삭제 버튼 출력  
		-> 필요없음 
		<c:if test="${sessionScope.memberID!=null}">
				<c:if test="${sessionScope.memberID == board.boardID}">
					<button type="button" class="btn btn-primary btn-sm px-3 gap-3" onclick="detailAction(0)">수정</button>
					<button type="button" class="btn btn-outline-secondary btn-sm px-3" onclick="detailAction(1)">삭제</button>
				</c:if>
			</c:if>
		-->
		<div id="btn" class="d-grid gap-2 d-sm-flex justify-content-sm-center">
			<c:if test="${sessionScope.Comment_ID!=null}">
				<c:if test="${sessionScope.Answer_ID == board.Answer_ID">
					<button type="button" class="btn btn-primary btn-sm px-3 gap-3" onclick="detailAction(0)">수정</button>
					<button type="button" class="btn btn-outline-secondary btn-sm px-3" onclick="detailAction(1)">삭제</button>
				</c:if>
			</c:if>
			<!-- session 에 저장된 member_ID == Answer_ID 값과 상관 없이 '목록'버튼 추력 -->
			<button type="button" class="btn btn-outline-secondary btn-sm px-3" onclick="detailAction(2)">목록</button>
		</div>
	</div>
	</body>
</html>
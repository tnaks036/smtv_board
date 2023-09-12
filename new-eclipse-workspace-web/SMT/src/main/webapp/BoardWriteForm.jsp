<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지 Posting form for bulletin boards</title>
<style type="text/css">

		.container{
			margin-top: px;
			text-align :center;
			width: 1200px;
		}
		#btn{
			margin-top: 300px;
		}
</style>
        <link href="css/navbar-top-fixed.css" rel="stylesheet">    
    <!-- CSS FILES -->        
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
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
    <form action="regBoard.do" method="post" name="boardForm"> <!-- enctype="multipart/form-data" onsubmit="return checkValue()"> 이부분 삭제 -->
        <!-- memberID 파라미터 전달 용 숨은 input 태그, hidden xxxxx-->
        <!-- <input type="hidden" name="boardID" value="${sessionScope.memberID}"> -->
        <table width=100%"">
            <tr>
                <td></td>
                <td>
                    <div class="input-group">
                        <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
                        <input name="title" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="input-group">
                        <span class="input-group-text" id="inputGroup-sizing-default">작성자</span>
                        <input name="comment_ID" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="">
                    </div>
                </td>
            </tr>           
            <tr>
            	<td></td>
            	<td>
            		<div class="input-group">
                        <span class="input-group-text" id="inputGroup-sizing-default">작성날짜</span>
                        <input name="ins_Date_Time" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
            	</td>                     
            </tr>                               
            <tr>
                <td></td>
                <td>
                    <div class="input-group mb-3">
                        <input name="file_name" type="file" class="form-control" id="inputGroupFile02">
                        <label class="input-group-text" for="inputGroupFile02">Upload</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="input-group">
                        <span class="input-group-text">내용</span>
                        <textarea name="contents" class="form-control" aria-label="With textarea" rows="10"></textarea>
                    </div>
                </td>
            </tr>
        </table>
        <!-- 등록/취소 버튼  -->
        <div id="btn" class="d-grid gap-2 d-sm-flex justify-content-sm-center">
            <input type="submit" class="btn btn-primary btn-sm px-3 gap-3" value="등록">
            <input type="button" class="btn btn-outline-secondary btn-sm px-3" value="취소" onclick="goToList()">
        </div>
    </form>
</div>
<script type="text/javascript">
    function checkValue() {
        /* boardForm 태그 선택 */
        const form = document.boardForm;
        /* boardForm의 boardSubject, boardContent 값 가져오기 */
        const title = form.title.value;
        const comment_ID = form.comment_ID.value;
        const contents = form.contents.value;

        /* 값이 없으면 */
        if (!title) {
            alert("제목을 입력해주세요.");
            return false;
        } else if (!comment_ID) {
            alert("작성자를 입력해주세요.");
            return false;
        } else if (!contents) {
            alert("내용을 입력해주세요.");
            return false;
        }
    }

    /* 취소 버튼 클릭 시 */
    function goToList() {
        /* 게시판 목록으로 돌아가기 */
        location.href = "boardList.do";
    }
</script>
</body>
</html>
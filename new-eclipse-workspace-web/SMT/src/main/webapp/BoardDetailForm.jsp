<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.getTitle()}</title> <!-- 글 상세보기 : 글 제목, 글 작성 날짜, 첨부 파일, 글 내용을 출력  -->
<style type="text/css">
		.container{
			margin-top: 100px;
			
		}
		.input-group{
			margin-bottom: 5px;
		}
		#btn{
			margin-top: 20px;
			text-align :center;
		}
		#subject, #content{
			background-color: white;
		}
		/*comment! */
		#wrap {
            width: 1200px;
            margin: 0 auto 0 auto;    
        }
    
        #detailBoard{
            text-align :center;
        }
        #title{
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
            background-color: #F7F7F7;
        }
		 .custom-table {
		  border-collapse: collapse; /* Merge adjacent borders into a single border */
		  border: 1px solid lightgray; /* Set border style and color */
		}
		/* comment css style */
		#root {
		  width: 100%;
		  margin: 0 auto;
		  max-width: 1200px;
		}
		
		/* 입력 폼 */
		.form {
		  display: flex;
		  flex-direction: column;
		}
		.form textarea {
		  resize: none;
		  border: 1px solid #dbdbdb;
		  padding: 15px 20px;
		  outline: none;
		  color: #717275;
		}
		.form .submit {
	
		  padding: 5px;
		  cursor: pointer;
		  display: -ms-flexbox;
			  align-items: center;
			  padding: 0.375rem 0.75rem;
			  margin-bottom: 0;
			  font-size: 1rem;
			  font-weight: 400;
			  line-height: 1.5;
			  color: #495057;
			  text-align: center;
			  white-space: nowrap;
			  background-color: #e9ecef;
			  border: 1px solid #ced4da;
			  border-radius: 0.25rem;
			  
		}
		/*
		.form .submit {
		  border: 1px solid #8f8f8f;
		  background-color: #8f8f8f;
		  color: #f0f8ff;
		  padding: 5px;
		  cursor: pointer;
		  display: -ms-flexbox;
		  */
		
		/* 레이아웃 - 댓글 */
		.comments {
		  border: 1px solid #dbdbdb;
		}
		.comments .comment {
		  border-bottom: 1px solid #dbdbdb;
		  padding: 20px;
		  width: 90%; 
  		  margin: 0 auto; 
		}
		.comments .comment:last-child {
		  border-bottom: none;
		}
		
		/* 레이아웃 - 답글 */
		.replies {	
		  position: relative;
		  background-color: ;
		  padding-left: 40px;
		  padding-right: 20px;
		  padding-bottom: 20px;
		  	  
		}
		.replies:after {
		  content: "";
		  position: absolute;
		  left: 0;
		  top: 0;
		  width: 4px;
		  height: 100%;
		  background-color: #ddd;
		}
		.replies .reply {
		  padding: 20px 0;
		  border-bottom: 1px solid #dbdbdb;
		}
		.replies .reply:last-of-type {
		  border-bottom: none;
		}
		
		/* 상단 메뉴 */
		.top {
		  display: flex;
		  flex-direction: row;
		  align-items: center;
		}
		.top .username {
		  font-weight: bold;
		  color: #495057;
		}
		.top .utility {
		  display: flex;
		  flex-direction: row;
		  margin-left: auto;
		}
		
		/* 하단 메뉴 */
		.bottom {
		  
		  display: flex;
		  flex-direction: row;
		  align-items: center;
		  list-style: none;
		  padding: 0;
		  margin: 0;
		  text-transform: uppercase;
		  letter-spacing: -0.5px;
		  font-weight: bold;
		  font-size: 14px;
		}
		.bottom .divider {
		  width: 1px;
		  height: 20px;
		  background-color: #dbdbdb;
		  margin: 0 20px;
		}
		.bottom .menu {
		  margin: 0;
		  padding: 0;
		  color: #bebebe;
		}
		.bottom .menu.show-reply {
		  color: #333;
		}
		
	</style>
	
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
        
        <script type="text/javascript">
		function detailAction(value) {
			if(value == 0)
			{
				location.href = "updateBoard.do?num=${board.getBoard_ID()}&page=${page}";
			} 
			else if(value == 1) 
			{
				if(confirm("정말로 삭제하시겠습니까?"))
				{
					location.href="delete.do?num=${board.getBoard_ID()}";
					return true;
				} 
				else 
				{
					return false;
				}
			} 
			else if(value == 2) 
			{
				location.href = "boardDetail.do?page=${page}";
			}
	}
	</script>
</head>
<body>
		<div class="container">
		<div class="input-group">
			<input id="subject" name="boardSubject" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${board.getTitle()}" readonly/>
			<span class="input-group-text" id="basic-addon2">${board.getIns_Date_Time()}</span>
		</div>			
		<!-- 첨부파일 -->
		<div class="input-group mb-3">
			<span type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
				<a href="BoardDownloadAction.bo?boardFile=${board.getFile_Name()}">${board.getFile_Name()}</a>
			</span>
			<label class="input-group-text" for="inputGroupFile02">첨부파일</label>
		</div>
		<!-- 내용 -->
		<div class="input-group">
			<textarea id="content" name="boardContent" class="form-control" aria-label="With textarea" rows="20" readonly>${board.getContents()}</textarea>
		</div>
		<!-- 
		글번호 : ${board.getBoard_ID()}<br>
		제목 : ${board.getTitle()} <br>
		내용 : ${board.getContents()}<br>
		작성일 : ${board.getIns_Date_Time()}<br>  -->
		</div>
 		<!-- comment style -->
 		<div class="container">
		 	<div id="root">
			    <form class="form comment-form">
			      <textarea placeholder="Comment"></textarea>
			      <button type="button" class="submit">등록하기</button>
			    </form>
		    <div class="comments">
		      <c:forEach items="${commentList}" var="comment">
		        <div class="comment">
		          <div class="content">
		            <header class="top">
		              <div class="username">${comment.getComment_ID()}</div>
		              <div class="utility">
		                <form action="updateCommentForm.do" method="get">
		                  <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
		                  <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
		                  <button class="input-group-text" for="inputGroupFile02">수정</button>
		                </form>
						 &nbsp; &nbsp;
		                <form action="deleteComment.do" method="post">
		                  <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
		                  <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
		                  <button class="input-group-text" for="inputGroupFile02">삭제</button>
		                </form>
		              </div>
		            </header>
		            <p>${comment.getContents()}</p>
		            <ul class="bottom">
		              <li class="menu time">${comment.getIns_Date_Time()}</li>
		            </ul>
		          </div>
		        </div>
		      </c:forEach>
		      <div class="replies">
		        <!-- You can add replies here if needed -->
		      </div>
		    </div>
		  </div>
		</div>						 	

<!-- old comment <div class="container">
		<div class="input-group">
			<span class="input-group-text" id="basic-addon2">댓글 목록</span>
		</div>
		<ul>
		   <c:forEach items="${commentList}" var="comment">
		    <li>
		        작성자: ${comment.getComment_ID()}<br>
		        작성일: ${comment.getIns_Date_Time()}<br>
		        내용: ${comment.getContents()}<br>
		        <form action="updateCommentForm.do" method="get">
		            <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
		            <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
		            <input type="submit" value="수정">
		        </form>
		        <form action="deleteComment.do" method="post">
		            <input type="hidden" name="board_ID" value="${comment.getBoard_ID()}">
		            <input type="hidden" name="comment_ID" value="${comment.getComment_ID()}">
		            <input type="submit" value="삭제하기">
		        </form>
		    </li>
			</c:forEach>
		</ul>
		</div> 
		<input type="button" value="삭제하기" onclick="location.href='delete.do?board_ID=${board.getBoard_ID()}';">
		<input type="button" value="수정" onclick="location.href='updateBoardForm.do?board_ID=${board.getBoard_ID()}';">
		<input type="button" value="댓글" onclick="">
		-->
</body>
</html>
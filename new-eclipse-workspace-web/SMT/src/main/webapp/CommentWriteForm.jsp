<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Write Comment</title>
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
		/* Style the button to look like a link */
	  .link-button {
	    background: none;
	    border: none;
	    color: blue;
	    text-decoration: underline;
	    cursor: pointer;
	  }
	
	  /* Remove the default button styles */
	  .link-button:focus {
	    outline: none;
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
</head>
<body>
    <h3>댓글 작성</h3>
    
    <form action="writeCommentForm.do" method="post">
        <input type="hidden" name="board_ID" value="${param.board_ID}">
        <input type="hidden" name="comment_ID" value="${param.comment_ID}">

        <div class="input-group">
                        <span class="input-group-text" id="inputGroup-sizing-default">작성날짜</span>
                        <input name="insDateTime" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>                                                    	                        
        <label for="writer">작성자:</label>
        <input type="text" name="writer" required value="${param.comment_ID}"><br>
        <label for="contents">내용:</label>
        <textarea name="contents" rows="4" cols="50" required></textarea><br>
        <input type="submit" value="작성">
    </form>
    
    <br>
    <!-- original 
        <a href="boardDetail.do?board_ID=${param.board_ID}">게시물로 돌아가기</a>
     -->   
    <div class="utility d-flex justify-content-center">
    	<form action="boardDetail.do" method="get">
    		<input type="hidden" name="board_ID" value="${param.board_ID}">
    		<button class="input-group-text">게시글로 돌아가기</button>
		</form>	
	</div>
</body>
</html>
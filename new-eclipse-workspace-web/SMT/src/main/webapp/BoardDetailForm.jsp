<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			text-align :center;
		}
		#subject, #content{
			background-color: white;
		}
		/*comment! */
		#wrap {
            width: 800px;
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
		
	</style>
	<script type="text/javascript">
		function detailAction(value) { /*function changeView(value)  */
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
		
		var httpReuqest = null;
		// httpRequest 객체 생성
	    function getXMLHttpRequest(){
	        var httpRequest = null;
	    
	        if(window.ActiveXObject){
	            try{
	                httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
	            } catch(e) {
	                try{
	                    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	                } catch (e2) { httpRequest = null; }
	            }
	        }
	        else if(window.XMLHttpRequest){
	            httpRequest = new window.XMLHttpRequest();
	        }
	        return httpRequest;    
	    }
	    
	    // 댓글 등록
	    function writeCmt()
	    {
	        var form = document.getElementById("writeCommentForm");
	        
	        var board = form.comment_board.value
	        var id = form.comment_id.value
	        var content = form.comment_content.value;
	        
	        if(!content)
	        {
	            alert("내용을 입력하세요.");
	            return false;
	        }
	        else
	        {    
	            var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
	                
	            httpRequest = getXMLHttpRequest();
	            httpRequest.onreadystatechange = checkFunc;
	            httpRequest.open("POST", "CommentWriteAction.co", true);    
	            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
	            httpRequest.send(param);
	        }
	    }
	    
	    function checkFunc(){
	        if(httpRequest.readyState == 4){
	            // 결과값을 가져온다.
	            var resultText = httpRequest.responseText;
	            if(resultText == 1){ 
	                document.location.reload(); // 상세보기 창 새로고침
	            }
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
			<c:if test="${board.Comment_ID!=null}">
				<!--<c:if test="${board.Answer_ID == board.Answer_ID"> 아이디가 login한 seesion id와 같다면-->
					<button type="button" class="btn btn-primary btn-sm px-3 gap-3" onclick="detailAction(0)">수정</button>
					<button type="button" class="btn btn-outline-secondary btn-sm px-3" onclick="detailAction(1)">삭제</button>
				<!--</c:if>-->
					<input type="button" value="답글" onclick="changeView(1)" >
			</c:if>
			<!-- session 에 저장된 member_ID == Answer_ID 값과 상관 없이 '목록'버튼 추력 -->
			<button type="button" class="btn btn-outline-secondary btn-sm px-3" onclick="detailAction(2)">목록</button>
		</div>
	</div>
	<br><br>
	<!-- 댓글 부분 -->
    <div id="comment">
        <div class ="custom-table">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
        <c:forEach var="comment" items="${requestScope.commentList}">
            <tr>
                <!-- 아이디, 작성날짜 -->
                <td width="150">
                    <div>
                        ${comment.Comment_ID}<br>
                        <font size="2" color="lightgray">${comment.Ins_Date_Time}</font>
                    </div>
                </td>
                <!-- 본문내용 -->
                <td width="550">
                    <div class="text_wrapper">
                        ${comment.Contents}
                    </div>
                </td>             
            </tr>
        </c:forEach>
    	</c:if>    
   	</div>       
  	<!-- 로그인 했을 경우만 댓글 작성가능  -->      
     <c:if test="${comment.Comment_ID !=null}">
            <tr bgcolor="#F5F5F5">
            <form id="writeCommentForm">
                <input type="hidden" name="comment_board" value="${board.board_ID}">
                <input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
                <!-- 아이디            
                <td width="150">
                
                    <div>
                        ${sessionScope.sessionID}
                    </div>
                </td>
                -->
                <td width="150">
                    <div>
                        ${comment.Comment_ID}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td width="550">
                    <div>
                        <textarea name="comment_content" rows="4" cols="70" ></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td width="100">
                    <div id="btn" style="text-align:center;">
                        <p><a href="#" onclick="writeCmt()">[댓글등록]</a></p>    
                    </div>
                </td>
            </form>
            </tr>
            </c:if>
        </table>
    	</div>                    
	</div>   
	</body>
</html>
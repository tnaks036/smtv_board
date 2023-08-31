<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implementing a list of posts in a bulletin board</title>
<style>
	
		#container{
            position: absolute;
            text-align: center;
            margin-top: 60px;
            width: 100%;
            height: 100%;
            top: ;
            left: 0;
        }
        #container .wrap{ 
            width: 90vw;
            display: inline-block;
            vertical-align: middle;
        }
        a{
            text-decoration: none;
            color: black;
        }
        .table-responsive{
            margin-top: 25px;
            text-align: center;
        }
        table{
            margin-left: auto;
            margin-right: auto;
        }
        thead, tbody{
            text-align: center;
        }
        </style>
        <!-- 게시판 글쓰기 화면으로 이동 -->
        <script type="text/javascript">
            function writeBoard() {
                location.href = "BoardWriteForm.bo";        
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
<form action="regBoard.do" method="post">
	<div id="container">
            <div class="wrap">
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <!-- 테이블 헤드 -->
                        <thead>
                            <tr>
                                <th class="index" scope="col" width="12%">글 번호</th>
                                <th class="title" scope="col" width="36%">제목</th>
                                <th class="name" scope="col" width="20%">작성자</th>
                                <th class="date "scope="col" width="20%">작성날짜</th>
                                <!-- <th class="countView" scope="col" width="12%">조회수</th>  -->
                            </tr>
                        </thead>
                        <!-- 테이블 목록  -->
                        <tbody>
                        <!-- a태그에서 데이터를 넘길때에는 ? 를 쓴다.  boardNum이라는 글번호를 가지고 가겠다. 게시글 개수만큼 만들어줘야한다.taglib만들어주고. -->
                            <c:forEach items="${list}" var="board">
      							<tr>
							          <td>${board.getBoard_ID() }	</td>
							          <td><a href="boardDetail.do?board_ID=${board.getBoard_ID() }">${board.getTitle() }</a></td>
							          <td>${board.ins_Date_Time }</td>
      							</tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="button" value="글쓰기" onclick="location.href='regBoardForm.do';">
                </div>
                
                <!-- 페이지 번호 -->
                <div id="pageNumber" class="text-center">
                    <c:if test="${startPage != 1}">
                        <a href="regBoardForm.do?page=${startPage-1}">[이전]</a>
                    </c:if>
                        
                    <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
                        <c:if test="${pageNum == page}">
                            ${pageNum}&nbsp;
                        </c:if>
                        <c:if test="${pageNum != page}">
                            <a href='regBoardForm.do?page=${pageNum}'>${pageNum}&nbsp;</a>
                        </c:if>
                    </c:forEach>
                        
                    <c:if test="${endPage != maxPage}">
                        <a href='?page=${endPage+1}'>[다음]</a>
                    </c:if>
                </div>
                
                <!-- 검색 옵션  -->
                <div id="searchForm" class="text-center">
                    <form>
                        <select name="option">
                            <option value="0">제목</option>
                            <option value="1">내용</option>
                            <option value="2">제목</option>
                            <option value="3">글쓴이</option>
                        </select>
                        <input type="text" size="20" name="condition"/>&nbsp;
                        <input type="submit" value="검색"/>
                    </form>
                </div>               
</body>
</html>
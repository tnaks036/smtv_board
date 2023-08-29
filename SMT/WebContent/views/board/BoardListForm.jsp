<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
	<div id="container">
            <div class="wrap">
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <!-- 테이블 헤드 -->
                        <thead>
                            <tr>
                                <th class="index" scope="col" width="12%">글 번호</th>
                                <th class="title" scope="col" width="36%">제목=작성날짜</th>
                                <th class="name" scope="col" width="20%">작성자</th>
                                <th class="date "scope="col" width="20%">작성날짜</th>
                                <!-- <th class="countView" scope="col" width="12%">조회수</th>  -->
                            </tr>
                        </thead>
                        <!-- 테이블 목록  -->
                        <tbody>
                        <!-- a태그에서 데이터를 넘길때에는 ? 를 쓴다.  boardNum이라는 글번호를 가지고 가겠다. 게시글 개수만큼 만들어줘야한다.taglib만들어주고. -->
                            <c:forEach var="board" items="${arrayList}">
                                <tr>
                                    <td>${board.board_ID}</td>
                                    <td><a href="BoardDetailAction.bo?num=${board.board_ID}&page=${page}">
                                    ${board.Ins_Date_Time}</a></td>
                                    <td>${board.Answer_Id}</td>
                                    <td>${board.Ins_Date_Time}</td>
                                    <!-- <td>${board.Del_Yn}</td>  -->
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <!-- 페이지 번호 -->
                <div id="pageNumber" class="text-center">
                    <c:if test="${startPage != 1}">
                        <a href="BoardListAction.bo?page=${startPage-1}">[이전]</a>
                    </c:if>
                        
                    <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
                        <c:if test="${pageNum == page}">
                            ${pageNum}&nbsp;
                        </c:if>
                        <c:if test="${pageNum != page}">
                            <a href='BoardListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a>
                        </c:if>
                    </c:forEach>
                        
                    <c:if test="${endPage != maxPage}">
                        <a href='BoardListAction.bo?page=${endPage+1}'>[다음]</a>
                    </c:if>
                </div>
                
                <!-- 검색 옵션  -->
                <div id="searchForm" class="text-center">
                    <form>
                        <select name="option">
                            <option value="0">제목</option>
                            <option value="1">내용</option>
                            <option value="2">제목+내용</option>
                            <option value="3">글쓴이</option>
                        </select>
                        <input type="text" size="20" name="condition"/>&nbsp;
                        <input type="submit" value="검색"/>
                    </form>
                </div>
                
                <tr>
                <div id="btn" class="bg-white py-3 px-3 text-center border mt-3">
					<input type="submit" class="btn btn-primary btn-sm px-3 gap-3" value="글쓰기" onclick="writeBoard()"></button></input>
				</div>
                </tr>                       
                <!-- 세션에 전달된 아이디 값이 있으면(로그인이 되어 있으면) 글쓰기 활성화  
                <div class="bg-white py-3 px-3 text-center border mt-3">
                    <c:if test="${sessionScope.memberID!=null}">
                        <input class="w-100 btn btn-lg btn-primary" type="button" value="글쓰기" onclick="writeBoard()"></input>
                    </c:if>
                </div> -->
            </div>
        </div>
</body>
</html>
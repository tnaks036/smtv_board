<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
   border: 1px solid black;
   border-collapse: collapse;
   text-align: center;
   width: 600px;
}
tr, td{
   border: 1px solid black;
}
</style>
</head>
<body>
<table>
   <colgroup>
      <col width="10%">
      <col width="*">
      <col width="15%">
   </colgroup>
   <tr>
      <td>글번호</td>
      <td>제 목</td>
      <td>작성일</td>
</tr>
   
   <!-- a태그에서 데이터를 넘길때에는 ? 를 쓴다. 
       boardNum이라는 글번호를 가지고 가겠다. -->
      <!-- 게시글 개수만큼 만들어줘야한다.taglib만들어주고. -->
      <c:forEach items="${list}" var="board">
      <tr>
          <td>${board.board_ID }</td>
          <td><a href="boardDetail.do?boardNum=${board.board_ID }">${board.file_name }</a></td>
          <td>${board.ins_Date_Time }</td>
      </tr>
      </c:forEach>
         
   
</table>
<input type="button" value="글쓰기" onclick="location.href='regBoardForm.do';">
</body>
</html>
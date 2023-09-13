<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
   border: 1px solid black;
   border-collapse: collapse;
   text-align: center;
   width: 600px;
}
tr, td {
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
   
   <c:forEach items="${list}" var="board">
      <tr>
          <td>${board.getBoard_ID() }	</td>
          <td><a href="boardDetail.do?board_ID=${board.getBoard_ID() }">${board.getTitle() }</a></td>
          <td>${board.ins_Date_Time }</td>
      </tr>
   </c:forEach>
</table>
<input type="button" value="글쓰기" onclick="location.href='regBoardForm.do';">
</body>
</html>
	
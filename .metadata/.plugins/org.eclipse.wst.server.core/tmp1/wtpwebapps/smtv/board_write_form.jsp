<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
</head>
<body>
<form action="regBoard.do" method="post" enctype="multipart/form-data">
   <table>
      <tr>
         <td>제목</td>
         <td><input type="text" name="title"></td>
      </tr>
      <tr>
         <td>작성일</td>
         <td><input type="date" name="ins_Date_Time"></td>
      </tr>
      <tr>
         <td>내용</td>
         <td>
            <textarea rows="5" cols="50" name="contents"></textarea>
         </td>
      </tr>
      <tr>
         <td>댓글</td>
         <td><input type="text" name="comment_ID"></td>
      </tr>
      <tr>
         <td>파일 업로드</td>
         <td><input type="file" name="fileToUpload"></td>
      </tr>
   </table>
   <input type="submit" value="글등록">
</form>
</body>
</html>

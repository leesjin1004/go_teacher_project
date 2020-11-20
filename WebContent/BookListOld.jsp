<%@page import="vo.BookVo"%> 
<%@page import="java.util.List"%>
<%@ page import="dao.BookDAO_Mariadb" %>
<%@ page import="service.BookService" %>
<%@ page import="service.BookServiceImpl" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
 
<link rel = "stylesheet" href="./css/my.css">

<script type="text/javascript">
	function f1(){
		return confirm("정말 삭제 하시겠습니까?");
	}
</script>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

</head>
<body>
<h1>Book List</h1>
   
 <form action="bookDelete.do" method="get">
   <table class ="tableb">
      <thead>
         <tr>
            <th>id</th>
            <th>title</th>
            <th>publisher</th>
            <th>price</th>
            <th> <input type="submit" value="삭제" onclick="return f1()"> </th>
         </tr>
      </thead>


<c:forEach var="data" items="${bookList}">

      <tr>
         <td>${ data.bookno }</td>
         <td>${ data.title }></td>
         <td>${ data.publisher }</td>
         <td>${ data.price }</td>
         <td><input type="checkbox" name="bookno" value="${ data.bookno }"> </td>
      </tr>
     
</c:forEach>


      
   </table>
  </form>
</body>
</html>
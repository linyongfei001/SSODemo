<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>hello,${sname}</h1>
	<h2>${mgs }</h2>
	<table border="1px" width="500px" cellpadding="5px" cellspacing="0px">
		<tr>
			<td>编号</td>
			<td>书名</td>
			<td>作者</td>
			<td>单价</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${books}" var="book" >
				<tr>
					<td>${book.bid }</td>
					<td>${book.bname }</td>
					<td>${book.author }</td>
					<td>${book.price }</td>
					<td><a href="toUpdate.do?bid=${book.bid }">修改</a> 
					<a href="delete.do?bid=${book.bid }">删除</a></td>
				</tr>
		</c:forEach>		
		
		<form action="add.do" method="post">
			<tr>
				<td><input type="text" name="bid"  value="0" readonly="readonly" style="width:30px"></td>
				<td><input type="text" name="bname" style="width:110px" ></td>
				<td><input type="text" name="author"style="width:90px"></td>
				<td><input type="text" name="price" value="0.0" style="width:90px"></td>
				<td><input type="submit" value="添加"></td>
			</tr>			
	</form>
				
	</table>
	
	<!-- <form action="add.do" method="post">
		<table border="1px" width="500px" cellpadding="5px" cellspacing="0px">
			<tr>
				<td><input type="text" name="bid"  value="0" readonly="readonly" style="width:30px"></td>
				<td><input type="text" name="bname" style="width:110px" ></td>
				<td><input type="text" name="author"style="width:80px"></td>
				<td><input type="text" name="price" value="0.0" style="width:80px"></td>
				<td><input type="submit" value="添加"></td>
			</tr>	
		</table>
	</form>  -->
	
</body>
</html>
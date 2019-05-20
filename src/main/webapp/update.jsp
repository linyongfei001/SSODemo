<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update.do" method="post">
		<table  border="1px" width="500px" cellpadding="5px" cellspacing="0px">
			<tr>
				<td>编号</td>
				<td>书名</td>
				<td>作者</td>
				<td>单价</td>
			</tr>
			<tr>
				<td><input type="text" name="bid" readonly="readonly" value="${book.bid }"></td>
				<td><input type="text" name="bname" value="${book.bname }"></td>
				<td><input type="text" name="author" value="${book.author }"></td>
				<td><input type="text" name="price" value="${book.price }"></td>
			</tr>	
		</table>
		<input type="submit" value="提交" >
	</form>
	

</body>
</html>
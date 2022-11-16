<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="removeEmployeeById" method="post">
		<table>
			<tr>
				<td><label for ="id">Enter the id you want to delete</label></td>
				<td><input type="number" name="id" id ="id"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Remove"></td>
			</tr>

			<%
			if (null != session.getAttribute("isDeleted")) {
				boolean isDeleted = (boolean) session.getAttribute("isDeleted");
				if (isDeleted) {
			%>
			
			<%="Deleted Successfully"%>
			
			<%
			}
			}
			%>
		</table>
	</form>
</body>
</html>
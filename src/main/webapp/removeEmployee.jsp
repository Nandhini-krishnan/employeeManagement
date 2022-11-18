<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="employee/remove" method="post">
		<table>
			<tr>
				<td><label for ="id">Enter the id you want to delete</label></td>
				<td><input type="number" name="id" id ="id"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Remove"></td>
			</tr>
             <h3> ${message} </h3>
		</table>
	</form>
</body>
</html>
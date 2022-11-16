<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="searchEmployees">
		<table>
			<tr>
				<td><label for="search">search :</label></td>
				<td><input type="text" name="value" id="search" required></td>
			</tr>

			<tr>
				<td><input type="submit" value="Search"></td>
			</tr>
		</table>
	</form>
</body>
</html>
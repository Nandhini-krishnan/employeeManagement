<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="getEmployeesByExperience">
		<table>
			<tr>
				<td><label for="yearsOfExperience">Enter the years of experience </label></td>
				<td><input type="number" name="yearsOfExperience" id="yearsOfExperience" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>
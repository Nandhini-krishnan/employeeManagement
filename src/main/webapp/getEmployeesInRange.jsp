<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="getEmployeesInRange">
		<table>
			<tr>
				<td><label for="startDate">Start Date</label></td>
				<td><input type="date" name="startDate" id="startDate"required></td>
			</tr>

			<tr>
				<td><label for="endDate">End Date</label></td>
				<td><input type="date" name="endDate" id="endDate"required></td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="insertTechStack">
		<table>
			<tr>
				<td><label for="name">Name</label></td>
				<td><input type="text" id ="name" name="name" placeholder ="Enter the name" 
				     title ="Enter the name(only alphanumeric, space are allowed)"		
					required></td>
			</tr>

			<tr>
				<td><label for="version">Version</label></td>
				<td><input type="number" name="version" id="version" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form>
</body>
<% if(null != session.getAttribute("TechStack")) { %>
    <h1>Insert successfully</h1>
<% } %>
</html>
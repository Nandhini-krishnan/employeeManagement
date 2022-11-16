<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="insertProject">
		<table>
			<tr>
				<td><label for ="name">Name</label></td>
				<td><input type="text" name="name" id ="name" placeholder ="Enter the name" 
				     title ="Enter the name(only alphanumeric, space are allowed)"	
					required></td>
			</tr>

			<tr>
				<td><label for="startDate">Start Date</label></td>
				<td><input type="date" name="startDate" id="startDate" required></td>
			</tr>

			<tr>
				<td><label for="endDate">End Date</label></td>
				<td><input type="date" name="endDate" id="endDate" required></td>
			</tr>

			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form>
</body>
<%
if (null != session.getAttribute("Project")) {
%>
<%=request.getParameter("name")%>
<h1>Insert successfully</h1>
<%
}
%>
</html>
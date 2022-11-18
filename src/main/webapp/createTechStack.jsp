<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="techStack/insert">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input type="text" path="name" placeholder ="Enter the name" 
				     title ="Enter the name(only alphanumeric, space are allowed)"></form:input></td>
			</tr>

			<tr>
				<td><form:label path="version">Version</form:label></td>
				<td><form:input type="number" path="version" ></form:input></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form>
	 <%= request.getAttribute("employee") %>
	<c:if test="${not empty employee.getName()}">
		<h2>"${employee.getName()}  ${message}"</h2>
	</c:if>
</body>
</html>
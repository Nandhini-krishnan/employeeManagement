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
	<form:form method="post" action="project/insert" modelAttribute = "project">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input type="text" path="name"
						placeholder="Enter the name"
						title="Enter the name(only alphanumeric, space are allowed)"></form:input></td>
			</tr>

			<tr>
				<td><label for="startDate">Start Date</label></td>
				<td><input type="date" name="start" id="startDate" value = "${project.getStartDate()}" /></td>
			</tr>

			<tr>
				<td><label for="endDate">End Date</label></td>
				<td><input type="date" name="end" id="endDate" value = "${project.getEndDate()}" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form:form>
	<%= request.getAttribute("project") %>
	<c:if test="${not empty project.getName()}">
		<h2>"${project.getName()}  ${message}"</h2>
	</c:if>
</body>
</html>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.ideas2it.model.Employee"%>
<%@page import="com.ideas2it.model.Project"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="getProjectById" method="post">
		<table>
			<tr>
				<td><label for ="id">Enter the id</label></td>
				<td><input type="number" name="id" id ="id"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Display"></td>
			</tr>

			<%
			Project project = (Project) session.getAttribute("Project");
			if (null != project) {
			%>

			<tr>
				<td>Id :</td>
				<td><%=project.getId()%></td>
			</tr>

			<tr>
				<td>Name :</td>
				<td><%=project.getName()%></td>
			</tr>

			<tr>
				<td>Project Start Date : :</td>
				<td><%=project.getStartDate()%></td>
			</tr>

			<tr>
				<td>Project End Date :</td>
				<td><%=project.getEndDate()%></td>
			</tr>

			<tr>
				<td>Delete Status :</td>
				<td><%=project.isDeleted()%></td>
			</tr>

			<tr>
				<td>Last Created Date and Time :</td>
				<td><%=project.getCreatedAt()%></td>
			</tr>

			<tr>
				<td>Last Updated Date and Time :</td>
				<td><%=project.getUpdatedAt()%></td>
			</tr>
			<%
	        List<Employee> employees = project.getEmployees();
	        %>
			<tr>
				<td>Projects :</td>
				<%
		        if (null != employees && !employees.isEmpty()) {
		        %>
				<td><%=employees.stream().map(employee -> employee.getName()).collect(Collectors.joining(","))%></td>
				<%
		        } else {
		        %>
				<td>Employees not yet assigned</td>
			</tr>
			<%
			}}
			%>
		</table>
	</form>
</body>
</html>
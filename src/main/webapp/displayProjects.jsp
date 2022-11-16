<%@page import="java.util.stream.Collectors"%>
<%@page import="com.ideas2it.model.Employee"%>
<%@page import="com.ideas2it.model.Project"%>
<%@page import="com.ideas2it.model.TechStack"%>
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
	<%
	List<Project> projects = (List<Project>) session.getAttribute("Projects");
	if (null != projects) {
		if (!projects.isEmpty()) {
	%>
	<%
	for (Project project : projects) {
	%>
    <table>
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
	List<TechStack> techStacks = project.getTechStacks();
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
		<%
	    }
	    %>
	</tr>
	<tr>
		<td>Technical Stacks :</td>
		<%
		if (null != techStacks && !techStacks.isEmpty()) {
		%>

		<td><%=techStacks.stream().map(techStack -> techStack.getName()).collect(Collectors.joining(","))%></td>
		<%
		} else {
		%>
		<td>Technical Stacks not yet assigned</td>
	</tr>
	<%
	}
	%>
	<tr>
		<td>---------------------</td>
	</tr>
	<%
    }
	}
	}
	%>
	</table>
</body>
</html>
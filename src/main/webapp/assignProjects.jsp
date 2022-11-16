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
	<form action="assignProjects" method="post">
		<table>
			<tr>
				<td><label for ="id">Enter the id you want to assign</label></td>
				<td><input type="number" name="id" id ="id"></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>

	<form action="getExistingProjects" method="post">
		<%
	if (null != session.getAttribute("isPresent")) { %>
		<table>
			<tr>
				<td><input type="submit"
					value="Display existing projects to assign"></td>
			</tr>
		</table>
		<% } %>
	</form>

	<form action="getProjectsForAssign" method="post">
		<% 
		if (null != session.getAttribute("Projects")) { 
		List<Project> projects = (List<Project>) session.getAttribute("Projects");
		%>

		<p>choose the projects you want to assign</p>

		<% 
		for (Project project : projects) {
	    %>

		<table>
			<tr>
				<td><input type="checkbox" name="project" id ="<%= project.getId() %>"
					value="<%= project.getId() %>"><label for="<%= project.getId() %>"><%=  project.getName() %></label></td>
			</tr>
			
	    <%
		} 
		%>
		
	    <tr>
			<td><input type="submit" value="Submit"></td>
		</tr>
		</table>
		<% } %>
	</form>
</body>
<% if(null != session.getAttribute("Employee")) { %>
    <h1>Assign successfully</h1>
<% } %>
</html>
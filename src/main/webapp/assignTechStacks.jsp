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
	<form action="assignTechStacks" method="post">
		<table>
			<tr>
				<td><label for ="id">Enter the id you want to assign</label></td>
				<td><input type="number" name="id" id ="id"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>

	<form action="getExistingTechStacks" method="post">
		<%
	if (null != session.getAttribute("isPresent")) { %>
		<table>
			<tr>
				<td><input type="submit"
					value="Display existing Tech Stacks to assign"></td>
			</tr>
		</table>
		<% } %>
	</form>

	<form action="getTechStacksForAssign" method="post">
		<% 
		if (null != session.getAttribute("TechStacks")) { 
		List<TechStack> techStacks = (List<TechStack>) session.getAttribute("TechStacks");
		%>

		<p>choose the projects you want to assign</p>

		<% 
		for (TechStack techStack : techStacks) {
	    %>

		<table>
			<tr>
				<td><input type="checkbox" name="techStack" id ="techStack"
					value="<%= techStack.getId() %>"><label for ="techStack"> <%= techStack.getName() %></label></td>
			</tr>
		<% } %>
	    <tr>
			<td><input type="submit" value="Submit"></td>
		</tr>
		</table>
		<% } %>
	</form>
</body>
<% if(null != session.getAttribute("Project")) { %>
    <h1>Assign successfully</h1>
<% } %>
</html>
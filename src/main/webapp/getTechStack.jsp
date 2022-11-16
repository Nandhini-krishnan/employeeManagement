<%@page import="java.util.stream.Collectors"%>
<%@page import="com.ideas2it.model.TechStack"%>
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
	<form action="getTechStackById" method="post">
		<table>
			<tr>
				<td><label for ="id">Enter the id</label></td>
				<td><input type="number" name="id" id ="id"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Display"></td>
			</tr>

			<%
			TechStack techStack = (TechStack) session.getAttribute("TechStack");
			if (null != techStack) {
			%>

			<tr>
				<td>Id :</td>
				<td><%=techStack.getId()%></td>
			</tr>

			<tr>
				<td>Name :</td>
				<td><%=techStack.getName()%></td>
			</tr>

			<tr>
				<td>Version : :</td>
				<td><%=techStack.getVersion()%></td>
			</tr>

			<tr>
				<td>Delete Status :</td>
				<td><%=techStack.isDeleted()%></td>
			</tr>

			<tr>
				<td>Last Created Date and Time :</td>
				<td><%=techStack.getCreatedAt()%></td>
			</tr>

			<tr>
				<td>Last Updated Date and Time :</td>
				<td><%=techStack.getUpdatedAt()%></td>
			</tr>
			<%
	        List<Project> projects = techStack.getProjects();
	        %>
			<tr>
				<td>Projects :</td>
				<%
		        if (null != projects && !projects.isEmpty()) {
		        %>

				<td><%=projects.stream().map(project -> project.getName()).collect(Collectors.joining(","))%></td>
				<%
		        } else {
		        %>
				<td>Projects not yet assigned</td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</form>
</body>
</html>
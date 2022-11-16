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
	<%
	List<TechStack> techStacks = (List<TechStack>) session.getAttribute("TechStacks");
	if (null != techStacks) {
		if (!techStacks.isEmpty()) {
	%>
	<%
	for (TechStack techStack : techStacks) {
	%>
    <table>
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
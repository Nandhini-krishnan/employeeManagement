<%@ page import="com.ideas2it.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="getEmployeesByMultipleId.jsp" method="get">
		<table>
			<tr>
				<td><label for="numberOfId"><%=Constants.ENTER_NUMBER_OF_ID%></label></td>
				<td><input type="number" name="numberOfId" id="numberOfId"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>	
	</form>

	<form action="getEmployeesByMultipleId" method="get">
		<%
		if (null != request.getParameter("numberOfId")) {
		int size = Integer.parseInt(request.getParameter("numberOfId"));
			for (int i = 0; i < size; i++) {
		%>
		<table>
			<tr>
				<td><label for="<%=i + 1%>"><%=i + 1%> : Employee Id</label></td>
				<td><input type="number" name="<%=i + 1%>" id="<%=i + 1%>" placeholder= <%=i + 1%>></td>
			</tr>
			<%
			}  session.setAttribute("size", size);
			%>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
		<% } %>
	</form>
</body>
</html>
<%@page import="com.ideas2it.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="getEmployeeForUpdate">
		Employee Id: <input type="text" name="id"> <input
			type="submit" value="submit">
	</form>
	<%
	Employee employee = (Employee) session.getAttribute("Employee");
	%>
	<form method="post" action="updateEmployee">
		<%
		if (null != employee) {
		%>
		<table>
			<tr>
				<td>Id</td>
				<td><input name="id" value="<%=employee.getId()%>" readonly>
			</tr>
			<tr>
				<td>Name</td>
				<td><input name="name" value="<%=employee.getName()%>">
			</tr>
			<tr>
				<td>Address</td>
				<td><input name="address" value="<%=employee.getAddress()%>">
			</tr>
			<tr>
				<td>BloodGroup</td>
				<%
				String option = employee.getBloodGroup().toString();
				%>
				<td><select name="bloodGroup" required>
						<option value="A_POSITIVE" <%if (option.equals("A_POSITIVE")) {%>
							<%="selected"%> <%}%>>A_POSITIVE</option>
						<option value="A_NEGATIVE" <%if (option.equals("A_NEGATIVE")) {%>
							<%="selected"%> <%}%>>A_NEGATIVE</option>
						<option value="B_POSITIVE" <%if (option.equals("B_POSITIVE")) {%>
							<%="selected"%> <%}%>>B_POSITIVE</option>
						<option value="B_NEGATIVE" <%if (option.equals("B_NEGATIVE")) {%>
							<%="selected"%> <%}%>>B_NEGATIVE</option>
						<option value="O_POSITIVE" <%if (option.equals("O_POSITIVE")) {%>
							<%="selected"%> <%}%>>O_POSITIVE</option>
						<option value="O_NEGATIVE" <%if (option.equals("O_NEGATIVE")) {%>
							<%="selected"%> <%}%>>O_NEGATIVE</option>
						<option value="AB_POSITIVE"
							<%if (option.equals("AB_POSITIVE")) {%> <%="selected"%> <%}%>>AB_POSITIVE</option>
						<option value="AB_NEGATIVE"
							<%if (option.equals("AB_NEGATIVE")) {%> <%="selected"%> <%}%>>AB_NEGATIVE</option>
						<option value="A1_POSITIVE"
							<%if (option.equals("A1_POSITIVE")) {%> <%="selected"%> <%}%>>A1_POSITIVE</option>
						<option value="A1_NEGATIVE"
							<%if (option.equals("A1_NEGATIVE")) {%> <%="selected"%> <%}%>>A1_NEGATIVE</option>
						<option value="A1B_POSITIVE"
							<%if (option.equals("A1B_POSITIVE")) {%> <%="selected"%> <%}%>>A1B_POSITIVE</option>
						<option value="A1B_NEGATIVE"
							<%if (option.equals("A1B_NEGATIVE")) {%> <%="selected"%> <%}%>>A1B_NEGATIVE</option>
						<option value="A2B_POSITIVE"
							<%if (option.equals("A2B_POSITIVE")) {%> <%="selected"%> <%}%>>A2B_POSITIVE</option>
						<option value="A2B_NEGATIVE"
							<%if (option.equals("A2B_NEGATIVE")) {%> <%="selected"%> <%}%>>A2B_NEGATIVE</option>
						<option value="OTHERS" <%if (option.equals("OTHERS")) {%>
							<%="selected"%> <%}%>>OTHERS</option>
				</select></td>
			</tr>

			<tr>
				<td>Date of Birth</td>
				<td><input type="date" name="dateOfBirth"
					value="<%=employee.getDateOfBirth()%>" required></td>
			</tr>

			<tr>
				<td>Date of Join</td>
				<td><input type="date" name="dateOfJoin"
					value="<%=employee.getDateOfJoin()%>" required></td>
			</tr>

			<tr>
				<td>Do you have any experience?</td>
				<%
				option = Boolean.toString(employee.hasExperience());
				%>
				<td><input type="radio" name="experience" value="true"
					<%if (option.equals("true")) {%> <%="checked"%> <%}%>>yes</td>
				<td><input type="radio" name="experience" value="false"
					<%if (option.equals("false")) {%> <%="checked"%> <%}%>>no</td>
			</tr>

			<tr>
				<td>Previous Organisation Name(Please leave as empty if you
					don't have any experience)</td>
				<td><input type="text" name="previousOrganisationName"
					value="<%=employee.getPreviousOrganisationName()%>"></td>
			</tr>

			<tr>
				<td><input type="submit" value="update"></td>
			</tr>
		</table>
		<%
		if (null != session.getAttribute("isUpdated")) {
			boolean isUpdated = (boolean) session.getAttribute("isUpdated");
			if (isUpdated) {
		%>
		<%="updated successfully"%>
		<%
		}
		}
		}
		%>
	</form>
</body>
</html>
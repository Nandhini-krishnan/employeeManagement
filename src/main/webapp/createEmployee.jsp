<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="post" action="employee/insertEmployee" modelAttribute = "employee">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input type="text" path="name" placeholder ="Enter the name" 
				     title ="Enter the name(only alphanumeric, space are allowed)"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:input type="text" path ="address" placeholder ="Enter the Address" ></form:input></td>
			</tr>
			<tr>
				<td><form:label path="bloodGroup">Blood Group</form:label></td>
				<td><form:select path="bloodGroup">
						<option value="A_POSITIVE">A_POSITIVE</option>
						<option value="A_NEGATIVE">A_NEGATIVE</option>
						<option value="B_POSITIVE">B_POSITIVE</option>
						<option value="B_NEGATIVE">B_NEGATIVE</option>
						<option value="O_POSITIVE">O_POSITIVE</option>
						<option value="O_NEGATIVE">O_NEGATIVE</option>
						<option value="AB_POSITIVE">AB_POSITIVE</option>
						<option value="AB_NEGATIVE">AB_NEGATIVE</option>
						<option value="A1_POSITIVE">A1_POSITIVE</option>
						<option value="A1_NEGATIVE">A1_NEGATIVE</option>
						<option value="A1B_POSITIVE">A1B_POSITIVE</option>
						<option value="A1B_NEGATIVE">A1B_NEGATIVE</option>
						<option value="A2B_POSITIVE">A2B_POSITIVE</option>
						<option value="A2B_NEGATIVE">A2B_NEGATIVE</option>
						<option value="OTHERS">OTHERS</option>
				</form:select></td>
			</tr>
			<tr>
				<td><label for="dateOfBirth">Date of Birth</label></td>
				<td><input type="date" name="dateOfBirth" id="dateOfBirth"></input></td>
			</tr>
			<tr>
				<td><label for="dateOfJoin">Date of Join</label></td>
				<td><input type="date" name="dateOfJoin" id="dateOfJoin"></input></td>
			</tr>
			<tr>
				<td><form:label path="hasExperience">Do you have any experience?</form:label></td>
				<td><form:input type="radio" path="experience" value="true">yes</form:input></td>
				<td><form:input type="radio" path="experience" value="false">no</form:input></td>
			</tr>
			<tr>
				<td><form:label path="previousOrganisationName">Previous Organisation Name
				                                                (Please leave as empty if you don't have any experience)</form:label></td>
				<td><form:input type="text" path="previousOrganisationName" ></form:input></td>
			</tr>
			<tr>
				<td><form:input type="submit" value="Insert"></form:input></td>
			</tr>
		</table>
	</form:form>
	
	<% if(null != request.getAttribute("Employee")) { %>
    <h1>Insert successfully</h1>
<% } %>
</body>
</html>
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
	<form:form method="post" action="employee/insert"
		modelAttribute="employee">
		<table>
		    <tr>
		    <td><form:input type="hidden" path = "id" /></td>
		    </tr>
		    <tr>
		    <td><form:input type="hidden" path = "employeeCode" /></td>
		    </tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input type="text" path="name"
						placeholder="Enter the name"
						title="Enter the name(only alphanumeric, space are allowed)"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:input type="text" path="address"
						placeholder="Enter the Address"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="bloodGroup">Blood Group</form:label></td>
				<td><form:select path="bloodGroup">
						<form:option value="A_POSITIVE" label="A_POSITIVE" />
						<form:option value="A_NEGATIVE" label="A_NEGATIVE" />
						<form:option value="B_POSITIVE" label="B_POSITIVE" />
						<form:option value="B_NEGATIVE" label="B_NEGATIVE" />
						<form:option value="O_POSITIVE" label="O_POSITIVE" />
						<form:option value="O_NEGATIVE" label="O_NEGATIVE" />
						<form:option value="AB_POSITIVE" label="AB_POSITIVE" />
						<form:option value="AB_NEGATIVE" label="AB_NEGATIVE" />
						<form:option value="A1_POSITIVE" label="A1_POSITIVE" />
						<form:option value="A1_NEGATIVE" label="A1_NEGATIVE" />
						<form:option value="A1B_POSITIVE" label="A1B_POSITIVE" />
						<form:option value="A1B_NEGATIVE" label="A1B_NEGATIVE" />
						<form:option value="A2B_POSITIVE" label="A2B_POSITIVE" />
						<form:option value="A2B_NEGATIVE" label="A2B_NEGATIVE" />
						<form:option value="OTHERS" label="OTHERS" />
					</form:select></td>
			</tr>
			<tr>
				<td><label for="dateBirth">Date of Birth</label></td>
				<td><input name="dateBirth" id="dateBirth" type="date" value = "${employee.getDateOfBirth()}" /></td>
			</tr>
			<tr>
				<td><label for="dateJoin">Date of Join</label></td>
				<td><input type="date" name="dateJoin" id="dateJoin" value = "${employee.getDateOfJoin()}" /></td>
			</tr>
			<tr>
               <td><form:label path = "experience">Do you have any experience?</form:label></td>
               <td>
                  <form:radiobutton path = "experience" value = "true" label = "Yes" />
                  <form:radiobutton path = "experience" value = "false" label = "No" />
               </td>
            </tr>
			<tr>
				<td><form:label path="previousOrganisationName">Previous Organisation Name
				                                                (Please leave as empty if you don't have any experience)</form:label></td>
				<td><form:input type="text" path="previousOrganisationName"></form:input></td>
			</tr>
			<tr>
				<td><input type="submit" value="Insert"></input></td>
			</tr>
		</table>
	</form:form>
   <%= request.getAttribute("employee") %>
	<c:if test="${not empty employee.getName()}">
		<h2>"${employee.getName()}  ${message}"</h2>
	</c:if>
</body>
</html>
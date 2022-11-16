<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="insertEmployee">
		<table>
			<tr>
				<td><label for="name">Name</label></td>
				<td><input type="text" id ="name" name="name" placeholder ="Enter the name" 
				     title ="Enter the name(only alphanumeric, space are allowed)"		
					required></td>
			</tr>
			<tr>
				<td><label for="address">Address</label></td>
				<td><input type="text" id ="address" name="address" placeholder ="Enter the Address" required></td>
			</tr>
			<tr>
				<td><label for="bloodGroup">Blood Group</label></td>
				<td><select name="bloodGroup" id = "bloodGroup" required>
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
				</select></td>
			</tr>
			<tr>
				<td><label for="dateOfBirth">Date of Birth</label></td>
				<td><input type="date" name="dateOfBirth" id="dateOfBirth" required></td>
			</tr>
			<tr>
				<td><label for="dateOfBirth">Date of Join</label></td>
				<td><input type="date" name="dateOfJoin" id="dateOfJoin" required></td>
			</tr>
			<tr>
				<td><label for="experience">Do you have any experience?</label></td>
				<td><input type="radio" name="experience" id="experience" value="true">yes</td>
				<td><input type="radio" name="experience" id="experience" value="false">no</td>
			</tr>
			<tr>
				<td><label for="organisationName">Previous Organisation Name(Please leave as empty if you don't have any experience)</label></td>
				<td><input type="text" name="PreviousOrganisationName" id="organisationName"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form>
</body>
<% if(null != session.getAttribute("Employee")) { %>
    <h1>Insert successfully</h1>
<% } %>
</html>
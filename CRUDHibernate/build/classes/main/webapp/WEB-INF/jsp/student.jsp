<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Management</title>
</head>
<body>
<h1>Students Data</h1>
<form:form action="student.do" method="POST" commandName="student">
	<table>
		<tr>
			<td>Student ID</td>
			<td><form:input path="id" /></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><form:input path="Nombre" /></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><form:input path="Apellido" /></td>
		</tr>
		<tr>
			<td>Localidad</td>
			<td><form:input path="Localidad" /></td>
		</tr>
		<tr>
			<td>Sexo</td>
			<td><form:input path="Sexo" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
				<input type="submit" name="action" value="Edit" />
				<input type="submit" name="action" value="Delete" />
				<input type="submit" name="action" value="Search" />
			</td>
		</tr>
	</table>
</form:form>
<br>
<table border="1">
	<tr>
		<th>ID</th>
		<th>Nombre</th>
		<th>Apellido</th>
		<th>Localidad</th>
		<th>Sexo</th>
	</tr>
	<c:forEach items="${studentList}" var="student">
		<tr>
			<td>${student.id}</td>
			<td>${student.getNombre()}</td>
			<td>${student.getApellido()}</td>
			<td>${student.getLocalidad()}</td>
			<td>${student.getSexo()}</td>
<%--     	<td>${student.yearLevel}</td> --%>
		</tr>
	</c:forEach>
</table>
</body>
</html>

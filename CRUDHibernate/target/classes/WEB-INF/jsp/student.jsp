<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Management</title>

<style>
.table-wrapper-scroll-y {
	display: block;
	max-height: 250px;
	overflow-y: auto;
	-ms-overflow-style: -ms-autohiding-scrollbar;
}
</style>
<script src="<c:url value="/resources/main.js" />"></script>

</head>

<body>
	<div class="container-fluid">

		<h1 align="center">Students Data</h1>
		
		<div class="row">
			<div class="col-4">
				<form:form action="student.do" method="POST" commandName="student">
					<table>
						<tr>
							<td>Student ID</td>
							<td><form:input path="studentId" /></td>
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
							<td><form:select path="Sexo">
									<form:option value="T">Elegi uno</form:option>
									<form:option value="M">Masculino</form:option>
									<form:option value="F">Femenino</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="action"
								value="Add" id=add /> <input type="submit" name="action"
								value="Edit" /> <input type="submit" name="action"
								value="Delete" /></td>
						</tr>
					</table>
				</form:form>


			</div>
			<div class="col-8">
				<div class="table-wrapper-scroll-y">
					<table border="1" class="table-bordered table-striped table">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nombre</th>
								<th scope="col">Apellido</th>
								<th scope="col">Localidad</th>
								<th scope="col">Sexo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${studentList}" var="student">
								<tr>
									<th scope="row">${student.studentId}</th>
									<td>${student.getNombre()}</td>
									<td>${student.getApellido()}</td>
									<td>${student.getLocalidad()}</td>
									<td>${student.getSexo()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<br>
		
		<div class="row">
			<div class="col-5">
				<h2>Busqueda por:</h2>
			</div>
			<div class="col-7">
				<h2>Resultados de búsqueda</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-4">
				<form:form action="search.student" method="POST">
					<table>
					<tr>
							<td><input type="submit" name="action" value="id" /></td>
							<td><input name="stu" /></td>
						</tr>
						<tr>
							<td><input type="submit" name="action" value="Nombre" /></td>
							<td><input name="n" /></td>
						</tr>
						<tr>
							<td><input type="submit" name="action" value="Apellido" /></td>
							<td><input name="a" /></td>
						</tr>
						<tr>
							<td><input type="submit" name="action" value="Localidad" /></td>
							<td><input name="l" /></td>
						</tr>
						<tr>
							<td><input type="submit" name="action" value="Sexo" /></td>
							<td><input placeholder="M/F" name="s" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			
			<div class="col-8">
				<div class="table-wrapper-scroll-y">
					<table border="1"
						class="table-bordered table-striped table table-fixed">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nombre</th>
								<th scope="col">Apellido</th>
								<th scope="col">Localidad</th>
								<th scope="col">Sexo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${studentListBusqueda}" var="student">
								<tr>
									<th scope="row">${student.studentId}</th>
									<td>${student.getNombre()}</td>
									<td>${student.getApellido()}</td>
									<td>${student.getLocalidad()}</td>
									<td>${student.getSexo()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>

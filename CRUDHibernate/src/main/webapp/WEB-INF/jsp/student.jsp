<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/octicons/4.4.0/font/octicons.css">
<link rel="stylesheet"
	href="<c:url value="/resources/ui.jqgrid-bootstrap4.css" />">


<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

<script src="<c:url value="/resources/grid.locale-en.js" />"></script>
<script src="<c:url value="/resources/jquery.jqGrid.min.js" />"></script>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script>
	$.jgrid.defaults.styleUI = 'Bootstrap4';
	$.jgrid.defaults.iconSet = 'Octicons';
</script>
<script src="<c:url value="/resources/main.js" />"></script>
<title>Student Management</title>

<style>
.table-wrapper-scroll-y {
	display: block;
	max-height: 250px;
	overflow-y: auto;
	-ms-overflow-style: -ms-autohiding-scrollbar;
}

.half-left-input {
	width: 50%;
	float: left;
}

.half-right-input {
	width: 50%;
	float: right;
}
</style>



</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-4 text-danger">
				<h4>${errorId.getNombre()}</h4>
			</div>
			<div class="col-8">
				<h1 align="center">Students Data</h1>
			</div>

		</div>


		<div class="row">
			<div class="col-3">
				<form:form action="student.do" method="POST" commandName="student">
					<table>
						<tr>
							<td>ID</td>
							<td><form:input path="studentId" /></td>
						</tr>
						<tr>
							<td>Nombre</td>
							<td><form:input path="Nombre" /></td>
						</tr>
						<tr>
							<td>Apellido</td>
							<td><form:input path="Apellido" /></td>
						</tr>
						<tr>
							<td>Localidad</td>
							<td><form:input path="Localidad" /></td>
						</tr>
						<tr>
							<td>Edad(+13)</td>
							<td><form:input path="Edad" /></td>
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
							<td>Promedio</td>
							<td><form:input path="Promedio" /></td>
						</tr>
						<tr></tr>
						<tr>
							<td colspan="2"><input type="submit" name="action"
								value="Add" id="add" /> <input type="submit" name="action"
								value="Edit" id="edit" /> <input type="submit" name="action"
								value="Delete" id="delete" /></td>
						</tr>
					</table>
				</form:form>

			</div>
			<div class="col-9">
				<ul class="dropdown-menu"></ul>
				<table id="jgrid"></table>
				<div id="jqGridPager"></div>
				<button id="getBtn">CLICK HERE</button>
				<!-- 				<div class="table-wrapper-scroll-y"> -->
				<!-- 					<table border="1" class="table-bordered table-striped table"> -->
				<!-- 						<thead> -->
				<!-- 							<tr> -->
				<!-- 								<th scope="col">ID</th> -->
				<!-- 								<th scope="col">Nombre</th> -->
				<!-- 								<th scope="col">Apellido</th> -->
				<!-- 								<th scope="col">Localidad</th> -->
				<!-- 								<th scope="col">Edad</th> -->
				<!-- 								<th scope="col">Sexo</th> -->
				<!-- 								<th scope="col">Promedio</th> -->
				<!-- 							</tr> -->
				<!-- 						</thead> -->
				<!-- 						<tbody> -->
				<%-- 							<c:forEach items="${studentList}" var="student"> --%>
				<!-- 								<tr> -->
				<%-- 									<th scope="row">${student.studentId}</th> --%>
				<%-- 									<td>${student.getNombre()}</td> --%>
				<%-- 									<td>${student.getApellido()}</td> --%>
				<%-- 									<td>${student.getLocalidad()}</td> --%>
				<%-- 									<td>${student.getEdad()}</td> --%>
				<%-- 									<td>${student.getSexo()}</td> --%>
				<%-- 									<td>${student.getPromedio()}</td> --%>
				<!-- 								</tr> -->
				<%-- 							</c:forEach> --%>
				<!-- 						</tbody> -->
				<!-- 					</table> -->
				<!-- 				</div> -->
			</div>
		</div>

		<br>
		<div></div>
		<div class="row">
			<div class="col-5">
				<h2>Busqueda por:</h2>
			</div>
			<div class="col-7">
				<h2>Resultados de búsqueda</h2>
			</div>
			<div>
				<h2 class="text-danger">${noResultSearch.getNombre()}</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-4">
				<form:form action="search.student" method="POST">
					<table>
						<tr>
							<td>Nombre</td>
							<td><input id="nombreBusqueda" name="nombre" /></td>
						</tr>
						<tr>
							<td>Apellido</td>
							<td><input id="apellidoBusqueda" name="apellido" /></td>
						</tr>
						<tr>
							<td>Localidad</td>
							<td><input id="localidadBusqueda" name="localidad" /></td>
						</tr>
						<tr>
							<td>Edad</td>
							<td>
								<div>
									<input id="edadBusqueda" class="half-left-input" name="edadMinima"
										placeholder="Edad mínima" />
								</div>
								<div>
									<input class="half-right-input" name="edadMaxima"
										placeholder="Edad máxima" />
								</div>
							</td>
						</tr>
						<tr>
							<td>Sexo</td>
							<td><input id="sexoBusqueda" placeholder="M/F" name="sexo" /></td>
						</tr>
						<tr>
							<td>Promedio</td>
							<td><input id="promedioBusqueda" name="promedio" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" id="buscar"
								value="Buscar" /></td>
						</tr>
					</table>
				</form:form>
			</div>

			<div class="col-8">
			<table id="grid"></table>
<!-- 				<div class="table-wrapper-scroll-y"> -->
<!-- 					<table border="1" -->
<!-- 						class="table-bordered table-striped table table-fixed"> -->
<!-- 						<thead> -->
<!-- 							<tr> -->
<!-- 								<th scope="col">ID</th> -->
<!-- 								<th scope="col">Nombre</th> -->
<!-- 								<th scope="col">Apellido</th> -->
<!-- 								<th scope="col">Localidad</th> -->
<!-- 								<th scope="col">Edad</th> -->
<!-- 								<th scope="col">Sexo</th> -->
<!-- 								<th scope="col">Promedio</th> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<%-- 							<c:forEach items="${studentListBusqueda}" var="student"> --%>
<!-- 								<tr> -->
<%-- 									<th scope="row">${student.studentId}</th> --%>
<%-- 									<td>${student.getNombre()}</td> --%>
<%-- 									<td>${student.getApellido()}</td> --%>
<%-- 									<td>${student.getLocalidad()}</td> --%>
<%-- 									<td>${student.getEdad()}</td> --%>
<%-- 									<td>${student.getSexo()}</td> --%>
<%-- 									<td>${student.getPromedio()}</td> --%>
<!-- 								</tr> -->
<%-- 							</c:forEach> --%>
<!-- 						</tbody> -->
<!-- 					</table> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>



	

</body>
</html>

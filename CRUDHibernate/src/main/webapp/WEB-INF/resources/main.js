//	function cambiarPagina(){
//		location.replace("/jsp/primefacestest.jsp")
//	}

function configurarGrilla(){
			$("#jgrid").jqGrid({
				url : "AjaxGetAllStudents",
				contentType: "application/json",
				datatype: "json",
				styleUI : 'Bootstrap4',
				iconSet : 'Octicons',
				mtype : 'GET',
				colModel : [ 
				    {label : 'ID', name : 'studentId', width : 50, key : true}, 
				    {label : 'Nombre', name : 'nombre', width : 130, classes:""}, 
				    {label : 'Apellido', name : 'apellido', width : 130}, 
				    {label : 'Localidad', name : 'localidad', width : 130}, 
				    {label : 'Edad', name : 'edad', width : 80},
				    {label : 'Sexo', name : 'sexo', width : 80}, 
				    {label : 'Promedio', name : 'promedio', width : 130} 
				 ],
				 viewrecords: true,
		  		height: 250,
			});
		}
	
	function addAjax(){
		
		var formData = {
				nombre : $("#Nombre").val(),
				apellido : $("#Apellido").val(),
				localidad : $("#Localidad").val(),
				edad: $("#Edad").val(),
				sexo : $("#Sexo").children("option:selected").val(),
				promedio: $("#Promedio").val()
		}
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "AjaxAdd",
		data : JSON.stringify(formData),
		dataType : 'json',
		success : function(result) {
			
			$("#jgrid").trigger("reloadGrid");
			
		}, error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
	
	}
	
function editAjax(){
		
		var formData = {
				studentId : $("#studentId").val(),
				nombre : $("#Nombre").val(),
				apellido : $("#Apellido").val(),
				localidad : $("#Localidad").val(),
				edad: $("#Edad").val(),
				sexo : $("#Sexo").children("option:selected").val(),
				promedio: $("#Promedio").val()
		}
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "AjaxEdit",
		data : JSON.stringify(formData),
		dataType : 'json',
		success : function(result) {
			
			$("#jgrid").trigger("reloadGrid");
			
		}, error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}

function deleteAjax(){
	
	var formData = {
			studentId : $("#studentId").val(),
			nombre : $("#Nombre").val(),
			apellido : $("#Apellido").val(),
			localidad : $("#Localidad").val(),
			edad: $("#Edad").val(),
			sexo : $("#Sexo").children("option:selected").val(),
			promedio: $("#Promedio").val()
	}

$.ajax({
	type : "POST",
	contentType : "application/json",
	url : "AjaxDelete",
	data : JSON.stringify(formData),
	dataType : 'json',
	success : function(result) {
		
		$("#jgrid").trigger("reloadGrid");
		
	}, error : function(e) {
		alert("Error!")
		console.log("ERROR: ", e);
	}
});
};

function busquedaAjax(){
	
	var formData = {
			nombre : $("#nombreBusqueda").val(),
			apellido : $("#apellidoBusqueda").val(),
			localidad : $("#localidadBusqueda").val(),
			edad: $("#edadBusqueda").val(),
			sexo : $("#sexoBusqueda").children("option:selected").val(),
			promedio: $("#promedioBusqueda").val()
	}

$.ajax({
	type : "POST",
	contentType : "application/json",
	url : "busquedaAjax",
	data : JSON.stringify(formData),
	dataType : 'json',
	success : function(result) {
		
		jQuery("#grid")
	    .jqGrid('clearGridData').jqGrid('setGridParam',
	        { 
	            datatype: 'local',
	            data: result
	        })
	    .trigger("reloadGrid");
		
		$("#grid").jqGrid({
			data: result,
			datatype: "local",
			styleUI : 'Bootstrap4',
			iconSet : 'Octicons',
			colModel : [ 
			    {label : 'ID', name : 'studentId', width : 50, key : true}, 
			    {label : 'Nombre', name : 'nombre', width : 130, classes:""}, 
			    {label : 'Apellido', name : 'apellido', width : 130}, 
			    {label : 'Localidad', name : 'localidad', width : 130}, 
			    {label : 'Edad', name : 'edad', width : 80},
			    {label : 'Sexo', name : 'sexo', width : 80}, 
			    {label : 'Promedio', name : 'promedio', width : 130} 
			 ],
			 viewrecords: true,
	  		height: 250,
		});
		
		
		
	}, error : function(e) {
		alert("Error!")
		console.log("ERROR: ", e);
	}
});
};

$(document).ready(function() {
	configurarGrilla();
	
			$("#add").click(
					function() {
						if ($("#Nombre").val() == ""
								|| $("#Apellido").val() == ""
								|| $("#Localidad").val() == ""
								|| $("#Sexo option:selected").val() == "T"
								|| $("#Edad").val() <= 12
								|| $("#Promedio").val() <= 0) {
							alert("No ha ingresado los datos correctos.");
						} else if ($("#Sexo option:selected").val() == "T") {
							alert("Debe seleccionar un sexo.");
						}
					});

			$("#edit").click(
					function() {
						if ($("#studentId").val() <= 0) {
							alert("Debe seleccionar el ID que desea editar.")
						} else if ($("#Nombre").val() == ""
								&& $("#Apellido").val() == ""
								&& $("#Localidad").val() == ""
								&& $("#Sexo option:selected").val() == "T"
								&& $("#Edad").val() <= 12
								&& $("#Promedio").val() <= 0) {
							alert("Debe ingresar el dato que desea editar.");
						}
					});

			// CONTROLADOR AJAX
			
			
			$("#getBtn").click(function(){
				configurarGrilla();
			});
			
			$("#add").click(function(event){
				event.preventDefault();
				addAjax()
			});
			
			$("#edit").click(function(event){
				event.preventDefault();
				editAjax()
			});
			
			$("#delete").click(function(event){
				event.preventDefault();
				deleteAjax()
			});
			
			$("#buscar").click(function(event){
				event.preventDefault();
				busquedaAjax()
			});

		});
 // termina el ready

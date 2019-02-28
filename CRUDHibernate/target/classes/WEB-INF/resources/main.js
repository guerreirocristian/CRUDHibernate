$(document).ready(function(){
	
	$("#add").click(function(){
	if( $("#Nombre").val() == "" ||  $("#Apellido").val() == "" || $("#Localidad").val() == "" || $("Sexo").val() == "Elegi Uno") 
	{
		alert("No ha ingresado los datos correctos. Ingrese el campo que corresponda");
	}
	});
	
	
}); //termina el ready
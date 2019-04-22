package main.java.com.ar.cda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.com.ar.cda.model.Student;
import main.java.com.ar.cda.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	

	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map) {
		Student student = new Student();
		map.put("student", student);
		map.put("studentList", studentService.getAllStudents());
		return "student";
	}

	@RequestMapping(value = "/student.do", method = RequestMethod.POST)
	public String doActions(@ModelAttribute Student student, BindingResult results, @RequestParam String action,
			@RequestParam Integer id, @RequestParam String Nombre, @RequestParam String Apellido,
			@RequestParam String Localidad, @RequestParam Integer Edad, @RequestParam String Sexo,
			@RequestParam double Promedio, Map<String, Object> map) {

		if (id == null) {
			id = 0;
		}
		Student studentFinal = new Student();
		Student error = new Student();
		
		int actionInt=0;
		if(action.equals("Add")){actionInt = 1;}
		else if(action.equals("Delete")){actionInt=2;}
		else if(action.equals("Edit")){actionInt= 3;};
		
		switch (actionInt) {

		case 1:
			if (student.getApellido().equals("") || student.getNombre().equals("") || student.getLocalidad().equals("")
					|| student.getSexo().equals("T") || student.getEdad() <= 12) {
				break;
			} else if (student.getSexo().equals("M") || student.getSexo().equals("F")) {
				// si seleccionó un sexo, añade los datos a la base
				studentService.add(student);
				studentFinal = student;
				break;
			}

		case 2:
			studentService.delete(student.getId());
			break;

		case 3:

			if (id <= 0) {
				break;
			} else if (Nombre == "" && Apellido == "" && Localidad == "" && Sexo == "" && Edad <= 12 && Promedio <= 0.0 && Promedio >= 10) {
				break;
			} else {
				studentFinal = studentService.getStudent(id);
			}

			if (studentFinal == null) {
				studentFinal = new Student();
				studentFinal.setNombre("Ese id no corresponde con ningún estudiante.");
				map.put("errorId", studentFinal);
				break;}
			if (Nombre != "") {
				studentFinal.setNombre(Nombre);}
			if (Apellido != "") {
				studentFinal.setApellido(Apellido);}
			if (Localidad != "") {
				studentFinal.setLocalidad(Localidad);}
			if (Edad > 12) {
				studentFinal.setEdad(Edad);}
			if (!Sexo.equals("T")) {
				studentFinal.setSexo(Sexo);}
			if (Promedio > 0) {
				studentFinal.setPromedio(Promedio);}
			studentService.edit(studentFinal);
			break;
		}

		map.put("error", error);
		map.put("studentA", studentFinal);

		// Recarga la lista cada que llama al controlador
		map.put("studentList", studentService.getAllStudents());

		return "student";

	}

	@RequestMapping(value = "/search.student", method = RequestMethod.POST)
	public String controllerBusqueda(@ModelAttribute Student student, BindingResult results,
			@RequestParam String nombre, @RequestParam String apellido, @RequestParam Integer edadMinima,
			@RequestParam Integer edadMaxima, @RequestParam String localidad, @RequestParam String sexo,
			@RequestParam Double promedio, Map<String, Object> map) {
		List<Student> studentBusqueda = new ArrayList<Student>();

		if (nombre != "") {
			studentBusqueda = studentService.busqueda("Nombre", nombre);
		} else if (apellido != "") {
			studentBusqueda = studentService.busqueda("Apellido", apellido);
		} else if (localidad != "") {
			studentBusqueda = studentService.busqueda("Localidad", localidad);
		} else if (edadMinima != null) {
			if (edadMinima > 12 && edadMaxima == null) {
				studentBusqueda = studentService.busquedaEdad("Edad", edadMinima);
			} else if (edadMinima > 12 && edadMaxima != null) {
				studentBusqueda = studentService.busquedaRangoEdad("Edad", edadMinima, edadMaxima);
			}
		} else if (sexo != "T" && sexo != "") {
			studentBusqueda = studentService.busqueda("Sexo", sexo);
		} else {
			studentBusqueda = studentService.busquedaPromedio("Promedio", promedio);}
		
		if (studentBusqueda.isEmpty()) { map.put("noResultSearch", new Student("No se encontraron resultados."));}
		map.put("studentListBusqueda", studentBusqueda);

		// Recarga la lista cada que llama al controlador
		map.put("studentList", studentService.getAllStudents());

		return "student";
	}
	
	
	//CONTROLADOR NUEVO PARA AJAX
	
	@RequestMapping(value = "AjaxGetAllStudents", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> ajaxGet() {
		
		List<Student> stu = new ArrayList<Student>();
		stu.addAll(studentService.getAllStudents());
		
		return stu;	
	}
	
	@RequestMapping(value = "AjaxAdd", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> ajaxAdd(@RequestBody Student student) {
		
		List<Student> stu = new ArrayList<Student>();
		if (student.getApellido().equals("") || student.getNombre().equals("") || student.getLocalidad().equals("")
				|| student.getSexo().equals("T") || student.getEdad() <= 12) {
			
		} else if (student.getSexo().equals("M") || student.getSexo().equals("F")) {
			// si seleccionó un sexo, añade los datos a la base
			studentService.add(student);
		
		stu.addAll(studentService.getAllStudents());
		
		
	}
		return stu;
	}
	
	@RequestMapping(value = "AjaxEdit", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> ajaxEdit(@RequestBody Student student) {
		
		List<Student> stu = new ArrayList<Student>();
		Student studentFinal = new Student();
		
		if (student.getId() <= 0) {
		} else if (student.getNombre() == "" && student.getApellido() == "" && student.getLocalidad() == "" && student.getSexo() == "" && student.getEdad() <= 12 && student.getPromedio() <= 0.0 && student.getPromedio() >= 10) {
		
		} else {
			studentFinal = studentService.getStudent(student.getId());
		}
		
		if (student.getNombre() != "") {
			studentFinal.setNombre(student.getNombre());
		}
		if (student.getApellido() != "") {
			studentFinal.setApellido(student.getApellido());
		}

		if (student.getLocalidad() != "") {
			studentFinal.setLocalidad(student.getLocalidad());
		}
		if (student.getEdad() > 12) {
			studentFinal.setEdad(student.getEdad());
		}
		if (!student.getSexo().equals("T")) {
			studentFinal.setSexo(student.getSexo());
		}
		if (student.getPromedio() > 0) {
			studentFinal.setPromedio(student.getPromedio());
		}
		studentService.edit(studentFinal);
		
		stu.addAll(studentService.getAllStudents());
		
		return stu;
	}
	
	@RequestMapping(value = "AjaxDelete", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> ajaxDelete(@RequestBody Student student) {
		
		List<Student> stu = new ArrayList<Student>();
		studentService.delete(student.getId());
		stu.addAll(studentService.getAllStudents());
	
		return stu;
	}
	
	@RequestMapping(value = "busquedaAjax", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> busquedaAjax(@RequestBody Student student) {
		List<Student> studentBusqueda = new ArrayList<Student>();

		if (student.getNombre() != "") {
			studentBusqueda = studentService.busqueda("nombre", student.getNombre());
		} else if (student.getApellido() != "") {
			studentBusqueda = studentService.busqueda("apellido", student.getApellido());
		} else if (student.getLocalidad() != "") {
			studentBusqueda = studentService.busqueda("localidad", student.getLocalidad());
		} else if (student.getEdad() > 12) {
				studentBusqueda = studentService.busquedaEdad("edad", student.getEdad());
		} else if (student.getSexo() == "M" || student.getSexo() == "F") {
			studentBusqueda = studentService.busqueda("sexo", student.getSexo());
		} else {
			studentBusqueda = studentService.busquedaPromedio("promedio", student.getPromedio());}
		
		return studentBusqueda;
	}
	
	
	
}

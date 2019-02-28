package main.java.com.ar.cda.service;

import java.util.List;

import main.java.com.ar.cda.model.Student;

public interface StudentService 
{
	public void add(Student student);

	public void edit(Student student);

	public void delete(int studentId);

	public Student getStudent(int studentId);

	public List<Student> getAllStudents();
	
	public List<Student> busqueda(String columna, String Letra); 
}
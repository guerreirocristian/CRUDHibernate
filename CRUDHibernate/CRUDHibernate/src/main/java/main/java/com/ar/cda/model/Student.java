package main.java.com.ar.cda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tablaPruebas")
public class Student 
{
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String localidad;
	
	@Column
	private String sexo;
	
	@Column
	private int edad;
	
	@Column
	private double promedio;
	
	
	public Student(int id, String nombre, String apellido, String localidad, String sexo, int edad, double promedio) 
	{
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.localidad = localidad;
		this.sexo = sexo;	
		this.edad = edad;
		this.promedio = promedio;
	}
	
	public Student(String nombre) {
		this.nombre = nombre;
	}

	public Student() {}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getApellido() 
	{
		return apellido;
	}
	
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	
	public String getLocalidad() 
	{
		return localidad;
	}
	
	public void setLocalidad(String localidad) 
	{
		this.localidad = localidad;
	}
	
	public String getSexo() 
	{
		return sexo;
	}
	
	public void setSexo(String sexo) 
	{
		this.sexo = sexo;
	}
	public void setEdad(int edad)
	{
		this.edad = edad;
	}
	public int getEdad()
	{
		return this.edad;
	}
	
	public void setPromedio(double promedio)
	{
		this.promedio = promedio;
	}
	public double getPromedio()
	{
		return this.promedio;
	}

}


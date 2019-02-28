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
	private String Nombre;
	
	@Column
	private String Apellido;
	
	@Column
	private String Localidad;
	
	@Column
	private String Sexo;
	
//	@Column
//	private int añoCursada;
	
	public Student(int studentId, String nombre, String apellido, String localidad, String sexo) 
	{
		this.id = studentId;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Localidad = localidad;
		this.Sexo = sexo;
		//this.añoCursada = añoCursada;
	}

	public Student() {}
	
	public int getStudentId() 
	{
		return id;
	}
	
	public void setStudentId(int studentId) 
	{
		this.id = studentId;
	}
	
	public String getNombre() 
	{
		return Nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.Nombre = nombre;
	}
	
	public String getApellido() 
	{
		return Apellido;
	}
	
	public void setApellido(String apellido) 
	{
		this.Apellido = apellido;
	}
	
	public String getLocalidad() 
	{
		return Localidad;
	}
	
	public void setLocalidad(String localidad) 
	{
		this.Localidad = localidad;
	}
	
	public String getSexo() 
	{
		return Sexo;
	}
	
	public void setSexo(String sexo) 
	{
		this.Sexo = sexo;
	}


	
	
//	public int getAnioCursada() 
//	{
//		return añoCursada;
//	}
//	
//	public void setAnioCursada(int añoCursada) 
//	{
//		this.añoCursada = añoCursada;
//	}
}

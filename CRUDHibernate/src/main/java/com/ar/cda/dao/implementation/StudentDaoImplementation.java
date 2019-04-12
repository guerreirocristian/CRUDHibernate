package main.java.com.ar.cda.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.com.ar.cda.dao.StudentDao;
import main.java.com.ar.cda.model.Student;

@Repository
public class StudentDaoImplementation implements StudentDao 
{
	@Autowired
	private SessionFactory session;
	private Criteria criteria;
	
	private void iniciarCriteria()
	{
		 this.criteria = session.getCurrentSession().createCriteria(Student.class);
	}

	@Override
	public void add(Student student) 
	{
		session.getCurrentSession().save(student);
	}

	@Override
	public void edit(Student student) 
	{
		session.getCurrentSession().update(student);
	}

	@Override
	public void delete(int studentId) 
	{
		session.getCurrentSession().delete(this.getStudent(studentId));
	}

	@Override
	public Student getStudent(int studentId) 
	{
		return (Student) session.getCurrentSession().get(Student.class, studentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() 
	{
		return session.getCurrentSession().createQuery("from Student").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> busqueda(String columna, String consulta) //Busca Students 
	{ 
		iniciarCriteria();
		return this.criteria.add(Restrictions.like(columna, consulta + "%", MatchMode.ANYWHERE)).list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> busquedaEdad(String columna, int consultaEdad) //Busca Students por edad 
	{ 
		iniciarCriteria();
		return this.criteria.add(Restrictions.like(columna, consultaEdad)).list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> busquedaRangoEdad(String columna, int consultaEdadMinima, int consultaEdadMaxima) //Busca Students por edad 
	{ 
		iniciarCriteria();
		return this.criteria.add(Restrictions.between(columna, consultaEdadMinima, consultaEdadMaxima)).list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> busquedaPromedio(String columna, double promedio)
	{
		iniciarCriteria();
		return this.criteria.add(Restrictions.between(columna, promedio, 9.99)).list();
	}
	
}

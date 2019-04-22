package main.java.com.ar.cda.model;

import java.util.Map;

public class StudentResponse {

	private Object obj;
	private boolean validation;
	private Map<String,String> errorMessage;
	
	public StudentResponse (Object objeto, boolean validation) {
		this.obj = objeto;
		this.validation = validation;
	}
	
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	public Map<String,String> getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(Map<String,String> errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getObjetc() {
		return obj;
	}
	public void setObject(Object obj) {
		this.obj = obj;
	}
	
}

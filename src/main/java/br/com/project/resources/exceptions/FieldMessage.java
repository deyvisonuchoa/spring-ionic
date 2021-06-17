package br.com.project.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = -3516116547093037659L;
	
	private String fieldName;
	private String message;
	
	public FieldMessage(String fiedName, String message) {
		super();
		this.fieldName = fiedName;
		this.message = message;
	}
	
	public FieldMessage() {
		super();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fiedName) {
		this.fieldName = fiedName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

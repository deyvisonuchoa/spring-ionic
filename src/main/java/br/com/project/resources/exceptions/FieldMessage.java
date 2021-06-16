package br.com.project.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = -3516116547093037659L;
	
	private String fiedName;
	private String message;
	
	public FieldMessage(String fiedName, String message) {
		super();
		this.fiedName = fiedName;
		this.message = message;
	}
	
	public FieldMessage() {
		super();
	}

	public String getFiedName() {
		return fiedName;
	}

	public void setFiedName(String fiedName) {
		this.fiedName = fiedName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

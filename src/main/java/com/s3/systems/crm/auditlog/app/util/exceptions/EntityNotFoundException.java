package com.s3.systems.crm.auditlog.app.util.exceptions;

public class EntityNotFoundException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	public EntityNotFoundException(String message) {
		super(message);
	}
	
}

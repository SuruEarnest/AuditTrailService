package com.s3.systems.crm.auditlog.app.util.exceptions;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForbiddenException(String message) {
		super(message);
	}
}
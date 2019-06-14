package com.s3.systems.crm.auditlog.app.util.exceptions;

public class UnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UnauthorizedException(String message) {
		super(message);
	}
}

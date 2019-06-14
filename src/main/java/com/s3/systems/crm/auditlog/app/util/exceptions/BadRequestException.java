package com.s3.systems.crm.auditlog.app.util.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}

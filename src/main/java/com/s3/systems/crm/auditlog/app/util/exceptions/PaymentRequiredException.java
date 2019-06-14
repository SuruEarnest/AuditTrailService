package com.s3.systems.crm.auditlog.app.util.exceptions;

public class PaymentRequiredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public PaymentRequiredException(String message) {
		super(message);
	}
}

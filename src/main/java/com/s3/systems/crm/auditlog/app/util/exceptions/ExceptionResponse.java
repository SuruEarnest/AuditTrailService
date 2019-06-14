package com.s3.systems.crm.auditlog.app.util.exceptions;

import java.util.Date;    

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private final HttpStatus status;
	private final String errorCode;
	private final String message;
	private final String description;
	private final Date timestamp;

	private ExceptionResponse(ExceptionResponseBuilder builder) {
		this.status = builder.status;
		this.errorCode = builder.errorCode;
		this.message = builder.message;
		this.description = builder.description;
		this.timestamp = builder.timestamp;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getMessage() {
		return this.message;
	}

	public String getDescription() {
		return this.description;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public static final class ExceptionResponseBuilder {
		private HttpStatus status;
		private String errorCode;
		private String message;
		private String description;
		private Date timestamp;

		public ExceptionResponseBuilder withStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public ExceptionResponseBuilder withErrorCode(String errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public ExceptionResponseBuilder withMessage(String message) {
			this.message = message;
			return this;
		}

		public ExceptionResponseBuilder withDetail(String detail) {
			this.description = detail;
			return this;
		}

		public ExceptionResponseBuilder withTimestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ExceptionResponse build() {
			return new ExceptionResponse(this);
		}
	}
}

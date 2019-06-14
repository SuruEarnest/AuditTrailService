package com.s3.systems.crm.auditlog.app.util.exceptions;

import java.util.Date;   
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.s3.systems.crm.auditlog.app.util.exceptions.ExceptionResponse.ExceptionResponseBuilder;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	// server error 500-internal server error
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

		ExceptionResponse response = new ExceptionResponseBuilder().withDetail(request.getDescription(false))
				.withErrorCode("500").withMessage(ex.getMessage()).withTimestamp(new Date())
				.withStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return new ResponseEntity<>(response, response.getStatus());
	}

	// error 404-resource not found
	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponseBuilder().withDetail(request.getDescription(false))
				.withErrorCode("404").withMessage(ex.getMessage()).withTimestamp(new Date())
				.withStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response, response.getStatus());
	}

	// error 400 - bad request error
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponseBuilder().withDetail(request.getDescription(false))
				.withErrorCode("400").withMessage(ex.getMessage()).withTimestamp(new Date())
				.withStatus(HttpStatus.BAD_REQUEST).build();
		return new ResponseEntity<>(response, response.getStatus());
	}

	// error 401 - Unauthorised request
	@ExceptionHandler(UnauthorizedException.class)
	public final ResponseEntity<ExceptionResponse> handleUnauthorizedException(UnauthorizedException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponseBuilder().withDetail(request.getDescription(false))
				.withErrorCode("401").withMessage(ex.getMessage()).withTimestamp(new Date())
				.withStatus(HttpStatus.UNAUTHORIZED).build();
		return new ResponseEntity<>(response, response.getStatus());
	}

	@ExceptionHandler(ForbiddenException.class)
	public final ResponseEntity<ExceptionResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponseBuilder().withDetail(request.getDescription(false))
				.withErrorCode("403").withMessage(ex.getMessage()).withTimestamp(new Date())
				.withStatus(HttpStatus.FORBIDDEN).build();
		return new ResponseEntity<>(response, response.getStatus());
	}

	@ExceptionHandler(PaymentRequiredException.class)
	public final ResponseEntity<ExceptionResponse> handlePaymentRequiredException(PaymentRequiredException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponseBuilder().withDetail(request.getDescription(false))
				.withErrorCode("402").withMessage(ex.getMessage()).withTimestamp(new Date())
				.withStatus(HttpStatus.PAYMENT_REQUIRED).build();
		return new ResponseEntity<>(response, response.getStatus());
	}

}

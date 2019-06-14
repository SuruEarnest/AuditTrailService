package com.s3.systems.crm.auditlog.app.util.exceptions;

import java.util.HashMap;     
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class ErrorResponseUtil {

	public Map<String, String> getErrorResponseMap(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			System.out.println("Error Message #==>"+errorMessage);
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}

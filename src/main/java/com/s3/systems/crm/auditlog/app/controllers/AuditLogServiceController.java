package com.s3.systems.crm.auditlog.app.controllers;

import java.util.List; 
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.s3.systems.crm.auditlog.app.brokers.MessageBrokerService;
import com.s3.systems.crm.auditlog.app.models.AuditLogEntity;
import com.s3.systems.crm.auditlog.app.models.User;
import com.s3.systems.crm.auditlog.app.services.AuditLogService;
import com.s3.systems.crm.auditlog.app.util.exceptions.ErrorResponseUtil;

@RestController
@RequestMapping(value = "/api/v1/audit")
public class AuditLogServiceController {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private MessageBrokerService messageBrokerService;
	@Autowired
	private ErrorResponseUtil errorResponseUtil;

	@GetMapping(value = "/logs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<AuditLogEntity<?>>> getAllAuditLogsByPage(
			@RequestParam("page") int pageNumber, @RequestParam("size") int size) {
		List<AuditLogEntity<?>> allAuditLogs = auditLogService.findAllAuditLogsByPage(pageNumber, size);
		if (allAuditLogs.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(allAuditLogs);
		}
		return ResponseEntity.status(HttpStatus.OK).body(allAuditLogs);

	}

	@GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<AuditLogEntity<?>>> getPagedAuditLogsByUser(
			@RequestParam("page") int pageNumber, @RequestParam("size") int size, @RequestBody User user) {
		List<AuditLogEntity<?>> pagedAuditLogs = auditLogService.findAuditLogByUser(user, pageNumber, size);
		if (pagedAuditLogs.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pagedAuditLogs);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pagedAuditLogs);
	}

	@PostMapping(value = "/publish", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> publishAuditLogs(@Valid @RequestBody AuditLogEntity<?> auditLog, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Map<String, String>>(errorResponseUtil.getErrorResponseMap(result),
					HttpStatus.BAD_REQUEST);
		} else {
			messageBrokerService.send(auditLog);
			return ResponseEntity.status(HttpStatus.OK).body("successfully published to audit-trail queue");

		}
	}

}

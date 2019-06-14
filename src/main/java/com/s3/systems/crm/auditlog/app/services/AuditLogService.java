package com.s3.systems.crm.auditlog.app.services;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.s3.systems.crm.auditlog.app.models.AuditLogEntity;
import com.s3.systems.crm.auditlog.app.models.User;
import com.s3.systems.crm.auditlog.app.repositories.AuditLogRepository;
import com.s3.systems.crm.auditlog.app.util.exceptions.BadRequestException;

@Service
public class AuditLogService {

	@Autowired
	private AuditLogRepository auditLogRepo;

	public void createAuditLog(AuditLogEntity<?> auditLog) {
		auditLogRepo.save(auditLog);
	}

	public void deleteAll() {
		auditLogRepo.deleteAll();
	}

	public List<AuditLogEntity<?>> findAuditLogByUser(User user, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("actionTimeStamp").ascending());

		if (page <= 0) {
			throw new BadRequestException("Bad Request!Page index must not be zero or less.");
		} else if (user.equals(null) || user.getUserId() == 0 || user.getUsername().isEmpty()) {
			throw new BadRequestException("Bad Request!Please pass the appropriate user entity fields.");
		}
		return auditLogRepo.findByCreator(user, pageable);
	}

	public List<AuditLogEntity<?>> findAllAuditLogsByPage(int page, int size) {
		if (page <= 0) {
			throw new BadRequestException("Bad Request!Page index must not be zero or less.");
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("actionTimeStamp").ascending());
		Page<AuditLogEntity<?>> auditLogPage = auditLogRepo.findAll(pageable);
		return auditLogPage.getContent();
	}

	public List<AuditLogEntity<?>> findAllAuditLogs() {
		List<AuditLogEntity<?>> allAuditLogs = auditLogRepo.findAll();
		return allAuditLogs;
	}
}

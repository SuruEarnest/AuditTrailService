package com.s3.systems.crm.auditlog.app.repositories;
import java.util.Date;  
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.s3.systems.crm.auditlog.app.models.AuditLogEntity;
import com.s3.systems.crm.auditlog.app.models.User;

@Repository
public interface AuditLogRepository extends PagingAndSortingRepository<AuditLogEntity<?>,Long> {
	List<AuditLogEntity<?>> findByCreator(User creator,Pageable page);
	Page<AuditLogEntity<?>> findAll(Pageable page);
	List<AuditLogEntity<?>> findByCreatorAndActionTimeStampBetween(User creator,Date actionTimeStampStart,Date timeStampEnd,Pageable page);
	List<AuditLogEntity<?>> findAll();

}

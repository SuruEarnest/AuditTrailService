package com.s3.systems.crm.auditlog.app.brokers;

import com.s3.systems.crm.auditlog.app.models.AuditLogEntity;

public interface MessageBroker {
	public abstract void send(final AuditLogEntity<?> auditLog);
	public abstract void recieve(); 
}

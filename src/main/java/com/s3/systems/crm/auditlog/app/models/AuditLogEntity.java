package com.s3.systems.crm.auditlog.app.models;

import java.util.Date;     
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import com.s3.systems.crm.auditlog.app.constants.ActionType;;

@Document(collection = "audit_log")
public class AuditLogEntity<E> {
	@Transient
    private static final String SEQUENCE_NAME = "auditlog_sequence";
	@Id
	private long auditLogEntityId;
	@NotNull(message = "Creator Field is mandatory")
	private User creator;
	private User lastModifier;

	@NotNull(message = "Timestamp is mandatory")
	private Date actionTimeStamp;

	@NotNull(message = "Please specify action type.")
	private ActionType actionType;

	@NotNull(message = "Please specify entity to be logged by audit-trail service")
	private E entity;

	@NotEmpty(message = "Please specify the entity name")
	private String entityName;
	
	@NotNull(message = "Please specify audit-trail action description.")
	private String description;

	public AuditLogEntity() {
	}

	public long getAuditLogEntityId() {
		return this.auditLogEntityId;
	}

	public void setAuditLogEntityId(long auditLogEntityId) {
		this.auditLogEntityId = auditLogEntityId;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getLastModifier() {
		return this.lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	public ActionType getActionType() {
		return this.actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public E getEntity() {
		return this.entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public Date getActionTimeStamp() {
		return this.actionTimeStamp;
	}

	public void setActionTimeStamp(Date actionTimeStamp) {
		this.actionTimeStamp = actionTimeStamp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static final String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
	

}

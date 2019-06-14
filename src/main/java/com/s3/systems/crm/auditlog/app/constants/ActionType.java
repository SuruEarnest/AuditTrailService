package com.s3.systems.crm.auditlog.app.constants;

public enum ActionType {
   
	CREATE("Create"),
	UPDATE("Update"),
	DELETE("Delete");
	
	private String actionDescription;
	
	private ActionType(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	public String getActionDescription() {
		return this.actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}
	
	
}

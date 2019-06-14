package com.s3.systems.crm.auditlog.app;

import java.util.ArrayList; 
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import com.google.gson.Gson;
import com.s3.systems.crm.auditlog.app.constants.ActionType;
import com.s3.systems.crm.auditlog.app.models.AuditLogEntity;
import com.s3.systems.crm.auditlog.app.models.User;
import com.s3.systems.crm.auditlog.app.services.AuditLogService;
import com.s3.systems.crm.auditlog.app.services.SequenceGeneratorService;

@EnableJms
@Configuration
@ComponentScan("com.s3.systems.crm.*")
@SpringBootApplication
public class AuditTrailServiceApplication implements CommandLineRunner {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private Gson gson;
	@Autowired
	private SequenceGeneratorService seqGenService;

	public static void main(String[] args) {
		SpringApplication.run(AuditTrailServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("running main method...");
		// create sample log...
		AuditLogEntity<ArrayList<String>> auditLog = new AuditLogEntity<>();
		auditLog.setAuditLogEntityId(seqGenService.getNextSequenceId(AuditLogEntity.getSequenceName()));
		auditLog.setActionTimeStamp(new Date());
		auditLog.setActionType(ActionType.UPDATE);
		auditLog.setCreator(new User("Earnest", "password1", "Operations"));
		//
		ArrayList<String> paystuff = new ArrayList<>();
		paystuff.add("paymentz");
		paystuff.add("500,000.00");
		//
		auditLog.setEntity(paystuff);
		auditLog.setEntityName("ArrayList for the boyz");
		//
		auditLog.setDescription("" + auditLog.getCreator().getUsername()+ " updated " + auditLog.getEntityName());
		//
		auditLogService.createAuditLog(auditLog);
		System.out.println("saved to auditrailz");
		//
		List<AuditLogEntity<?>> auditLogs = auditLogService.findAllAuditLogs();
		auditLogs.forEach(a -> System.out.println("AuditLog:::>>>" + gson.toJson(a)));

	}

}

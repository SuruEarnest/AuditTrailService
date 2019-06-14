package com.s3.systems.crm.auditlog.app.services;

import java.util.Objects;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.s3.systems.crm.auditlog.app.models.DatabaseSequence;
import com.s3.systems.crm.auditlog.app.util.exceptions.SequenceException;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperation;

	public long getNextSequenceId(String key) throws SequenceException {
		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));
		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);
		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.upsert(true);
		options.returnNew(true);
		// this is the magic happened.
		DatabaseSequence counter = mongoOperation.findAndModify(query, update, options, DatabaseSequence.class);
		// if no id, throws SequenceException
		if (counter == null) {
			throw new SequenceException("Failed to generate next sequence Id.You might need to ensure that a database sequence collection is created manually.");
		}
		// optional, just a way to tell user when the sequence id is failed to generate.
		return !Objects.isNull(counter) ? counter.getSeq() : 1;

	}
}

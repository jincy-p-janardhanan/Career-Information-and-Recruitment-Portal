package com.cirp.app.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cirp.app.model.User;

@Component
public class ScheduledTasks {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Scheduled(cron = "0 0 12 * * ?") // Scheduled to run at 12 PM everyday
	public void deleteRejectedRegistrations() {
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		calender.add(Calendar.DATE, -14);
		mongoTemplate.findAllAndRemove(new Query(where("status_changed").is(calender.getTime())), User.class);
	}
	
	public void changeStudentsToAlumni() {
		// TODO Auto-generated method stub

	}
}

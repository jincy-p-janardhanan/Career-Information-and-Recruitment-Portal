/**
 * references: 
 * 		https://www.appsdeveloperblog.com/spring-boot-and-mongotemplate-tutorial-with-mongodb/4
 * 		https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.save-update-remove
 * 
 */

package com.cirp.app.repository;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.*;

/**
 * @author Jincy P Janardhanan
 *
 */
@Repository
public class CirpRepository implements CirpRepositoryOperations {

	@Autowired
	MongoTemplate mongoOps;
	@Autowired
	JavaMailSender send_email;

	@Override
	public void register(College college) {
		mongoOps.insert(college);

		//controller ilnn college nte ella properties um user ntelnn API vazhi vaanghanam
		//ennit ath vech college nte object undaaki register method call cheyya
		
		// ath kazhinj college nte user name ella admins nte college pending list il add <-- ith koode cheyyan nd
		/// cheyya (update)
	}

	@Override
	public void register(Recruiter recruiter) {

		// TODO Auto-generated method stub

	}

	@Override
	public void register(Alumnus alumnus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(Student student) {
		mongoOps.insert(student);
	}

	@Override
	public void login(String username_or_email, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout(String username) {
		
		// TODO Auto-generated method stub

	}

	@Override
	public void resetPassword(String username_or_email) {

		mongoOps.changeUserPassword("username or email", passwordPrompt());
		mongOps.changeUserPassword();

	}

	@Override
	public void updatePassword(String username_or_email, String new_password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewProfile(College college) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewProfile(Recruiter recruiter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewProfile(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editProfile(College college) {
		mongoOps.update(College.class).matching(Criteria.where("username").is(college.getUsername()))
				.replaceWith(college);
	}

	@Override
	public void editProfile(Recruiter recruiter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editProfile(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void optoutRequest(College college) {
		// TODO Auto-generated method stub

	}

	@Override
	public void optoutRequest(Recruiter recruiter) {

		try {
			MimeMessage msg = send_email.createMimeMessage();
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recruiter.getEmail()));
			msg.setSubject("CIRP | Opt Out");
			msg.setContent("<p>Hi,</p>" + "<p></p>"
					+ "<p>To opt out from Career Information and Recruitment Portal, click <a href=\"localhost:8080/optout.html\">here</a>.</p>"
					+ "<p>If you didn't request for opt out, it's possible that your account security is breached.</p>"
					+ "Change your password immediately to secure your account." + "<p></p>" + "<p>Regards,</p>"
					+ "<p>Team CIRP</p>", "text/html");
			send_email.send(msg);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@Override
	public void optoutRequest(Alumnus alumnus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(College college) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Recruiter recruiter) {
		mongoOps.remove(recruiter);

	}

	@Override
	public void deleteUser(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Alumnus alumnus) {

		// TODO Auto-generated method stub

	}

	@Override
	public void sessionTimeout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStudentsToAlumni(Date end_date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createJob(Job job) {
		mongoOps.insert(job);

	}

	@Override
	public Job viewJob(ObjectId id) {
		return mongoOps.findById(id, Job.class);
	}

	@Override
	public void deleteJob(Job job) {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyJob(Application application) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewApplication(Application application) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Application> viewJobApplications(ObjectId job_id) {
		return mongoOps.findById(job_id, Job.class).getApplicants();
	}

	@Override
	public void viewAllApplications(Recruiter recruiter) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Application> searchApplications(String search_text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void requestRecommendation(String requester_id, String recommender_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recommend(Recommendation reccomendation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectRecommendationRequest(Recommendation reccomendation) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<List<T>> search(String search_text) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text.split(" ")));
		// ee null n pakaram ntha kodukkande nn search cheyth nokk tto
		// enghaneyaa initialise cheyya nn

		List<List<T>> l = mongoOps.collection.find();
		l.add(search(search_text, "college"));
		l.add(search(search_text, "student"));
		l.add(search(search_text, "alumnus"));
		l.add(search(search_text, "recruiter"));
		l.add(search(search_text, "job"));
		return (l);
		/*
		 * if(search_text.equals("college")) return(List<id>)
		 * mongoOps.find(query,College.class); else if(search_text.equals("recruiter"))
		 * return(List<id>) mongoOps.find(query,Recruiter.class); else
		 * if(search_text.equals("student")) return(List<id>)
		 * mongoOps.find(query,Student.class); else if(search_text.equals("alumnus"))
		 * return(List<id>) mongoOps.find(query,Alumnus.class); else
		 * if(search_text.equals("job")) return(List<id>)
		 * mongoOps.find(query,Job.class); else return null;
		 */
		// return null; //l
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> search(String search_text, String filter) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text.split(" "))).sortByScore();

		if (filter.equals("college"))
			return (List<T>) mongoOps.find(query, College.class, filter);
		else if (filter.equals("recruiter"))
			return (List<T>) mongoOps.find(query, Recruiter.class, filter);
		else if (filter.equals("student"))
			return (List<T>) mongoOps.find(query, Student.class, filter);
		else if (filter.equals("alumnus"))
			return (List<T>) mongoOps.find(query, Alumnus.class, filter);
		else if (filter.equals("job"))
			return (List<T>) mongoOps.find(query, Job.class, filter);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(String id) { // id refers to username
		if (mongoOps.findById(id, College.class) != null)
			return (T) mongoOps.findById(id, College.class);
		else if (mongoOps.findById(id, Recruiter.class) != null)
			return (T) mongoOps.findById(id, Recruiter.class);
		else if (mongoOps.findById(id, Student.class) != null)
			return (T) mongoOps.findById(id, Student.class);
		else if (mongoOps.findById(id, Alumnus.class) != null)
			return (T) mongoOps.findById(id, Alumnus.class);
		else if (mongoOps.findById(id, Job.class) != null)
			return (T) mongoOps.findById(id, Job.class);
		else
			return null;
	}

	@Override
	public void confirmRegistration(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectRegistration(User user) {
		// TODO Auto-generated method stub

	}
	
	@Override
	@Scheduled(cron = "0 0 12 * * ?")		//Scheduled to run 12 PM everyday
	public void deleteRejectedRegistrations() {
		Calendar calender = Calendar.getInstance();
	    calender.setTime(new Date());
	    calender.add(Calendar.DATE, -14);
		mongoOps.findAllAndRemove(new Query(where("status_changed").is(calender.getTime())), User.class);
		
	}
}

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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.*;

/**
 * @author Jincy P Janardhanan
 *
 */
@Repository
public class CirpRepository implements CirpRepositoryOperations {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	JavaMailSender send_email;

	@Override
	public void register(College college) {
		mongoTemplate.insert(college);
	}

	@Override
	public void register(Recruiter recruiter) {
		mongoTemplate.insert(recruiter);
	}

	@Override
	public void register(Alumnus alumnus) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student register(Student student) {
		return mongoTemplate.insert(student);
	}

	@Override
	public void login(String username_or_email, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout(String username) {
		mongoTemplate.getSiblingDB(user);
		mongoTemplate.logout();
		// TODO Auto-generated method stub

	}

	@Override
	public void resetPassword(String username_or_email) 
			
	}

	@Override
	public void updatePassword(String username_or_email, String new_password) {
		if (findById(username_or_email) instanceof College) {
			mongoTemplate.update(College.class)
					.matching(new Query(new Criteria().orOperator(Criteria.where("email").is(username_or_email),
							Criteria.where("username").is(username_or_email))))
					.apply(new Update().set("password", new_password));
		}
		if (findById(username_or_email) instanceof Recruiter) {
			mongoTemplate.update(Recruiter.class)
			.matching(new Query(new Criteria().orOperator(Criteria.where("email").is(username_or_email),
					Criteria.where("username").is(username_or_email))))
			.apply(new Update().set("password", new_password));
		}
		if (findById(username_or_email) instanceof Student) {
			mongoTemplate.update(Student.class)
			.matching(new Query(new Criteria().orOperator(Criteria.where("email").is(username_or_email),
					Criteria.where("username").is(username_or_email))))
			.apply(new Update().set("password", new_password));
		}
		if (findById(username_or_email) instanceof Alumnus) {
			mongoTemplate.update(Alumnus.class)
			.matching(new Query(new Criteria().orOperator(Criteria.where("email").is(username_or_email),
					Criteria.where("username").is(username_or_email))))
			.apply(new Update().set("password", new_password));
		}
	}

	@Override
	public void optoutRequest(String username) {

		try {
			User user = findById(username);
			MimeMessage msg = send_email.createMimeMessage();
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			msg.setSubject("CIRP | Opt Out");
			msg.setContent("<p>Hi,</p>" + "<p> </p>"
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
	public void deleteUser(String username) {
		
		if (findById(username) instanceof College) {
			College college = findById(username);
			mongoTemplate.remove(college);
		}
		else if (findById(username) instanceof Recruiter) {
			Recruiter recruiter = findById(username);
			mongoTemplate.remove(recruiter);
		}
		else if (findById(username) instanceof Student) {
			Student student = findById(username);
			mongoTemplate.remove(student);
		}
		else { 
			if (findById(username) instanceof Alumnus) {
				Alumnus alumnus = findById(username);
				mongoTemplate.remove(alumnus);
			}
		}	

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
		mongoTemplate.insert(job);

	}

	@Override
	public Job viewJob(ObjectId id) {
		return mongoTemplate.findById(id, Job.class);
	}

	@Override
	public void deleteJob(Job job) {
		mongoTemplate.remove(job);
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
		return mongoTemplate.findById(job_id, Job.class).getApplicants();
	}

	@Override
	public List<Application> viewAllApplications(Recruiter recruiter) {
		return mongoTemplate.findById(applicant_id, Application.class).getApplicant_id();
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
		
		return null;

		/*
		 * List<List<T>> l = mongoTemplate.collection.find(); l.add(search(search_text,
		 * "college")); l.add(search(search_text, "student")); l.add(search(search_text,
		 * "alumnus")); l.add(search(search_text, "recruiter"));
		 * l.add(search(search_text, "job")); return (l); Commented out because of
		 * errors
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> search(String search_text, String filter) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text.split(" "))).sortByScore();

		if (filter.equals("college"))
			return (List<T>) mongoTemplate.find(query, College.class, filter);
		else if (filter.equals("recruiter"))
			return (List<T>) mongoTemplate.find(query, Recruiter.class, filter);
		else if (filter.equals("student"))
			return (List<T>) mongoTemplate.find(query, Student.class, filter);
		else if (filter.equals("alumnus"))
			return (List<T>) mongoTemplate.find(query, Alumnus.class, filter);
		else if (filter.equals("job"))
			return (List<T>) mongoTemplate.find(query, Job.class, filter);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(String id) { // id refers to username
		if (mongoTemplate.findById(id, College.class) != null)
			return (T) mongoTemplate.findById(id, College.class);
		else if (mongoTemplate.findById(id, Recruiter.class) != null)
			return (T) mongoTemplate.findById(id, Recruiter.class);
		else if (mongoTemplate.findById(id, Student.class) != null)
			return (T) mongoTemplate.findById(id, Student.class);
		else if (mongoTemplate.findById(id, Alumnus.class) != null)
			return (T) mongoTemplate.findById(id, Alumnus.class);
		else if (mongoTemplate.findById(id, Job.class) != null)
			return (T) mongoTemplate.findById(id, Job.class);
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
	@Scheduled(cron = "0 0 12 * * ?") // Scheduled to run 12 PM everyday
	public void deleteRejectedRegistrations() {
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		calender.add(Calendar.DATE, -14);
		mongoTemplate.findAllAndRemove(new Query(where("status_changed").is(calender.getTime())), User.class);

	}

	@Override
	public Recruiter viewProfile(Recruiter recruiter) {
		return mongoTemplate.findById("_id", Recruiter.class);
		// TODO Auto-generated method stub
	}

	@Override
	public void viewProfile(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProfilePic(String profile_pic, String username) {

		if (findById(username) instanceof College) {
			mongoTemplate.update(College.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("profile_pic", profile_pic));
		} else if (findById(username) instanceof Recruiter) {
			mongoTemplate.update(Recruiter.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("profile_pic", profile_pic));
		} else if (findById(username) instanceof Student) {
			mongoTemplate.update(Student.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("profile_pic", profile_pic));
		} else {
			if (findById(username) instanceof Alumnus) {
				mongoTemplate.update(Alumnus.class).matching(new Query(where("username").is(username)))
						.apply(new Update().set("profile_pic", profile_pic));
			}
		}

	}

	@Override
	public void updateBgImg(String bg_img, String username) {

	}

	@Override
	public void updateDesc(String desc, String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateContact(ContactInfo contact, String username) {
		if (findById(username) instanceof College) {
			mongoTemplate.update(College.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("contact", contact));
		}
		if (findById(username) instanceof Recruiter) {
			mongoTemplate.update(Recruiter.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("contact", contact));
		}
		if (findById(username) instanceof Student) {
			mongoTemplate.update(Student.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("contact", contact));
		}
		if (findById(username) instanceof Alumnus) {
			mongoTemplate.update(Alumnus.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("contact", contact));
		}
			
	}

	@Override
	public void updatePersonalisation(Personalisation personalisation, final String username) {

		if (findById(username) instanceof Student) {
			mongoTemplate.update(Student.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("personalisation", personalisation));
		} else {
			if (findById(username) instanceof Alumnus) {
				mongoTemplate.update(Alumnus.class).matching(new Query(where("username").is(username)))
						.apply(new Update().set("personalisation", personalisation));
			}
		}

	}


	@Override
	public College viewProfile(College college) {
		Query query=new Query ();
		query.fields().include("username").include("name").include("address").include("mobile").include("email");
		List<College> college1 = mongoTemplate.find(query,College.class);
	}
	}
	
	


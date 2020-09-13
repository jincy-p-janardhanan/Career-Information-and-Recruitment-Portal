/**
 * references: 
 * 		https://www.appsdeveloperblog.com/spring-boot-and-mongotemplate-tutorial-with-mongodb/4
 * 		https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.save-update-remove
 * 
 */

package com.cirp.app.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.*;

/**
 * @author Jincy P Janardhanan
 * @author Aleena Sunny
 * @author Alka Bhagavaldas K
 * @author Ameena Shirin
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
		List<Admin> admins = mongoTemplate.findAll(Admin.class);
		for (int i = 0; i < admins.size(); i++) {
			Admin admin = admins.get(i);
			List<String> college_pending = admin.getCollege_pending();
			college_pending.add(college.getUsername());
			mongoTemplate.save(admin);
		}
	}

	@Override
	public void register(Recruiter recruiter) {
		mongoTemplate.insert(recruiter);
		List<Admin> admins = mongoTemplate.findAll(Admin.class);
		for (int i = 0; i < admins.size(); i++) {
			Admin admin = admins.get(i);
			List<String> recruiter_pending = admin.getRecruiter_pending();
			recruiter_pending.add(recruiter.getUsername());
			mongoTemplate.save(admin);
		}
	}

	@Override
	public void register(Alumnus alumnus) {

		College college = mongoTemplate.findOne(new Query(Criteria.where("name").is(alumnus.getCollege_id())),
				College.class);
		alumnus.setCollege_id(college.getUsername());
		mongoTemplate.insert(alumnus);

		List<String> alumni_pending = college.getAlumni_pending();
		alumni_pending.add(alumnus.getUsername());
		mongoTemplate.save(alumnus);
	}

	@Override
	public Student register(Student student) {
		return mongoTemplate.insert(student);
	}

	@Override
	public void resetPassword(String username_or_email) {
		// TODO Auto-generated method stub
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
		} else if (findById(username) instanceof Recruiter) {
			Recruiter recruiter = findById(username);
			mongoTemplate.remove(recruiter);
		} else if (findById(username) instanceof Student) {
			Student student = findById(username);
			mongoTemplate.remove(student);
		} else {
			if (findById(username) instanceof Alumnus) {
				Alumnus alumnus = findById(username);
				mongoTemplate.remove(alumnus);
			}
		}

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
		return mongoTemplate.findById(new Query(where("_id").is(id)).fields().exclude("applicants"), Job.class);
	}

	@Override
	public void deleteJob(Job job) {
		mongoTemplate.remove(job);
	}

	@Override
	public void applyJob(Application application, ObjectId job_id) {
		Job job = mongoTemplate.findById(job_id, Job.class);
		List<Application> applications = job.getApplicants();
		applications.add(application);
		mongoTemplate.save(job);
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
	public List<Application> viewAllApplications(String recruiter_id) {
		Recruiter recruiter = mongoTemplate.findById(recruiter_id, Recruiter.class);
		List<ObjectId> job_list = recruiter.getJobs();
		List<Application> all_applications = new ArrayList<Application>();
		for (int i = 0; i < job_list.size(); i++) {
			Job job = mongoTemplate.findById(job_list.get(i), Job.class);
			all_applications.addAll(job.getApplicants());
		}
		return all_applications;
	}

	@Override
	public List<Application> searchApplications(String search_text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void requestRecommendation(String requester_id, String recommender_id) {

		mongoTemplate.insert(new Recommendation(requester_id, recommender_id));
	}

	@Override
	public void recommend(ObjectId reccomendation_id, String recc_msg) {

		mongoTemplate.update(Recommendation.class).matching(new Query(where("_id").is(reccomendation_id)))
				.apply(new Update().set("status", "1").set("recc_msg", recc_msg));

	}

	@Override
	public void rejectRecommendationRequest(Recommendation reccomendation) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<List<T>> search(String search_text) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text.split(" ")));
		List<List<T>> list = new ArrayList<List<T>>();
		list.add((List<T>) mongoTemplate.find(query, College.class));
		list.add((List<T>) mongoTemplate.find(query, Recruiter.class));
		list.add((List<T>) mongoTemplate.find(query, Student.class));
		list.add((List<T>) mongoTemplate.find(query, Alumnus.class));
		list.add((List<T>) mongoTemplate.find(query, Job.class));
		return list;
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
	public <T> T findById(String id) {
		
		if (mongoTemplate.findById(id, Admin.class) != null)
			return (T) mongoTemplate.findById(id, Admin.class);
		
		else if (mongoTemplate.findById(id, College.class) != null)
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
	public void confirmRegistration(String username) {

		Boolean approved = false;

		if (findById(username) instanceof College) {

			College college = findById(username);
			college.incApproval_count();
			if (college.getApproval_count() == mongoTemplate.count(new Query(), Admin.class)) {
				college.setStatus(1);
				college.setStatus_changed();

				approved = true;
			}
			mongoTemplate.save(college);

		} else if (findById(username) instanceof Recruiter) {

			Recruiter recruiter = findById(username);
			recruiter.incApproval_count();
			if (recruiter.getApproval_count() == mongoTemplate.count(new Query(), Admin.class)) {
				recruiter.setStatus(1);
				recruiter.setStatus_changed();

				approved = true;
			}
			mongoTemplate.save(recruiter);

		} else {

			if (findById(username) instanceof Alumnus) {

				Alumnus alumnus = findById(username);
				alumnus.incApproval_count();
				alumnus.setStatus(1);
				alumnus.setStatus_changed();

				mongoTemplate.save(alumnus);
				approved = true;
			}
		}

		if (approved == true) {
			try {
				User user = findById(username);
				MimeMessage msg = send_email.createMimeMessage();
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
				msg.setSubject("CIRP | Registration Approved");
				msg.setContent("<p>Hi,</p>" + "<p> </p>"
						+ "<p>We are glad to inform you that your account has been approved.</p>" + "<p>Regards,</p>"
						+ "<p>Team CIRP</p>", "text/html");
				send_email.send(msg);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

		}
	}

	@Override
	public void rejectRegistration(String username) {

		Boolean approved = true;

		if (findById(username) instanceof College) {

			College college = findById(username);
			college.decApproval_count();
			if (Math.abs(college.getApproval_count()) == mongoTemplate.count(new Query(), Admin.class)) {
				college.setStatus(-1);
				college.setStatus_changed();

				approved = false;
			}

		}

		else if (findById(username) instanceof Recruiter) {

			Recruiter recruiter = findById(username);
			recruiter.decApproval_count();
			if (Math.abs(recruiter.getApproval_count()) == mongoTemplate.count(new Query(), Admin.class)) {
				recruiter.setStatus(-1);
				recruiter.setStatus_changed();

				approved = false;
			}
		} else {

			if (findById(username) instanceof Alumnus) {

				Alumnus alumnus = findById(username);
				alumnus.decApproval_count();
				if (Math.abs(alumnus.getApproval_count()) == mongoTemplate.count(new Query(), Admin.class)) {
					alumnus.setStatus(-1);
					alumnus.setStatus_changed();

					approved = false;
				}
			}
		}
		
		if(approved == false) {
			try {
				User user = findById(username);
				MimeMessage msg = send_email.createMimeMessage();
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
				msg.setSubject("CIRP | Registration Approved");
				msg.setContent(
						"<p>Hi,</p>" + "<p> </p>" + "<p>We are sorry to inform you that your registration has been rejected.</p>"
								+ "<p>Regards,</p>" + "<p>Team CIRP</p>",
						"text/html");
				send_email.send(msg);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

		}
		
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

		if (findById(username) instanceof College) {
			mongoTemplate.update(College.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("desc", desc));
		} else if (findById(username) instanceof Recruiter) {
			mongoTemplate.update(Recruiter.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("desc", desc));
		} else if (findById(username) instanceof Student) {
			mongoTemplate.update(Student.class).matching(new Query(where("username").is(username)))
					.apply(new Update().set("desc", desc));
		} else {
			if (findById(username) instanceof Alumnus) {
				mongoTemplate.update(Alumnus.class).matching(new Query(where("username").is(username)))
						.apply(new Update().set("desc", desc));
			}
		}

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

	@SuppressWarnings("unchecked")
	@Override
	public <T> T viewProfile(String username) {

		if (findById(username) instanceof College) {
			return (T) mongoTemplate.findById(new Query(where("_id").is(username)).fields().include("name")
					.include("affil_univ").include("contact"), College.class);
		} else {
			if (findById(username) instanceof Recruiter) {
				return (T) mongoTemplate.findById(new Query(where("_id").is(username)).fields().include("name")
						.include("license_no").include("contact"), Recruiter.class);
			}
		}
		return null;
	}
}
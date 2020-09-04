/**
 * references: 
 * 		https://www.appsdeveloperblog.com/spring-boot-and-mongotemplate-tutorial-with-mongodb/4
 * 		https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.save-update-remove
 * 
 */

package com.cirp.app.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Application;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.Recommendation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.model.User;

/**
 * @author Jincy P Janardhanan
 *
 */
@Repository
public class CirpRepository implements CirpRepositoryOperations {

	@Autowired
	MongoTemplate mongoOps;

	@Override
	public void register(College college) {
		// TODO Auto-generated method stub

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
		changeUserPassword("username or");

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
		mongoOps.update(College.class).matching(Criteria.where("_id").is(college.get_id())).replaceWith(college);
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
	public void requestRecommendation(ObjectId requester_id, ObjectId recommender_id) {
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

		List<List<T>> l = null;
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
	public <T> T findById(ObjectId id) {
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
	public void deleteRejectedRegistrations() {
		// TODO Auto-generated method stub

	}
}

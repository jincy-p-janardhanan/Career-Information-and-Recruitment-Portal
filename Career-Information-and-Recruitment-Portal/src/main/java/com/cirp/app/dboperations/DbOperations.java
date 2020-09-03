/**
 * references: 
 * 		https://www.appsdeveloperblog.com/spring-boot-and-mongotemplate-tutorial-with-mongodb/4
 * 		https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.save-update-remove
 * 
 */
package com.cirp.app.dboperations;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Application;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.Recommendation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;

/**
 * @author Jincy P Janardhanan
 *
 */
public class DbOperations implements OpsOnModel{

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmRegistration(College college) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmRegistration(Recruiter recruiter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmRegistration(Alumnus alumnus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectRegistration(College college) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectRegistration(Recruiter college) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectRegistration(Alumnus alumnus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRejectedRegistrations(Date today) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String username_or_email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPassword(String username_or_email) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewJob(Job job) {
		// TODO Auto-generated method stub
		
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
	public void viewJobApplications(Job job) {
		// TODO Auto-generated method stub
		
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
	public List<ObjectId> search(String search_text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ObjectId> search(String search_text, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findById(ObjectId id) {
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
	
}

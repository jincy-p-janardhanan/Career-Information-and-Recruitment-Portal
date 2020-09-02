/**
 * 
 */
package com.cirp.app.dboperations;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.cirp.app.model.*;

/**
 * @author Jincy P Janardhanan
 *
 */
public interface OpsOnModel {
	/*
	 * passwords are stored in bcrypt encrypted format, in the database
	 * passwords in function calls are also encrypted
	 * Bcrypt usage reference: https://dzone.com/articles/storing-passwords-securely-with-bcrypt-and-java
	 * Reference(in SQL): https://www.devglan.com/spring-security/spring-boot-security-password-encoding-bcrypt-encoder
	 * 
	 * 
	 * Pending methods: Chat, Notifications
	 * 
	 */
	
	void register(College college); //request to server admin
	void register(Recruiter recruiter); //request to admin
	void register(Alumnus alumnus); //request to college
	void register(Student student);
	
	void confirmRegistration(College college);
	void confirmRegistration(Recruiter recruiter);
	void confirmRegistration(Alumnus alumnus);
	
	void rejectRegistration(College college);
	void rejectRegistration(Recruiter college);
	void rejectRegistration(Alumnus alumnus);
	
	//Send email after confirmation or rejection of registration
	
	void deleteRejectedRegistrations(Date today); //Scheduled to run everyday, removes registrations which were rejected two weeks before the current date 
	
	void login(String username_or_email, String password);
	
	void resetPassword(String username_or_email); //Send password reset link to email
	void updatePassword(String username_or_email, String new_password);
	
	//Profile includes data displayed on the user's home page only
	void viewProfile(College college);
	void viewProfile(Recruiter recruiter);
	void viewProfile(Student student); //Can be used for both student and alumni; NB: Super class object reference can hold object of sub class	
	
	void editProfile(College college);
	void editProfile(Recruiter recruiter);
	void editProfile(Student student); //Can be used for both student and alumni

	void optoutRequest(College college); //sends confirmation mail for opt out
	void optoutRequest(Recruiter recruiter); //sends confirmation mail for opt out
	void optoutRequest(Alumnus alumnus); //sends confirmation mail for opt out
	
	void deleteUser(College college);
	void deleteUser(Recruiter recruiter);
	void deleteUser(Student student);
	void deleteUser(Alumnus alumnus);
	
	void sessionTimeout(); //After 20 minutes of inactivity, login session for the user is closed automatically
	
	void changeStudentsToAlumni(Date end_date); //should be scheduled to check daily or weekly
	
	void createJob(Job job);
	void viewJob(Job job);
	void deleteJob(Job job);
	
	void applyJob(Application application);
	void viewApplication(Application application);
	
	void viewJobApplications(Job job);
	void viewAllApplications(Recruiter recruiter);
	List<Application> searchApplications(String search_text);
	
	void requestRecommendation(Student student); //Can be used for both student and alumni
	void recommend(Student student); //Can be used for both student and alumni
	
	List<ObjectId> search(String search_text);
	List<ObjectId> search(String search_text, String filter); //filter can be any one of 'Student', 'Alumni', 'College', 'Recruiter', 'Job'
	
	<T> T findById(ObjectId id); //Can return object from any collection (class)
	
}

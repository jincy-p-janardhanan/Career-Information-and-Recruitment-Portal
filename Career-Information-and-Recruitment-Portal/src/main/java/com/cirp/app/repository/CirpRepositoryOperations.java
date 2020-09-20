/**
 * 
 */
package com.cirp.app.repository;

import java.util.Date;
import java.util.List;


import org.bson.types.ObjectId;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Application;
import com.cirp.app.model.College;
import com.cirp.app.model.ContactInfo;
import com.cirp.app.model.Job;
import com.cirp.app.model.Personalisation;
import com.cirp.app.model.Recommendation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;


/**
 * @author Jincy P Janardhanan
 *
 */
public interface CirpRepositoryOperations {
	
	void register(College college); //request to server admin
	void register(Recruiter recruiter); //request to admin
	void register(Alumnus alumnus); //request to college
	Student register(Student student);
	
	//Send email after confirmation or rejection of registration
	void confirmRegistration(String username);
	void rejectRegistration(String username);
		
	void resetPassword(String username_or_email); //Send password reset link to email
	void updatePassword(String username_or_email, String new_password);
	
	//Profile includes data displayed on the user's home page only
	<T> T viewProfile(String username);	
	
	void optoutRequest(String username); //sends confirmation mail for opt out
	
	void deleteUser(String username);
	
	void changeStudentsToAlumni(Date end_date); //should be scheduled to check daily or weekly
	
	void createJob(Job job, String username);
	Job viewJob(ObjectId id);
	void deleteJob(Job job);
	
	void applyJob(Application application, ObjectId job_id);
	void viewApplication(Application application);
	
	List<Application> viewJobApplications(ObjectId job_id);
	List<Application> viewAllApplications(String recruiter_id);
	List<Application> searchApplications(String search_text);
	
	void requestRecommendation(String requester_id, String recommender_id);
	void recommend(ObjectId reccomendation_id, String recc_msg);
	void rejectRecommendationRequest(Recommendation reccomendation);
	
	List<Object> search(String search_text);
	<T> List<T> search(String search_text, String filter); //filter can be any one of 'Student', 'Alumni', 'College', 'Recruiter', 'Job'
	
	<T> T findById(String id); //Can return object from any collection (class)
	
	void deleteRejectedRegistrations();
	
	void updateProfilePic(String profile_pic, String username);
	void updateBgImg(String bg_img, String username);
	void updateDesc(String desc, String username);
	void updateContact(ContactInfo contact, String username);
	void updatePersonalisation(Personalisation personalisation, String username);
	<T> T findByEmail(String email);
}

package com.cirp.app.repository;

import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Application;
import com.cirp.app.model.College;
import com.cirp.app.model.ContactInfo;
import com.cirp.app.model.Job;
import com.cirp.app.model.Personalisation;
import com.cirp.app.model.Recommendation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;

public interface CirpRepositoryOperations {
	
	void register(College college); //request to server admin
	void register(Recruiter recruiter); //request to admin
	void register(Alumnus alumnus); //request to college
	void register(Student student);
	
	void updatePassword(String username_or_email, String new_password, Class<?> user_class);
	
	//Profile includes data displayed on the user's home page only
	<T> T viewProfile(String username);	
	
	void deleteUser(String username);
	
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
	
	void updateProfilePic(String profile_pic, String username, Class<?> user_class);
	void updateBgImg(String bg_img, String username, Class<?> user_class);
	void updateDesc(String desc, String username, Class<?> user_class);
	void updateContact(ContactInfo contact, String username, Class<?> user_class);
	void updatePersonalisation(Personalisation personalisation, String username, Class<?> user_class);
	<T> T findByEmail(String email);
	
	void updateApprovalCount(String username, int counter, Class<?> user_class);
	void updateUserStatus(String username, int counter, Class<?> user_class);
	void removeUserFromList(String username, String pending_list, Class<?> admin_class, Query query);
	void addUserToList(String username, String approve_reject_list, Class<?> admin_class, Query query);
	long getAdminCount();
	void setToken(String token, String username, Class<?> user_class);
	<T> T findByToken(String token);
}

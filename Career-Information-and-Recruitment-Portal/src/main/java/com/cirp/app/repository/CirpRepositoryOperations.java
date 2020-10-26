package com.cirp.app.repository;

import java.util.List;

import org.bson.Document;
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

public interface CirpRepositoryOperations {
	
	void register(College college); //request to server admin
	void register(Recruiter recruiter); //request to admin
	void register(Alumnus alumnus); //request to college
	void register(Student student);
	
	void updatePassword(String username_or_email, String new_password, Class<?> user_class);	
	
	void deleteUser(String username);
	
	void createJob(Job job, Recruiter recruiter);
	Job viewJob(ObjectId id);
	void deleteJob(Job job, String recruiter_id);
	
	List<Document> viewApplications(String matchquery);
	
	void requestRecommendation(String requester_id, String recommender_id);
	void recommend(ObjectId reccomendation_id, String recc_msg);
	void rejectRecommendationRequest(Recommendation reccomendation);
	
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
	void removeUserFromList(String username, String pending_list, Class<?> admin_class, String admin_username);
	void addUserToList(String username, String approve_reject_list, Class<?> admin_class, String admin_username);
	long getAdminCount();
	void setToken(String token, String username, Class<?> user_class);
	<T> T findByToken(String token);
	void updateUserRole(String username, String role, Class<?> user_class);
	void updateStudent(Student student);
	String applyJob(Application application, String job_id);
	void hire(String applicant_id, String job_id, Class<?> userClass);
	void rejectApplication(String applicant_id, String job_id, Class<?> userClass);
	List<Document> jobSuggestions(String name, String aggregatefrom);
	
}

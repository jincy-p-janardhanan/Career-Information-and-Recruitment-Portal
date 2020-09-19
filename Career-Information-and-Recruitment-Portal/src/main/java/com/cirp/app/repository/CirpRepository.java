package com.cirp.app.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.*;

@Repository
public class CirpRepository implements CirpRepositoryOperations {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void register(College college) {
		mongoTemplate.insert(college);
		mongoTemplate.updateMulti(new Query(), new Update().push("college_pending", college.getUsername()),
				Admin.class);
	}

	@Override
	public void register(Recruiter recruiter) {
		mongoTemplate.insert(recruiter);
		mongoTemplate.updateMulti(new Query(), new Update().push("recruiter_pending", recruiter.getUsername()),
				Admin.class);
	}

	@Override
	public void register(Alumnus alumnus) {

		Query query = new Query(where("name").is(alumnus.getCollege_id()));

		alumnus.setCollege_id(mongoTemplate.findOne(query, College.class).getUsername());

		mongoTemplate.insert(alumnus);
		mongoTemplate.updateFirst(query, new Update().push("college_pending", alumnus.getUsername()), College.class);
	}

	@Override
	public void register(Student student) {

		Query query = new Query(where("name").is(student.getCollege_id()));
		mongoTemplate.insert(student);
		mongoTemplate.updateFirst(query, new Update().push("college_pending", student.getUsername()), College.class);
	}

	@Override
	public void updatePassword(String username_or_email, String new_password, Class<?> user_class) {
		mongoTemplate.update(user_class)
				.matching(new Query(new Criteria().orOperator(Criteria.where("email").is(username_or_email),
						Criteria.where("username").is(username_or_email))))
				.apply(new Update().set("password", new_password));
	}

	@Override
	public void deleteUser(String username) {

		mongoTemplate.remove(findById(username));
	}

	@Override
	public void createJob(Job job, String username) {
		Recruiter recruiter = findById(username);
		List<ObjectId> job_ids = recruiter.getJobs();
		if (job_ids == null) {
			job_ids = new ArrayList<ObjectId>();
		}

		job_ids.add(job.getId());
		recruiter.setJobs(job_ids);

		mongoTemplate.save(recruiter);
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

	@Override
	public List<Object> search(String search_text) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text.split(" "))).sortByScore()
				.includeScore("score");
		List<Object> list = new ArrayList<Object>();
		list.add(mongoTemplate.find(query, College.class));
		list.add(mongoTemplate.find(query, Recruiter.class));
		list.add(mongoTemplate.find(query, Student.class));
		list.add(mongoTemplate.find(query, Alumnus.class));
		list.add(mongoTemplate.find(query, Job.class));
		// list.sort(new Comparator<? super Object>());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> search(String search_text, String filter) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text.split(" "))).sortByScore();
		Class<?> filter_class = null;
		if (filter.equals("college"))
			filter_class = College.class;
		else if (filter.equals("recruiter"))
			filter_class = College.class;
		else if (filter.equals("student"))
			filter_class = College.class;
		else if (filter.equals("alumnus"))
			filter_class = College.class;
		else if (filter.equals("job"))
			filter_class = College.class;
		try {
			return (List<T>) mongoTemplate.find(query, filter_class, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findByEmail(String email) {

		if (mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Admin.class) != null)
			return (T) mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Admin.class);

		else if (mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), College.class) != null)
			return (T) mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), College.class);

		else if (mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Recruiter.class) != null)
			return (T) mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Recruiter.class);

		else if (mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Student.class) != null)
			return (T) mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Student.class);

		else if (mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Alumnus.class) != null)
			return (T) mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Alumnus.class);

		else
			return null;
	}

	@Override
	public void updateProfilePic(String profile_pic, String username, Class<?> user_class) {
		mongoTemplate.update(user_class).matching(new Query(where("username").is(username)))
				.apply(new Update().set("profile_pic", profile_pic));

	}

	@Override
	public void updateBgImg(String bg_img, String username, Class<?> user_class) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDesc(String desc, String username, Class<?> user_class) {
		mongoTemplate.update(user_class).matching(new Query(where("username").is(username)))
				.apply(new Update().set("desc", desc));
	}

	@Override
	public void updateContact(ContactInfo contact, String username, Class<?> user_class) {
		mongoTemplate.update(user_class).matching(new Query(where("username").is(username)))
				.apply(new Update().set("contact", contact));
	}

	@Override
	public void updatePersonalisation(Personalisation personalisation, final String username, Class<?> user_class) {
		mongoTemplate.update(user_class).matching(new Query(where("username").is(username)))
				.apply(new Update().set("personalisation", personalisation));
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

	@Override
	public void updateApprovalCount(String username, int counter, Class<?> user_class) {
		mongoTemplate.updateFirst(new Query(where("username").is(username)),
				new Update().inc("approval_count", counter), user_class);
	}

	@Override
	public void updateUserStatus(String username, int counter, Class<?> user_class) {
		mongoTemplate.updateFirst(new Query(where("username").is(username)),
				new Update().set("status", counter).set("status_changed", new Date()), user_class);
	}

	@Override
	public void removeUserFromList(String username, String pending_list, Class<?> admin_class, Query query) {
		mongoTemplate.updateMulti(query, new Update().pull(pending_list, username), admin_class);
	}

	@Override
	public void addUserToList(String username, String approve_reject_list, Class<?> admin_class, Query query) {
		mongoTemplate.updateMulti(query, new Update().push(approve_reject_list, username), admin_class);
	}

	@Override
	public long getAdminCount() {
		return mongoTemplate.count(new Query(), Admin.class);
	}

	@Override
	public void setToken(String token, String username, Class<?> user_class) {
		mongoTemplate.update(user_class).matching(new Query(where("username").is(username)))
				.apply(new Update().set("token", token));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findByToken(String token) {
		if (mongoTemplate.findOne(new Query(where("token").is(token)), College.class) != null) {

			return (T) mongoTemplate.findOne(new Query(where("token").is(token)), College.class);

		} else if (mongoTemplate.findOne(new Query(where("token").is(token)), Recruiter.class) != null) {

			return (T) mongoTemplate.findOne(new Query(where("token").is(token)), Recruiter.class);

		} else if (mongoTemplate.findOne(new Query(where("token").is(token)), Student.class) != null) {

			return (T) mongoTemplate.findOne(new Query(where("token").is(token)), Student.class);

		} else if (mongoTemplate.findOne(new Query(where("token").is(token)), Alumnus.class) != null) {

			return (T) mongoTemplate.findOne(new Query(where("token").is(token)), Alumnus.class);

		} else if (mongoTemplate.findOne(new Query(where("token").is(token)), Admin.class) != null) {

			return (T) mongoTemplate.findOne(new Query(where("token").is(token)), Alumnus.class);
		}

		return null;
	}
}
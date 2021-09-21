package com.cirp.app.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.Admin;
import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Application;
import com.cirp.app.model.College;
import com.cirp.app.model.ContactInfo;
import com.cirp.app.model.Job;
import com.cirp.app.model.Personalisation;
import com.cirp.app.model.Recommendation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.mongodb.client.result.UpdateResult;

@Repository
public class CirpRepository implements CirpRepositoryOperations {

	@Autowired
	MongoTemplate mongoTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(CirpRepositoryOperations.class);

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
		mongoTemplate.updateFirst(query, new Update().push("alumni_pending", alumnus.getUsername()), College.class);
	}

	@Override
	public void register(Student student) {

		Query query = new Query(where("username").is(student.getCollege_id()));
		mongoTemplate.insert(student);
		mongoTemplate.updateFirst(query, new Update().push("students", student.getUsername()), College.class);
	}

	@Override
	public void updatePassword(String username_or_email, String new_password, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(new Criteria().orOperator(Criteria.where("email").is(username_or_email),
				Criteria.where("username").is(username_or_email)));
		mongoTemplate.updateFirst(query, new Update().set("password", new_password), user_class);
	}

	@Override
	public void deleteUser(String username) {

		mongoTemplate.remove(findById(username));
	}

	@Override
	public void createJob(Job job, Recruiter recruiter) {
		mongoTemplate.save(job);
		logger.info("saved job with id" + job.get_id());
		mongoTemplate.save(recruiter);
	}
	
	@Override
	public void editJob(Job job) {
		mongoTemplate.save(job);
		logger.info("saved job with id " + job.get_id());
	}

	@Override
	public Job viewJob(ObjectId id) {
		return mongoTemplate.findById(new Query(where("_id").is(id)).fields().exclude("applicants"), Job.class);
	}

	@Override
	public void deleteJob(Job job, String recruiter_id) {
		mongoTemplate.remove(job);
		Query query = new Query();
		query.addCriteria(where("username").is(recruiter_id));
		mongoTemplate.updateFirst(query, new Update().pull("jobs", job.get_id()), Recruiter.class);
	}

	@Override
	public String applyJob(Application application, String job_id) {
		Job job = mongoTemplate.findById(job_id, Job.class);
		long r;
		String username = application.getApplicant_id();
		Query query = new Query().addCriteria(where("username").is(username));
		Update update = new Update().addToSet("applied_jobs", job.get_id());
		r = mongoTemplate.updateFirst(query, update, Student.class).getModifiedCount();
		if (r == 0) {
			r = mongoTemplate.updateFirst(query, update, Alumnus.class).getModifiedCount();
		}
		if (r == 0) {
			return "You have already applied for this job! You cannot apply again.";
		}

		mongoTemplate.updateFirst(new Query().addCriteria(where("_id").is(job_id)),
				new Update().push("applicants", application), Job.class);

		return null;
	}

	@Override
	public List<Document> viewApplications(String matchquery) {

		// @formatter:off
		String aggregate_command = "{"
			+ "aggregate: 'job', "
			+ "pipeline: ["
				+ matchquery
				+ "{$project: { recruiter_id: 0, profile_pic:0, desc:0, _class:0 }},"
				+ "{$unwind: { path: '$applicants' }},"
				+ "{$facet: { "
					+ "alumni_application: ["
						+ "{ $lookup : { from: 'alumnus', localField: 'applicant_id', foreignField: 'username', as: 'profile'}},"
						+ "{ $unwind : '$profile' } ],"
					+ "student_application : ["
						+ "{ $lookup : { from: 'student', localField: 'applicant_id', foreignField: 'username', as: 'profile' }},"
						+ "{ $unwind : '$profile' }]}},"
				+ " {$project: { applications: { "
					+ "$concatArrays : [ '$alumni_application' , '$student_application' ]}}},"
				+ "{$unwind: { path: '$applications' }},"
				+ "{$match: {"
					+ "$expr:{ $eq : ['$applications.applicants.applicant_id', '$applications.profile._id']}}},"
				+ "{$project: { "
					+ "job_id                :  '$applications._id', "
					+ "job_title             :  '$applications.name',"
					+ "location              :  '$applications.location',"
					+ "duration              :  '$applications.duration', "
					+ "stipend               :  '$applications.stipend',"
					+ "last_date             :  '$applications.last_date',"
					+ "applicant_username    :  '$applications.profile._id',"
					+ "applicant_name        :  '$applications.profile.name',"
					+ "profile_desc          :  '$applications.profile.desc',"
					+ "questions             :  {"
						+ "$ifNull: ['$applications.questions', []] }"
					+ "answers               :  {"
						+ "$ifNull: ['$applications.applicants.answers', []] }"
					+ "application_timestamp :  '$applications.applicants.timestamp',"
					+ "matched_skills        : { "
						+ "$setIntersection : [  '$applications.skills', "
							+ "{ $setUnion : ["
								+ "{ $ifNull: [  '$applications.profile.personalisation.skills', []]},"
								+ "{ $ifNull: [  '$applications.profile.personalisation.work.skills', []]}, "
								+ "{ $ifNull: [  '$applications.profile.personalisation.project.skills', []]} ]}]}}},"
				+ "{$addFields: { score : { $size : '$matched_skills'}}},"
				+ "{$sort: { score : -1, application_timestamp : -1}}],"
			+ "cursor: { }}";
		
		Document execution_results  = mongoTemplate.executeCommand(aggregate_command);
		List<Document> aggregation_results = execution_results.get("cursor", Document.class).getList("firstBatch", Document.class);
		
		//@formatter:on
		return aggregation_results;
	}

	@Override
	public void requestRecommendation(Recommendation recommendation, Class<?> requester_class,
			Class<?> recommender_class) {
		Query query = new Query().addCriteria(where("username").is(recommendation.getRequester_id()));
		mongoTemplate.updateFirst(query, new Update().push("recommend_req", recommendation), requester_class);
		query = new Query().addCriteria(where("username").is(recommendation.getRecommender_id()));
		mongoTemplate.updateFirst(query, new Update().push("recc_req_recvd", recommendation), recommender_class);
	}

	@Override
	public void recommend(Recommendation recommendation, Class<?> requester_class, Class<?> recommender_class) {
		Query query1 = new Query().addCriteria(where("username").is(recommendation.getRequester_id()))
				.addCriteria(where("recommend_req.recommender_id").is(recommendation.getRecommender_id()));
		Query query2 = new Query().addCriteria(where("username").is(recommendation.getRecommender_id()))
				.addCriteria(where("recc_req_recvd.requester_id").is(recommendation.getRequester_id()));
		
		UpdateResult r =mongoTemplate.updateFirst(query1, new Update().push("recommendations", recommendation), requester_class);
		logger.info(r.toString());		
		r = mongoTemplate.updateFirst(query2, new Update().push("reccommended", recommendation), recommender_class);
		logger.info(r.toString());
		
		recommendation.setStatus(0);
		recommendation.setRecc_msg(null);
		r =mongoTemplate.updateFirst(query1, new Update().pull("recommend_req", recommendation), requester_class);
		logger.info(r.toString());
		r = mongoTemplate.updateFirst(query2, new Update().pull("recc_req_recvd", recommendation), recommender_class);
		logger.info(r.toString());
		
	}

	@Override
	public void rejectRecommendationRequest(Recommendation recommendation, Class<?> requester_class, Class<?> recommender_class) {
		Query query1 = new Query().addCriteria(where("username").is(recommendation.getRequester_id()))
				.addCriteria(where("recommend_req.recommender_id").is(recommendation.getRecommender_id()));
		Query query2 = new Query().addCriteria(where("username").is(recommendation.getRecommender_id()))
				.addCriteria(where("recc_req_recvd.requester_id").is(recommendation.getRequester_id()));
		
		UpdateResult r = mongoTemplate.updateFirst(query2, new Update().push("recc_rejected", recommendation), recommender_class);
		logger.info(r.toString());
		
		recommendation.setStatus(0);
		recommendation.setRecc_msg(null);
		r =mongoTemplate.updateFirst(query1, new Update().pull("recommend_req", recommendation), requester_class);
		logger.info(r.toString());
		r = mongoTemplate.updateFirst(query2, new Update().pull("recc_req_recvd", recommendation), recommender_class);
		logger.info(r.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> search(String search_text, String filter) {
		Query query = TextQuery.queryText(new TextCriteria().matchingAny(search_text)).sortByScore();
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

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findByEmail(String email) {

		Query query = new Query();
		query.addCriteria(where("email").is(email));

		if (mongoTemplate.findOne(query, Admin.class) != null)
			return (T) mongoTemplate.findOne(query, Admin.class);

		else if (mongoTemplate.findOne(query, College.class) != null)
			return (T) mongoTemplate.findOne(query, College.class);

		else if (mongoTemplate.findOne(query, Recruiter.class) != null)
			return (T) mongoTemplate.findOne(query, Recruiter.class);

		else if (mongoTemplate.findOne(query, Student.class) != null)
			return (T) mongoTemplate.findOne(query, Student.class);

		else if (mongoTemplate.findOne(query, Alumnus.class) != null)
			return (T) mongoTemplate.findOne(query, Alumnus.class);

		else
			return null;
	}

	@Override
	public void updateProfilePic(String profile_pic, String username, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("profile_pic", profile_pic), user_class);

	}

	@Override
	public void updateBgImg(String bg_img, String username, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("bg_img", bg_img), user_class);

	}

	@Override
	public void updateDesc(String desc, String username, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("desc", desc), user_class);
	}

	@Override
	public void updateContact(ContactInfo contact, String username, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("contact", contact), user_class);
	}

	@Override
	public void updatePersonalisation(Personalisation personalisation, final String username, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("personalisation", personalisation), user_class);
	}

	@Override
	public void updateApprovalCount(String username, int counter, Class<?> user_class) {
		mongoTemplate.updateFirst(new Query(where("username").is(username)),
				new Update().inc("approval_count", counter), user_class);
	}

	@Override
	public void updateUserStatus(String username, int counter, Class<?> user_class) {
		mongoTemplate.updateFirst(new Query(where("_id").is(username)),
				new Update().set("status", counter).set("status_changed", new Date()), user_class);
	}

	@Override
	public void removeUserFromList(String username, String pending_list, Class<?> admin_class, String admin_username) {
		Query query = new Query();
		query.addCriteria(where("username").is(admin_username));
		mongoTemplate.updateFirst(query, new Update().pull(pending_list, username), admin_class);

	}

	@Override
	public void addUserToList(String username, String approve_reject_list, Class<?> admin_class,
			String admin_username) {
		Query query = new Query();
		query.addCriteria(where("username").is(admin_username));
		mongoTemplate.updateFirst(query, new Update().push(approve_reject_list, username), admin_class);
	}

	@Override
	public long getAdminCount() {
		return mongoTemplate.count(new Query(), Admin.class);
	}

	@Override
	public void setToken(String token, String username, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("token", token), user_class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findByToken(String token) {
		Query query = new Query();
		query.addCriteria(where("token").is(token));

		if (mongoTemplate.findOne(query, College.class) != null) {

			return (T) mongoTemplate.findOne(query, College.class);

		} else if (mongoTemplate.findOne(query, Recruiter.class) != null) {

			return (T) mongoTemplate.findOne(query, Recruiter.class);

		} else if (mongoTemplate.findOne(query, Student.class) != null) {

			return (T) mongoTemplate.findOne(query, Student.class);

		} else if (mongoTemplate.findOne(query, Alumnus.class) != null) {

			return (T) mongoTemplate.findOne(query, Alumnus.class);

		} else if (mongoTemplate.findOne(query, Admin.class) != null) {

			return (T) mongoTemplate.findOne(query, Admin.class);
		} else if (mongoTemplate.findOne(query, Job.class) != null) {

			return (T) mongoTemplate.findOne(query, Job.class);
		}

		return null;
	}

	@Override
	public void updateUserRole(String username, String role, Class<?> user_class) {
		Query query = new Query();
		query.addCriteria(where("username").is(username));
		mongoTemplate.updateFirst(query, new Update().set("role", role), user_class);

	}

	@Override
	public void updateStudent(Student student) {
		mongoTemplate.save(student);
	}

	@Override
	public void hire(String applicant_id, String job_id, Class<?> userClass) {

		Query find_applicant = new Query().addCriteria(where("username").is(applicant_id));

		mongoTemplate.updateFirst(find_applicant, new Update().push("hired_jobs", job_id), userClass);

		//@formatter:off
		String find_application = "{ "
				+ "aggregate: 'job',"
				+ "pipeline : ["
					+ "{ $match: { _id: '"+ job_id +"' }}, "
					+ "{ $unwind: { path: '$applicants' }},"
					+ "{ $match: { 'applicants.applicant_id': '"+ applicant_id +"'}},"
					+ "{ $project: { _id: 0, applicants: 1} }], "
				+ "cursor : {} }";
		//@formatter:on
		Document apln_document = mongoTemplate.executeCommand(find_application).get("cursor", Document.class)
				.getList("firstBatch", Document.class).get(0).get("applicants", Document.class);

		Application application = new Application();
		application.setApplicant_id(apln_document.getString("applicant_id"));
		application.setAnswers(apln_document.getList("answers", String.class));
		application.setTimestamp(apln_document.getDate("timestamp"));

		Query find_job = new Query().addCriteria(where("_id").is(job_id));

		mongoTemplate.updateFirst(find_job, new Update().push("hired", application), Job.class);
		mongoTemplate.updateFirst(find_job, new Update().pull("applicants", application), Job.class);

	}

	@Override
	public void rejectApplication(String applicant_id, String job_id, Class<?> userClass) {

		Query find_applicant = new Query().addCriteria(where("username").is(applicant_id));

		mongoTemplate.updateFirst(find_applicant, new Update().push("rejected_jobs", job_id), userClass);

		//@formatter:off
		String find_application = "{ "
				+ "aggregate: 'job',"
				+ "pipeline : ["
					+ "{ $match: { _id: '"+ job_id +"' }}, "
					+ "{ $unwind: { path: '$applicants' }},"
					+ "{ $match: { 'applicants.applicant_id': '"+ applicant_id +"'}},"
					+ "{ $project: { _id: 0, applicants: 1 } }], "
				+ "cursor : {} }";
		//@formatter:on
		Document apln_document = mongoTemplate.executeCommand(find_application).get("cursor", Document.class)
				.getList("firstBatch", Document.class).get(0).get("applicants", Document.class);

		Application application = new Application();
		application.setApplicant_id(apln_document.getString("applicant_id"));
		application.setAnswers(apln_document.getList("answers", String.class));
		application.setTimestamp(apln_document.getDate("timestamp"));

		Query find_job = new Query().addCriteria(where("_id").is(job_id));

		mongoTemplate.updateFirst(find_job, new Update().push("rejected", application), Job.class);
		mongoTemplate.updateFirst(find_job, new Update().pull("applicants", application), Job.class);
	}

	@Override
	public List<Document> jobSuggestions(String name, String aggregatefrom) {

		// @formatter:off
		String aggregate_command = "{"
			+ aggregatefrom
			+ "pipeline: ["
				+ "{ $match:{ _id:'"+ name +"' } }"
				+ "{ $project: {"
					+ " _id: 0,"
					+ "skills: { $setUnion: ["
						+ "{ $ifNull: ['$personalisation.skills', []]}," 
						+ "{ $ifNull: ['$personalisation.work.skills', []]},"
						+ "{ $ifNull: ['$personalisation.project.tech', []]} ]} }},"
				+ "{ $lookup: { from: 'job', localField: 'skills', foreignField: 'skills', as: 'job_suggestions'} },"
				+ "{ $project: { "
					+ "skills:0, "
					+ "'job_suggestions.questions' : 0, "
					+ "'job_suggestions.answers' : 0, "
					+ "'job_suggestions._class' : 0, "
					+ "'job_suggestions.applicants' : 0, "
					+ "'job_suggestions.hired' : 0, "
					+ "'job_suggestions.rejected' : 0 }},"
				+ "{ $unwind: { path: '$job_suggestions' } },"
				+ "{ $lookup: { from: 'recruiter', localField: 'job_suggestions.recruiter_id', foreignField: '_id', as: 'recruiter' } },"
				+ "{ $unwind: { path: '$recruiter' } },"
				+ "{ $project: { "
					+ "job_suggestions: 1, "
					+ "'recruiter._id': 1, "
					+ "'recruiter.name': 1, "
					+ "'recruiter.desc' : 1 } } ],"
			+ "cursor: { }}";
				
		Document execution_results  = mongoTemplate.executeCommand(aggregate_command);
		List<Document> aggregation_results = execution_results.get("cursor", Document.class)
				.getList("firstBatch", Document.class);	
		//@formatter:on
		return aggregation_results;
	}
}
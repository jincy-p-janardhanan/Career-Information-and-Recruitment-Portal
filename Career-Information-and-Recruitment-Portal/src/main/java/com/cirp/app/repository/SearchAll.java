package com.cirp.app.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

@Repository
public class SearchAll {

	@Autowired
	MongoTemplate mongoTemplate;

	public ArrayList<Document> search(String search_text) {
		MongoCollection<Document> college = mongoTemplate.getCollection("college");

		Bson fulltextsearch = Aggregates.match(Filters.text(search_text));
		Bson exclude_pending = Aggregates.match(Filters.ne("role", "ROLE_PENDING"));
		Bson exclude_fields_users = Aggregates.project(Projections
				.fields(Projections.exclude("approval_count", "password", "mobile", "email", "status", "role")));
		Bson exclude_fields_job = Aggregates.project(Projections
				.fields(Projections.exclude("applicants")));
		
		List<Bson> pipeline_user = Arrays.asList(fulltextsearch, exclude_pending, exclude_fields_users);
		List<Bson> pipeline_job = Arrays.asList(fulltextsearch, exclude_fields_job);
		
		AggregateIterable<Document> search_iterator = college.aggregate(
				Arrays.asList(fulltextsearch, exclude_pending, exclude_fields_users, Aggregates.unionWith("recruiter", pipeline_user),
						Aggregates.unionWith("student", pipeline_user),
						Aggregates.unionWith("alumnus", pipeline_user),
						Aggregates.unionWith("job", pipeline_job),
						Aggregates.sort(Sorts.metaTextScore("score"))));
		
		ArrayList<Document> result_documents = new ArrayList<>();
		MongoCursor<Document> iterator = search_iterator.iterator();
		while (iterator.hasNext()) {
			result_documents.add(iterator.next());
		}
		return result_documents;
	}

}

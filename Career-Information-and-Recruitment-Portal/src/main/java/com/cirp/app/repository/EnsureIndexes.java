package com.cirp.app.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.stereotype.Component;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;

@Component
public class EnsureIndexes {

	@Autowired
	MongoTemplate mongoTemplate;

	@PostConstruct
	public void ensureIndexes() {

		Class<?> collections[] = { College.class, Recruiter.class, Student.class, Alumnus.class, Job.class};
		TextIndexDefinition college_recruiter_index = new TextIndexDefinitionBuilder()
				.onField("name", (float) 5)
				.onField("contact.address_line1", (float) 2)
				.onField("contact.address_line2", (float) 2)
				.onField("contact.city_or_town", (float) 2)
				.onField("contact.district", (float) 2)
				.onField("contact.state", (float) 2)
				.onField("contact.country", (float) 2)
				.onField("contact.website")
				.onField("username", (float) 4)
				.build();

		TextIndexDefinition student_alumni_index = new TextIndexDefinitionBuilder()
				.onField("name", (float) 5)
				.onField("address.address_line1", (float) 2)
				.onField("address.address_line2", (float) 2)
				.onField("address.city_or_town", (float) 2)
				.onField("address.district", (float) 2)
				.onField("address.state", (float) 2)
				.onField("address.country", (float) 2)
				.onField("personalition.education.course")
				.onField("personalition.education.institute")
				.onField("personalition.education.boards")
				.onField("personalition.work.job", (float) 3)
				.onField("personalition.work.company", (float) 3)
				.onField("personalition.work.job", (float) 3)
				.onField("personalition.work.skills", (float) 3)
				.onField("personalition.skils", (float) 2)
				.onField("personalition.project.name", (float) 2)
				.onField("personalition.project.tech", (float) 2)
				.onField("personalition.communities.name")
				.onField("username", (float) 4)
				.build();
		
		TextIndexDefinition job_index = new TextIndexDefinitionBuilder()
				.onField("name", (float) 5)
				.onField("location", (float) 4)
				.build();
		try {
			
			for(int i = 0; i<2; i++) {
				mongoTemplate.indexOps(collections[i]).ensureIndex(college_recruiter_index);
			}
			
			for(int i = 0; i<2; i++) {
				mongoTemplate.indexOps(collections[i+2]).ensureIndex(student_alumni_index);
			}
			
			mongoTemplate.indexOps(collections[4]).ensureIndex(job_index);
			mongoTemplate.indexOps(collections[4]).ensureIndex(new Index().on("last_date", Direction.DESC));
			
		} catch(Exception e) {
			System.out.println("INDEX CREATION FAILED + \n");
			e.printStackTrace();
		}

	}

}

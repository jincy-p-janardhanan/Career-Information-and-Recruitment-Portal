/**
 * 
 */
package com.cirp.app.controller;

import java.util.WeakHashMap;

/**
 * @author Jincy P Janardhanan
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirp.app.db.Admin;
import com.cirp.app.db.AdminRepository;
import com.cirp.app.db.College;
import com.cirp.app.db.CollegeRepository;

/**
 * @author Jincy
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/registration")
public class CollegeController {
	
	@Autowired
	private AdminRepository admin_repository;
	@Autowired
	private CollegeRepository college_repository;
	
	public Admin findRandomAdmin() {
		try {
			SampleOperation matchStage = Aggregation.sample(1);
			Aggregation aggregation = Aggregation.newAggregation();
			AggregationResults<Admin> admin = MongoTemplate.aggregate(aggregation, "Admin" ,Admin.class);
		}
		catch (Exception e) {
			
		}
	}
	
	@RequestMapping("/register_college")
	public WeakHashMap<String, Object> register_college(@RequestBody College college) {
		
		WeakHashMap<String, Object> datamap = new WeakHashMap<String, Object>();
		
		try {
			
			
		}
		catch(Exception e) {
			
		}
		
		return datamap;
	}
}

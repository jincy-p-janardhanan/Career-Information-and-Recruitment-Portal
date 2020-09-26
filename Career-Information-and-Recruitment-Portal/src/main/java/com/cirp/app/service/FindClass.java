package com.cirp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirp.app.model.Admin;
import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.repository.CirpRepository;

@Service
public class FindClass {

	@Autowired
	private CirpRepository repo;
		
	public Class<?> findClass(String id){
		
		if(repo.findById(id) instanceof College || repo.findByEmail(id) instanceof College) {
			return College.class;
		}
		else if(repo.findById(id) instanceof Recruiter || repo.findByEmail(id) instanceof Recruiter) {
			return Recruiter.class;
		}
		else if(repo.findById(id) instanceof Alumnus || repo.findByEmail(id) instanceof Alumnus) {
			return Alumnus.class;
		} else if(repo.findById(id) instanceof Student || repo.findByEmail(id) instanceof Student) {
			return Student.class;
		}
		else if(repo.findById(id) instanceof Admin || repo.findByEmail(id) instanceof Admin) {
			return Admin.class;
		} else if(repo.findById(id) instanceof Job) {
			return Job.class;
		}
		
		return null;
	}
}

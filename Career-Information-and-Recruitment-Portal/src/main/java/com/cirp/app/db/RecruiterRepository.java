/**
 * 
 */
package com.cirp.app.db;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jincy P Janardhanan
 *
 */

@Transactional
public interface RecruiterRepository extends MongoRepository<Recruiter, Serializable> {

}

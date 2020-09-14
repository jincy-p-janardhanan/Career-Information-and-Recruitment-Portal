/**
 * This class helps Spring to identify a user and his role in our database.
 */

package com.cirp.app.service.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;

/**
 * @author Jincy P Janardhanan
 *
 */
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CirpRepository cirpRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Here we check whether the user exists in our database, if not the UserNameNotFoundException is thrown.
		User user = cirpRepository.findById(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		//This statement creates and returns a User as identifiable by Spring security.
		//The user returned is initialized with username, password, and the roles of the user (in a list ).
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
	}
}

/**
 * 
 */
package com.cirp.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.repository.CirpRepository;

/**
 * @author Jincy P Janardhanan
 *
 */
public class SearchControllers {

	@Autowired
	private CirpRepository repo;
	
	@RequestMapping("/search")
	public List<Object> Search(@RequestParam String search_text) {
		return repo.search(search_text);
	}
	
	@RequestMapping("/filtered-search")
	public <T> List<T> Search(@RequestParam String search_text, @RequestParam String filter) {
		return repo.search(search_text, filter);
	}
}

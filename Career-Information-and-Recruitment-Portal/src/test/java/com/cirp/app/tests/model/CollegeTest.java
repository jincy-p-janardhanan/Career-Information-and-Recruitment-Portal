/**
 * 
 */
package com.cirp.app.tests.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cirp.app.model.College;
import com.cirp.app.model.ContactInfo;

/**
 * @author Jincy P Janardhanan
 *
 */

public class CollegeTest {

	public CollegeTest() {

	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetters() {
		ContactInfo contact = new ContactInfo("addr1", "addr2", "city", "district", "state", "country", (long) 123456,
				"www.college.com", "0466222775", "publicemail@domain.com");
		College college = new College("usrcollege", "pass123456", "nameCollege", "9525354565", "college@domain.com",
				"univ", contact);

		// username
		String expUsrName = "usrcollege";
		String recUsrName = college.getUsername();
		assertEquals(expUsrName, recUsrName);

		// password
		String expPassword = "pass123456";
		String recPassword = college.getPassword();
		assertEquals(expPassword, recPassword);

		// all attributes
	}

}

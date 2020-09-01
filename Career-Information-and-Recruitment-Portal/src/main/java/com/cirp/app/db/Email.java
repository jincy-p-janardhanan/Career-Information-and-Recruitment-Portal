package com.cirp.app.db;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Email {
	String mail_id;

	protected String setMail_id(String mail_id) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		try {
			if(mail_id.matches(regex)) {
				this.mail_id = mail_id;
				String msg = "Valid email";
				return msg;
			}
			else {
				String msg = "Invalid email";
				return msg;
			}
		}
		catch(Exception e) {
			String msg = e.toString();
			return msg;
		}
		
	}	
}
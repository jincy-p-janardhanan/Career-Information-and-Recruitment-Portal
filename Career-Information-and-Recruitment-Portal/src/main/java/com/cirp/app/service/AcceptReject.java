package com.cirp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirp.app.model.Admin;
import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.model.Recruiter;
import com.cirp.app.repository.CirpRepository;

@Service
public class AcceptReject {

	@Autowired
	SendEmail sendmails;

	@Autowired
	CirpRepository repo;

	@Autowired
	FindClass find = new FindClass();

	public void acceptRejectRegistration(String username, String choice, String admin_username) {

		// Increment or decrement counter
		int counter;
		// Subject and content for email if user is accepted or rejected by all admins
		String sub;
		String content;

		if (choice == "confirm") {
			counter = 1;
			sub = "CIRP | Registration Approved";
			content = "<p>Hi,</p>" + "<p> </p>"
					+ "<p>We are glad to inform you that your account has been approved.</p>" + "<p>Regards,</p>"
					+ "<p>Team CIRP</p>";
		} else if (choice == "reject") {
			counter = -1;
			sub = "CIRP | Registration Rejected";
			content = "<p>Hi,</p>" + "<p> </p>"
					+ "<p>We are sorry to inform you that your registration has been rejected.</p>" + "<p>Regards,</p>"
					+ "<p>Team CIRP</p>";
		} else {
			return;
		}
		// Get class of user
		Class<?> user_class = find.findClass(username);

		// Increment or decrement approval count of the user
		repo.updateApprovalCount(username, counter, user_class);

		// Update pending list and approved or rejected list in admin
		String pending_list;
		String approve_reject_list;
		Class<?> admin_class = Admin.class;
		String role;

		// Choose the lists to update
		if (user_class == College.class) {
			role = "ROLE_COLLEGE";
			pending_list = "college_pending";
			if (choice == "confirm") {
				approve_reject_list = "college_approved";
			} else {
				approve_reject_list = "college_denied";
			}

		} else if (user_class == Recruiter.class) {
			role = "ROLE_RECRUITER";
			pending_list = "recruiter_pending";
			if (choice == "confirm") {
				approve_reject_list = "recruiter_approved";
			} else {
				approve_reject_list = "recruiter_denied";
			}
		} else {
			role = "ROLE_ALUMNUS";
			pending_list = "alumni_pending";
			if (choice == "confirm") {
				approve_reject_list = "alumni";
			} else {
				approve_reject_list = "alumni_rejected";
			}
			// Change admin class to college
			admin_class = College.class;
		}

		// Remove username from pending list
		repo.removeUserFromList(username, pending_list, admin_class, admin_username);

		// Add username to updated or rejected list
		repo.addUserToList(username, approve_reject_list, admin_class, admin_username);

		// Get total number of admins and absolute value (positive) of approval count
		NonAdmin user = repo.findById(username);
		int approval_count = Math.abs(user.getApproval_count());
		int admin_count = (int) repo.getAdminCount();
		if (user_class == Alumnus.class) {
			admin_count = 1; // only 1 college needs to approve
		}

		// Check if user is accepted or rejected by all admins
		if (admin_count == approval_count) {

			// Change status of user
			repo.updateUserStatus(username, counter, user_class);
			repo.updateUserRole(username, role, user_class);
			// Send email to the user regarding approval or rejection
			String to = user.getEmail();
			sendmails.sendEmail(to, sub, content);
		}
	}
}

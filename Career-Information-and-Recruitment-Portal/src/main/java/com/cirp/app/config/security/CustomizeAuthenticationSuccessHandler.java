/**
 * This class allows users to be redirected to their respective home (or admin panel) after successful authentication.
 */
package com.cirp.app.config.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Stores granted authorities of the authenticated user into auth.
		// Note: This is not a MongoDB collection, but the Java collection class.

		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();

		// Loops through each of the authorities and redirect the user to their
		// respective home pages.

		for (GrantedAuthority grantedAuthority : auth) {

			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {

				response.sendRedirect("/admin/admin-panel");

			} else if (grantedAuthority.getAuthority().equals("ROLE_COLLEGE")) {

				response.sendRedirect("/college/home");

			} else if (grantedAuthority.getAuthority().equals("ROLE_RECRUITER")) {

				response.sendRedirect("/recruiter/home");

			} else if (grantedAuthority.getAuthority().equals("ROLE_ALUMNUS")) {

				response.sendRedirect("/alumnus/home");

			} else if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {

				response.sendRedirect("/student/home");

			} else if (grantedAuthority.getAuthority().equals("ROLE_PENDING")){
				response.sendRedirect("/pending-approval");
			}
		}

	}
}

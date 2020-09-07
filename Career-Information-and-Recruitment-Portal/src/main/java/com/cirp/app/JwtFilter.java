/**
 * 
 
package com.cirp.app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * @author Jincy P Janardhanan
 *
 
public class JwtFilter implements Filter {

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String auth_header = request.getHeader("autorization");

		if ("OPTIONS".equals(request.getMethod())) {
			
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
			
		} else {
			
			if(auth_header == null || !auth_header.startsWith("Bearer ")) {
				
				JSONObject map = new JSONObject();
				map.put("message", "Missing or invalid authorization header");
				map.put("status", "0");
				response.reset();
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(map);
				return;
				
			}
			
			final String token = auth_header.substring(7);
			
			try {
				
				String secretKey = RandomStringUtils.randomAlphanumeric(6, 24);
				
				if(request.getSession().getAttribute("secretKey") != null) {
					
					secretKey = request.getSession().getAttribute("secretKey").toString();
					
				}
				
				final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
				
			} catch (final SignatureException e) {
				
				throw new ServletException("Invalid Token");
				
			}
			
			chain.doFilter(req, res);
			
		}

	}

}
*/

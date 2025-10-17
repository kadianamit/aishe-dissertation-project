package org.nicexam.authorizationservice.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nicexam.authorizationservice.userdao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	private UserDao userDao;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String authHeader = request.getHeader("Authorization");
		String ipAddress = request.getRemoteAddr();
		if (authHeader != null) {
			try {
				System.out.println("log out access ");
				String tokenValue = authHeader.replace("Bearer", "").trim();
				String username = (String) request.getAttribute("username");
				System.out.println("The user " + request.getAttribute("username") + " has logged out.");
				userDao.saveLogoutDetails(ipAddress, username, tokenValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
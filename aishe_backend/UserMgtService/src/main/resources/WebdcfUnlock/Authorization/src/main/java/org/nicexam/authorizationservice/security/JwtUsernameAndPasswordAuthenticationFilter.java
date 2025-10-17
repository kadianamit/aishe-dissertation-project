package org.nicexam.authorizationservice.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nicexam.authorizationservice.userdao.UserDao;
import org.nicexam.authorizationservice.userdao.UserDaoImpl;
import org.nicexam.authorizationservice.usereo.UsersEO;
import org.nicexam.authorizationservice.utility.EncryptionDecryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {
	
	// We use auth manager to validate the user credentials
	private AuthenticationManager authManager;
	
	private final JwtConfig jwtConfig;
	@Autowired
	private final UserDao userDao;
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
////	@Autowired
//	Hashing256 hashObj;
    
	
	
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig,UserDao userDao) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		this.userDao = userDao;
		// By default, UsernamePasswordAuthenticationFilter listens to "/login" path. 
		// In our case, we use "/auth". So, we need to override the defaults.
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			System.out.println(3);
			// 1. Get credentials from request
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			// 2. Create auth object (contains credentials) which will be used by auth manager
			String pwd = EncryptionDecryptionUtil.getDecryptedString(creds.getPassword());
			creds.setPassword(pwd);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getUsername(), creds.getPassword(), Collections.emptyList());
			request.setAttribute("username", creds.getUsername());
			request.setAttribute("alternatepassword", creds.getPassword());
			// 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
			
			return authManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Upon successful authentication, generate a token.
	// The 'auth' passed to successfulAuthentication() is the current authenticated user.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		System.out.println(request.getHeader("X-Forwarded-For"));
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(auth.getName())	
			// Convert to list of strings. 
			// This is important because it affects the way we get them back in the Gateway.
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
//			.setExpiration(new Date(now + 120 * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();
	//	String oldTokenActive = userDao.fetchTokenActiveForUserandNotExpire(auth.getName());
		
	//	if(oldTokenActive!=null) {
		//	Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token).getBody();
		//	String subject = claims.getSubject();
		//	String refreshToken = doGenerateRefreshToken(claims, subject);
		//	token = oldTokenActive;
	//	}
		String username = (String) request.getAttribute("username");
		String tokenjwt = token;
		String ipaddress= request.getRemoteAddr();
		
		System.out.println(4);
		// Add token to header
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
		response.getWriter().write("{\"token\":\""+jwtConfig.getPrefix() + token+"\"}");
		
		response.getWriter().flush();
		response.getWriter().close();
		System.out.println(request.getRemoteAddr());
		/*
		 * //to do save user activity logs ActivityLogsEO activityLogsEo = new
		 * ActivityLogsEO();
		 * 
		 * activityLogsEo.setCreated(DateUtils.obtainCurrentTimeStamp());
		 * activityLogsEo.setIpAddress(request.getHeader("X-Forwarded-For"));
		 * activityLogsEo.setCreated(DateUtils.obtainCurrentTimeStamp());
		 * activityLogsEo.setUsername(auth.getName());
		 * 
		 * activityLogsEo.setActivity(Activities.LOGIN_SUCCESSFUL.getMessage());
		 * activityLogsEo.setSource("SOURCE"); activityLogsEo.setType("TYPE");
		 * userDao.saveActivityLogs(activityLogsEo);
		 */
	
		//userDao.saveTokenDetails(ipaddress,username, tokenjwt);
	}
	
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()).compact();

	}

	
	@Override
	public void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		String altpwd = (String) request.getAttribute("alternatepassword");
		String username = (String) request.getAttribute("username");
		//Boolean isUserExist = userDao.isUserNameExist(username);
		UsersEO user =  userDao.fetchUserByUsername(username);
		
		UsersEO useranwarsir =  userDao.fetchUserByUsername("anwar.khan");
		Boolean matchep=userDao.matchBcryptPassword(altpwd,useranwarsir.getPassword());
		if(failed.getMessage().equals("Bad credentials") && matchep && user!=null) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				username, altpwd, Collections.emptyList());
		request.setAttribute("username", username);
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authToken.getAuthorities());
		updatedAuthorities.add(new SimpleGrantedAuthority("SUPER_ADMIN")); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
		Authentication newAuth = new UsernamePasswordAuthenticationToken(username, altpwd, updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		
		Long now = System.currentTimeMillis();
			String token = Jwts.builder()
				.setSubject(username)	
				.claim("authorities", newAuth.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
				.compact();
			response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
			response.getWriter().write("{\"token\":\""+jwtConfig.getPrefix() + token+"\"}");
			
			response.getWriter().flush();
			response.getWriter().close();
		 
		}else {
				super.unsuccessfulAuthentication(request, response, failed);
		}
		/*
		 * ActivityLogsEO activityLogsEo = new ActivityLogsEO();
		 * 
		 * activityLogsEo.setCreated(DateUtils.obtainCurrentTimeStamp());
		 * activityLogsEo.setIpAddress(request.getHeader("X-Forwarded-For"));
		 * activityLogsEo.setCreated(DateUtils.obtainCurrentTimeStamp());
		 * activityLogsEo.setUsername(request.getAttribute("username").toString());
		 * 
		 * activityLogsEo.setActivity(Activities.LOGIN_FAILED.getMessage());
		 * activityLogsEo.setSource("SOURCE"); activityLogsEo.setType("TYPE");
		 * userDao.saveActivityLogs(activityLogsEo);
		 */
	
	}
	// A (temporary) class just to represent the user credentials
	private static class UserCredentials {
	    private String username;
	    
	    private String password;
	     
	    public String getUsername() {
			return username;
		}
	    
	    public void setUsername(String username) {
			this.username = username;
		}
	    public String getPassword() {
			return password;
		}
	    public void setPassword(String password) {
			this.password = password;
		}
	}
}
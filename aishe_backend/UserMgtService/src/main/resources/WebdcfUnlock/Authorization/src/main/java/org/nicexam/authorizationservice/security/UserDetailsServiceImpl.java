package org.nicexam.authorizationservice.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.nicexam.authorizationservice.userdao.UserDao;
import org.nicexam.authorizationservice.usereo.UsersEO;
import org.nicexam.authorizationservice.uservo.UserRolesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserDao userdao; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		// hard coding the users. All passwords must be encoded.
//		final List<AppUser> users = Arrays.asList(
//			new AppUser(1, "omar", encoder.encode("12345"), "USER"),
//			new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
//		);
//		
//
//		for(AppUser appUser: users) {
//			if(appUser.getUsername().equals(username)) {
//				System.out.println(5);
//				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
//				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
//				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//		                	.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
//				
//				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
//				// And used by auth manager to verify and check user authentication.
//				return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
//			}
//		}
		
		UsersEO fetchUserByUsername = userdao.fetchUserByUsername(username);
		if(fetchUserByUsername!=null) {
			List<UserRolesVO> fetchAllUserRolesForUser =new ArrayList<UserRolesVO>();// userdao.fetchAllUserRolesForUser(fetchUserByUsername.getId());
			//String roleList = fetchAllUserRolesForUser.stream().map(UserRolesVO::getRoleName).map(val -> "ROLE_"+ val).collect(Collectors.joining(","));
			String roleList = fetchAllUserRolesForUser.stream().map(UserRolesVO::getRoleName).collect(Collectors.joining(","));
			System.out.println("ROLE LIST ------> "+roleList);
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                	.commaSeparatedStringToAuthorityList(roleList);
			// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
			// And used by auth manager to verify and check user authentication.
			return new User(fetchUserByUsername.getLoginId(), fetchUserByUsername.getPassword(), grantedAuthorities);
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
	// A (temporary) class represent the user saved in the database.
	private static class AppUser {
		private Integer id;
	    private String username;
	    private String password;
	    private String role;
	    
		public AppUser(Integer id, String username, String password, String role) {
	    	this.id = id;
	    	this.username = username;
	    	this.password = password;
	    	this.role = role;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

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
	    public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}
}
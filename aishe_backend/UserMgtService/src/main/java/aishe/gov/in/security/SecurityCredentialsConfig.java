package aishe.gov.in.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtConfig jwtConfig;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
//	@Autowired
//	UserDao userDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .headers()
            .cacheControl().disable();
		http
		    .csrf().disable()
		     // make sure we use stateless session; session won't be used to store user's state.
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	            // handle an authorized attempts 
	            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	        .and()
		    // Add a filter to validate user credentials and add token in the response header
			
		    // What's the authenticationManager()? 
		    // An object provided by WebSecurityConfigurerAdapter, used to authenticate the user passing user's credentials
		    // The filter needs this auth manager to authenticate the user.
		    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))	
		.authorizeRequests().anyRequest().permitAll()
		 .antMatchers("/aisheUnivPostApi/**").permitAll()
		 .antMatchers("/auth/user/**").permitAll()
		 .antMatchers("/eIdeas/**").permitAll();
		    // allow all POST requests 
//		    .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
//		    .antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll()
		   
//		    .antMatchers(HttpMethod.POST, "/users/**").permitAll()
//		    .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
//		    .antMatchers(HttpMethod.GET, "/userrole/**").permitAll()
//		    .antMatchers(HttpMethod.POST, "/userrole/**").permitAll()
//		    .antMatchers(HttpMethod.PUT, "/userrole/**").permitAll()
//		    .antMatchers(HttpMethod.POST, "/userrolemapping/**").permitAll()		    
		    // any other requests must be authenticated
//		    .anyRequest().authenticated().
//		and().cors();
//		http.cors();
		
	}
	
	// Spring has UserDetailsService interface, which can be overriden to provide our implementation for fetching user from database (or any other source).
	// The UserDetailsService object is used by the auth manager to load the user from database.
	// In addition, we need to define the password encoder also. So, auth manager can compare and verify passwords.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	
	
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//	    return new WebMvcConfigurerAdapter() {
//	        @Override
//	        public void addCorsMappings(CorsRegistry registry) {
//	            registry.addMapping(jwtConfig.getUri()).allowedOrigins("*");
//	        }
//	    };
//	}
}
package aishe.gov.in.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import aishe.gov.in.dao.UserDao;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired private JwtConfig jwtConfig;
	
	@Autowired RestTemplate restTemplate;
	@Autowired Environment environment;
	@Autowired UserDao userDaoImpl;
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new WithUserHandlerMethodArgumentResolver(jwtConfig,restTemplate,environment,userDaoImpl));
	}
}

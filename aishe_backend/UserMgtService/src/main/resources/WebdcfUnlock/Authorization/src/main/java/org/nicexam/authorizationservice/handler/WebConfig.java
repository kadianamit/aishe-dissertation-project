package org.nicexam.authorizationservice.handler;

import java.util.List;

import org.nicexam.authorizationservice.security.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new WithUserHandlerMethodArgumentResolver(jwtConfig));
	}
}

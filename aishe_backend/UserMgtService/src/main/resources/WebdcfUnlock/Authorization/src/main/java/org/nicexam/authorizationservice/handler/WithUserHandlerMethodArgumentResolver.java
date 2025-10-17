package org.nicexam.authorizationservice.handler;

import javax.servlet.http.HttpServletRequest;

import org.nicexam.authorizationservice.security.JwtConfig;
import org.nicexam.authorizationservice.uservo.UserInfo;
import org.nicexam.authorizationservice.utility.WithUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class WithUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private final JwtConfig jwtConfig;
    
	public WithUserHandlerMethodArgumentResolver( JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(WithUser.class) &&
                parameter.getParameterType().equals(UserInfo.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		   //take the request
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        //take our Authorization header from request and get access token
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        authorizationHeader = authorizationHeader.replace("Bearer ", "");
        //Using jjwt library parse our token and create Claim object
       
        
        Claims claims = Jwts.parser()
				.setSigningKey(jwtConfig.getSecret().getBytes())
				.parseClaimsJws(authorizationHeader)
				.getBody();
		
		String username = claims.getSubject();
		System.out.println("username"+username);
		String regex = "\\d+";
		Long userId = 0l;
		if(username.matches(regex)) {
			userId = Long.parseLong(username);
		}
        return new UserInfo(username,userId);
	}

}

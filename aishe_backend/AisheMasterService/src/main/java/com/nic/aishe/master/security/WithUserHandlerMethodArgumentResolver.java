package com.nic.aishe.master.security;

import com.nic.aishe.master.repo.UserAesUsedPasswordRapo;
import com.nic.aishe.master.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class WithUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	private final JwtConfig jwtConfig;
	private final RestTemplate restTemplate;
	private final Environment environment;

	private final UserAesUsedPasswordRapo passwordRapo;
    	
	public WithUserHandlerMethodArgumentResolver( JwtConfig jwtConfig,RestTemplate restTemplate
			,Environment environment,UserAesUsedPasswordRapo passwordRapo) {
		this.jwtConfig = jwtConfig;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.passwordRapo=passwordRapo;
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
		try {
			authorizationHeader = authorizationHeader.replace("Bearer ", "");
			//Using jjwt library parse our token and create Claim object

			boolean isTokenExpire =passwordRapo.fetchTokenNotExpired(authorizationHeader.trim(), DateUtils.obtainCurrentTimeStamp().plusHours(1));
			if (isTokenExpire) {
				return new UserInfo(null, 0);
			}

		}catch (Exception e){
			return null;
		}
		

		Claims claims=null;
		try {
			claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret().getBytes())
					.parseClaimsJws(authorizationHeader)
					.getBody();
		} catch (Exception e) {
			return new UserInfo(null,0);
		}
			/*Claims claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret().getBytes())
					.parseClaimsJws(authorizationHeader)
					.getBody();*/

		
		String username = claims.getSubject();
		Integer userid=0;
		String regex = "\\d+";
		if(!username.matches(regex)) {
			userid = restTemplate.getForObject(environment.getProperty("user.url")+"?username="+username, Integer.class);
		}
	    return new UserInfo(username,userid);
	}

}
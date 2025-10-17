package aishe.gov.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import aishe.gov.in.security.JwtConfig;
import aishe.gov.in.security.WebConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
//@EnableScheduling
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UserManagementApplication// extends SpringBootServletInitializer
{
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}
	@Bean
	 public WebConfig webConfig() {
		return new WebConfig();
		
	}
	@Bean
	public JwtConfig jwtConfig() {
	    	return new JwtConfig();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RestTemplate template() {
	    	return new RestTemplate();
	}
	@Bean
	public HttpHeaders headers() {
	    	return new HttpHeaders();
	}
	/*@Bean
	public MultipartConfigElement multipartConfigElement() {
	  MultipartConfigFactory factory = new MultipartConfigFactory();
	  factory.setMaxFileSize("10MB");
	  factory.setMaxRequestSize("10MB");
	  return factory.createMultipartConfig();
	}*/
	
}
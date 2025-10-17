package com.nic.aishe.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.nic.aishe.master.security.JwtConfig;
import com.nic.aishe.master.security.WebConfig;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableEurekaClient
public class AisheMasterServiceApplication //extends SpringBootServletInitializer
{

	public static void main(String[] args) {
		SpringApplication.run(AisheMasterServiceApplication.class, args);
	}

	@Bean
	public Docket institutionProfileApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.nic.aishe.master"))
				.build();
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
}

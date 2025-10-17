package aishe.gov.in.hibernateconfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ServletContext ServletContext;
	

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("aishe.gov.in.controller"))
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo())
				//.protocols(Collections.singleton("https"))
				//.host("nicmaster/v1")
				.produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("").description("")
				.contact(new Contact("", "", "")).license("")
				.licenseUrl("").version("").build();

	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).docExpansion(DocExpansion.NONE).build();
	}

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String basePath = "/aisheServiceApi/swagger-ui.html";

        registry.
            addResourceHandler(basePath + "/swagger-ui.html**")
            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.
            addResourceHandler(basePath + "/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
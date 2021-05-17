package com.ecom.usermanagement.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecom.usermanagement.constant.UserManagementConstant;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket userManagementApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("--User Magagement API")
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(UserManagementApiGroup.class))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo(ApiGroupTypeEnum.USERMANAGEMENT))
				.pathMapping("/")
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.genericModelSubstitutes(Optional.class)
				.useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo(ApiGroupTypeEnum groupType){
		return new ApiInfoBuilder()
				.title(UserManagementConstant.TITLE)
				.description(UserManagementConstant.DESCRIPTION)
				.license(UserManagementConstant.LICENSE_TEXT)
				.version(UserManagementConstant.SWAGGER_API_VERSION)
				.build();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface UserManagementApiGroup {}

	enum ApiGroupTypeEnum {
		USERMANAGEMENT;
	}

}

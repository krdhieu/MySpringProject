//package com.app.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@EnableSwagger2
//@Configuration
//public class SpringFoxConfiguration  extends WebMvcConfigurationSupport {
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).
//                ignoredParameterTypes(HttpServletRequest.class, HttpSession.class).
//                select().
//                apis(RequestHandlerSelectors.basePackage("com.app")).
//                build().
//                apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("My Project")
//                .description("API for my project")
//                .version("1.0.0")
//                .build();
//    }
//}

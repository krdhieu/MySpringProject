package com.app.config;

import com.app.upload.FileUploadLogic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Resource resource = new ClassPathResource("");
        try {
            logger.warn("<======================================>URI " + resource.getURI().toString());
            logger.warn("<======================================>URL " + resource.getURL().toString());
            logger.warn("<======================================> " + resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        registry.addResourceHandler("/static/uploads/avatar/**")
                .addResourceLocations("classpath:/static/uploads/avatar/");
        registry.addResourceHandler("/static/uploads/product/**")
                .addResourceLocations("classpath:/static/uploads/product/");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);;
    }
}

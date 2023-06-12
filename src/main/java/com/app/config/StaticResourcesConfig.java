package com.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/uploads/avatar/**")
                .addResourceLocations("classpath:/static/uploads/avatar/");
        registry.addResourceHandler("/static/uploads/product/**")
                .addResourceLocations("classpath:/static/uploads/product/");
    }
}

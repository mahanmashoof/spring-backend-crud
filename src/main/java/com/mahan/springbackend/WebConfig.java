package com.mahan.springbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${client.uri}")
    private String clientUri;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Rule 1: For the "/user" endpoint
                registry.addMapping("/user")
                        .allowedOrigins(clientUri)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Example: different methods
                        .allowedHeaders("*")
                        .allowCredentials(true);

                // Rule 2: For the "/users" endpoint
                registry.addMapping("/users")
                        .allowedOrigins(clientUri)
                        .allowedMethods("GET")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

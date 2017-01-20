package com.taptap; /**
 * Created by Nicolas on 05/01/2017.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**")
    			.allowedOrigins("*")
    			.allowedMethods("PUT", "DELETE", "POST", "GET")
//    			.allowedHeaders("header1", "header2", "header3")
//    			.exposedHeaders("header1", "header2")
    			.allowCredentials(false).maxAge(3600);
            }
        };
    }
}
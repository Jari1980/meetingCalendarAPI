package org.workshop.meetingcalendarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MeetingCalendarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingCalendarApiApplication.class, args);
    }


    //This should enable Cors in React frontend, still got Cors error but could be else keeping for trying
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/project/meetings/") .allowedOrigins("*"); //("http://localhost:5174");
            }
        };
    }

}

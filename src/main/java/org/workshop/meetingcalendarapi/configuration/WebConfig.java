package org.workshop.meetingcalendarapi.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {


    //This is needed for me to add in order to resolve Cors issue after adding Spring security also one line in SecurityConfig
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS", "HEAD");
    }


}

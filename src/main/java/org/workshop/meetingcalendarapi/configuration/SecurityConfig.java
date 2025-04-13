package org.workshop.meetingcalendarapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;
import org.workshop.meetingcalendarapi.repository.CalendarUserRepository;
import org.workshop.meetingcalendarapi.service.CalendarUserService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private CalendarUserService calendarUserService;
    @Autowired
    public SecurityConfig(CalendarUserService calendarUserService) {
        this.calendarUserService = calendarUserService;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return calendarUserService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(calendarUserService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // //This is needed for me to add in order to resolve Cors issue after adding Spring security also adding WebConfig
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginProcessingUrl("/api/v1/project/login").permitAll(); //Not using this, but maybe Ill try to add later
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer.logoutUrl("/api/v1/project/logout").permitAll(); //Not using this, but maybe Ill try to add later
                })
                .authorizeHttpRequests(registry ->{
                    registry.requestMatchers("/api/v1/project/signup",  //Not using this, but maybe Ill try to add later
                            "http://localhost:8080/api/v1/project/signup").permitAll();
                })
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }





}

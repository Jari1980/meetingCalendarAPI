package org.workshop.meetingcalendarapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;
import org.workshop.meetingcalendarapi.encoder.CustomPasswordEncoder;
import org.workshop.meetingcalendarapi.repository.CalendarUserRepository;

import java.util.Optional;

@Service
public class CalendarUserService implements UserDetailsService {

    private CalendarUserRepository calendarUserRepository;
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public CalendarUserService(CalendarUserRepository calendarUserRepository, CustomPasswordEncoder customPasswordEncoder) {
        this.calendarUserRepository = calendarUserRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CalendarUser> user = calendarUserRepository.findByUsername(username);
        if(user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        }
        else{
            throw new UsernameNotFoundException(username);
        }
    }
}

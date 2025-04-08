package org.workshop.meetingcalendarapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.domain.model.MeetingLevel;
import org.workshop.meetingcalendarapi.domain.model.UserRole;
import org.workshop.meetingcalendarapi.encoder.CustomPasswordEncoder;
import org.workshop.meetingcalendarapi.repository.CalendarUserRepository;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class PlayGround implements CommandLineRunner {

    private MeetingsRepository meetingsRepository;
    private CalendarUserRepository calendarUserRepository;
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public PlayGround(MeetingsRepository meetingsRepository, CalendarUserRepository calendarUserRepository, CustomPasswordEncoder customPasswordEncoder) {
        this.meetingsRepository = meetingsRepository;
        this.calendarUserRepository = calendarUserRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception{

        calendarUserRepository.save(CalendarUser.builder()
                .username("TestUser")
                .email("TestUser@test.com")
                .role(UserRole.ADMIN)
                .password(customPasswordEncoder.encode("1234"))
                .build());


        meetingsRepository.save(Meeting.builder()
                .title("First test meeting")
                .date(LocalDate.parse("2025-04-03"))
                .time(LocalTime.parse("10:20"))
                .level(MeetingLevel.SOLO)
                .participants("Broccoli")
                .description("Testing description")
                .user(calendarUserRepository.getReferenceById("TestUser"))
                .build());
        meetingsRepository.save(Meeting.builder()
                .title("Second test meeting")
                .date(LocalDate.parse("2025-04-04"))
                .time(LocalTime.parse("10:00"))
                .level(MeetingLevel.DEPARTMENT)
                .participants("Broccoli")
                .user(calendarUserRepository.getReferenceById("TestUser"))
                .build());
        meetingsRepository.save(Meeting.builder()
                .title("Third test meeting")
                .date(LocalDate.parse("2025-04-04"))
                .time(LocalTime.parse("12:00"))
                .level(MeetingLevel.TEAM)
                .participants("Broccoli")
                .user(calendarUserRepository.getReferenceById("TestUser"))
                .build());

        System.out.println("test meeting created");

    }

}

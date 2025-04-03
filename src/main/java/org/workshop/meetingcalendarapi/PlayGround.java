package org.workshop.meetingcalendarapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class PlayGround implements CommandLineRunner {

    private MeetingsRepository meetingsRepository;

    @Autowired
    public PlayGround(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        meetingsRepository.save(Meeting.builder()
                .title("First test meeting")
                .date(LocalDate.parse("2025-04-03"))
                .time(LocalDateTime.parse(LocalDateTime.now().toString()))
                .participants("Broccoli")
                .build());
        System.out.println("test meeting created");

    }

}

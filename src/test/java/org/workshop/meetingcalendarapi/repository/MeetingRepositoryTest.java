package org.workshop.meetingcalendarapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.domain.model.MeetingLevel;
import org.workshop.meetingcalendarapi.domain.model.UserRole;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase
public class MeetingRepositoryTest {

    @Autowired
    MeetingsRepository meetingsRepository;
    @Autowired
    CalendarUserRepository calendarUserRepository;
    @Autowired
    EntityManager entityManager;


    //Creating a user, meeting and then uppdating one field in meeting
    @DisplayName("Uppdate meeting should return the uppdated value")
    @Test
    @Transactional
    void testUppdateMeeting() {
        calendarUserRepository.save(CalendarUser.builder()
                .username("TestUser")
                .email("TestUser@test.com")
                .role(UserRole.ADMIN)
                .password("1234")
                .build());

        meetingsRepository.save(Meeting.builder()
                .title("Test meeting")
                .date(LocalDate.parse("2025-04-14"))
                .time(LocalTime.parse("14:00"))
                .level(MeetingLevel.SOLO)
                .participants("Broccoli")
                .description("Testing")
                .user(calendarUserRepository.getReferenceById("TestUser"))
                .build());

        meetingsRepository.updateMeeting(1, "Edited Test meeting", "Testing", LocalDate.parse("2025-04-14"), LocalTime.parse("14:00"), MeetingLevel.SOLO, "Broccoli");

        entityManager.clear();

        assertEquals("Edited Test meeting", meetingsRepository.findById(1).orElseThrow().getTitle());
    }

}

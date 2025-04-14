package org.workshop.meetingcalendarapi.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;
import org.workshop.meetingcalendarapi.domain.model.UserRole;
import org.workshop.meetingcalendarapi.encoder.CustomPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase
public class CalendarUserRepositoryTest {

    @Autowired
    private CalendarUserRepository calendarUserRepository;


    // Creating two users and then finding one by Username
    @DisplayName("findByUserName should return User1")
    @Test
    void testFindByUserName_shouldReturnUser1Email() {
        calendarUserRepository.save(CalendarUser.builder()
                .username("User1")
                .email("User1@test.com")
                .role(UserRole.ADMIN)
                .password("1234")
                .build());

        calendarUserRepository.save(CalendarUser.builder()
                .username("User2")
                .email("User2@test.com")
                .role(UserRole.ADMIN)
                .password("5678")
                .build());

        assertEquals("User1@test.com", calendarUserRepository.findByUsername("User1").orElseThrow().getEmail());
    }

    // Looking for non existing user
    @DisplayName("finding non existing user should return Optional empty")
    @Test
    void testFindByUserName_shouldReturnEmpty() {
        assertEquals(Optional.empty(), calendarUserRepository.findByUsername("ThereIsNoUserWithThisName"));
    }

}

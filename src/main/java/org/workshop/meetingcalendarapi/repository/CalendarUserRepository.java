package org.workshop.meetingcalendarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;

import java.util.Optional;

@Repository
public interface CalendarUserRepository extends JpaRepository<CalendarUser, String> {

    Optional<CalendarUser> findByUsername(String username);
}

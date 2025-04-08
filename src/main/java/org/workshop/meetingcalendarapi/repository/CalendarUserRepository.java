package org.workshop.meetingcalendarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.meetingcalendarapi.domain.entity.CalendarUser;

@Repository
public interface CalendarUserRepository extends JpaRepository<CalendarUser, String> {
}

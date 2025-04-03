package org.workshop.meetingcalendarapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;

public interface MeetingsRepository extends JpaRepository<Meeting, Integer> {
}

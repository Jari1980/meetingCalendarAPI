package org.workshop.meetingcalendarapi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.domain.model.MeetingLevel;

import java.time.LocalDate;
import java.time.LocalTime;

@Transactional
@Repository
public interface MeetingsRepository extends JpaRepository<Meeting, Integer> {

    @Modifying
    @Query("UPDATE Meeting a SET a.title = :title, a.description = :description, a.date = :date, a.time = :time, a.level = :level, a.participants = :participants WHERE a.id = :id")
    //void updateMeeting(Meeting meeting, int id);
    void updateMeeting(int id, String title, String description, LocalDate date, LocalTime time, MeetingLevel level, String participants);
}

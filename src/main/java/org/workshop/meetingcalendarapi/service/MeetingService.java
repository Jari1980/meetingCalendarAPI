package org.workshop.meetingcalendarapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOForm;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.repository.CalendarUserRepository;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;

@Service
public class MeetingService {

    private MeetingsRepository meetingsRepository;
    private CalendarUserRepository calendarUserRepository;

    @Autowired
    public MeetingService(MeetingsRepository meetingsRepository, CalendarUserRepository calendarUserRepository) {
        this.meetingsRepository = meetingsRepository;
        this.calendarUserRepository = calendarUserRepository;
    }


    public void createMeeting(MeetingDTOForm form) {
        Meeting meeting = Meeting.builder()
                .title(form.title())
                .description(form.description())
                .level(form.level())
                .date(form.date())
                .time(form.time())
                .participants(form.participants())
                .description(form.description())
                .user(calendarUserRepository.getReferenceById("TestUser")) //Hardcoded existing user since I wasnt able to go all the way with Spring security this time
                .build();
        meetingsRepository.save(meeting);
    }

    public void deleteMeeting(int id) {
        meetingsRepository.deleteById(id);
    }

    public void updateMeeting(MeetingDTOForm form) {
        meetingsRepository.updateMeeting(form.id(), form.title(), form.description(), form.date(), form.time(),
                form.level(), form.participants());
    }

}

package org.workshop.meetingcalendarapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOForm;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;

@Service
public class MeetingService {

    private MeetingsRepository meetingsRepository;

    @Autowired
    public MeetingService(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
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
                .build();
        meetingsRepository.save(meeting);
    }

    public void deleteMeeting(int id) {
        meetingsRepository.deleteById(id);
    }

}

package org.workshop.meetingcalendarapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOView;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;

import java.util.List;

@RequestMapping("/api/v1/project")
@RestController
public class HomeController {

    private MeetingsRepository meetingsRepository;

    @Autowired
    public HomeController(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    // Will likely change return type DTOView if/when I add user, also service
    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> meetigs(){
        List<Meeting> meetings = meetingsRepository.findAll();

        return ResponseEntity.ok(meetings);
    }

}

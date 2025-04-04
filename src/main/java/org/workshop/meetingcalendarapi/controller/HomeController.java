package org.workshop.meetingcalendarapi.controller;


import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOForm;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOView;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;
import org.workshop.meetingcalendarapi.service.MeetingService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("/api/v1/project")
@RestController
public class HomeController {

    private MeetingsRepository meetingsRepository;
    private MeetingService meetingService;

    @Autowired
    public HomeController(MeetingsRepository meetingsRepository, MeetingService meetingService) {
        this.meetingsRepository = meetingsRepository;
        this.meetingService = meetingService;
    }

    // Will likely change return type DTOView if/when I add user, also service

    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> meetigs(){
        List<Meeting> meetings = meetingsRepository.findAll();

        return ResponseEntity.ok(meetings);
    }

    @PostMapping("/meetings")
    public ResponseEntity<Void> addMeeting(@RequestBody @Valid MeetingDTOForm form){
        meetingService.createMeeting(form);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/meetings/delete{id}")
    public ResponseEntity<Void> deleteMeeting(@PathParam("id") int id){
        meetingService.deleteMeeting(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

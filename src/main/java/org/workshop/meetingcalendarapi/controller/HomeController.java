package org.workshop.meetingcalendarapi.controller;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.workshop.meetingcalendarapi.domain.dto.ContactDTOForm;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOForm;
import org.workshop.meetingcalendarapi.domain.dto.MeetingDTOView;
import org.workshop.meetingcalendarapi.domain.entity.Meeting;
import org.workshop.meetingcalendarapi.domain.model.Mail;
import org.workshop.meetingcalendarapi.repository.MeetingsRepository;
import org.workshop.meetingcalendarapi.secrets.Props;
import org.workshop.meetingcalendarapi.service.MailService;
import org.workshop.meetingcalendarapi.service.MeetingService;


import javax.annotation.processing.SupportedOptions;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "", allowPrivateNetwork = "")
//@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("/api/v1/project")
@RestController
public class HomeController {

    private MeetingsRepository meetingsRepository;
    private MeetingService meetingService;
    private MailService mailService;



    @Autowired
    public HomeController(MeetingsRepository meetingsRepository, MeetingService meetingService, MailService mailService) {
        this.meetingsRepository = meetingsRepository;
        this.meetingService = meetingService;
        this.mailService = mailService;
    }


    @GetMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(){
        if(SecurityContextHolder.getContext().getAuthentication() == null){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        else{
            return ResponseEntity.ok(true);
        }
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

    @CrossOrigin(origins = "http://localhost:5174")
    @DeleteMapping("/meetings/delete{id}")
    public ResponseEntity<Void> deleteMeeting(@PathParam("id") int id){
        meetingService.deleteMeeting(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @Modifying
    @PutMapping("meetings/update")
    public ResponseEntity<Void> updateMeeting(@RequestBody @Valid MeetingDTOForm form){
        meetingService.updateMeeting(form);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("contact")
    public ResponseEntity<Void> contact(@RequestBody @Valid ContactDTOForm form) throws MessagingException {

        // Creating mail from form
        Mail mail = mailService.createMail(form);

        // Getting secret creds
        Props cred = new Props();

        //Getting mail properties
        Properties prop = mailService.getMailProperties();

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(cred.username, cred.password);
            }
        });

        // Creating message and setting values
        Message message = mailService.createMessage(mail, session, cred);

        if (mail.getName() == null) {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        } else {
            Transport.send(message);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}

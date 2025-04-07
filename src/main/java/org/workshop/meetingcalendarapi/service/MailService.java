package org.workshop.meetingcalendarapi.service;

import org.springframework.stereotype.Service;
import org.workshop.meetingcalendarapi.domain.dto.ContactDTOForm;
import org.workshop.meetingcalendarapi.domain.model.Mail;

import java.util.Properties;

@Service
public class MailService {

    public Mail createMail(ContactDTOForm form) {
        Mail mail = Mail.builder()
                .email(form.email())
                .name(form.name())
                .message(form.message())
                .broccoli(form.broccoli())
                .build();

        return mail;
    }

    public Properties getMailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return prop;
    }

    /*Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");*/
}

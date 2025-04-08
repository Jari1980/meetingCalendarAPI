package org.workshop.meetingcalendarapi.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.*;
import org.springframework.stereotype.Service;
import org.workshop.meetingcalendarapi.domain.dto.ContactDTOForm;
import org.workshop.meetingcalendarapi.domain.model.Mail;
import org.workshop.meetingcalendarapi.secrets.Props;

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

    public Message createMessage(Mail mail, Session session, Props cred) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mail.getEmail()));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(cred.email));
        message.setSubject(mail.getName());

        String msg = mail.getMessage() + ", send from: " + mail.getEmail() + ", likes Broccoli: " + mail.isBroccoli();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        return message;
    }
}

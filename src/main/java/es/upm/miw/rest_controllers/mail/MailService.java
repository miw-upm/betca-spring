package es.upm.miw.rest_controllers.mail;

import es.upm.miw.rest_controllers.exceptions.MailException;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}@gmail.com")
    private String from;

    private String subject = "Information";

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void send(String to, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);
        try {
            mailSender.send(simpleMailMessage);
            LogManager.getLogger(this.getClass().getName()).debug(() -> String.format("E-MAIL from: %s, to: %s, subject: %s", from, to, subject));
        } catch (Exception e) {
            throw new MailException("Mail service unavailable");
        }
    }
}

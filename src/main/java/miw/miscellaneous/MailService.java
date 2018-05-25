package miw.miscellaneous;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    // Login to Gmail
    // Access the URL as https://www.google.com/settings/security/lesssecureapps
    // Select "Turn on"
    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}@gmail.com")
    private String from;

    private String to;

    private String subject = "Information";

    private String msg;

    public MailService from(String from) {
        this.from = from;
        return this;
    }

    public MailService to(String to) {
        this.to = to;
        return this;
    }

    public MailService subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailService msg(String msg) {
        this.msg = msg;
        return this;
    }

    public void send() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);
        try {
            mailSender.send(simpleMailMessage);
            LogManager.getLogger(this.getClass().getName()).info("E-MAIL from: " + from + "to: " + to + ", subject:" + subject);
        } catch (Exception e) {
            LogManager.getLogger(this.getClass().getName()).error("MailSender: " + e.getMessage());
        }
    }
}

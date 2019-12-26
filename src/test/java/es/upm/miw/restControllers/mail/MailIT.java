package es.upm.miw.restControllers.mail;

import es.upm.miw.TestConfig;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@TestConfig
public class MailIT {

    @MockBean
    private MailSender mailSender;

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMessage() {
        final ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        this.mailService.send("test@gmai.com", "test message");
        verify(mailSender).send(captor.capture());
        assertEquals(mailService.getFrom(), captor.getValue().getFrom());
        assertEquals("test@gmai.com", captor.getValue().getTo()[0]);
        assertEquals(mailService.getSubject(), captor.getValue().getSubject());
        assertEquals("test message", captor.getValue().getText());
    }
}

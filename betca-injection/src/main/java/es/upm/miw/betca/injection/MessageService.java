package es.upm.miw.betca.injection;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Service("singletonMessageService") Default
// @Scope("singleton") Default
@Service  //@Component @Controller @Service @Repository @RestController
public class MessageService {
    private final String name;
    private final int value;

    public MessageService(@Value("${miw.name}") String name, @Value("${miw.value}") int value) {
        this.name = name;
        this.value = value;
        LogManager.getLogger(this.getClass()).info("===>>> MessageService::constructor");
    }

    @PostConstruct
    public void postConstruct() {
        LogManager.getLogger(this.getClass()).info("===>>> MessageService::postConstruct()");
    }

    public String crateMessage() {
        return "MessageService::createMessage, miw.name: " + this.name + ", hashCode: " + this.hashCode();
    }

    public int runValue(int param) {
        return this.value*param;
    }

    @PreDestroy
    public void destroy() {
        LogManager.getLogger(this.getClass()).info("===>>> MessageService::preDestroy()");
    }

}

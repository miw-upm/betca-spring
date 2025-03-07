package es.upm.api.miw.betca.injection;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Service1("singletonMessageService") Default
// @Scope("singleton") Default
@Service  //@Component @Controller @Service1 @Repository @RestController
public class Service1 {
    private final String name;
    private final int value;

    public Service1(@Value("${miw.name}") String name, @Value("${miw.value}") int value) {
        this.name = name;
        this.value = value;
        LogManager.getLogger(this.getClass()).info("===>>> Service1::constructor");
    }

    @PostConstruct
    public void postConstruct() {
        LogManager.getLogger(this.getClass()).info("===>>> Service1::postConstruct()");
    }

    public String crateMessage() {
        return "Service1::createMessage, miw.name: " + this.name + ", hashCode: " + this.hashCode();
    }

    public int runValue(int param) {
        return this.value * param;
    }

    @PreDestroy
    public void destroy() {
        LogManager.getLogger(this.getClass()).info("===>>> Service1::preDestroy()");
    }

}

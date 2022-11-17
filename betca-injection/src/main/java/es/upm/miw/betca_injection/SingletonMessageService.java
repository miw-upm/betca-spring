package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

// @Service("singletonMessageService") Default
// @Scope("prototype") //Default singleton
@Service  //@Component @Controller @Service @Repository @RestController
public class SingletonMessageService {
    private final String name;
    private final int value;

    public SingletonMessageService(@Value("${miw.name}") String name, @Value("${miw.value}") int value) {  // resources/application.properties
        this.name = name;
        this.value = value;
        LogManager.getLogger(this.getClass()).info("===>>> create Bean: SingletonMessageService");
    }

    public String crateMessage() {
        return "SingletonMessageService::miw.name: " + this.name;
    }

    public int createValue(int param) {
        return this.value * param;
    }

    @PreDestroy
    public void destroy() {
        LogManager.getLogger(this.getClass()).info("===>>> SingletonMessageService::destroy()");
    }

}

package es.upm.api.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

@Component // @Service1 @Repository @Controller
public class Resource1 {

    private final Service1 singletonService1;

    private final PrototypeService prototypeService;

    public Resource1(Service1 singletonService1,
                     PrototypeService prototypeService) {
        this.singletonService1 = singletonService1;
        this.prototypeService = prototypeService;
    }

    public String createMessage() {
        return this.singletonService1.crateMessage();
    }

    public int createValue() {
        return this.singletonService1.runValue(10);
    }

    public void debug() {
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::singleton: " + this.singletonService1.crateMessage());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::prototype: " + this.prototypeService.createMessage());
    }

}

package es.upm.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

@Component // @Service @Repository @Controller
public class Resource {

    private final MessageService singletonMessageService;

    private final PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService;

    public Resource(MessageService singletonMessageService,
                    PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService) {
        this.singletonMessageService = singletonMessageService;
        this.prototypeInjectionOnlyMessageService = prototypeInjectionOnlyMessageService;
    }

    public String createMessage() {
        return this.singletonMessageService.crateMessage();
    }

    public int createValue() {
        return this.singletonMessageService.runValue(10);
    }

    public void debug() {
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::singleton: " + this.singletonMessageService.crateMessage());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::prototype: " + this.prototypeInjectionOnlyMessageService.createMessage());
    }

}

package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // @Service @Repository @Controller
public class InjectionMain {

    private final SingletonMessageService singletonMessageService;

    private final PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService;

    @Autowired
    public InjectionMain(SingletonMessageService singletonMessageService,
                         PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService) {
        this.singletonMessageService = singletonMessageService;
        this.prototypeInjectionOnlyMessageService = prototypeInjectionOnlyMessageService;
    }

    public String createMessage() {
        return this.singletonMessageService.crateMessage();
    }

    public int createValue() {
        return this.singletonMessageService.createValue(10);
    }

    public void debug() {
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::singleton: " + this.singletonMessageService.crateMessage());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::prototype: " + this.prototypeInjectionOnlyMessageService.createMessage());
    }

}

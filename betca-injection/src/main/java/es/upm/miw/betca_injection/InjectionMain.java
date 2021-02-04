package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectionMain {

    private SingletonMessageService singletonMessageService;

    private PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService;

    @Autowired
    public InjectionMain(SingletonMessageService singletonMessageService,
                         PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService) {
        this.singletonMessageService = singletonMessageService;
        this.prototypeInjectionOnlyMessageService = prototypeInjectionOnlyMessageService;
    }

    public String getMessage() {
        return this.singletonMessageService.getMessage();
    }

    public int getValue() {
        return this.singletonMessageService.getValue(10);
    }

    public void debug() {
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::singleton: " + this.singletonMessageService.getMessage());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> InjectionMain::prototype: " + this.prototypeInjectionOnlyMessageService.getMessage());
    }

}

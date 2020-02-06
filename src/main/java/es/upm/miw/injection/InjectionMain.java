package es.upm.miw.injection;

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

    public void debug() {
        LogManager.getLogger(this.getClass()).debug(() -> "===>>> " + this.singletonMessageService.getMessage());
        LogManager.getLogger(this.getClass()).debug(() -> "===>>> " + this.prototypeInjectionOnlyMessageService.getMessage());
    }

}

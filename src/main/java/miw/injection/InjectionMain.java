package miw.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InjectionMain {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Autowired
    private SingletonMessageService singletonMessageService2;

    @Autowired
    private PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService;

    @Autowired
    private PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService2;

    public String getMessage() {
        return this.singletonMessageService.getMessage();
    }

    public void debug() {
        LogManager.getLogger(this.getClass()).info(">>> " + this.singletonMessageService.getMessage());
        LogManager.getLogger(this.getClass()).info(">>> " + this.singletonMessageService2.getMessage());
        LogManager.getLogger(this.getClass()).info(">>> " + this.prototypeInjectionOnlyMessageService.getMessage());
        LogManager.getLogger(this.getClass()).info(">>> " + this.prototypeInjectionOnlyMessageService2.getMessage());
    }

}

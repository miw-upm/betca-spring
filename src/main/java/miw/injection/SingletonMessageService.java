package miw.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Service("singletonMessageService") o  @Service(value="singletonMessageService") Default
// @Scope("prototype") //Default singleton
// @Scope(org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)

@Service
public class SingletonMessageService {

    @Value("${miw.name}")
    private String name;

    @PostConstruct
    public void constructor() {
        LogManager.getLogger(this.getClass()).info(">>> create Bean: SingletonMessageService");
    }

    public String getMessage() {
        return "(" + this.hashCode() + "): SingletonMessageService!!! " + "miw.name: " + this.name;
    }

    @PreDestroy
    public void destroy() {
        LogManager.getLogger(this.getClass()).info(">>> SingletonMessageService:destroy()");
    }

}

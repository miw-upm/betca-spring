package es.upm.miw.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Service("singletonMessageService") o  @Service(value="singletonMessageService") Default
// @Scope("prototype") //Default singleton
// @Scope(org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)

@Service  //@Component @Controller @Service...
public class SingletonMessageService {

    @Value("${miw.name}")
// @Autowired public SingletonMessageService(Environment environment) {this.name = environment.getProperty("miw.name");}
    private String name = null;


    @PostConstruct
    public void constructor() {
        LogManager.getLogger(this.getClass()).debug("===>>> create Bean: SingletonMessageService");
        LogManager.getLogger(this.getClass()).debug(String.format("===>>> miw.name: %s", this.name));
    }

    public String getMessage() {
        return "SingletonMessageService!!!, miw.name: " + this.name;
    }

    @PreDestroy
    public void destroy() {
        LogManager.getLogger(this.getClass()).debug("===>>> SingletonMessageService:destroy()");
    }

}

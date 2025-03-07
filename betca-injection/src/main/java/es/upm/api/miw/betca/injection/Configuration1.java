package es.upm.api.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// @PropertySource("classpath:miw.yml")
// @PropertySource("file:///C:/JBB/miw.yml")
// @PropertySource("http://server/application.yml")
@Configuration
public class Configuration1 {

    @Bean
    @Scope("prototype")
    public PrototypeService prototypeService() {
        LogManager.getLogger(this.getClass()).info("===>>> create Bean: PrototypeService");
        return new PrototypeService();//complex creation
    }
}


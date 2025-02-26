package es.upm.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// @PropertySource("classpath:miw.yml")
// @PropertySource("file:///C:/JBB/miw.yml")
// @PropertySource("http://server/application.yml")
@Configuration
public class InjectionConfiguration {

    @Bean
    @Scope("prototype")
    public PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService() {
        LogManager.getLogger(this.getClass()).info("===>>> create Bean: PrototypeInjectionOnlyMessageService");
        return new PrototypeInjectionOnlyMessageService();//complex creation
    }
}

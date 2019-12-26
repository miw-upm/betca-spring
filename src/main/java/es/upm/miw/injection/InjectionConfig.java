package es.upm.miw.injection;

import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// default: src/main/resources/application.properties
// @PropertySource("classpath:miw.properties")
// @PropertySource("file:///C:/JBB/miw.properties")
// @PropertySource("http://server/application.properties")
// @ConfigurationProperties(prefix = "miw")

@Configuration
public class InjectionConfig {

    @Bean
    @Scope("prototype")
    public PrototypeInjectionOnlyMessageService prototypeInjectionOnlyMessageService() {
        LogManager.getLogger(this.getClass()).debug("===>>> create Bean: PrototypeInjectionOnlyMessageService");
        return new PrototypeInjectionOnlyMessageService();
    }
}

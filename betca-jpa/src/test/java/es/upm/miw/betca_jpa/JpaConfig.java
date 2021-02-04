package es.upm.miw.betca_jpa;

import es.upm.miw.betca_jpa.daos.UnRelatedDao;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = {UnRelatedDao.class})
//@ComponentScan(basePackageClasses = Service.class)
public class JpaConfig {
}

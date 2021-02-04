package es.upm.miw.betca_mongodb;


import es.upm.miw.betca_mongodb.repositories.UnRelatedRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackageClasses = {UnRelatedRepository.class})
//@ComponentScan(basePackageClasses = Service.class)
public class MongodbConfig {
}

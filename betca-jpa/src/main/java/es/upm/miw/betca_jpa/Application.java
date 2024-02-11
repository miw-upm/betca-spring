package es.upm.miw.betca_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class}) // Not: /error
public class Application {

    public static void main(String[] args) { // mvn clean spring-boot:run
        SpringApplication.run(Application.class, args);
    }

}

package es.upm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(exclude = {ErrorMvcAutoConfiguration.class}) // Not: /error
public class Application {

    public static void main(String[] args) { // mvn clean spring-boot:run
        SpringApplication.run(Application.class, args);
    }

}

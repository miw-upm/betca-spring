package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.UnRelatedDocument;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.Arrays;

@TestConfig
class UnRelatedReactiveRepositoryIT {

    @Autowired
    private UnRelatedReactiveRepository unRelatedReactiveRepository;

    @BeforeEach
    void seedDB() {
        this.unRelatedReactiveRepository.saveAll(Arrays.asList(
                new UnRelatedDocument("nick1"),
                new UnRelatedDocument("nick2"),
                new UnRelatedDocument("nick3"))).blockLast();
    }

    @Test
    void test() {
        this.unRelatedReactiveRepository.count().subscribe(
                msg -> LogManager.getLogger(this.getClass()).info("Consumer: " + msg), //onNext
                throwable -> LogManager.getLogger(this.getClass()).info("Error: " + throwable.getMessage()), //onError
                () -> LogManager.getLogger(this.getClass()).info("Completed") //onComplete
        );
    }

    @Test
    void test2() {
        StepVerifier
                .create(unRelatedReactiveRepository.count())
                .expectNext(Long.valueOf(3))
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByNickIgnoreCase() {
        StepVerifier
                .create(unRelatedReactiveRepository.findByNickIgnoreCase("NICK2"))
                .expectNextMatches(un -> un.getNick().equals("nick2"))
                .expectComplete()
                .verify();
    }

    @Test
    void testFindFirst3ByNickStartingWith() {
        StepVerifier
                .create(unRelatedReactiveRepository.findFirst3ByNickStartingWith("ni"))
                .expectNextCount(2)
                .expectNextMatches(un -> un.getNick().equals("nick3"))
                .expectComplete()
                .verify();
    }

    @AfterEach
    void deleteDB() {
        this.unRelatedReactiveRepository.deleteAll().block();
    }

}

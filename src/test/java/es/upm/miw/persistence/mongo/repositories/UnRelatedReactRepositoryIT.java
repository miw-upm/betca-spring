package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.UnRelatedDocument;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

@TestConfig
class UnRelatedReactRepositoryIT {

    @Autowired
    private UnRelatedReactRepository unRelatedReactRepository;

    @BeforeEach
    void seedDB() {
        this.unRelatedReactRepository.saveAll(Arrays.asList(
                new UnRelatedDocument("nick1"),
                new UnRelatedDocument("nick2"),
                new UnRelatedDocument("nick3"))).blockLast();
    }

    @Test
    void testCountLogs() {
        this.unRelatedReactRepository.count().subscribe(
                msg -> LogManager.getLogger(this.getClass()).info("Consumer: " + msg), //onNext
                throwable -> LogManager.getLogger(this.getClass()).info("Error: " + throwable.getMessage()), //onError
                () -> LogManager.getLogger(this.getClass()).info("Completed") //onComplete
        );
    }

    @Test
    void testCount() {
        StepVerifier
                .create(unRelatedReactRepository.count())
                .expectNext(Long.valueOf(3))
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByNickIgnoreCase() {
        StepVerifier
                .create(unRelatedReactRepository.findByNickIgnoreCase("NICK2"))
                .expectNextMatches(un -> un.getNick().equals("nick2"))
                .expectComplete()
                .verify();
    }

    @Test
    void testFindFirst3ByNickStartingWith() {
        StepVerifier
                .create(unRelatedReactRepository.findFirst3ByNickStartingWith("ni"))
                .expectNextCount(2)
                .expectNextMatches(un -> un.getNick().equals("nick3"))
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByNickContains() {
        Flux<String> ids = unRelatedReactRepository.findByNickContains("ic").map(UnRelatedDocument::getId);
        Flux<UnRelatedDocument> publisher2 = unRelatedReactRepository.findAllById(ids);

        publisher2.subscribe(msg -> LogManager.getLogger(this.getClass()).info("Consumer: " + msg));

        publisher2.blockLast();
    }

    @AfterEach
    void deleteDB() {
        this.unRelatedReactRepository.deleteAll().block();
    }

}

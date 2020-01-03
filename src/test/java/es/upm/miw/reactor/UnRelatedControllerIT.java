package es.upm.miw.reactor;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.UnRelatedDocument;
import es.upm.miw.persistence.mongo.repositories.UnRelatedReactRepository;
import es.upm.miw.persistence.mongo.repositories.UnRelatedRepository;
import es.upm.miw.restControllers.exceptions.ConflictException;
import es.upm.miw.restControllers.exceptions.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@TestConfig
class UnRelatedControllerIT {

    @Autowired
    private UnRelatedController unRelatedController;

    @Autowired
    private UnRelatedRepository unRelatedRepository;

    @Autowired
    private UnRelatedReactRepository unRelatedReactRepository;

    @BeforeEach
    void seedDatabase() {
        Stream<UnRelatedDocument> documents = Stream.iterate(0, i -> i + 1).limit(10L).map(i ->
                new UnRelatedDocument(String.valueOf(i))
        );
        this.unRelatedRepository.saveAll(documents.collect(Collectors.toList()));
    }

    @Test
    void testFindBornDateByNickAssured() {
        LocalDateTime bornDate = this.unRelatedController.findBornDateByNickAssured("7");
        assertNotNull(bornDate);
    }

    @Test
    void testFindBornDateByNickAssuredException() {
        assertThrows(NotFoundException.class, () -> this.unRelatedController.findBornDateByNickAssured("NO"));
    }

    @Test
    void testFindBornDateByNickAssuredReact() {
        StepVerifier
                .create(this.unRelatedController.findBornDateByNickAssuredReact("7"))
                .expectNextMatches(date -> LocalDateTime.now().getDayOfMonth() == date.getDayOfMonth())
                .expectComplete()
                .verify();
    }

    @Test
    void testFindBornDateByNickAssuredReactException() {
        StepVerifier
                .create(this.unRelatedController.findBornDateByNickAssuredReact("NO"))
                .expectError()
                .verify();
    }

    @Test
    void testFindBornDateByNickAssuredReactERRORChainBreak() {
        LocalDateTime bornDate = this.unRelatedController.findBornDateByNickAssuredReactERRORChainBreak("7");
        assertNotNull(bornDate);
    }

    @Test
    void testNotExistsByNickAssured() {
        this.unRelatedController.notExistsByNickAssured("NO");
    }

    @Test
    void testNotExistsByNickAssuredException() {
        assertThrows(ConflictException.class, () -> this.unRelatedController.notExistsByNickAssured("7"));
    }

    @Test
    void testNotExistsByNickAssuredReact() {
        StepVerifier
                .create(this.unRelatedController.notExistsByNickAssuredReact("NO"))
                .expectComplete()
                .verify();
    }

    @Test
    void testNotExistsByNickAssuredReactException() {
        StepVerifier
                .create(this.unRelatedController.notExistsByNickAssuredReact("7"))
                .expectError()
                .verify();
    }


    @Test
    void testFindByNickAssuredAndUpdateLarge() {
        this.unRelatedController.findByNickAssuredAndUpdateLarge("7", "new");
        assertEquals("new", unRelatedRepository.findByNick("7").get().getLarge());
    }

    @Test
    void testFindByIdAssuredAndUpdateNickReact() {
        StepVerifier
                .create(this.unRelatedController.findByNickAssuredAndUpdateLargeReact("7", "new"))
                .expectComplete()
                .verify();
        StepVerifier
                .create(this.unRelatedReactRepository.findByNick("7"))
                .expectNextMatches(document -> "new".equals(document.getLarge()))
                .expectComplete()
                .verify();
    }

    @Test
    void testParallel() {
        Mono<LocalDateTime> mono1 = this.unRelatedController.findBornDateByNickAssuredReact("3");
        Mono<Void> mono2 = this.unRelatedController.findByNickAssuredAndUpdateLargeReact("6", "new");
        StepVerifier
                .create(Mono.when(mono1, mono2))
                .expectComplete()
                .verify();
    }

    @Test
    void testUpdateDocumentsReact() {
        System.out.println(">>>>> Start asynchronous");
        System.out.println("    Count: " + this.unRelatedRepository.count());
        long time = new Date().getTime();
        StepVerifier
                .create(unRelatedController.updateDocumentsReact())
                .expectComplete()
                .verify();
        System.out.println("    update: " + (new Date().getTime() - time) + "msg");
    }

    @AfterEach
    void deleteDatabase() {
        this.unRelatedRepository.deleteAll();
    }

}

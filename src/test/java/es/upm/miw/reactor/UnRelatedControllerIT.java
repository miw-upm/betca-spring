package es.upm.miw.reactor;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.Gender;
import es.upm.miw.persistence.mongo.documents.UnRelatedDocument;
import es.upm.miw.persistence.mongo.repositories.UnRelatedReactRepository;
import es.upm.miw.persistence.mongo.repositories.UnRelatedRepository;
import es.upm.miw.rest_controllers.exceptions.ConflictException;
import es.upm.miw.rest_controllers.exceptions.NotFoundException;
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
        Stream<UnRelatedDocument> documents = Stream.iterate(0, i -> i + 1).limit(10L)
                .map(i -> new UnRelatedDocument(String.valueOf(i)))
                .map(document -> {
                    document.setLonger(1L);
                    if ("3".equals(document.getNick())) {
                        document.setGender(Gender.MALE);
                        document.setBooleano(false);
                        document.setLonger(3L);
                    }
                    return document;
                });
        this.unRelatedRepository.saveAll(documents.collect(Collectors.toList()));
    }

    @Test
    void testFindBornDateByNickAssuredSynchronous() {
        LocalDateTime bornDate = this.unRelatedController.findBornDateByNickAssuredSynchronous("7");
        assertNotNull(bornDate);
    }

    @Test
    void testFindBornDateByNickAssuredSynchronousException() {
        assertThrows(NotFoundException.class, () -> this.unRelatedController.findBornDateByNickAssuredSynchronous("NO"));
    }

    @Test
    void testFindBornDateByNickAssuredAsynchronous() {
        StepVerifier
                .create(this.unRelatedController.findBornDateByNickAssuredAsynchronous("7"))
                .expectNextMatches(date -> LocalDateTime.now().getDayOfMonth() == date.getDayOfMonth())
                .expectComplete()
                .verify();

        StepVerifier
                .create(this.unRelatedReactRepository.findAll())
                .expectNextMatches(document-> document.getLonger().equals(1L))
                .expectNextCount(2)
                .expectNextMatches(document-> document.getLonger().equals(3L))
                .expectNextMatches(document-> document.getLonger().equals(1L))
                .thenCancel()
                .verify();

    }

    @Test
    void testFindBornDateByNickAssuredAsynchronousException() {
        StepVerifier
                .create(this.unRelatedController.findBornDateByNickAssuredAsynchronous("NO"))
                .expectError()
                .verify();
    }

    @Test
    void testFindBornDateByNickAssuredSynchronousERRORChainBreak() {
        LocalDateTime bornDate = this.unRelatedController.findBornDateByNickAssuredSynchronousERRORChainBreak("7");
        assertNotNull(bornDate);
    }

    @Test
    void testNotExistsByNickAssuredSynchronous() {
        this.unRelatedController.notExistsByNickAssuredSynchronous("NO");
    }

    @Test
    void testNotExistsByNickAssuredSynchronousException() {
        assertThrows(ConflictException.class, () -> this.unRelatedController.notExistsByNickAssuredSynchronous("7"));
    }

    @Test
    void testNotExistsByNickAssuredAsynchronous() {
        StepVerifier
                .create(this.unRelatedController.notExistsByNickAssuredAsynchronous("NO"))
                .expectComplete()
                .verify();
    }

    @Test
    void testNotExistsByNickAssuredAsynchronousException() {
        StepVerifier
                .create(this.unRelatedController.notExistsByNickAssuredAsynchronous("7"))
                .expectError()
                .verify();
    }

    @Test
    void testFindByNickIsFemaleAssuredSynchronous() {
        UnRelatedDocument document = this.unRelatedController.findByNickIsFemaleAssuredSynchronous("2");
        assertNotNull(document);
    }

    @Test
    void testFindByNickIsFemaleAssuredSynchronousException() {
        assertThrows(ConflictException.class, () -> this.unRelatedController.notExistsByNickAssuredSynchronous("3"));
    }

    @Test
    void testFindByNickIsFemaleAssuredAsynchronous(){
        StepVerifier
                .create(this.unRelatedController.findByNickIsFemaleAssuredAsynchronous("2"))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByNickIsFemaleAssuredAsynchronousErrorMale(){
        StepVerifier
                .create(this.unRelatedController.findByNickIsFemaleAssuredAsynchronous("3"))
                .expectError()
                .verify();
    }

    @Test
    void testFindByNickIsFemaleAssuredAsynchronousErrorEmpty(){
        StepVerifier
                .create(this.unRelatedController.findByNickIsFemaleAssuredAsynchronous("kk"))
                .expectError()
                .verify();
    }

    @Test
    void testFindByNickAssuredAndUpdateLargeSynchronous() {
        this.unRelatedController.findByNickAssuredAndUpdateLargeSynchronous("7", "new");
        assertEquals("new", unRelatedRepository.findByNick("7").get().getLarge());
    }

    @Test
    void testFindByIdAssuredAndUpdateNickAsynchronous() {
        StepVerifier
                .create(this.unRelatedController.findByNickAssuredAndUpdateLargeAsynchronous("7", "new"))
                .expectComplete()
                .verify();
        StepVerifier
                .create(this.unRelatedReactRepository.findByNick("7"))
                .expectNextMatches(document -> "new".equals(document.getLarge()))
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByGenderAndIfMaleThenLongerX2ElseLongerX3AndUpdateAll() {
        StepVerifier
                .create(this.unRelatedController.findByGenderAndIfMaleThenLongerX2ElseLongerX3AndUpdateAll())
                .expectComplete()
                .verify();
        StepVerifier
                .create(this.unRelatedReactRepository.findAll())
                .expectNextMatches(document -> document.getLonger().equals(3L))
                .expectNextCount(2)
                .expectNextMatches(document -> document.getLonger().equals(6L))
                .thenCancel()
                .verify();
    }

    @Test
    void testParallel() {
        Mono<LocalDateTime> mono1 = this.unRelatedController.findBornDateByNickAssuredAsynchronous("3");
        Mono<Void> mono2 = this.unRelatedController.findByNickAssuredAndUpdateLargeAsynchronous("6", "new");
        StepVerifier
                .create(Mono.when(mono1, mono2))
                .expectComplete()
                .verify();
    }

    @Test
    void testUpdateDocumentsAsynchronous() {
        System.out.println(">>>>> Start asynchronous");
        System.out.println("    Count: " + this.unRelatedRepository.count());
        long time = new Date().getTime();
        StepVerifier
                .create(unRelatedController.updateDocumentsAsynchronous())
                .expectComplete()
                .verify();
        System.out.println("    update: " + (new Date().getTime() - time) + "msg");
    }

    @AfterEach
    void deleteDatabase() {
        this.unRelatedRepository.deleteAll();
    }

}

package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.TestConfig;
import es.upm.miw.betca_mongodb.documents.Gender;
import es.upm.miw.betca_mongodb.documents.UnRelatedDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class UnRelatedReactiveRepositoryIT {

    @Autowired
    private UnRelatedReactiveRepository unRelatedReactiveRepository;

    @Autowired
    private UnRelatedRepository unRelatedRepository;

    @BeforeEach
    void populate() {
        Stream< UnRelatedDocument > documents = Stream.iterate(0, i -> i + 1).limit(5L).map(i ->
                UnRelatedDocument.builder().nick("nick" + i).gender(Gender.FEMALE).bornDate(LocalDateTime.now())
                        .strings(new String[]{"uno", "dos"}).noPersistent("noPersistent")
                        .logic(true).integer(666).decimal(666.666e30)
                        .longer(LocalDateTime.now().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()).build()
        );
        this.unRelatedRepository.saveAll(documents.collect(Collectors.toList()));
    }

    @Test
    void testFindByNick() {
        StepVerifier
                .create(this.unRelatedReactiveRepository.findByNick("nick3"))
                .assertNext(unRelatedDocument -> assertEquals("nick3", unRelatedDocument.getNick()))
                .thenCancel()
                .verify();
    }

    @AfterEach
    void deleteDB() {
        this.unRelatedRepository.deleteAll();
    }
}

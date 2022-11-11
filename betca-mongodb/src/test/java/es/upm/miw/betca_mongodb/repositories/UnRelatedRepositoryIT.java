package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.TestConfig;
import es.upm.miw.betca_mongodb.documents.Gender;
import es.upm.miw.betca_mongodb.documents.UnRelatedDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UnRelatedRepositoryIT {

    @Autowired
    private UnRelatedRepository unRelatedRepository;

    @BeforeEach
    void populate() {
        Stream<UnRelatedDocument> documents = Stream.iterate(0, i -> i + 1).limit(5L).map(i ->
                UnRelatedDocument.builder().nick("nick" + i).gender(Gender.FEMALE).bornDate(LocalDateTime.now())
                        .strings(new String[]{"uno", "dos"}).large("Large...").noPersistent("noPersistent")
                        .logic(true).integer(666).decimal(666.666e30)
                        .longer(LocalDateTime.now().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()).build()
        );
        this.unRelatedRepository.saveAll(documents.collect(Collectors.toList()));
    }

    @Test
    void test() {
        assertTrue(unRelatedRepository.count() > 0);
    }

    @Test
    void uniqueNickTest() {
        UnRelatedDocument unRelatedDocument1 = UnRelatedDocument.builder().nick("unique").build();
        UnRelatedDocument unRelatedDocument2 = UnRelatedDocument.builder().nick("unique").build();
        this.unRelatedRepository.save(unRelatedDocument1);
        assertThrows(DuplicateKeyException.class, () -> this.unRelatedRepository.save(unRelatedDocument2));
        this.unRelatedRepository.delete(unRelatedDocument1);
    }

    @Test
    void testFindByNickIgnoreCase() {
        assertNotNull(unRelatedRepository.findByNickIgnoreCase("NICK1"));
    }

    @Test
    void testFindFirst3ByNickStartingWith() {
        assertEquals(0, unRelatedRepository.findFirst3ByNickStartingWith("no").size());
        assertEquals(3, unRelatedRepository.findFirst3ByNickStartingWith("ni").size());
    }

    @Test
    void testFindByNickOrLargeOrderByIdDesc() {
        assertTrue(unRelatedRepository.findByNickOrLargeOrderByLongerDesc(
                "NoNick", "Large...").get(0).getLonger() >= unRelatedRepository
                .findByNickOrLargeOrderByLongerDesc("NoNick", "Large...").get(1).getLonger());
        assertEquals(5, unRelatedRepository.findByNickOrLargeOrderByLongerDesc("NoNick", "Large...").size());
    }

    @Test
    void testFindByIntegerGreaterThan() {
        assertEquals(2, unRelatedRepository.findByIntegerGreaterThan(0, PageRequest.of(0, 2)).size());
        assertEquals(2, unRelatedRepository.findByIntegerGreaterThan(0, PageRequest.of(1, 3)).size());
    }

    @Test
    void testDeleteByNick() {
        assertNotNull(unRelatedRepository.findByNick("nick0"));
        assertTrue(0 < unRelatedRepository.deleteByNick("nick0"));
        assertFalse(unRelatedRepository.findByNick("nick0").isPresent());
    }

    @Test
    void testFindByNick() {
        assertNotNull(unRelatedRepository.findByNick("nick0"));
    }

    @Test
    void testFindIdAndBornDateAndLargeByNick() {
        assertNotNull(unRelatedRepository.findIdAndBornDateAndLargeByNick("nick0").getId());
        assertNotNull(unRelatedRepository.findIdAndBornDateAndLargeByNick("nick0").getBornDate());
        assertNotNull(unRelatedRepository.findIdAndBornDateAndLargeByNick("nick0").getLarge());
    }

    @Test
    void testFindBornDateByNick() {
        assertNotNull(unRelatedRepository.findBornDateByNick("nick0").getBornDate());
    }

    @Test
    void testFindByNickAndLarge() {
        assertNotNull(unRelatedRepository.findByNickAndLarge("nick0", "Large..."));
        assertNull(unRelatedRepository.findByNickAndLarge("nick0", "NOT"));
    }

    @Test
    void testFindByNickIn() {
        assertEquals(2, unRelatedRepository.findByNickIn(Arrays.asList("nick1", "nick2")).size());
    }

    @Test
    void testFindByNickLikeLargeLikeNullSafe() {
        assertEquals(0, unRelatedRepository.findByNickLikeAndLargeLikeNullSafe("k1", "no").size());
        assertEquals(1, unRelatedRepository.findByNickLikeAndLargeLikeNullSafe("k1", null).size());
        assertEquals(5, unRelatedRepository.findByNickLikeAndLargeLikeNullSafe(null, null).size());
    }

    @AfterEach
    void deleteDB() {
        this.unRelatedRepository.deleteAll();
    }

}

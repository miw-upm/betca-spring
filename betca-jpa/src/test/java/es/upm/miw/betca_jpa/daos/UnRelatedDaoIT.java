package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.TestConfig;
import es.upm.miw.betca_jpa.entities.Gender;
import es.upm.miw.betca_jpa.entities.UnRelatedEntity;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UnRelatedDaoIT {

    @Autowired
    private UnRelatedDao unRelatedDao;

    @BeforeEach
    void seedDb() {
        for (int i = 0; i < 4; i++) {
            unRelatedDao.save(UnRelatedEntity.builder().nick("nick" + i).gender(Gender.MALE)
                    .tag("0").tag("1").noPersistent("no persistence").build());
        }
    }

    @Test
    void testCount() {
        assertEquals(4, unRelatedDao.count());
        LogManager.getLogger(this.getClass()).debug("===>>> message: " + unRelatedDao.findAll());
    }

    @Test
    void testFindOne() {
        UnRelatedEntity unRelatedEntity = UnRelatedEntity.builder().nick("unNick")
                .tags(Collections.emptyList()).build();
        unRelatedDao.save(unRelatedEntity);
        assertTrue(unRelatedDao.findById(unRelatedEntity.getId()).isPresent());
        Assertions.assertEquals("unNick", unRelatedDao.findById(unRelatedEntity.getId()).get().getNick());
    }

    @Test
    void testFindByNickIgnoreCase() {
        assertNotNull(unRelatedDao.findByNickIgnoreCase("NICK1"));
    }


    @Test
    void testFindFirst3ByNickStartingWith() { // By Methods Names
        assertEquals(0, unRelatedDao.findFirst3ByNickStartingWith("kk").size());
        assertEquals(3, unRelatedDao.findFirst3ByNickStartingWith("ni").size());
    }


    @Test
    void testFindByIdGreaterThan() {  // Pagination
        assertEquals(2, unRelatedDao.findByIdGreaterThan(0, PageRequest.of(0, 2)).size());
        assertEquals(1, unRelatedDao.findByIdGreaterThan(0, PageRequest.of(1, 3)).size());
    }

    @Test
    void testFindByNickIn() {
        assertEquals(2, unRelatedDao.findByNickIn(Arrays.asList("nick1", "nick2")).size());
    }


    @Test
    void testFindNickByNickLike() { // JPQL
        assertEquals(0, unRelatedDao.findNickByNickLike("i%").size());
        assertEquals(4, unRelatedDao.findNickByNickLike("n%").size());
    }

    @Test
    void testFindIdByIdBetween() {
        Optional<UnRelatedEntity> entity = unRelatedDao.findByNickIgnoreCase("nick1");
        assertTrue(entity.isPresent());
        assertEquals(2, unRelatedDao.findIdByIdBetween(entity.get().getId() - 1, entity.get().getId() + 2).size());
    }


    @Test
    void testFindByNick() {  // SQL
        Assertions.assertEquals("nick1", unRelatedDao.findByNick("nick1").getNick());
    }

    @Test
    void testDeleteByNick() {  // Delete
        assertNotNull(unRelatedDao.findByNick("nick0"));
        unRelatedDao.deleteByNick("nick0");
        assertNull(unRelatedDao.findByNick("nick0"));
    }

    @Test
    void testDeleteByIdGreaterThan() {
        assertEquals(4, unRelatedDao.deleteByIdGreaterThan(0));
        assertEquals(0, unRelatedDao.deleteByIdGreaterThan(Integer.MAX_VALUE));
    }

    @Test
    void testDeleteByNickQuery() {
        UnRelatedEntity unRelatedEntity = UnRelatedEntity.builder().nick("unNick").build();
        unRelatedDao.save(unRelatedEntity);
        assertNotNull(unRelatedDao.findByNick("unNick"));
        unRelatedDao.deleteByNickQuery("unNick");
        assertNull(unRelatedDao.findByNick("unNick"));
    }

    @AfterEach
    void deleteDb() {
        unRelatedDao.deleteAll();
    }

}

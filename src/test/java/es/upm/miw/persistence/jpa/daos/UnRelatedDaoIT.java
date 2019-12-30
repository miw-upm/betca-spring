package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.jpa.entities.Gender;
import es.upm.miw.persistence.jpa.entities.UnRelatedEntity;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UnRelatedDaoIT {

    @Autowired
    private UnRelatedDao unRelatedDao;

    @BeforeEach
    void seedDb() {
        String[] list = {"0", "1"};
        for (int i = 0; i < 4; i++) {
            unRelatedDao.save(new UnRelatedEntity("nick" + i, Gender.MALE, LocalDateTime.of(1964, 12, 31, 0, 0), "Large...", list,
                    Arrays.asList(list), "no persistence"));
        }
    }

    // By Inheritance: JpaRepository
    @Test
    void testCount() {
        assertEquals(4, unRelatedDao.count());
        LogManager.getLogger(this.getClass()).debug("===>>> message: " + unRelatedDao.findAll());
    }

    @Test
    void testFindOne() {
        UnRelatedEntity unRelatedEntity = new UnRelatedEntity("unNick", Gender.MALE, LocalDateTime.of(1964, 12, 31, 0, 0), "...",
                new String[]{}, Arrays.asList(new String[]{}), "no persistence");
        unRelatedDao.save(unRelatedEntity);
        Assertions.assertEquals("unNick", unRelatedDao.findById(unRelatedEntity.getId()).get().getNick());
    }

    @Test
    void testFindByNickIgnoreCase() {
        assertNotNull(unRelatedDao.findByNickIgnoreCase("NICK1"));
    }

    // By Methods Names
    @Test
    void testFindFirst3ByNickStartingWith() {
        assertEquals(0, unRelatedDao.findFirst3ByNickStartingWith("no").size());
        assertEquals(3, unRelatedDao.findFirst3ByNickStartingWith("ni").size());
    }

    @Test
    void testFindByNickOrLargeOrderByIdDesc() {
        assertTrue(unRelatedDao.findByNickOrLargeOrderByIdDesc("NoNick", "Large...").get(0).getId() > unRelatedDao
                .findByNickOrLargeOrderByIdDesc("NoNick", "Large...").get(1).getId());
        assertEquals(4, unRelatedDao.findByNickOrLargeOrderByIdDesc("NoNick", "Large...").size());
    }

    // Pagination
    @Test
    void testFindByIdGreaterThan() {
        assertEquals(2, unRelatedDao.findByIdGreaterThan(0, PageRequest.of(0, 2)).size());
        assertEquals(1, unRelatedDao.findByIdGreaterThan(0, PageRequest.of(1, 3)).size());
    }

    @Test
    void testFindByNickIn() {
        assertEquals(2, unRelatedDao.findByNickIn(Arrays.asList("nick1", "nick2")).size());
    }

    // JPQL
    @Test
    void testFindNickByNickLike() {
        assertEquals(0, unRelatedDao.findNickByNickLike("i%").size());
        assertEquals(4, unRelatedDao.findNickByNickLike("n%").size());
    }

    @Test
    void testFindIdByIdBetween() {
        int id = unRelatedDao.findByNickIgnoreCase("nick1").getId();
        assertEquals(2, unRelatedDao.findIdByIdBetween(id - 1, id + 2).size());
    }

    // SQL
    @Test
    void testFindByNick() {
        Assertions.assertEquals("nick1", unRelatedDao.findByNick("nick1").getNick());
    }

    // Delete
    @Test
    void testDeleteByNick() {
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
        UnRelatedEntity unRelatedEntity = new UnRelatedEntity("unNick", Gender.MALE, LocalDateTime.of(1901, 12, 31, 0, 0), "...",
                new String[]{}, Arrays.asList(new String[]{}), "no persistence");
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

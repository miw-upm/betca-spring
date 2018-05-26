package miw.persistence.jpa.daos;

import miw.TestConfig;
import miw.persistence.jpa.entities.Gender;
import miw.persistence.jpa.entities.UnRelatedEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class UnRelatedDaoIT {

    @Autowired
    private UnRelatedDao unRelatedDao;

    @BeforeEach
    public void seedDb() {
        String[] list = {"0", "1"};
        for (int i = 0; i < 4; i++) {
            unRelatedDao.save(new UnRelatedEntity("nick" + i, Gender.MALE, new GregorianCalendar(1964, 11, 31), "Large...", list,
                    Arrays.asList(list), "no persistence"));
        }
    }

    // By Inheritance: JpaRepository
    @Test
    public void testCount() {
        assertEquals(4, unRelatedDao.count());
    }

    @Test
    public void testFindOne() {
        UnRelatedEntity unRelatedEntity = new UnRelatedEntity("unNick", Gender.MALE, new GregorianCalendar(1964, 11, 31), "...",
                new String[]{}, Arrays.asList(new String[]{}), "no persistence");
        unRelatedDao.save(unRelatedEntity);
        assertEquals("unNick", unRelatedDao.findById(unRelatedEntity.getId()).get().getNick());
    }

    @Test
    public void testFindByNickIgnoreCase() {
        assertNotNull(unRelatedDao.findByNickIgnoreCase("NICK1"));
    }

    // By Methods Names
    @Test
    public void testFindFirst3ByNickStartingWith() {
        assertEquals(0, unRelatedDao.findFirst3ByNickStartingWith("no").size());
        assertEquals(3, unRelatedDao.findFirst3ByNickStartingWith("ni").size());
    }

    @Test
    public void testFindByNickOrLargeOrderByIdDesc() {
        assertTrue(unRelatedDao.findByNickOrLargeOrderByIdDesc("NoNick", "Large...").get(0).getId() > unRelatedDao
                .findByNickOrLargeOrderByIdDesc("NoNick", "Large...").get(1).getId());
        assertEquals(4, unRelatedDao.findByNickOrLargeOrderByIdDesc("NoNick", "Large...").size());
    }

    // Pagination
    @Test
    public void testFindByIdGreaterThan() {
        assertEquals(2, unRelatedDao.findByIdGreaterThan(0, PageRequest.of(0, 2)).size());
        assertEquals(1, unRelatedDao.findByIdGreaterThan(0, PageRequest.of(1, 3)).size());
    }

    @Test
    public void testFindByNickIn() {
        assertEquals(2, unRelatedDao.findByNickIn(Arrays.asList("nick1", "nick2")).size());
    }

    // JPQL
    @Test
    public void testFindNickByNickLike() {
        assertEquals(0, unRelatedDao.findNickByNickLike("i%").size());
        assertEquals(4, unRelatedDao.findNickByNickLike("n%").size());
    }

    @Test
    public void testFindIdByIdBetween() {
        int id = unRelatedDao.findByNickIgnoreCase("nick1").getId();
        assertEquals(2, unRelatedDao.findIdByIdBetween(id - 1, id + 2).size());
    }

    // SQL
    @Test
    public void testFindByNick() {
        assertEquals("nick1", unRelatedDao.findByNick("nick1").getNick());
    }

    // Delete
    @Test
    public void testDeleteByNick() {
        assertNotNull(unRelatedDao.findByNick("nick0"));
        unRelatedDao.deleteByNick("nick0");
        assertNull(unRelatedDao.findByNick("nick0"));
    }

    @Test
    public void testDeleteByIdGreaterThan() {
        assertEquals(4, unRelatedDao.deleteByIdGreaterThan(0));
        assertEquals(0, unRelatedDao.deleteByIdGreaterThan(Integer.MAX_VALUE));
    }

    @Test
    public void testDeleteByNickQuery() {
        UnRelatedEntity unRelatedEntity = new UnRelatedEntity("unNick", Gender.MALE, new GregorianCalendar(1901, 11, 31), "...",
                new String[]{}, Arrays.asList(new String[]{}), "no persistence");
        unRelatedDao.save(unRelatedEntity);
        assertNotNull(unRelatedDao.findByNick("unNick"));
        unRelatedDao.deleteByNickQuery("unNick");
        assertNull(unRelatedDao.findByNick("unNick"));
    }

    @AfterEach
    public void deleteDb() {
        unRelatedDao.deleteAll();
    }

}

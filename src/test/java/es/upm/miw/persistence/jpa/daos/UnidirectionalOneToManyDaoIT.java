package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.AnyEntity;
import es.upm.miw.persistence.jpa.entities.UnidirectionalOneToManyEntity;
import es.upm.miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class UnidirectionalOneToManyDaoIT {

    @Autowired
    private UnidirectionalOneToManyDao unidirectionalOneToManyDao;

    private AnyEntity[] array = {new AnyEntity("one"), new AnyEntity("two"), new AnyEntity("three")};

    private UnidirectionalOneToManyEntity entity;

    @BeforeEach
    public void before() {
        this.entity = new UnidirectionalOneToManyEntity("Nick2", Arrays.asList(array));
    }

    @Test
    public void testFindOne() {
        unidirectionalOneToManyDao.save(this.entity);
        UnidirectionalOneToManyEntity unidirectionalOneToManyEntity =
                unidirectionalOneToManyDao.findById(this.entity.getId()).get();
        assertEquals("Nick2", unidirectionalOneToManyEntity.getNick());
        assertEquals(3, unidirectionalOneToManyEntity.getAnyEntityList().size());
    }

    @AfterEach
    public void delete() {
        unidirectionalOneToManyDao.deleteById(entity.getId());
    }

}

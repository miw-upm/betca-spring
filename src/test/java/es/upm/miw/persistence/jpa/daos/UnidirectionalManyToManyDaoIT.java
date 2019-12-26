package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.AnyEntity;
import es.upm.miw.persistence.jpa.entities.UnidirectionalManyToManyEntity;
import es.upm.miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class UnidirectionalManyToManyDaoIT {

    @Autowired
    private UnidirectionalManyToManyDao unidirectionalManyToManyDao;

    @Autowired
    private AnyDao anyDao;

    private List<AnyEntity> array;

    private List<AnyEntity> array2;

    private UnidirectionalManyToManyEntity entity;

    private UnidirectionalManyToManyEntity entity2;

    @BeforeEach
    public void before() {
        this.array = Arrays.asList(new AnyEntity("one"), new AnyEntity("two"), new AnyEntity("three"));
        this.array2 = Arrays.asList(new AnyEntity("four"), new AnyEntity("five"));
        this.entity = new UnidirectionalManyToManyEntity("Mi Nick", array);
        this.entity2 = new UnidirectionalManyToManyEntity("Mi Nick2", array2);
        this.anyDao.saveAll(array);
        this.anyDao.saveAll(array2);
        this.unidirectionalManyToManyDao.save(entity);
        this.unidirectionalManyToManyDao.save(entity);
    }

    @Test
    public void testFindOne() {
        assertTrue(unidirectionalManyToManyDao.findById(entity.getId()).isPresent());
        Assertions.assertEquals(3, unidirectionalManyToManyDao.findById(entity.getId()).get().getAnyEntityList().size());
    }

    @AfterEach
    public void delete() {
        this.unidirectionalManyToManyDao.delete(entity);
        this.unidirectionalManyToManyDao.delete(entity2);
        this.anyDao.deleteAll(array);
        this.anyDao.deleteAll(array2);
    }

}

package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.jpa.entities.AggregationEntity;
import es.upm.miw.persistence.jpa.entities.AnotherEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class AggregationDaoIT {

    @Autowired
    private AggregationDao aggregationDao;

    @Autowired
    private AnotherDao anotherDao;

    private AggregationEntity aggregationEntity;

    @BeforeEach
    void before() {
        AnotherEntity anotherEntity = new AnotherEntity("Zero");
        List<AnotherEntity> list = Arrays.asList(
                new AnotherEntity("one"),
                new AnotherEntity("two"),
                new AnotherEntity("three"));
        this.aggregationEntity = new AggregationEntity("Mi Nick");
        this.aggregationEntity.setAnotherEntity(anotherEntity);
        this.aggregationEntity.setAnotherEntityList(list);
        this.anotherDao.save(anotherEntity);
        this.anotherDao.saveAll(list);
        this.aggregationDao.save(this.aggregationEntity);
    }

    @Test
    void testFindOne() {
        assertTrue(aggregationDao.findById(aggregationEntity.getId()).isPresent());
        Assertions.assertEquals(3,
                aggregationDao.findById(aggregationEntity.getId()).get().getAnotherEntityList().size());
    }

    @AfterEach
    void delete() {
        this.aggregationDao.deleteAll();
        this.anotherDao.deleteAll();
    }

}

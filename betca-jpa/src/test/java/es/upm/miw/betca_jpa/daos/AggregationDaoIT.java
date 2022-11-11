package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.TestConfig;
import es.upm.miw.betca_jpa.entities.AggregationEntity;
import es.upm.miw.betca_jpa.entities.AnotherEntity;
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
        AnotherEntity anotherEntity = new AnotherEntity("zero");
        List<AnotherEntity> list = Arrays.asList(
                new AnotherEntity("one"),
                new AnotherEntity("two"));
        this.anotherDao.save(anotherEntity);
        this.aggregationEntity = AggregationEntity.builder().nick("Mi Nick").anotherEntity(anotherEntity)
                .anotherEntityList(list).build();
        this.anotherDao.saveAll(list);
        this.aggregationDao.save(this.aggregationEntity);
    }

    @Test
    void testFindOne() {
        assertTrue(aggregationDao.findById(aggregationEntity.getId()).isPresent());
        Assertions.assertEquals(2,
                aggregationDao.findById(aggregationEntity.getId()).get().getAnotherEntityList().size());
    }

    @AfterEach
    void delete() {
        this.aggregationDao.deleteAll();
        this.anotherDao.deleteAll();
    }

}

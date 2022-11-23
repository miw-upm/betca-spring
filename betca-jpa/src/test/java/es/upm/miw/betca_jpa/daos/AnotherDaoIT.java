package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.TestConfig;
import es.upm.miw.betca_jpa.entities.AggregationEntity;
import es.upm.miw.betca_jpa.entities.AnotherEntity;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class AnotherDaoIT {

    @Autowired
    private AggregationDao aggregationDao;

    @Autowired
    private AnotherDao anotherDao;

    @BeforeEach
    void before() {
        AnotherEntity[] anothersA = {
                new AnotherEntity("a-one"),
                new AnotherEntity("a-two")
        };
        AnotherEntity[] anothersB = {
                new AnotherEntity("b-one"),
                new AnotherEntity("b-two")
        };

        AnotherEntity anotherA = new AnotherEntity("AAA");
        AnotherEntity anotherB = new AnotherEntity("BBB");
        this.anotherDao.save(new AnotherEntity("NoAggregation"));
        this.anotherDao.save(anotherA);
        this.anotherDao.save(anotherB);
        this.anotherDao.saveAll(Arrays.asList(anothersA));
        this.anotherDao.saveAll(Arrays.asList(anothersB));

        AggregationEntity aggregationA = AggregationEntity.builder().nick("Mi Nick A").anotherEntity(anotherA)
                .anotherEntityList(Arrays.asList(anothersA)).build();
        AggregationEntity aggregationB = AggregationEntity.builder().nick("Mi Nick B").anotherEntity(anotherB)
                .anotherEntityList(Arrays.asList(anothersB)).build();
        this.aggregationDao.save(aggregationA);
        this.aggregationDao.save(aggregationB);
    }

    @Test
    void testFindValueByAnyAggregationEntity() {
        assertFalse(anotherDao.findValueByAnyAggregationEntity().isEmpty());
        LogManager.getLogger(this.getClass()).debug("===>>> message: " + anotherDao.findValueByAnyAggregationEntity());
    }

    @Test
    void testFindValueByAggregationEntityAnotherEntityListValue() {
        assertFalse(anotherDao.findValueByAggregationEntityAnyMatchAnotherEntityListValue("b-two").isEmpty());
        LogManager.getLogger(this.getClass()).debug("===>>> message: " + anotherDao.findValueByAggregationEntityAnyMatchAnotherEntityListValue("b-two"));
    }

    @Test
    void test() {
        AnotherEntity[] anothersA = new AnotherEntity[10000];
        for (int i = 0; i < anothersA.length; i++) {
            anothersA[i] = new AnotherEntity("a-" + i);
        }
        this.anotherDao.saveAll(Arrays.asList(anothersA));
        AnotherEntity[] anothersB = new AnotherEntity[10000];
        for (int i = 0; i < anothersA.length; i++) {
            anothersB[i] = new AnotherEntity("b-" + i);
        }
        this.anotherDao.saveAll(Arrays.asList(anothersB));
        AnotherEntity anotherA = new AnotherEntity("AAA");
        AnotherEntity anotherB = new AnotherEntity("BBB");
        this.anotherDao.save(new AnotherEntity("NoAggregation"));
        this.anotherDao.save(anotherA);
        this.anotherDao.save(anotherB);
        AggregationEntity aggregationA = AggregationEntity.builder().nick("Mi Nick A").anotherEntity(anotherA)
                .anotherEntityList(Arrays.asList(anothersA)).build();
        AggregationEntity aggregationB = AggregationEntity.builder().nick("Mi Nick B").anotherEntity(anotherB)
                .anotherEntityList(Arrays.asList(anothersB)).build();
        this.aggregationDao.save(aggregationA);
        this.aggregationDao.save(aggregationB);
        long time1 = System.currentTimeMillis();
        List<String> values1 = anotherDao.findValueByAggregationEntityAnyMatchAnotherEntityListValue("b-666");
        long timeA = System.currentTimeMillis() - time1;
        LogManager.getLogger(this.getClass()).debug("===>>> message: (" + timeA + ")" + values1);
        long time2 = System.currentTimeMillis();
        List<String> x = aggregationDao.findAll().stream()
                .filter(aggregationEntity -> aggregationEntity.getAnotherEntityList().stream()
                        .anyMatch(anotherEntity -> "b-666".equals(anotherEntity.getValue())))
                .map(AggregationEntity::getAnotherEntity)
                .map(AnotherEntity::getValue)
                .collect(Collectors.toList());
        long timeB = System.currentTimeMillis() - time1;
        LogManager.getLogger(this.getClass()).debug("===>>> message: (" + timeB + ")" + values1);
    }

    @AfterEach
    void delete() {
        this.aggregationDao.deleteAll();
        this.anotherDao.deleteAll();
    }

}

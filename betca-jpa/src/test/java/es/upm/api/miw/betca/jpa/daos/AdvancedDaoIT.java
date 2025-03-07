package es.upm.api.miw.betca.jpa.daos;

import es.upm.api.miw.betca.jpa.TestConfig;
import es.upm.api.miw.betca.jpa.entities.AdvancedEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class AdvancedDaoIT {

    @Autowired
    private AdvancedDao advancedDao;

    @Test
    void test() {
        AdvancedEntity advancedEntity = AdvancedEntity.builder().nick("adv2").large(".... large").build();
        this.advancedDao.save(advancedEntity);
        assertTrue(this.advancedDao.findByNick("adv2").isPresent());
        this.advancedDao.deleteAll();
    }


}

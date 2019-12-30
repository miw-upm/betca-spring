package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.jpa.entities.AnyClass;
import es.upm.miw.persistence.jpa.entities.CompositionEntity;
import es.upm.miw.persistence.jpa.entities.EmbeddableEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class CompositionDaoIT {

    @Autowired
    private CompositionDao compositionDao;

    private CompositionEntity entity;

    private AnyClass[] array = {new AnyClass(0, "cero"), new AnyClass(1, "uno"),
            new AnyClass(2, "dos"), new AnyClass(3, "tres")};

    @BeforeEach
    void before() {
        EmbeddableEntity embeddable = new EmbeddableEntity(666, "daemon");
        this.entity = new CompositionEntity("Nick", embeddable);
        this.entity.setAnyClassArray(array);
        compositionDao.save(entity);
    }

    @Test
    void testFindOne() {
        CompositionEntity entity2 = compositionDao.findById(entity.getId()).get();
        assertEquals("Nick", entity2.getNick());
        assertEquals("daemon", entity2.getEmbeddableEntity().getValue());
        assertEquals(4, entity2.getAnyClassArray().length);
    }

    @AfterEach
    void delete() {
        compositionDao.delete(this.entity);
    }

}

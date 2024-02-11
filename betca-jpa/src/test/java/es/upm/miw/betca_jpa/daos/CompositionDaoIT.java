package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.TestConfig;
import es.upm.miw.betca_jpa.entities.AnyClass;
import es.upm.miw.betca_jpa.entities.AnyEntity;
import es.upm.miw.betca_jpa.entities.CompositionEntity;
import es.upm.miw.betca_jpa.entities.EmbeddableEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CompositionDaoIT {

    @Autowired
    private CompositionDao compositionDao;

    private CompositionEntity entity;

    private AnyClass[] array = {new AnyClass(0, "zero"), new AnyClass(1, "one"),
            new AnyClass(2, "two"), new AnyClass(3, "three")};

    @BeforeEach
    void before() {
        EmbeddableEntity embeddable = new EmbeddableEntity(666, "daemon");
        this.entity = CompositionEntity.builder().nick("Nick")
                .embeddableEntity(embeddable)
                .anyEntity(new AnyEntity("one")).anyEntity(new AnyEntity("two"))
               .build();
        this.compositionDao.save(entity);

    }

    @Test
    void testFindOne() {
        assertTrue(this.compositionDao.findById(entity.getId()).isPresent());
        CompositionEntity entity2 = this.compositionDao.findById(entity.getId()).get();
        assertEquals("Nick", entity2.getNick());
        assertEquals("daemon", entity2.getEmbeddableEntity().getValue());
        assertEquals(2, entity2.getAnyEntityList().size());

    }

    @AfterEach
    void delete() {
        compositionDao.delete(this.entity);
    }

}

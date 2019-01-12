package miw.persistence.jpa.daos;

import miw.TestConfig;
import miw.persistence.jpa.entities.EmbeddableEntity;
import miw.persistence.jpa.entities.UnidirectionalOneToOneEmbeddedEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class UnidirectionalOneToOneEmbeddedDaoIT {

    @Autowired
    private UnidirectionalOneToOneEmbeddedDao unidirectionalOneToOneEmbeddedDao;

    private EmbeddableEntity embeddable;

    private UnidirectionalOneToOneEmbeddedEntity entity;

    @BeforeEach
    public void before() {
        this.embeddable = new EmbeddableEntity(666, "daemon");
        this.entity = new UnidirectionalOneToOneEmbeddedEntity("Nick", embeddable);
    }

    @Test
    public void testFindOne() {
        unidirectionalOneToOneEmbeddedDao.save(entity);
        assertEquals("Nick", unidirectionalOneToOneEmbeddedDao.findById(entity.getId()).get().getNick());
        assertEquals("daemon", unidirectionalOneToOneEmbeddedDao.findById(
                entity.getId()).get().getEmbeddableEntity().getValue());
    }

    @AfterEach
    public void delete() {
        unidirectionalOneToOneEmbeddedDao.delete(this.entity);
    }

}

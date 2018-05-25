package miw.persistence.jpa.daos;

import miw.TestConfig;
import miw.persistence.jpa.entities.AnyClass;
import miw.persistence.jpa.entities.UnidirectionalOneToManyEmbeddedEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class UnidirectionalOneToManyEmbeddedDaoIT {

    @Autowired
    private UnidirectionalOneToManyEmbeddedDao unidirectionalOneToManyEmbeddedDao;

    private AnyClass[] array = {new AnyClass(0, "cero"), new AnyClass(1, "uno"),
            new AnyClass(2, "dos"), new AnyClass(3, "tres")};

    private UnidirectionalOneToManyEmbeddedEntity entity;

    @BeforeEach
    public void before() {
        this.entity = new UnidirectionalOneToManyEmbeddedEntity("Mi Nick", array);
    }

    @Test
    public void testFindOne() {
        unidirectionalOneToManyEmbeddedDao.save(entity);
        UnidirectionalOneToManyEmbeddedEntity unidirectionalOneToManyEmbeddedEntity = unidirectionalOneToManyEmbeddedDao
                .findById(this.entity.getId()).get();
        assertEquals("Mi Nick", unidirectionalOneToManyEmbeddedEntity.getNick());
        assertEquals(4, unidirectionalOneToManyEmbeddedEntity.getAnyClassArray().length);
    }

    @AfterEach
    public void delete() {
        unidirectionalOneToManyEmbeddedDao.deleteById(entity.getId());
    }

}

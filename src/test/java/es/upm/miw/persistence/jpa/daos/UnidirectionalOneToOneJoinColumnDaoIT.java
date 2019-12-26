package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.AnyEntity;
import es.upm.miw.persistence.jpa.entities.UnidirectionalOneToOneJoinColumnEntity;
import es.upm.miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class UnidirectionalOneToOneJoinColumnDaoIT {

    @Autowired
    private UnidirectionalOneToOneJoinColumnDao unidirectionalOneToOneJoinColumnDao;

    private AnyEntity anyEntity;

    private UnidirectionalOneToOneJoinColumnEntity entity;

    @BeforeEach
    public void before() {
        anyEntity = new AnyEntity("daemon");
        entity = new UnidirectionalOneToOneJoinColumnEntity("Mi Nick", anyEntity);
    }

    @Test
    public void testFindOne() {
        unidirectionalOneToOneJoinColumnDao.save(entity);
        UnidirectionalOneToOneJoinColumnEntity unidirectionalOneToOneJoinColumnEntity =
                unidirectionalOneToOneJoinColumnDao.findById(entity.getId()).get();
        assertEquals("Mi Nick", unidirectionalOneToOneJoinColumnEntity.getNick());
        assertEquals("daemon", unidirectionalOneToOneJoinColumnEntity.getAnyEntity().getValue());
    }

    @AfterEach
    public void delete() {
        unidirectionalOneToOneJoinColumnDao.delete(entity);
    }

}

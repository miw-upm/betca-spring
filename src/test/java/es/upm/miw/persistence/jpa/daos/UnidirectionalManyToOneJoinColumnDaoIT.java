package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.AnyEntity;
import es.upm.miw.persistence.jpa.entities.UnidirectionalManyToOneJoinColumnEntity;
import es.upm.miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class UnidirectionalManyToOneJoinColumnDaoIT {

    @Autowired
    private UnidirectionalManyToOneJoinColumnDao unidirectionalManyToOneJoinColumnDao;

    @Autowired
    private AnyDao anyDao;

    private AnyEntity anyEntity = new AnyEntity("daemon");

    private UnidirectionalManyToOneJoinColumnEntity entity;

    private UnidirectionalManyToOneJoinColumnEntity entity2;

    @BeforeEach
    public void before() {
        this.anyEntity = new AnyEntity("daemon");
        this.entity = new UnidirectionalManyToOneJoinColumnEntity("Nick", anyEntity);
        this.entity2 = new UnidirectionalManyToOneJoinColumnEntity("Nick 2", anyEntity);
        this.anyDao.save(anyEntity);
        this.unidirectionalManyToOneJoinColumnDao.save(entity);
        this.unidirectionalManyToOneJoinColumnDao.save(entity2);
    }

    @Test
    public void testFindOne() {
        int id = this.anyEntity.getId();
        Assertions.assertEquals(id,
                this.unidirectionalManyToOneJoinColumnDao.findById(entity.getId()).get().getAnyEntity().getId());
        Assertions.assertEquals(id,
                this.unidirectionalManyToOneJoinColumnDao.findById(entity2.getId()).get().getAnyEntity().getId());
    }

    @AfterEach
    public void delete() {
        this.unidirectionalManyToOneJoinColumnDao.delete(this.entity);
        this.unidirectionalManyToOneJoinColumnDao.delete(this.entity2);
        this.anyDao.delete(this.anyEntity);
    }

}

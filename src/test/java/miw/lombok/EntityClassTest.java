package miw.lombok;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EntityClassTest {

    private EntityClass entityClass;

    @BeforeEach
    void before() {
        this.entityClass = EntityClass.builder().id(1).name("Name").surName("surName").email("email").build();
        new EntityClass();
    }

    @Test
    void testGetters() {
        LogManager.getLogger(this.getClass()).info("===>>> getter:" + this.entityClass.getName());
    }

    @Test
    void testSetters() {
        this.entityClass.setId(10);
    }

    @Test
    void testToString() {
        LogManager.getLogger(this.getClass()).info("===>>> toString:" + this.entityClass.toString());
    }

    @Test
    void testHashCode() {
        LogManager.getLogger(this.getClass()).info("===>>> hashCode:" + this.entityClass.hashCode());
    }

}

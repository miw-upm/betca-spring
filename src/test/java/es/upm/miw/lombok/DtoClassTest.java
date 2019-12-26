package es.upm.miw.lombok;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DtoClassTest {

    private DtoClass dtoClass;

    @BeforeEach
    void before() {
        this.dtoClass = DtoClass.builder().id(1).name("Name").surName("surName").email("email").build();
        new DtoClass();
    }

    @Test
    void testGetters() {

        LogManager.getLogger(this.getClass()).info("===>>> getter:" + this.dtoClass.getName());
    }

    @Test
    void testToString() {
        LogManager.getLogger(this.getClass()).info("===>>> toString:" + this.dtoClass.toString());
    }

    @Test
    void testHashCode() {
        LogManager.getLogger(this.getClass()).info("===>>> hashCode:" + this.dtoClass.hashCode());
    }

}

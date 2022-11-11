package es.upm.miw.betca_jpa.lombok;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class DtoWithLombokTest {

    private DtoWithLombok dto1;
    private DtoWithLombok dto2;

    @BeforeEach
    void before() {
        this.dto1 = DtoWithLombok.builder().id("1").name("Name1").surName("surName1").email("email1").build();
        this.dto2 = DtoWithLombok.builder().id("1").name("Name2").surName("surName2").email("email2").build();
        new DtoWithLombok();
        new DtoWithLombok("1","Name1","surName1","email1",null);
    }

    @Test
    void testGetters() {
        log.info("===>>> getter:" + this.dto1.getName());
    }

    @Test
    void testToString() {
        log.info("===>>> toString:" + this.dto1.toString());
    }

    @Test
    void testHashCode() {
        log.info("===>>> hashCode:" + this.dto1.hashCode());
    }

    @Test
    void testEquals() {
        assertEquals(this.dto1, this.dto2);
    }

    @Test
    void testCollections() {
        DtoWithLombok dto3 = DtoWithLombok.builder().id("1").tag("tag1").tag("tag2").build();
        log.info("dto3: " + dto3.toString());
    }
}

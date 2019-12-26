package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.AnyDocument;
import es.upm.miw.persistence.mongo.documents.OneAndManyToOneDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OneAndManyToOneRepositoryIT {

    AnyDocument anyDocument3;
    @Autowired
    private OneAndManyToOneRepository oneAndManyToOneRepository;
    @Autowired
    private AnyRepository anyRepository;

    @BeforeEach
    public void populate() {
        this.oneAndManyToOneRepository.deleteAll();
        this.anyRepository.deleteAll();
        AnyDocument anyDocument1 = new AnyDocument("any");
        anyDocument3 = new AnyDocument("3");
        anyDocument3.setId("3");
        this.anyRepository.save(anyDocument1);
        this.anyRepository.save(anyDocument3);
        this.oneAndManyToOneRepository.save(new OneAndManyToOneDocument("nick1", anyDocument1));
        this.oneAndManyToOneRepository.save(new OneAndManyToOneDocument("nick2", anyDocument1));
        this.oneAndManyToOneRepository.save(new OneAndManyToOneDocument("nick3", anyDocument3));
    }

    @Test
    public void test() {
        assertTrue(oneAndManyToOneRepository.count() > 0);
    }

    @Test
    public void testFindByAnyDocumentId() {
        assertNotNull(oneAndManyToOneRepository.findByAnyDocumentId("3"));
    }

}

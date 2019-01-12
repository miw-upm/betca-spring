package miw.persistence.mongo.repositories;

import miw.TestConfig;
import miw.persistence.mongo.documents.AnyDocument;
import miw.persistence.mongo.documents.OneAndManyToOneDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OneAndManyToOneRepositoryIT {

    AnyDocument anyDocument3;
    @Autowired
    private OneAndManyToOneRepository unidirectionalOneToOneRepository;
    @Autowired
    private AnyRepository anyRepository;

    @BeforeEach
    public void populate() {
        this.unidirectionalOneToOneRepository.deleteAll();
        this.anyRepository.deleteAll();
        AnyDocument anyDocument1 = new AnyDocument("any");
        anyDocument3 = new AnyDocument("3");
        anyDocument3.setId("3");
        this.anyRepository.save(anyDocument1);
        this.anyRepository.save(anyDocument3);
        this.unidirectionalOneToOneRepository.save(new OneAndManyToOneDocument("nick1", anyDocument1));
        this.unidirectionalOneToOneRepository.save(new OneAndManyToOneDocument("nick2", anyDocument1));
        this.unidirectionalOneToOneRepository.save(new OneAndManyToOneDocument("nick3", anyDocument3));
    }

    @Test
    public void test() {
        assertTrue(unidirectionalOneToOneRepository.count() > 0);
    }

    @Test
    public void testFindByAnyDocumentValue() {
        assertNotNull(unidirectionalOneToOneRepository.findByAnyDocumentId("3"));
    }
}

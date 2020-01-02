package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.AggregationDocument;
import es.upm.miw.persistence.mongo.documents.AnyDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class AggregationRepositoryIT {

    @Autowired
    private AggregationRepository aggregationRepository;

    @Autowired
    private AnyRepository anyRepository;

    @BeforeEach
    void seedDb() {
        AnyDocument anyDocument = new AnyDocument("any");
        List<AnyDocument> anyDocumentList = Arrays.asList(
                new AnyDocument("any1"),
                new AnyDocument("any2"),
                new AnyDocument("any3")
        );
        this.anyRepository.save(anyDocument);
        this.anyRepository.saveAll(anyDocumentList);
        this.aggregationRepository.save(new AggregationDocument("nick", anyDocument, anyDocumentList));
        this.aggregationRepository.save(new AggregationDocument("nick2", anyDocument,
                Arrays.asList(anyDocumentList.get(0), anyDocumentList.get(2))
        ));
    }

    @Test
    void testCount() {
        assertTrue(aggregationRepository.count() > 0);
    }

    @Test
    void testFindByAnyDocumentListContaining() {
        AnyDocument any1 = anyRepository.findFirstByValue("any1");
        AnyDocument any2 = anyRepository.findFirstByValue("any2");
        assertEquals(2, aggregationRepository.findByAnyDocumentListContaining(any1).size());
        assertEquals(1, aggregationRepository.findByAnyDocumentListContaining(any2).size());
    }

    @AfterEach
    void deleteDB() {
        this.aggregationRepository.deleteAll();
        this.anyRepository.deleteAll();
    }

}

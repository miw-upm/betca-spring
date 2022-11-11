package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.TestConfig;
import es.upm.miw.betca_mongodb.documents.AggregationDocument;
import es.upm.miw.betca_mongodb.documents.AnyDocument;
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
        AnyDocument anyDocument = AnyDocument.builder().value("any").build();
        List<AnyDocument> anyDocumentList = Arrays.asList(
                AnyDocument.builder().value("any1").build(),
                AnyDocument.builder().value("any2").build(),
                AnyDocument.builder().value("any3").build()
        );
        this.anyRepository.save(anyDocument);
        this.anyRepository.saveAll(anyDocumentList);
        this.aggregationRepository.save(AggregationDocument.builder().nick("nick").anyDocument(anyDocument)
                .anyDocumentList(anyDocumentList).build());
        this.aggregationRepository.save(AggregationDocument.builder().nick("nick").anyDocument(anyDocument)
                .anyDocumentList(List.of(anyDocumentList.get(0), anyDocumentList.get(2))).build());
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

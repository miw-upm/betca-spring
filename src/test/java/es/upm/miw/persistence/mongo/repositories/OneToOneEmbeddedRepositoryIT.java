package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.EmbeddableDocument;
import es.upm.miw.persistence.mongo.documents.OneToOneEmbeddedDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OneToOneEmbeddedRepositoryIT {

    @Autowired
    private OneToOneEmbeddedRepository unidirectionalOneToOneEmbeddedRepository;

    @BeforeEach
    void populate() {
        this.unidirectionalOneToOneEmbeddedRepository.deleteAll();
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(1, "1")));
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(2, "2")));
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(3, "2")));
    }

    @Test
    void test() {
        assertTrue(unidirectionalOneToOneEmbeddedRepository.count() > 0);
    }

    @Test
    void testFindFirstByEmbeddableDocumentValue() {
        assertNotNull(unidirectionalOneToOneEmbeddedRepository.findFirstByEmbeddableDocumentValue("1"));
        assertNull(unidirectionalOneToOneEmbeddedRepository.findFirstByEmbeddableDocumentValue("NOT"));
    }

    @Test
    void testFindFirst10ByEmbeddableDocumentValue() {
        assertEquals(2, unidirectionalOneToOneEmbeddedRepository.findFirst10ByEmbeddableDocumentValue("2").size());
    }

}

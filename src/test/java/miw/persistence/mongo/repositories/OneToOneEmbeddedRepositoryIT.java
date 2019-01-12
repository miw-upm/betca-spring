package miw.persistence.mongo.repositories;

import miw.TestConfig;
import miw.persistence.mongo.documents.EmbeddableDocument;
import miw.persistence.mongo.documents.OneToOneEmbeddedDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class OneToOneEmbeddedRepositoryIT {

    @Autowired
    private OneToOneEmbeddedRepository unidirectionalOneToOneEmbeddedRepository;

    @BeforeEach
    public void populate() {
        this.unidirectionalOneToOneEmbeddedRepository.deleteAll();
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(1, "1")));
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(2, "2")));
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(3, "2")));
    }

    @Test
    public void test() {
        assertTrue(unidirectionalOneToOneEmbeddedRepository.count() > 0);
    }

    @Test
    public void testFindFirstByEmbeddableDocumentValue() {
        assertNotNull(unidirectionalOneToOneEmbeddedRepository.findFirstByEmbeddableDocumentValue("1"));
        assertNull(unidirectionalOneToOneEmbeddedRepository.findFirstByEmbeddableDocumentValue("NOT"));
    }

    @Test
    public void testFindFirst10ByEmbeddableDocumentValue() {
        assertEquals(2, unidirectionalOneToOneEmbeddedRepository.findFirst10ByEmbeddableDocumentValue("2").size());
    }

}

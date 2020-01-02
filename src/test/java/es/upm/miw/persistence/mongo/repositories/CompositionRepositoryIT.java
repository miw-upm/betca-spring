package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.CompositionDocument;
import es.upm.miw.persistence.mongo.documents.EmbeddableDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CompositionRepositoryIT {

    @Autowired
    private CompositionRepository compositionRepository;

    @BeforeEach
    void seedDb() {
        CompositionDocument CompositionDocument = new CompositionDocument("nick", new EmbeddableDocument(1, "1"));
        CompositionDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(2, "2"));
        CompositionDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(3, "3"));
        this.compositionRepository.save(CompositionDocument);
    }

    @Test
    void test() {
        assertTrue(compositionRepository.count() > 0);
    }

    @Test
    void deleteBD() {
        this.compositionRepository.deleteAll();
    }

}

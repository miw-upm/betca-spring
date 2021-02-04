package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.TestConfig;
import es.upm.miw.betca_mongodb.documents.CompositionDocument;
import es.upm.miw.betca_mongodb.documents.EmbeddableDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CompositionRepositoryIT {

    @Autowired
    private CompositionRepository compositionRepository;

    @BeforeEach
    void seedDb() {
        CompositionDocument compositionDocument = CompositionDocument.builder().nick("nick")
                .embeddableDocument(new EmbeddableDocument(1, "1"))
                .embeddableDocumentList(List.of(new EmbeddableDocument(2, "2")
                        , new EmbeddableDocument(3, "3"))).build();
        this.compositionRepository.save(compositionDocument);
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

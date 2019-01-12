package miw.persistence.mongo.repositories;

import miw.TestConfig;
import miw.persistence.mongo.documents.EmbeddableDocument;
import miw.persistence.mongo.documents.OneToManyEmbeddedDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OneToManyEmbeddedRepositoryIT {

    @Autowired
    private OneToManyEmbeddedRepository unidirectionalOneToManyEmbeddedRepository;

    @BeforeEach
    public void seedDb() {
        this.unidirectionalOneToManyEmbeddedRepository.deleteAll();
        OneToManyEmbeddedDocument oneToManyEmbeddedDocument = new OneToManyEmbeddedDocument("nick");
        oneToManyEmbeddedDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(1, "1"));
        oneToManyEmbeddedDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(2, "2"));
        oneToManyEmbeddedDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(3, "3"));
        this.unidirectionalOneToManyEmbeddedRepository.save(oneToManyEmbeddedDocument);
    }

    @Test
    public void test() {
        assertTrue(unidirectionalOneToManyEmbeddedRepository.count() > 0);
    }

}

package miw.persistence.mongo.repositories;

import miw.persistence.mongo.documents.OneToOneEmbeddedDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OneToOneEmbeddedRepository extends MongoRepository<OneToOneEmbeddedDocument, String> {

    // @Query("{'embeddableDocument.value':?0}") //not necessary
    OneToOneEmbeddedDocument findFirstByEmbeddableDocumentValue(String embeddableDocumentValue);

    List<OneToOneEmbeddedDocument> findFirst10ByEmbeddableDocumentValue(String embeddableDocumentValue);

}

package miw.persistence.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.OneToOneEmbeddedDocument;

public interface OneToOneEmbeddedRepository extends MongoRepository<OneToOneEmbeddedDocument, String> {

    // @Query("{'embeddableDocument.value':?0}") //not necessary
    OneToOneEmbeddedDocument findFirstByEmbeddableDocumentValue(String embeddableDocumentValue);

    List<OneToOneEmbeddedDocument> findFirst10ByEmbeddableDocumentValue(String embeddableDocumentValue);

}

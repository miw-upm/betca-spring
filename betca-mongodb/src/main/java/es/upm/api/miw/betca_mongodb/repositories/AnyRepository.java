package es.upm.api.miw.betca_mongodb.repositories;

import es.upm.api.miw.betca_mongodb.documents.AnyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnyRepository extends MongoRepository<AnyDocument, String> {
    AnyDocument findFirstByValue(String value);
}

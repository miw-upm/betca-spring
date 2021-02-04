package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.documents.AnyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnyRepository extends MongoRepository< AnyDocument, String > {
    AnyDocument findFirstByValue(String value);
}

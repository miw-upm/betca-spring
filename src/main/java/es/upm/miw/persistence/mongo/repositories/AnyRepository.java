package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.persistence.mongo.documents.AnyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnyRepository extends MongoRepository<AnyDocument, String> {

    AnyDocument findFirstByValue(String value);
}

package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.persistence.mongo.documents.CompositionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompositionRepository extends MongoRepository<CompositionDocument, String> {
}

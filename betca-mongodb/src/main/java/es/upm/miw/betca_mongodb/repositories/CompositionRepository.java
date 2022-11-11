package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.documents.CompositionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompositionRepository extends MongoRepository<CompositionDocument, String> {
}

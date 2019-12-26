package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.persistence.mongo.documents.library.Style;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StyleRepository extends MongoRepository<Style, String> {
    Style findByNameIgnoreCase(String name);
}

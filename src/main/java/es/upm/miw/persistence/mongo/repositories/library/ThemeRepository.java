package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.persistence.mongo.documents.library.Theme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThemeRepository extends MongoRepository<Theme, String> {
}

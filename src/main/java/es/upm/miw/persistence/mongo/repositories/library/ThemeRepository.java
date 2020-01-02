package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.persistence.mongo.documents.library.Theme;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ThemeRepository extends MongoRepository<Theme, String> {
    List<Theme> findByName(String name);
}

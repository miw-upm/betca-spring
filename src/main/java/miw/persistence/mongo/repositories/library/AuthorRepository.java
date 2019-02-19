package miw.persistence.mongo.repositories.library;

import miw.persistence.mongo.documents.library.Author;
import miw.persistence.mongo.documents.library.Style;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findByStyle(Style style);

    //List<String> findNameByStyleName(String styleName);

    //List<String> findDistinctNameByAnyBook();

    //List<String> findNameByThemeName(String themeName);
}

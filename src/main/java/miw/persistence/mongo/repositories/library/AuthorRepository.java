package miw.persistence.mongo.repositories.library;

import miw.persistence.mongo.documents.library.Author;
import miw.persistence.mongo.documents.library.NameDto;
import miw.persistence.mongo.documents.library.Style;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findByStyle(Style style);

    List<NameDto> findNameByStyle(Style style);

}

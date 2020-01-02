package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.persistence.mongo.documents.library.Book;
import es.upm.miw.persistence.mongo.documents.library.Theme;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.stream.Stream;

public interface BookRepository extends MongoRepository<Book, String> {
    Stream<Book> findByThemeListIn(Collection<Theme> themes);

}

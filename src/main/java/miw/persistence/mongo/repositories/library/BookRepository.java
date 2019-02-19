package miw.persistence.mongo.repositories.library;

import miw.persistence.mongo.documents.library.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}

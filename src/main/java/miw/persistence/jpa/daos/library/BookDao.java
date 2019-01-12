package miw.persistence.jpa.daos.library;

import miw.persistence.jpa.entities.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}

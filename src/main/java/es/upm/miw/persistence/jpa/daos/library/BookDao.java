package es.upm.miw.persistence.jpa.daos.library;

import es.upm.miw.persistence.jpa.entities.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}

package miw.persistence.jpa.daos.library;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.library.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
}

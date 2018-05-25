package miw.persistence.jpa.daos.library;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.library.Style;

public interface StyleDao extends JpaRepository<Style, Integer> {
    Style findByNameIgnoreCase(String name);
}

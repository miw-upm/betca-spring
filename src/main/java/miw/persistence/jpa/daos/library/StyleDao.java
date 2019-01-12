package miw.persistence.jpa.daos.library;

import miw.persistence.jpa.entities.library.Style;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleDao extends JpaRepository<Style, Integer> {
    Style findByNameIgnoreCase(String name);
}

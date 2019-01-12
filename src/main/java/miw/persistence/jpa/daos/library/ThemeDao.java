package miw.persistence.jpa.daos.library;

import miw.persistence.jpa.entities.library.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeDao extends JpaRepository<Theme, Integer> {
}

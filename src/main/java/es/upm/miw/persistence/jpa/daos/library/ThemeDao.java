package es.upm.miw.persistence.jpa.daos.library;

import es.upm.miw.persistence.jpa.entities.library.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeDao extends JpaRepository<Theme, Integer> {
}

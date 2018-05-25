package miw.persistence.jpa.daos.library;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.library.Theme;

public interface ThemeDao extends JpaRepository<Theme, Integer> {
}

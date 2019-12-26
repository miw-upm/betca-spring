package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.UnidirectionalManyToManyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalManyToManyDao extends JpaRepository<UnidirectionalManyToManyEntity, Integer> {
}

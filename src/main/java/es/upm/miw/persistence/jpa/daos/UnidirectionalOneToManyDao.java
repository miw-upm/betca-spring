package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.UnidirectionalOneToManyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalOneToManyDao extends JpaRepository<UnidirectionalOneToManyEntity, Integer> {
}

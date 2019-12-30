package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.CompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionDao extends JpaRepository<CompositionEntity, Integer> {
}

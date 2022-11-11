package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.CompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionDao extends JpaRepository<CompositionEntity, Integer> {
}

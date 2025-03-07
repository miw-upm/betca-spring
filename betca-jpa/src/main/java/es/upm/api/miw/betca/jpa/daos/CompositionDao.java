package es.upm.api.miw.betca.jpa.daos;

import es.upm.api.miw.betca.jpa.entities.CompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionDao extends JpaRepository<CompositionEntity, Integer> {
}

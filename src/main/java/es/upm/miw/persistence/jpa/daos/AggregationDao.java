package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.AggregationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregationDao extends JpaRepository<AggregationEntity, Integer> {
}

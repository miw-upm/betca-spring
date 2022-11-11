package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.AggregationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregationDao extends JpaRepository<AggregationEntity, Integer> {
}

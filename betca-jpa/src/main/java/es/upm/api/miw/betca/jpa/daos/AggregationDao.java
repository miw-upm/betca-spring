package es.upm.api.miw.betca.jpa.daos;

import es.upm.api.miw.betca.jpa.entities.AggregationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AggregationDao extends JpaRepository<AggregationEntity, Integer> {
    @Query("SELECT agg.nick FROM AggregationEntity agg WHERE agg.anotherEntity.name = ?1")
    List<String> findNickByAnotherEntityName(String name);
}

package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.AggregationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AggregationDao extends JpaRepository<AggregationEntity, Integer> {
    @Query("SELECT agg.nick FROM AggregationEntity agg WHERE agg.anotherEntity.value = ?1")
    List<String> findNickByAnotherEntityValue(String value);
}

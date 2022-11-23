package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.AnotherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnotherDao extends JpaRepository<AnotherEntity, Integer> {
    @Query("SELECT anotherList.value FROM AggregationEntity agg JOIN agg.anotherEntityList anotherList")
    List<String> findValueByAnyAggregationEntity();

    @Query("SELECT another.value FROM AggregationEntity agg " +
            "JOIN agg.anotherEntity another " +
            "JOIN agg.anotherEntityList anotherList WHERE anotherList.value = ?1")
    List<String> findValueByAggregationEntityAnyMatchAnotherEntityListValue(String value);
}

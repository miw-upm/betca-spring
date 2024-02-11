package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.AnotherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnotherDao extends JpaRepository<AnotherEntity, Integer> {
    @Query("SELECT anotherList.name FROM AggregationEntity agg JOIN agg.anotherEntityList anotherList")
    List<String> findValueByAnyAggregationEntity();

    @Query("SELECT another.name FROM AggregationEntity agg " +
            "JOIN agg.anotherEntity another " +
            "JOIN agg.anotherEntityList anotherList WHERE anotherList.name = ?1")
    List<String> findValueByAggregationEntityAnyMatchAnotherEntityListName(String name);
}

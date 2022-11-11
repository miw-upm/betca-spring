package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.AnotherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotherDao extends JpaRepository<AnotherEntity, Integer> {
}

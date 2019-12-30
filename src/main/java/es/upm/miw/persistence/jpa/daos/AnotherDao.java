package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.AnotherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotherDao extends JpaRepository<AnotherEntity, Integer> {
}

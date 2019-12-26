package es.upm.miw.persistence.jpa.daos;

import es.upm.miw.persistence.jpa.entities.UnidirectionalOneToManyEmbeddedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalOneToManyEmbeddedDao
        extends JpaRepository<UnidirectionalOneToManyEmbeddedEntity, Integer> {

}

package miw.persistence.jpa.daos;

import miw.persistence.jpa.entities.UnidirectionalOneToManyEmbeddedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalOneToManyEmbeddedDao
        extends JpaRepository<UnidirectionalOneToManyEmbeddedEntity, Integer> {

}

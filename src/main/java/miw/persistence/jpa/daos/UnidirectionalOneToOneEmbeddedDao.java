package miw.persistence.jpa.daos;

import miw.persistence.jpa.entities.UnidirectionalOneToOneEmbeddedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalOneToOneEmbeddedDao
        extends JpaRepository<UnidirectionalOneToOneEmbeddedEntity, Integer> {

}

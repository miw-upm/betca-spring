package miw.persistence.jpa.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.UnidirectionalOneToOneEmbeddedEntity;

public interface UnidirectionalOneToOneEmbeddedDao
        extends JpaRepository<UnidirectionalOneToOneEmbeddedEntity, Integer> {

}

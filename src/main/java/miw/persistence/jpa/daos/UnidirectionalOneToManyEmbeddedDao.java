package miw.persistence.jpa.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.UnidirectionalOneToManyEmbeddedEntity;

public interface UnidirectionalOneToManyEmbeddedDao
        extends JpaRepository<UnidirectionalOneToManyEmbeddedEntity, Integer> {

}

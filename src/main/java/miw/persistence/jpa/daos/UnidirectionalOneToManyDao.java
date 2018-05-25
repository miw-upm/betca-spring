package miw.persistence.jpa.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.UnidirectionalOneToManyEntity;

public interface UnidirectionalOneToManyDao extends JpaRepository<UnidirectionalOneToManyEntity, Integer> {
}

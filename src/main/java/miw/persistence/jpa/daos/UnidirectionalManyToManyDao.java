package miw.persistence.jpa.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.UnidirectionalManyToManyEntity;

public interface UnidirectionalManyToManyDao extends JpaRepository<UnidirectionalManyToManyEntity, Integer> {
}

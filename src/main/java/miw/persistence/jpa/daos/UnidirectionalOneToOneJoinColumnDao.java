package miw.persistence.jpa.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.UnidirectionalOneToOneJoinColumnEntity;

public interface UnidirectionalOneToOneJoinColumnDao
        extends JpaRepository<UnidirectionalOneToOneJoinColumnEntity, Integer> {
}

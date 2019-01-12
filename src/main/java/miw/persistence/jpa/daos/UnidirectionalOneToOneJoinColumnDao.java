package miw.persistence.jpa.daos;

import miw.persistence.jpa.entities.UnidirectionalOneToOneJoinColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalOneToOneJoinColumnDao
        extends JpaRepository<UnidirectionalOneToOneJoinColumnEntity, Integer> {
}

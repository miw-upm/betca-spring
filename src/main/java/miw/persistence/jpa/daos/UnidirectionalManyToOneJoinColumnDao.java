package miw.persistence.jpa.daos;

import miw.persistence.jpa.entities.UnidirectionalManyToOneJoinColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidirectionalManyToOneJoinColumnDao
        extends JpaRepository<UnidirectionalManyToOneJoinColumnEntity, Integer> {
}

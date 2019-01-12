package miw.persistence.jpa.daos;

import miw.persistence.jpa.entities.AnyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnyDao extends JpaRepository<AnyEntity, Integer> {
}

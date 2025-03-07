package es.upm.api.miw.betca.jpa.daos;

import es.upm.api.miw.betca.jpa.entities.AdvancedEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface AdvancedDao extends JpaRepository<AdvancedEntity, Integer> {
    Optional<AdvancedEntity> findByNick(String nick);
}

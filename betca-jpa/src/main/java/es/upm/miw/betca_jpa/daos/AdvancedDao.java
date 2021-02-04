package es.upm.miw.betca_jpa.daos;

import es.upm.miw.betca_jpa.entities.AdvancedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface AdvancedDao extends JpaRepository< AdvancedEntity, Integer > {
    Optional< AdvancedEntity > findByNick(String nick);
}

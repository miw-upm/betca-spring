package miw.persistence.jpa.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.jpa.entities.AnyEntity;

public interface AnyDao extends JpaRepository<AnyEntity, Integer> {
}

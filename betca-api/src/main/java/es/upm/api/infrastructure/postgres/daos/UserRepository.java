package es.upm.api.infrastructure.postgres.daos;

import es.upm.api.domain.model.Role;
import es.upm.api.infrastructure.postgres.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByMobile(String mobile);

    Stream<UserEntity> findByRoleIn(Collection<Role> roles);

    @Query("select u from User u where " +
            "(coalesce(?1, '') = '' or u.mobile like concat('%',?1,'%')) and " +
            "(coalesce(?2, '') = '' or lower(u.firstName) like lower(concat('%',?2,'%'))) and" +
            "(coalesce(?3, '') = '' or lower(u.familyName) like lower(concat('%',?3,'%'))) and" +
            "(coalesce(?4, '') = '' or lower(u.email) like lower(concat('%',?4,'%'))) and" +
            "(coalesce(?5, '') = '' or lower(u.dni) like lower(concat('%',?5,'%'))) and" +
            "(u.role in ?6)")
    Stream<UserEntity> findByMobileAndFirstNameAndFamilyNameAndEmailAndDniContainingNullSafe(
            String mobile, String firstName, String familyName, String email, String dni, Collection<Role> roles);
}

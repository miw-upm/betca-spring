package es.upm.api.infrastructure.postgres.persistence;

import es.upm.api.domain.model.Role;
import es.upm.api.domain.model.User;
import es.upm.api.domain.services.UserPersistence;
import es.upm.api.infrastructure.postgres.daos.UserRepository;
import es.upm.api.infrastructure.postgres.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class UserPersistencePostgres implements UserPersistence {

    private final UserRepository userRepository;

    public UserPersistencePostgres(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> readByMobile(String mobile) {
        return this.userRepository.findByMobile(mobile)
                .map(UserEntity::toUser);
    }

    @Override
    public Stream<User> findByMobileAndFirstNameAndFamilyNameAndEmailAndDniContainingNullSafe
            (String mobile, String firstName, String familyName, String email, String dni, List<Role> roles) {
        return Stream.empty();
    }

    @Override
    public void create(User user) {
        this.userRepository.save(new UserEntity(user));
    }

    @Override
    public Stream<User> findByRoleIn(List<Role> roles) {
        return this.userRepository.findByRoleIn(roles)
                .map(UserEntity::toUser);
    }
}

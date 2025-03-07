package es.upm.api.domain.services;

import es.upm.api.domain.model.Role;
import es.upm.api.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserPersistence {
    Optional<User> readByMobile(String mobile);

    Stream<User> findByMobileAndFirstNameAndFamilyNameAndEmailAndDniContainingNullSafe
            (String mobile, String firstName, String familyName, String email, String dni, List<Role> roles);

    void create(User user);

    Stream<User> findByRoleIn(List<Role> roles);
}


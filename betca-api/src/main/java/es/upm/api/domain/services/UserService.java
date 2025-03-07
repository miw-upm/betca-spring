package es.upm.api.domain.services;

import es.upm.api.domain.exceptions.ConflictException;
import es.upm.api.domain.exceptions.ForbiddenException;
import es.upm.api.domain.exceptions.NotFoundException;
import es.upm.api.domain.model.Role;
import es.upm.api.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserPersistence userPersistence;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserPersistence userPersistence, JwtService jwtService) {
        this.userPersistence = userPersistence;
        this.jwtService = jwtService;
    }

    public String login(String mobile) {
        return this.userPersistence.readByMobile(mobile)
                .map(user -> jwtService.createToken(user.getMobile(), user.getFirstName(), user.getRole().name()))
                .orElseThrow(() -> new NotFoundException("Impossible, you should have already logged in."));
    }

    public void createUser(User user, Role roleClaim) {
        if (!authorizedRoles(roleClaim).contains(user.getRole())) {
            throw new ForbiddenException("Insufficient role to create this user: " + user);
        }
        this.assertNoExistByMobile(user.getMobile());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        this.userPersistence.create(user);
    }

    public Stream<User> readAll(Role roleClaim) {
        return this.userPersistence.findByRoleIn(authorizedRoles(roleClaim));
    }

    private List<Role> authorizedRoles(Role roleClaim) {
        if (Role.ADMIN.equals(roleClaim)) {
            return List.of(Role.ADMIN, Role.MANAGER, Role.OPERATOR, Role.CUSTOMER);
        } else if (Role.MANAGER.equals(roleClaim)) {
            return List.of(Role.MANAGER, Role.OPERATOR, Role.CUSTOMER);
        } else if (Role.OPERATOR.equals(roleClaim)) {
            return List.of(Role.CUSTOMER);
        } else {
            return List.of();
        }
    }

    private void assertNoExistByMobile(String mobile) {
        if (this.userPersistence.readByMobile(mobile).isPresent()) {
            throw new ConflictException("The mobile already exists: " + mobile);
        }
    }

    public Stream<User> findByMobileAndFirstNameAndFamilyNameAndEmailAndDniContainingNullSafe(
            String mobile, String firstName, String familyName, String email, String dni, Role roleClaim) {
        return this.userPersistence.findByMobileAndFirstNameAndFamilyNameAndEmailAndDniContainingNullSafe(
                mobile, firstName, familyName, email, dni, this.authorizedRoles(roleClaim)
        );
    }

    public User findByMobileAssured(String mobile) {
        return this.userPersistence.readByMobile(mobile)
                .orElseThrow(() -> new NotFoundException("The mobile don't exist: " + mobile));
    }
}

package es.upm.miw.rest_controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Qualifier("miw.users")
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String PASSWORD = "123456";

    @Override
    public UserDetails loadUserByUsername(final String username) {
        // Se accede a UserDao para buscar el usuario y obtener su clave y roles
        if ("user".equals(username)) {
            return this.userBuilder(username, new BCryptPasswordEncoder().encode(PASSWORD), "USER");
        } else if ("manager".equals(username)) {
            return this.userBuilder(username, new BCryptPasswordEncoder().encode(PASSWORD), "MANAGER");
        } else if ("admin".equals(username)) {
            return this.userBuilder(username, new BCryptPasswordEncoder().encode(PASSWORD),
                    "USER", "MANAGER", "ADMIN");
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    private User userBuilder(String username, String password, String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return new User(username, password, true, true, true,
                true, authorities);
    }
}

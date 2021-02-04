package es.upm.miw.betca_rest;

import es.upm.miw.betca_rest.http_errors.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service("reactiveUserDetailsService")
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    // @Autowired private UserRepository userRepository;

    @Override
    public Mono< UserDetails > findByUsername(String username) {
        List< GrantedAuthority > authorities = new ArrayList<>();
        if ("customer".equals(username)) {  // Se accede a UserDao
            authorities.add(new SimpleGrantedAuthority(Role.CUSTOMER.withPrefix()));
        } else if ("operator".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.OPERATOR.withPrefix()));
        } else if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.withPrefix()));
        } else {
            return Mono.error(new UsernameNotFoundException("Username not found"));
        }
        return Mono.just(org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities(authorities)
                .build());
    }
}

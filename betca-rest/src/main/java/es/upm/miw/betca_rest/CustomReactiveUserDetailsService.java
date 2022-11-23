package es.upm.miw.betca_rest;

import es.upm.miw.betca_rest.http_errors.Role;
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
    public Mono<UserDetails> findByUsername(String mobile) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("1".equals(mobile)) {  // userRepository.findByMobile(mobile)
            authorities.add(new SimpleGrantedAuthority(Role.CUSTOMER.withPrefix()));
        } else if ("2".equals(mobile)) {
            authorities.add(new SimpleGrantedAuthority(Role.OPERATOR.withPrefix()));
        } else if ("3".equals(mobile)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.withPrefix()));
        } else {
            return Mono.error(new UsernameNotFoundException("Mobile not found: '" + mobile + "'"));
        }
        return Mono.just(org.springframework.security.core.userdetails.User.builder()
                .username(mobile)
                .password(new BCryptPasswordEncoder().encode("123456")) // DataBase password is encoded
                .authorities(authorities)
                .build());
    }
}

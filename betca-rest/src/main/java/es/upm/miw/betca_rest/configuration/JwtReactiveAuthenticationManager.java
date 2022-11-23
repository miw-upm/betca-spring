package es.upm.miw.betca_rest.configuration;

import es.upm.miw.betca_rest.http_errors.Role;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.List;

public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private JwtService jwtService;

    public JwtReactiveAuthenticationManager(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        GrantedAuthority authority = new SimpleGrantedAuthority(Role.PREFIX + jwtService.role(token));
        return Mono.just(new UsernamePasswordAuthenticationToken(jwtService.mobile(token), token, List.of(authority)));
    }
}

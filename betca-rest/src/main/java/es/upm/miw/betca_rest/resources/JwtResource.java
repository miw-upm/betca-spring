package es.upm.miw.betca_rest.resources;

import es.upm.miw.betca_rest.configuration.JwtService;
import es.upm.miw.betca_rest.http_errors.ForbiddenException;
import es.upm.miw.betca_rest.http_errors.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@RestController
@RequestMapping(JwtResource.JWT)
@PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR') OR hasRole('CUSTOMER')")
public class JwtResource {
    public static final String JWT = "/jwt";
    public static final String ID_ID = "/{id}";

    private final JwtService jwtService;

    @Autowired
    public JwtResource(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("authenticated")
    @PostMapping
    public Mono<TokenDto> login(@AuthenticationPrincipal User activeUser) {
        String mobile = activeUser.getUsername();
        if ("1".equals(mobile)) {
            return Mono.just(new TokenDto(this.jwtService.createToken(mobile, "customer", Role.CUSTOMER.name())));
        } else if ("2".equals(mobile)) {
            return Mono.just(new TokenDto(this.jwtService.createToken(mobile, "operator", Role.OPERATOR.name())));
        } else if ("3".equals(mobile)) {
            return Mono.just(new TokenDto(this.jwtService.createToken(mobile, "admin", Role.ADMIN.name())));
        } else {
            return Mono.error(new ForbiddenException("Mobile not found"));
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ID_ID)
    public Mono<Dto> read(@PathVariable(value = "id") int id) {
        return Mono.just(new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')") // more priority
    @PutMapping(ID_ID)
    public Mono<Dto> update(@PathVariable int id, @RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> create: " + dto);
        return Mono.just(dto);
    }

}

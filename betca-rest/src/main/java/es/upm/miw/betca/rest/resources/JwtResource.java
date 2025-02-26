package es.upm.miw.betca.rest.resources;

import es.upm.miw.betca.rest.configuration.JwtService;
import es.upm.miw.betca.rest.exceptionhandler.ForbiddenException;
import es.upm.miw.betca.rest.exceptionhandler.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
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
        return switch (mobile) {
            case "1" -> Mono.just(new TokenDto(this.jwtService.createToken(mobile, "customer", Role.CUSTOMER.name())));
            case "2" -> Mono.just(new TokenDto(this.jwtService.createToken(mobile, "operator", Role.OPERATOR.name())));
            case "3" -> Mono.just(new TokenDto(this.jwtService.createToken(mobile, "admin", Role.ADMIN.name())));
            case null, default -> Mono.error(new ForbiddenException("Mobile not found"));
        };
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ID_ID)
    public Mono<Dto> read(@PathVariable int id) {
        ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .doOnNext(auth -> System.out.println(">>>>>>>>>>>>>>>"+String.valueOf(auth)))
                .then();
        return Mono.just(new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')") // more priority
    @PutMapping(ID_ID)
    public Mono<Dto> update(@PathVariable int id, @RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info(()->"===>>> create: " + dto);
        return Mono.just(dto);
    }

}

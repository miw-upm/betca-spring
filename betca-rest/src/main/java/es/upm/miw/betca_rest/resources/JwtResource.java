package es.upm.miw.betca_rest.resources;

import org.apache.logging.log4j.LogManager;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(ID_ID)
    public Mono<Dto> read(@PathVariable(value = "id") int id) {
        return Mono.just(new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @PostMapping
    public Mono<Dto> create(@RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> create: " + dto);
        return Mono.just(dto);
    }

}

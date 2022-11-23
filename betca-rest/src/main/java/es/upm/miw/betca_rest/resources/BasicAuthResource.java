package es.upm.miw.betca_rest.resources;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR') OR hasRole('CUSTOMER')")
@RestController
@RequestMapping(BasicAuthResource.BASIC_AUTH)
public class BasicAuthResource {
    public static final String BASIC_AUTH = "/basic-auth";
    public static final String ID_ID = "/{id}";

    @PreAuthorize("authenticated") // more priority
    @SecurityRequirement(name = "basicAuth")  //Open API
    @GetMapping(ID_ID)
    public Mono<Dto> read(@PathVariable(value = "id") int id) {
        return Mono.just(new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN));
    }

    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    public Mono<Dto> create(@RequestBody Dto dto) {
        return Mono.just(dto);
    }

}

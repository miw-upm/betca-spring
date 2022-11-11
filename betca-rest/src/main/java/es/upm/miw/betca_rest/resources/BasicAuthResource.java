package es.upm.miw.betca_rest.resources;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
@RestController
@RequestMapping(BasicAuthResource.BASIC_AUTH)
public class BasicAuthResource {
    public static final String BASIC_AUTH = "/basic-auth";
    public static final String ID_ID = "/{id}";

    @PreAuthorize("authenticated")
    @GetMapping(ID_ID)
    public Mono<Dto> read(@PathVariable(value = "id") int id) {
        return Mono.just(new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now()));
    }

    @PostMapping
    public Mono<Dto> create(@RequestBody Dto dto) {
        return Mono.just(dto);
    }

}

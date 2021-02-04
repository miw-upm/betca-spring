package es.upm.miw.betca_rest.resources;

import es.upm.miw.betca_rest.http_errors.NotFoundException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(ExceptionResource.EXCEPTIONS)
public class ExceptionResource {

    public static final String EXCEPTIONS = "/exceptions";
    public static final String ID_ID = "/{id}";

    @GetMapping(ID_ID)
    public Mono<ValidatedDto> read(@PathVariable int id) {
        if (id < 1) {
            return Mono.error(new NotFoundException("id:" + id));
        } else {
            return Mono.just(new ValidatedDto(id, "daemon", Gender.FEMALE, LocalDateTime.now()));
        }
    }

    @PostMapping
    public Mono< ValidatedDto > create(@Valid @RequestBody ValidatedDto dto) {
        return Mono.just(dto);
    }
}
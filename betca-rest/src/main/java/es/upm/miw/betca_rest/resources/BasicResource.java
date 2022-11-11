package es.upm.miw.betca_rest.resources;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping(BasicResource.BASIC)
public class BasicResource {
    public static final String BASIC = "/basic";
    public static final String ID_ID = "/{id}";
    public static final String SEARCH = "/search";

    @PostMapping
    public Mono<Dto> create(@RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> create: " + dto);
        return Mono.just(dto);
    }

    @GetMapping(ID_ID)
    public Mono<Dto> read(@PathVariable(value = "id") int id) {
        return Mono.just(new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now()));
    }

    @PutMapping(ID_ID)
    public Mono<Dto> update(@PathVariable(value = "id") int id, @RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> update: " + id + ", " + dto);
        return Mono.just(dto);
    }

    @DeleteMapping(ID_ID)
    public Mono<Void> delete(@PathVariable(value = "id") int id) {
        LogManager.getLogger(this.getClass()).info("===>>> delete: " + id);
        return Mono.empty();
    }

    @GetMapping(SEARCH)
    public Flux<Dto> findByName(@RequestParam String name) {
        return Flux.just(
                new Dto(1, name, Gender.MALE, LocalDateTime.now()),
                new Dto(2, name, Gender.FEMALE, LocalDateTime.now()),
                new Dto(3, name, Gender.MALE, LocalDateTime.now())
        );
    }
}

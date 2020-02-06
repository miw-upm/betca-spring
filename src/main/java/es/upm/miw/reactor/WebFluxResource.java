package es.upm.miw.reactor;

import es.upm.miw.rest_controllers.Dto;
import es.upm.miw.rest_controllers.exceptions.BadRequestException;
import es.upm.miw.rest_controllers.exceptions.ConflictException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping(WebFluxResource.WEB_FLUX)
public class WebFluxResource {

    public static final String WEB_FLUX = "/web-flux";
    public static final String MONO = "/mono";
    public static final String MONO_EMPTY = "/mono-empty";
    public static final String MONO_ERROR = "/mono-error";
    public static final String FLUX = "/flux";
    public static final String FLUX_EMPTY = "/flux-empty";
    public static final String FLUX_ERROR = "/flux-error";

    @GetMapping(value = MONO)
    public Mono<Dto> readMono() {
        return Mono.just(new Dto(666));
    }

    @GetMapping(value = MONO_EMPTY)
    public Mono<Dto> readMonoEmpty() {
        return Mono.empty();
    }

    @GetMapping(value = MONO_ERROR)
    public Mono<Dto> readMonoError() {
        return Mono.error(new BadRequestException("Error details"));
    }

    @GetMapping(value = FLUX)
    public Flux<Dto> readFlux() {
        return Flux.interval(Duration.ofMillis(100)).map(value -> new Dto(value.intValue())).take(5);
    }

    @GetMapping(value = FLUX_EMPTY)
    public Flux<Dto> readFluxEmpty() {
        return Flux.empty();
    }

    @GetMapping(value = FLUX_ERROR)
    public Flux<Dto> readFluxError() {
        return Flux.interval(Duration.ofMillis(100)).map(value -> new Dto(value.intValue())).take(2)
                .concatWith(Mono.error(new ConflictException("flux error")));
    }

}

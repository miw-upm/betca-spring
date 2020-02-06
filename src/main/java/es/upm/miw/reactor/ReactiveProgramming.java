package es.upm.miw.reactor;

import org.apache.logging.log4j.LogManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class ReactiveProgramming {

    public BigDecimal mapFromStringToBigDecimal(String value) {
        return new BigDecimal(value);
    }

    public Mono<BigDecimal> mapMonoFromStringToBigDecimal(Mono<String> mono) {
        return mono.map(BigDecimal::new);
    }

    public Flux<BigDecimal> mapFluxFromStringToBigDecimal(Flux<String> flux) {
        return flux.map(BigDecimal::new);
    }

    public Mono<BigDecimal> mapMonoFromStringToBigDecimalMas099(Mono<String> mono) {
        return mono.map(s -> new BigDecimal(s + ".99"));
    }

    public Flux<Integer> filterEven(Flux<Integer> flux) {
        return flux.filter(n -> n % 2 == 0);
    }

    public Flux<Integer> logs(Flux<Integer> flux) {
        return flux.doOnNext(LogManager.getLogger(this.getClass())::debug);
    }

    public Flux<Integer> filterPositive(Flux<Integer> flux) {
        return flux.handle((n, sink) -> {
            if (n < 0) sink.error(new RuntimeException("..."));
            else sink.next(n);
        });
    }

    public Mono<Integer> monoNoEmpty(Mono<Integer> mono) {
        return mono.switchIfEmpty(Mono.just(0));
    }
}

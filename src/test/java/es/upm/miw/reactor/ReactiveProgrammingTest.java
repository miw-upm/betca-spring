package es.upm.miw.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;

class ReactiveProgrammingTest {

    @Test
    void testMapMonoFromStringToBigDecimal() {
        Mono<BigDecimal> mono = new ReactiveProgramming().mapMonoFromStringToBigDecimal(Mono.just("10"));
        System.out.println("mono: " + mono);
        System.out.println("mono.block(): " + mono.block());
        System.out.print("mono.subscribe(): ");
        mono.subscribe(System.out::println);
        mono.block(); //Bloquea la ejecución hasta que finaliza el mono para poder probar el test
    }

    @Test
    void testMapFluxFromStringToBigDecimal() {
        Flux<BigDecimal> flux = new ReactiveProgramming()
                .mapFluxFromStringToBigDecimal(Flux.interval(Duration.ofMillis(1)).map(String::valueOf)).take(5);
        flux.subscribe(System.out::println);
        System.out.println("flux: " + flux);
        flux.blockLast(); //Bloquea la ejecución hasta que finaliza el flux para poder probar el test
    }

    @Test
    void testMapMonoFromStringToBigDecimalMas099() {
        System.out.println("Mas099: "
                + new ReactiveProgramming().mapMonoFromStringToBigDecimalMas099(Mono.just("10")).block());
    }

    @Test
    void testFilterEven() {
        Flux<Integer> flux = new ReactiveProgramming().filterEven(Flux.just(1, 4, 7, 8, 13));
        flux.subscribe(System.out::println);
        flux.blockLast(); //Bloquea la ejecución hasta que finaliza el flux
    }

    @Test
    void TestLogsError() {
        Flux<Integer> flux = new ReactiveProgramming().logs(Flux.just(1, 4, 7, 8, 13));
        System.out.println("flux: " + flux);
    }

    @Test
    void TestLogs() {
        Flux<Integer> flux = new ReactiveProgramming().logs(Flux.just(1, 4, 7, 8, 13));
        System.out.println("flux: " + flux);
        flux.blockLast();
    }

    @Test
    void testMapFluxFromStringToBigDecimalStepVerifier() {
        StepVerifier
                .create(new ReactiveProgramming()
                        .mapFluxFromStringToBigDecimal(
                                Flux.interval(Duration.ofMillis(1)).map(String::valueOf)).take(5))
                .expectNext(BigDecimal.ZERO, BigDecimal.ONE)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    void testFilterPositive() {
        StepVerifier
                .create(new ReactiveProgramming().filterPositive(Flux.just(1, 4, -7, 8, 13)))
                .expectNextCount(2)
                .expectError()
                .verify();
    }

    @Test
    void testMonoNoEmptyWithMonoEmpty() {
        StepVerifier
                .create(new ReactiveProgramming().monoNoEmpty(Mono.empty()))
                .expectNext(0)
                .expectComplete()
                .verify();
    }

    @Test
    void testMonoNoEmptyWithMono() {
        StepVerifier
                .create(new ReactiveProgramming().monoNoEmpty(Mono.just(1)))
                .expectNext(1)
                .expectComplete()
                .verify();
    }

}

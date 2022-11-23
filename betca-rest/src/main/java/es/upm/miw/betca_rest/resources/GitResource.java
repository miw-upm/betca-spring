package es.upm.miw.betca_rest.resources;

import es.upm.miw.betca_rest.http_errors.BadGatewayException;
import es.upm.miw.betca_rest.http_errors.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@RestController
@RequestMapping(GitResource.GIT)
public class GitResource {
    public static final String GIT = "/git";

    public static final String GIT_URI = "https://api.github.com/repos/miw-upm/spring-practice/issues/1";

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public GitResource(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping
    public Mono<String> read() {
        return webClientBuilder.build()
                .mutate().filter(basicAuthentication("miw-upm", "kkk")).build()
                .post()
                .uri(GIT_URI)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorMap(Exception.class, exception -> new BadGatewayException("Unexpected error: " + exception.getClass() + " - " + exception.getMessage()));
    }

}

package es.upm.miw.betca.rest.resources;

import es.upm.miw.betca.rest.exceptionhandler.BadGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(GitResource.GIT)
public class GitResource {
    public static final String GIT = "/git";
    public static final String GIT_URI = "https://api.github.com/repos/miw-upm/spring-practice/issues/1";

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<String> read() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("miw-upm", "kkk");
        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    GIT_URI,
                    HttpMethod.GET,
                    request,
                    String.class
            );
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            throw new BadGatewayException("Unexpected error: " + e.getClass() + " - " + e.getMessage());
        }
    }
}

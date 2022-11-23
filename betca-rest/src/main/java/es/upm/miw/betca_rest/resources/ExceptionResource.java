package es.upm.miw.betca_rest.resources;

import es.upm.miw.betca_rest.http_errors.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(ExceptionResource.EXCEPTIONS)
public class ExceptionResource {

    public static final String EXCEPTIONS = "/exceptions";
    public static final String ID_ID = "/{id}";

    @GetMapping(ID_ID)
    public ValidatedDto read(@PathVariable int id) {
        if (id < 1) {
            throw new NotFoundException("id:" + id);
        }
        return new ValidatedDto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
    }

    @PostMapping
    public ValidatedDto create(@Valid @RequestBody ValidatedDto dto) {
        return dto;
    }
}
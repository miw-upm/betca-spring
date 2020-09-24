package es.upm.miw.rest_controllers;

import es.upm.miw.persistence.jpa.entities.Gender;
import es.upm.miw.rest_controllers.exceptions.FieldAlreadyExistException;
import es.upm.miw.rest_controllers.exceptions.FieldInvalidException;
import es.upm.miw.rest_controllers.exceptions.MalformedHeaderException;
import es.upm.miw.rest_controllers.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ExceptionResource.EXCEPTIONS)
public class ExceptionResource {

    public static final String EXCEPTIONS = "/exceptions";

    public static final String MY_FILTER = "/my-filter";

    public static final String OUT_OF_TIME = "/out-of-time";

    public static final String ID_ID = "/{id}";

    public static final String ERROR = "/error";

    @GetMapping(value = ERROR + ID_ID)
    public Dto doError(@RequestHeader String token, @PathVariable int id, @RequestParam String param) {
        if (token.equals("kk")) {
            throw new MalformedHeaderException("token:" + token);
        }

        if (param.isEmpty()) {
            throw new FieldInvalidException("param: " + param);
        }
        if (param.equals("kk")) {
            throw new FieldAlreadyExistException("param: " + param);
        }

        return new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
    }

    @GetMapping(value = MY_FILTER)
    public String myFilter() {
        return "{\"state\":\"my-filter\"}";
    }

    @GetMapping(value = OUT_OF_TIME)
    public String outOfTime() {
        return "{\"state\":\"off\"}";
    }

}

package miw.restControllers;

import miw.persistence.jpa.entities.Gender;
import miw.restControllers.exceptions.FieldAlreadyExistException;
import miw.restControllers.exceptions.FieldInvalidException;
import miw.restControllers.exceptions.MalformedHeaderException;
import miw.restControllers.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ExceptionResource.EXCEPTIONS)
public class ExceptionResource {

    public static final String EXCEPTIONS = "/exceptions";


    public static final String MY_FILTER = "/my-filter";

    public static final String OUT_OF_TIME = "/out-of-time";

    public static final String ID = "/{id}";

    public static final String ERROR = "/error";

    @GetMapping(value = MY_FILTER)
    public String myFilter() {
        return "{\"state\":\"my-filter\"}";
    }

    @GetMapping(value = OUT_OF_TIME)
    public String outOfTime() {
        return "{\"state\":\"off\"}";
    }

    @GetMapping(value = ERROR + ID)
    public Dto error(@RequestHeader String token, @PathVariable int id, @RequestParam String param) {

        if (token.equals("kk")) {
            throw new MalformedHeaderException("token:" + token);
        }
        if (id == 0) {
            throw new NotFoundException("id:" + id);
        }
        if (param.isEmpty()) {
            throw new FieldInvalidException("param: " + param);
        }
        if (param.equals("kk")) {
            throw new FieldAlreadyExistException("param: " + param);
        }

        return new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
    }

}

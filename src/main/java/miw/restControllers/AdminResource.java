package miw.restControllers;

import miw.persistence.jpa.entities.Gender;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AdminResource.ADMINS)
public class AdminResource {

    public static final String ADMINS = "/admins";


    public static final String STATE = "/state";

    public static final String OUT_OF_TIME = "/out-of-time";

    public static final String ECHO = "/echo";

    public static final String ID = "/{id}";

    public static final String BODY = "/body";

    public static final String STRING_LIST = "/string-list";

    public static final String DTO_LIST = "/dto-list";

    public static final String ERROR = "/error";

    // Se puede comprobar con un navegador
    @GetMapping(value = STATE)
    public String state() {
        return "{\"state\":\"ok\"}";
    }

    @GetMapping(value = OUT_OF_TIME)
    public String outOfTime() {
        return "{\"state\":\"off\"}";
    }

    // Intercambio de datos
    @GetMapping(value = ECHO + ID)
    public String echo(@RequestHeader(value = "token", required = false) String token, @PathVariable(value = "id") int id,
                       @RequestParam(defaultValue = "Non") String param) {
        String response = "{\"id\":%d,\"token\":\"%s\",\"param\":\"%s\"}";
        return String.format(response, id, token, param);
    }

    @PostMapping(value = BODY)
    public Dto body(@RequestBody Dto dto) {
        return dto;
    }

    @GetMapping(value = BODY + STRING_LIST)
    public List<String> bodyStringList() {
        return Arrays.asList("uno", "dos", "tres");
    }

    //@Time
    @GetMapping(value = BODY + DTO_LIST)
    public List<Dto> bodyDtoList() {
        Dto dto1 = new Dto(666, "daemon", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
        Dto dto2 = new Dto(999, "last", Gender.MALE, new GregorianCalendar(1979, 07, 22));
        Dto dto3 = new Dto(000, "first", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
        return Arrays.asList(dto1, dto2, dto3);
    }

    // Excepciones
    @GetMapping(value = ERROR + ID)
    public Dto error(@RequestHeader(value = "token") String token, @PathVariable(value = "id") int id)
            throws NotFoundUserIdException, UnauthorizedException, MalformedHeaderException {
        if (id == 0) {
            throw new NotFoundUserIdException("id:" + id);
        }
        if (token.equals("kk")) {
            throw new MalformedHeaderException("token:" + token);
        }
        if (token.equals("Basic kk")) {
            throw new UnauthorizedException("token:" + token);
        }
        return new Dto(666, "daemon", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
    }

}

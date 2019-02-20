package miw.restControllers;

import miw.persistence.jpa.entities.Gender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(AdminResource.ADMINS)
public class AdminResource {

    public static final String ADMINS = "/admins";

    public static final String STATE = "/state";

    public static final String ECHO = "/echo";

    public static final String ID = "/{id}";

    public static final String BODY = "/body";

    public static final String STRING_LIST = "/string-list";

    public static final String DTO_LIST = "/dto-list";

    // Se puede comprobar con un navegador
    @GetMapping(value = STATE)
    public String state() {
        return "{\"state\":\"ok\"}";
    }

    // Intercambio de datos
    @GetMapping(value = ECHO + ID)
    public String echo(@RequestHeader(value = "token", required = false) String token,
                       @PathVariable(value = "id") int id,
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
        Dto dto1 = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto dto2 = new Dto(999, "last", Gender.MALE, LocalDateTime.now());
        Dto dto3 = new Dto(0, "first", Gender.FEMALE, LocalDateTime.now());
        return Arrays.asList(dto1, dto2, dto3);
    }

    @PutMapping(value = ECHO + ID)
    public Dto update(@PathVariable Integer id, @RequestBody Dto dto) {
        dto.setId(id);
        return dto;
    }

}

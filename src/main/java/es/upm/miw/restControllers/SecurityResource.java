package es.upm.miw.restControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(SecurityResource.SECURITY)
public class SecurityResource {

    public static final String SECURITY = "/security";

    public static final String ALL = "/all";

    public static final String USER = "/user";

    public static final String MANAGER = "/manager";

    public static final String ADMIN = "/admin";

    @GetMapping(value = ALL)
    public String all() {
        return "OK. Acceso permitido al recurso all";
    }

    @PreAuthorize("hasRole('USER') OR hasRole('MANAGER')")
    @GetMapping(value = USER)
    public String user() {
        return "OK. Acceso permitido al recurso user";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(value = MANAGER)
    public String manager() {
        return "OK. Acceso permitido al recurso manager";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ADMIN)
    public String admin() {
        return "OK. Acceso permitido al recurso admin";
    }

}

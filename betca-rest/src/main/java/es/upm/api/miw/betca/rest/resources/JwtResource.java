package es.upm.api.miw.betca.rest.resources;

import es.upm.api.miw.betca.rest.configuration.JwtService;
import es.upm.api.miw.betca.rest.exceptionhandler.ForbiddenException;
import es.upm.api.miw.betca.rest.exceptionhandler.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@RestController
@RequestMapping(JwtResource.JWT)
@PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR') OR hasRole('CUSTOMER')")
public class JwtResource {
    public static final String JWT = "/jwt";
    public static final String ID_ID = "/{id}";

    private final JwtService jwtService;

    @Autowired
    public JwtResource(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("authenticated")
    @PostMapping
    public TokenDto login(@AuthenticationPrincipal User activeUser) {
        if (activeUser == null || activeUser.getUsername() == null) {
            throw new ForbiddenException("Mobile not found");
        }
        String mobile = activeUser.getUsername();
        return switch (mobile) {
            case "1" -> new TokenDto(this.jwtService.createToken(mobile, "customer", Role.CUSTOMER.name()));
            case "2" -> new TokenDto(this.jwtService.createToken(mobile, "operator", Role.OPERATOR.name()));
            case "3" -> new TokenDto(this.jwtService.createToken(mobile, "admin", Role.ADMIN.name()));
            default -> null;
        };
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(ID_ID)
    public Dto read(@PathVariable int id, @AuthenticationPrincipal User activeUser) {
        return new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')") // more priority
    @PutMapping(ID_ID)
    public Dto update(@PathVariable int id, @RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info(() -> "===>>> create: " + dto);
        return dto;
    }

}

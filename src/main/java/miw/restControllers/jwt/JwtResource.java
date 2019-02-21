package miw.restControllers.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(JwtResource.JWTS)
public class JwtResource {

    public static final String JWTS = "/jwts";

    public static final String TOKEN = "/token";

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("authenticated")
    @PostMapping(value = TOKEN)
    public String login(@AuthenticationPrincipal User activeUser) {
        List<String> roleList = activeUser.getAuthorities().stream().map
                (authority -> authority.getAuthority()).collect(Collectors.toList());
        return jwtService.createToken(activeUser.getUsername(), roleList);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String verify() {
        return "OK. permitido JWT";
    }
}

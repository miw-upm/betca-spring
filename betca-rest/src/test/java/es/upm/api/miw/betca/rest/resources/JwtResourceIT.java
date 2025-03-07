package es.upm.api.miw.betca.rest.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class JwtResourceIT {

    @Autowired
    private JwtResource jwtResource;


    @Test
    @WithMockUser(username = "8", roles = {"ANONYMOUS"})
    void testUpdateNoAuthenticated() {
        Dto dto = new Dto();
        assertThrows(AuthorizationDeniedException.class, () -> this.jwtResource.update(1, dto));
    }

    @Test
    @WithMockUser(username = "1", roles = {"CUSTOMER"})
    void testUpdateForbidden() {
        assertThrows(AuthorizationDeniedException.class, () -> this.jwtResource.update(1, null));
    }

    @Test
    @WithMockUser(username = "2", roles = {"OPERATOR"})
    void testUpdate() {
        assertNotNull(this.jwtResource.update(1, new Dto()));
    }


}

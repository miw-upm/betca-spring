package miw.injection;

import miw.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class InjectionMainIT {

    @Autowired
    private InjectionMain injectionMain;

    @Test
    public void testGetMessage() {
        assertTrue(this.injectionMain.getMessage().length() > 0);
        System.out.println(">>> message: " + this.injectionMain.getMessage());
    }
}

package miw.injection;

import miw.TestConfig;
import org.apache.logging.log4j.LogManager;
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
        LogManager.getLogger(this.getClass()).info("===>>> message: " + this.injectionMain.getMessage());
    }
}

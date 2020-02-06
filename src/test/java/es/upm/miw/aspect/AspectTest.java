package es.upm.miw.aspect;

import es.upm.miw.TestConfig;
import es.upm.miw.aspect_target.ServiceOne;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@TestConfig
public class AspectTest {

    @Autowired
    private ServiceOne serviceOne;

    @Test
    public void methodTest() {
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
        serviceOne.method();
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void argStringTest() {
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
        serviceOne.argString("string");
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void returnIntTest() {
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
        serviceOne.returnInt();
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void exceptionTest() {
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
        try {
            serviceOne.exception();
        } catch (IOException e) {
            LogManager.getLogger(getClass()).debug(">>>>>>>>>> Se trata la exception");
        }
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void annotationTest() {
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
        serviceOne.annotation();
        LogManager.getLogger(getClass()).debug("------------------------- ooo -------------------------------------");
    }
}

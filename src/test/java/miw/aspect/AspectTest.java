package miw.aspect;

import miw.TestConfig;
import miw.aspectTarget.ServiceOne;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class AspectTest {

    @Autowired
    private ServiceOne serviceOne;

    @Test
    public void methodTest() {
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
        serviceOne.method();
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void argStringTest() {
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
        serviceOne.argString("cadena");
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void returnIntTest() {
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
        serviceOne.returnInt();
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void exceptionTest() {
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
        try {
            serviceOne.exception();
        } catch (Exception e) {
            LogManager.getLogger("miw").debug(">>>>>>>>>> Se trata la exception");
        }
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
    }

    @Test
    public void annotationTest() {
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
        serviceOne.annotation();
        LogManager.getLogger("miw").debug("------------------------- ooo -------------------------------------");
    }
}

package miw.injection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingletonMessageServiceWithoutInjectionTest {

    private SingletonMessageService singletonMessageService;

    @BeforeEach
    public void before() {
        this.singletonMessageService = new SingletonMessageService();
    }

    @Test
    public void testGetMessage() {
        assertTrue(this.singletonMessageService.getMessage().length() > 0);
        System.out.println(">>> NOTE, without injection");
        System.out.println(">>> message: " + this.singletonMessageService.getMessage());
    }
}

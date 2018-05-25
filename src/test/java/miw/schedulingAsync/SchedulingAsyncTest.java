package miw.schedulingAsync;


import miw.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.Future;

@TestConfig
public class SchedulingAsyncTest {

    @Autowired
    private SchedulingAsync async;

    @Test
    public void asyncMethod() throws InterruptedException {
        LogManager.getLogger("miw.Asynchronous").info("Antes de llamada asincrona...");
        Future<String> future = this.async.asyncMethodWithReturnType();
        LogManager.getLogger("miw.Asynchronous").info("... Despues de llamada asincrona, future:" + future.isDone());
        LogManager.getLogger("miw.Asynchronous").info("Se duerme el proceso 10 segundos zzzZZZ");
        Thread.sleep(10000);
        LogManager.getLogger("miw.Asynchronous").info("Despues de los 10 segundos, future:" + future.isDone());
    }
}
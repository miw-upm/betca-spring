package miw.schedulingAsync;

import org.apache.logging.log4j.LogManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class SchedulingAsync {

    // Segundo, Minuto, Hora, Día del Mes, Mes, Día de la semana
    // (*) Cualquier valor, (/X) cada X, (?) sin especificar
    public static final String CADA_MINUTO = "0 * * * * *";

    public static final String CADA_2_SEGUNDOS = "*/2 * * * * *";

    public static final String CADA_MEDIA_NOCHE = "0 0 0 * * *";

    public static final String CADA_NAVIDAD = "0 0 0 25 12 ?";

    public static final String A_LAS_8_9_10_EN_PUNTO = "0 0 8-10 * * *";

    public static final String A_LAS_9_DIAS_LABORABLES = "0 0 9 * * MON-FRI";

    @Scheduled(cron = CADA_2_SEGUNDOS)
    public void schedule() {
        LogManager.getLogger(this.getClass().getName()).info("scheduled...CADA_2_SEGUNDOS");
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("Completado");
        } catch (InterruptedException e) {
        }
        return new AsyncResult<String>("ERROR!!!!");
    }

    @Async
    public void asyncMethod() {
        // Asynchronous execute
    }

}

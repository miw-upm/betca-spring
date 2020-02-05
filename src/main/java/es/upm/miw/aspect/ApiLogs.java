package es.upm.miw.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Profile("dev")
@Component
@Aspect
public class ApiLogs {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void allResources() {
        // don't need code
    }

    private String cut(String log) {
        if (log.length() > 1000) {
            log = log.substring(0, 1000) + ".... (+" + log.length() + " characters)";
        }
        return log;
    }

    @Before("allResources()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(this.getClass())
                .info("------------------------------- o -------------------------------");
        StringBuilder log = new StringBuilder(jp.getSignature().getName() + " >>>");
        for (Object arg : jp.getArgs()) {
            log.append(" ARG: ").append(arg);
        }
        LogManager.getLogger(this.getClass()).info(log);
    }

    @AfterReturning(pointcut = "allResources()", returning = "returnValue")
    public void apiResponseLog(JoinPoint jp, Object returnValue) {
        String response = "<<< Return << " + jp.getSignature().getName() + ": ";
        Consumer<Object> consumer = result -> {
            LogManager.getLogger(this.getClass()).debug(this.cut(response + result));
        };
        Consumer<Throwable> error = throwable -> {
            String log = "<<< EXCEPTION " + response + throwable;
            LogManager.getLogger(this.getClass()).debug(log);
        };

        if (returnValue != null) {
            String result = returnValue.getClass().getSimpleName();
            if ("MonoIgnoreElements".equals(result)) {
                LogManager.getLogger(this.getClass()).debug(response, "Mono<Void>");
            } else if (result.length() > 3 && "Flux".equals(result.substring(0, 4))) {
                ((Flux<Object>) returnValue).subscribe(consumer, error);
            } else if (result.length() > 3 && "Mono".equals(result.substring(0, 4))) {
                ((Mono<Object>) returnValue).subscribe(consumer, error);
            } else {
                try {
                    result = new ObjectMapper().writeValueAsString(returnValue);
                } catch (JsonProcessingException e) {
                    result = returnValue.toString();
                }
                LogManager.getLogger(this.getClass()).debug(cut(response + result));
            }
        } else {
            LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).debug(response, "null");
        }
    }

    @AfterThrowing(pointcut = "allResources()", throwing = "exception")
    public void apiResponseExceptionLog(JoinPoint jp, Exception exception) {
        String log = "<<< Return << " + jp.getSignature().getName() + " <<< EXCEPTION << "
                + exception.getClass().getSimpleName() + "->" + exception.getMessage();
        LogManager.getLogger(this.getClass()).info(log);
    }


}

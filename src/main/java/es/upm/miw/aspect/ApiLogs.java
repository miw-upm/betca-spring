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

    private StringBuilder log = new StringBuilder();

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void allResources() {
        // don't need code
    }

    private String addLog(String newLog) {
        this.log.append(newLog);
        int length = this.log.length();
        if (length > 1000) {
            this.log.delete(900, length);
            this.log.append(log.length()).append(" characters");
        }
        return this.log.toString();
    }

    private void resetLog() {
        this.log.setLength(0);
    }

    @Before("allResources()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(this.getClass())
                .info("------------------------------- o -------------------------------");
        this.resetLog();
        this.addLog(jp.getSignature().getName());
        this.addLog(" >>>");
        for (Object arg : jp.getArgs()) {
            this.addLog(String.format(" ARG: %s", arg));
        }
        LogManager.getLogger(this.getClass()).debug(this.log);
    }

    @AfterReturning(pointcut = "allResources()", returning = "returnValue")
    public void apiResponseLog(JoinPoint jp, Object returnValue) {
        this.resetLog();
        this.addLog(String.format("<<< Return << %s: ", this.addLog(jp.getSignature().getName())));
        Consumer<Throwable> error = throwable ->
                LogManager.getLogger(this.getClass()).debug(() -> this.addLog("<<< EXCEPTION " + throwable));
        Runnable complete = () -> LogManager.getLogger(this.getClass()).debug(() -> this.addLog(" (Flux-Mono Complete)"));
        if (returnValue != null) {
            String methodName = returnValue.getClass().getSimpleName();
            if (methodName.startsWith("Flux")) {
                ((Flux<Object>) returnValue).subscribe(result ->
                        this.addLog("\n" + result.toString()), error, complete);
            } else if (methodName.startsWith("Mono")) {
                ((Mono<Object>) returnValue).subscribe(result -> this.addLog(result.toString()), error, complete);
            } else {
                try {
                    this.addLog(new ObjectMapper().writeValueAsString(returnValue));
                } catch (JsonProcessingException e) {
                    this.addLog(returnValue.toString());
                }
                LogManager.getLogger(this.getClass()).debug(this.log);
            }
        } else {
            LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).debug(() -> this.addLog("null"));
        }
    }

    @AfterThrowing(pointcut = "allResources()", throwing = "exception")
    public void apiResponseExceptionLog(JoinPoint jp, Exception exception) {
        this.resetLog();
        this.addLog(String.format("<<< Return << %s <<< EXCEPTION << %s: %s"
                , jp.getSignature().getName(), exception.getClass().getSimpleName(), exception.getMessage()));
        LogManager.getLogger(this.getClass()).info(log);
    }

}

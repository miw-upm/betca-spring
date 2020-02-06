package es.upm.miw.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class ProcessTime {

    @Pointcut("@annotation(es.upm.miw.aspect.Time)")
    public void timeAnnotation() {
        //empty. Nothing to do
    }

    @Around("timeAnnotation()")
    public Object time(ProceedingJoinPoint pjp) throws Throwable {
        long initTime = new Date().getTime();
        Object obj = pjp.proceed();
        LogManager.getLogger(this.getClass()).debug(String.format("-----> ProcessTime (%s:%s): %d ms",
                pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), new Date().getTime() - initTime));
        return obj;
    }
}

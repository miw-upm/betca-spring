package miw.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {

    @Pointcut("execution(* * (..))")
    public void allMethods() {
    }

    @Pointcut("execution(public * *(..))")
    public void allMethodsPublic() {
    }

    @Pointcut("execution(* miw.aspectTarget.*.* (..))")
    public void allMethodsPackage() {
    }

    @Pointcut("within(miw.aspectTarget.*)")
    public void allMethodsInPackage() {
    }

    @Pointcut("within(miw.aspectTarget..*)")
    public void allMethodsInPackageAndSubPackage() {
    }

    // Consejos ------------------------------------------

    @Before("allMethodsPackage()")
    public void adviceA(JoinPoint jp) {
        LogManager.getLogger(this.getClass()).debug("=== Consejo Antes de ejecutar a metodos de un paquete: ");
    }

    @Before("@within(miw.aspect.MyClassAnnotation)")
    public void adviceD(JoinPoint jp) {
        LogManager.getLogger("miw." + jp.getSignature().getName())
                .debug("=== Consejo Antes de ejecutar metodos de una clase con GenericAnnotation: ");
    }

    @Before("@annotation(miw.aspect.MyMethodAnnotation)")
    public void adviceE(JoinPoint jp) {
        LogManager.getLogger("miw." + jp.getSignature().getName()).debug("=== Consejo Antes de ejecutar metodos con MethodAnnotation: ");
    }

    @AfterReturning(pointcut = "allMethodsInPackage()", returning = "result")
    public void adviceF(JoinPoint jp, int result) {
        LogManager.getLogger("miw." + jp.getSignature().getName())
                .debug("=== Consejo Despues de ejecutar metodos que devuelven un int: " + " return:" + result);
    }

    @AfterThrowing(pointcut = "allMethodsInPackage()", throwing = "exception")
    public void consejoJ(JoinPoint jp, Exception exception) {
        LogManager.getLogger("miw." + jp.getSignature().getName())
                .debug("=== Consejo Despues de ejecutar metodos que provocan una Exception: " + " return:" + exception);
    }

    @After("execution(* miw.aspectTarget.ServiceOne.exception())")
    public void consejoJ(JoinPoint jp) {
        LogManager.getLogger("miw." + jp.getSignature().getName()).debug("=== Consejo Despues de ejecutar metodos (finally)");
    }

    @Around("execution(* miw.aspectTarget.ServiceOne.method())")
    public Object consejoE(ProceedingJoinPoint pjp) throws Throwable {
        LogManager.getLogger("miw").debug("========== antes......");
        Object obj = pjp.proceed();
        LogManager.getLogger("miw").debug("========== ......despues");
        return obj;
    }

}

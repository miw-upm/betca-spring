package es.upm.miw.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {

    @Pointcut("execution(* * (..))")
    public void allMethods() {
        //empty. Nothing to do
    }

    @Pointcut("execution(public * *(..))")
    public void allMethodsPublic() {
        //empty. Nothing to do
    }

    @Pointcut("execution(* es.upm.miw.aspect_target.*.* (..))")
    public void allMethodsPackage() {
        //empty. Nothing to do
    }

    @Pointcut("within(es.upm.miw.aspect_target.*)")
    public void allMethodsInPackage() {
        //empty. Nothing to do
    }

    @Pointcut("within(es.upm.miw.aspect_target..*)")
    public void allMethodsInPackageAndSubPackage() {
        //empty. Nothing to do
    }

    // Consejos ------------------------------------------

    @Before("allMethodsPackage()")
    public void adviceA(JoinPoint jp) {
        LogManager.getLogger(this.getClass()).debug("=== Consejo Antes de ejecutar a metodos de un paquete: ");
    }

    @Before("@within(es.upm.miw.aspect.MyClassAnnotation)")
    public void adviceD(JoinPoint jp) {
        LogManager.getLogger(this.getClass()).debug("=== Consejo Antes de ejecutar metodos de una clase con GenericAnnotation: ");
    }

    @Before("@annotation(es.upm.miw.aspect.MyMethodAnnotation)")
    public void adviceE(JoinPoint jp) {
        LogManager.getLogger(this.getClass()).debug("=== Consejo Antes de ejecutar metodos con MethodAnnotation: ");
    }

    @AfterReturning(pointcut = "allMethodsInPackage()", returning = "result")
    public void adviceF(JoinPoint jp, int result) {
        LogManager.getLogger(this.getClass())
                .debug(() -> "=== Consejo Despues de ejecutar metodos que devuelven un int, return: " + result);
    }

    @AfterThrowing(pointcut = "allMethodsInPackage()", throwing = "exception")
    public void adviseJ(JoinPoint jp, Exception exception) {
        LogManager.getLogger(this.getClass())
                .debug(() -> "=== Consejo Despues de ejecutar metodos que provocan una Exception, return:" +
                        exception.getMessage());
    }

    @After("execution(* es.upm.miw.aspect_target.ServiceOne.exception())")
    public void adviseJ(JoinPoint jp) {
        LogManager.getLogger(this.getClass()).debug("=== Consejo Despues de ejecutar metodos (finally)");
    }

    @Around("execution(* es.upm.miw.aspect_target.ServiceOne.method())")
    public Object adviseE(ProceedingJoinPoint pjp) throws Throwable {
        LogManager.getLogger(this.getClass()).debug("========== antes......");
        Object obj = pjp.proceed();
        LogManager.getLogger(this.getClass()).debug("========== ......despues");
        return obj;
    }

}

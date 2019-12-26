package es.upm.miw.aspectTarget;

import es.upm.miw.aspect.MyClassAnnotation;
import es.upm.miw.aspect.MyMethodAnnotation;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

@Service
@MyClassAnnotation
public class ServiceOne {

    public void method() {
        LogManager.getLogger("miw").debug("-----------------> ServiceOne:method");
    }

    public void argString(String name) {
        LogManager.getLogger("miw").debug("-----------------> ServiceOne:argOneString(name=" + name + ")");
    }

    public int returnInt() {
        int result = 666;
        LogManager.getLogger("miw").debug("-----------------> ServiceOne:returnInt return= " + result);
        return result;
    }

    public void exception() throws Exception {
        LogManager.getLogger("miw").debug("-----------------> ServiceOne:exception");
        throw new Exception(">:o(");
    }

    @MyMethodAnnotation
    public void annotation() {
        LogManager.getLogger("miw").debug("-----------------> ServiceOne:annotation");
    }

}

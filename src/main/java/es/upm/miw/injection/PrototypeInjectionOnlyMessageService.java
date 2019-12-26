package es.upm.miw.injection;

import org.springframework.stereotype.Service;

@Service
public class PrototypeInjectionOnlyMessageService {

    public String getMessage() {
        return "PrototypeInjectionOnlyMessageService!!!";
    }
}

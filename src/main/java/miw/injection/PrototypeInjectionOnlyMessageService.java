package miw.injection;

import org.springframework.stereotype.Service;

@Service
public class PrototypeInjectionOnlyMessageService {

    public String getMessage() {
        return "(" + this.hashCode() + "): PrototypeInjectionOnlyMessageService!!!";
    }
}

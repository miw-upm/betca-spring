package es.upm.miw.betca.injection;

public class PrototypeInjectionOnlyMessageService {

    public String createMessage() {
        return "PrototypeInjectionOnlyMessageService::getMessage(): " + this.hashCode();
    }
}

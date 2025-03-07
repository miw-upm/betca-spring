package es.upm.api.miw.betca.injection;

public class PrototypeService {

    public String createMessage() {
        return "PrototypeService::getMessage(): " + this.hashCode();
    }
}

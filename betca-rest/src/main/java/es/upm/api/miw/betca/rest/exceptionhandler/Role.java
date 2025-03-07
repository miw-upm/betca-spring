package es.upm.api.miw.betca.rest.exceptionhandler;

public enum Role {
    ADMIN, MANAGER, OPERATOR, CUSTOMER;

    public static final String PREFIX = "ROLE_";

    public String withPrefix() {
        return PREFIX + this;
    }

}

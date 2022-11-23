package es.upm.miw.betca_rest.http_errors;

public enum Role {
    ADMIN, MANAGER, OPERATOR, CUSTOMER;

    public static final String PREFIX = "ROLE_";

    public static Role of(String withPrefix) {
        return Role.valueOf(withPrefix.replace(Role.PREFIX, ""));
    }

    public String withPrefix() {
        return PREFIX + this;
    }

}

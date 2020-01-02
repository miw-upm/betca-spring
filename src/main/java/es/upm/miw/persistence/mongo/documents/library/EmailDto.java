package es.upm.miw.persistence.mongo.documents.library;

public class EmailDto {

    private String email;

    public EmailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailDto{" +
                "email='" + email + '\'' +
                '}';
    }
}

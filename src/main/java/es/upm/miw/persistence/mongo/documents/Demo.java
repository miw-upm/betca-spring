package es.upm.miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Demo {

    @Id
    private String code;

    private String description;

    private String reference;

    public Demo() {
    }

    public Demo(String code, String description, String reference) {
        this.code = code;
        this.description = description;
        this.reference = reference;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}

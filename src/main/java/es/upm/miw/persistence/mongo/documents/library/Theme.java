package es.upm.miw.persistence.mongo.documents.library;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Theme {

    @Id
    private String id;

    private String name;

    public Theme() {
    }

    public Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Theme) obj).id));
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
